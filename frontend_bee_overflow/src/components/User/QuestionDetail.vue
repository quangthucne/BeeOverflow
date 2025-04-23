<template>
  <div class="container py-5">
    <!-- Loading & Error -->
    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary" style="width: 3rem; height: 3rem"></div>
    </div>
    <div v-else-if="error" class="alert alert-danger text-center">{{ error }}</div>

    <!-- Question Block -->
    <div v-else-if="question" class="card shadow-sm mb-4">
      <div class="card-body">
        <div class="d-flex align-items-center mb-3">
          <img
            :src="question.account.avatar || 'https://via.placeholder.com/40'"
            alt="Avatar"
            class="rounded-circle me-3"
            style="width: 40px; height: 40px"
          />
          <div>
            <div class="info d-flex align-items-center mb-2">
              <h4 class="mb-0 text-warning me-2">
                {{ question.account.fullname }}
              </h4>
              <div
                class="rank d-flex align-items-center"
                v-html="question.account.reputation.rank.icon"
              ></div>
            </div>
            <small class="text-muted">{{ formatDate(question.createdDate) }}</small>
          </div>
        </div>

        <h4 class="text-primary">{{ question.title }}</h4>
        <div class="mt-3" v-html="question.detail" />

        <div class="mt-3 d-flex flex-wrap gap-2" v-if="question.imagesQues?.length">
          <img
            v-for="(img, i) in question.imagesQues"
            :key="i"
            :src="img.name"
            class="rounded"
            style="width: 200px; height: 200px; object-fit: cover; cursor: pointer"
            @click="openImageViewer(i)"
          />
        </div>
      </div>

      <div class="card-footer d-flex justify-content-between flex-wrap">
        <div>
          <span v-if="question.tags.length" class="me-2 text-muted">Tags:</span>
          <span v-for="tag in question.tags" :key="tag.id" class="badge bg-primary me-1">
            {{ tag.name }}
          </span>
        </div>

        <div class="d-flex align-items-center gap-2">
          <button class="btn btn-outline-success btn-sm"><i class="fas fa-arrow-up"></i></button>
          <button class="btn btn-outline-danger btn-sm"><i class="fas fa-arrow-down"></i></button>
          <div class="mt-3">
            <button class="btn btn-outline-primary" @click="toggleMainAnswerForm">
              {{ showMainAnswerForm ? 'Ẩn form trả lời' : 'Trả lời câu hỏi' }}
            </button>

            <transition name="slide-up">
              <div
                v-if="showMainAnswerForm"
                class="answer-form-container shadow-lg p-4 bg-white mt-2"
              >
                <AnswerInputComponent
                  :question-id="question.id"
                  @submitted="toggleMainAnswerForm"
                  @cancel="toggleMainAnswerForm"
                />
              </div>
            </transition>
          </div>
          <span class="text-muted">Điểm: {{ question.votes }}</span>
        </div>
      </div>
    </div>

    <!-- Answers -->
    <div class="card shadow-sm">
      <div class="card-body">
        <h5 class="mb-4">
          Trả lời
          <span v-if="question">{{ question.answers.length }}</span>
          <span v-else>0</span>
        </h5>
        <div v-if="!topLevelAnswers.length" class="text-muted">Chưa có câu trả lời nào.</div>
        <div v-else>
          <div v-for="answer in topLevelAnswers" :key="answer.id" class="mt-3">
            <AnswerNode
              :answer="answer"
              :level="0"
              :account-avatar="answer.account.avatar"
              :current-account-id="accountId"
              :edit-forms="answerForms"
              :expanded="expandedAnswers"
              @toggle="toggleAnswer"
              @edit="editAnswer"
              @delete="deleteAnswer"
            />

            <!-- Toggle form trả lời -->
            <div>
              <button
                class="btn btn-outline-primary mt-2 ms-5"
                @click="toggleAnswerFormWithParent(answer.id)"
              >
                {{ answerForms[answer.id] ? 'Ẩn form trả lời' : 'Trả lời' }}
              </button>
              <transition name="slide-up">
                <div
                  v-if="answerForms[answer.id]"
                  class="answer-form-container shadow-lg p-4 bg-white"
                >
                  <AnswerInputComponent
                    :question-id="question.id"
                    :parent-id="answer.id"
                    @submitted="() => toggleAnswerFormWithParent(answer.id)"
                    @cancel="() => toggleAnswerFormWithParent(answer.id)"
                  />
                </div>
              </transition>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Image Viewer Modal -->
  <div class="modal fade" ref="imageModal" id="imageViewerModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
        <div class="modal-body position-relative text-center">
          <img
            v-if="currentImage"
            :src="currentImage.name"
            class="img-fluid"
            style="max-height: 75vh"
          />
          <button
            type="button"
            class="btn-close position-absolute top-0 end-0 m-3"
            data-bs-dismiss="modal"
          ></button>
          <button
            class="btn btn-secondary position-absolute top-50 start-0 translate-middle-y"
            @click="prevImage"
            v-if="question?.imagesQues?.length > 1"
          >
            ‹
          </button>
          <button
            class="btn btn-secondary position-absolute top-50 end-0 translate-middle-y"
            @click="nextImage"
            v-if="question?.imagesQues?.length > 1"
          >
            ›
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import { Modal } from 'bootstrap'
import AnswerNode from './AnswerNode.vue'
import AnswerInputComponent from './AnswerInputComponent.vue'
import { QuestionDTO, Response } from './types'
import Cookies from 'js-cookie'

const route = useRoute()
const question = ref<QuestionDTO | null>(null)
const loading = ref(true)
const error = ref<string | null>(null)
const expandedAnswers = ref<number[]>([])
const questionId = route.params.id

const showMainAnswerForm = ref(false)
const toggleMainAnswerForm = () => {
  showMainAnswerForm.value = !showMainAnswerForm.value
  // Đóng tất cả các form trả lời cho câu trả lời khác
  Object.keys(answerForms.value).forEach((key) => {
    answerForms.value[key] = false
  })
}

// Lấy accountId từ token
const accountId = getAccountIdFromToken()
console.log('accountid: ' + accountId)
function getAccountIdFromToken() {
  const token = Cookies.get('token')
  if (!token) return null

  const payload = JSON.parse(atob(token.split('.')[1]))
  return payload.accountId || payload.id
}

const toggleAnswer = (id: number) => {
  expandedAnswers.value = expandedAnswers.value.includes(id)
    ? expandedAnswers.value.filter((item) => item !== id)
    : [...expandedAnswers.value, id]
}

const questionOwnerId = computed(() => question.value?.account.id)

const topLevelAnswers = computed(() => {
  if (!question.value?.answers) return []

  // Lấy tất cả answer IDs có trong answersInParent
  const nestedAnswerIds = new Set<number>()
  const findNestedAnswers = (answers: any[]) => {
    answers.forEach((answer) => {
      if (answer.answersInParent?.length) {
        answer.answersInParent.forEach((nested: any) => {
          nestedAnswerIds.add(nested.id)
          findNestedAnswers(nested.answersInParent || [])
        })
      }
    })
  }

  findNestedAnswers(question.value.answers)

  // Lọc các câu trả lời không bị xóa và không phải là câu trả lời con
  return question.value.answers.filter((a) => !a.isDeleted && !nestedAnswerIds.has(a.id))
})

const formatDate = (dateStr: string) =>
  new Date(dateStr).toLocaleString('vi-VN', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  })

const answerForms = ref<Record<number, boolean>>({})

const toggleAnswerFormWithParent = (answerId: number) => {
  answerForms.value[answerId] = !answerForms.value[answerId]
}

// Image viewer
const imageModal = ref<HTMLElement | null>(null)
let bsModal: Modal | null = null
const currentImageIndex = ref<number | null>(null)

const currentImage = computed(() =>
  currentImageIndex.value !== null
    ? question.value?.imagesQues?.[currentImageIndex.value] || null
    : null,
)

const openImageViewer = (index: number) => {
  currentImageIndex.value = index
  if (bsModal) bsModal.show()
}

const nextImage = () => {
  if (!question.value || currentImageIndex.value === null) return
  currentImageIndex.value = (currentImageIndex.value + 1) % question.value.imagesQues.length
}

const prevImage = () => {
  if (!question.value || currentImageIndex.value === null) return
  currentImageIndex.value =
    (currentImageIndex.value - 1 + question.value.imagesQues.length) %
    question.value.imagesQues.length
}

const fetchQuestion = async () => {
  try {
    const res = await axios.get<Response>(`http://localhost:8080/question/${questionId}`)
    if (res.data.status === 1) question.value = res.data.data
    else error.value = res.data.message || 'Không thể lấy dữ liệu câu hỏi.'
  } catch (err: any) {
    error.value = 'Lỗi khi tải dữ liệu: ' + err.message
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchQuestion()
  if (imageModal.value) bsModal = new Modal(imageModal.value)
})
</script>

<style scoped>
.answer-form-container {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 1050;
  border-top: 1px solid #dee2e6;
  background-color: white;
}

.slide-up-enter-active,
.slide-up-leave-active {
  transition:
    transform 0.3s ease,
    opacity 0.3s ease;
}
.slide-up-enter-from,
.slide-up-leave-to {
  transform: translateY(100%);
  opacity: 0;
}
</style>
