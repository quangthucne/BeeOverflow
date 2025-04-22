<template>
  <div
    v-if="loading"
    class="d-flex justify-content-center align-items-center"
    style="height: 16rem"
  >
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem" role="status">
      <span class="visually-hidden">Loading...</span>
    </div>
  </div>

  <div v-else-if="error" class="alert alert-danger text-center p-4">
    {{ error }}
  </div>

  <div v-else-if="question" class="container my-5">
    <!-- Question Block -->
    <div class="card mb-4 shadow-sm">
      <div class="card-body">
        <div class="d-flex align-items-center mb-3">
          <img
            :src="question.account.avatar || 'https://via.placeholder.com/50'"
            alt="Avatar"
            class="rounded-circle me-3"
            style="width: 40px; height: 40px"
          />
          <div>
            <h6 class="mb-0">{{ question.account.fullname }} (@{{ question.account.username }})</h6>
            <small class="text-muted">
              {{ question.createdDate ? formatDate(question.createdDate) : 'No date available' }}
            </small>
          </div>
        </div>
        <h3 class="card-title text-primary mb-3 ms-3">{{ question.title }}</h3>
        <div class="detail" v-html="question.detail"></div>

        <div v-if="question?.imagesQues?.length" class="d-flex flex-wrap gap-2 mt-3 ms-3">
          <img
            v-for="(image, index) in question.imagesQues"
            :key="image.id"
            :src="image.name"
            alt="Question Image"
            class="img-fluid rounded"
            style="width: 200px; height: 200px; object-fit: cover; cursor: pointer"
            @click="openImageViewer(index)"
          />
        </div>
      </div>

      <div class="border-top pt-3 d-flex flex-wrap justify-content-between align-items-center">
  <!-- Tags -->
  <div v-if="question.tags.length" class="mb-2 mb-md-0">
    <span class="text-muted ms-4">Tags:</span>
    <span
      v-for="tag in question.tags"
      :key="tag.id"
      class="badge bg-primary text-white ms-2"
    >
      {{ tag.name }}
    </span>
  </div>

  <!-- Upvote/Downvote giống component danh sách -->
  <div class="d-flex align-items-center gap-2 mb-2">
  <button class="btn btn-outline-success btn-sm d-flex align-items-center gap-1">
    <i class="fas fa-arrow-up"></i> Upvote
  </button>
  <button class="btn btn-outline-danger btn-sm d-flex align-items-center gap-1">
    <i class="fas fa-arrow-down"></i> Downvote
  </button>
  <button class="btn btn-outline-primary btn-sm d-flex align-items-center gap-1">
    <i class="fas fa-comment"></i> Trả lời
  </button>
  <span class="text-muted me-4">Điểm: {{ question.votes || 0 }}</span>
</div>

</div>

    </div>

    <!-- Answers Block -->
    <div class="card shadow-sm">
      <div class="card-body">
        <h4 class="mb-4">Answers ({{ question.answers.length }})</h4>
        <div v-if="!question.answers.length" class="text-muted">No answers yet.</div>
        <div v-else>
          <AnswerNode
            v-for="answer in filteredAnswers"
            :key="answer.id"
            :answer="answer"
            :level="0"
            :account-avatar="question.account.avatar"
            @toggle="toggleAnswer"
            :expanded="expandedAnswers"
          />
        </div>
      </div>
    </div>
  </div>

  <div v-else class="text-muted text-center p-4">No question data available.</div>

  <!-- Image Viewer Modal -->
  <div
    class="modal fade"
    id="imageViewerModal"
    tabindex="-1"
    aria-labelledby="imageViewerModalLabel"
    aria-hidden="true"
    ref="imageModal"
  >
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
        <div class="modal-body text-center position-relative">
          <img
            v-if="currentImage"
            :src="currentImage.name"
            class="img-fluid"
            style="max-height: 70vh"
          />
          <button type="button" class="btn-close position-absolute top-0 end-0 m-3" data-bs-dismiss="modal" aria-label="Close"></button>
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

<script lang="ts">
import { defineComponent, ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'
import { Modal } from 'bootstrap'
import AnswerNode from './AnswerNode.vue'
import { QuestionDTO, Response } from './types'

export default defineComponent({
  name: 'QuestionDetail',
  components: {
    AnswerNode,
  },
  setup() {
    const route = useRoute()
    const question = ref<QuestionDTO | null>(null)
    const loading = ref(true)
    const error = ref<string | null>(null)
    const expandedAnswers = ref<number[]>([])
    const questionId = route.params.id

    const imageModal = ref<HTMLElement | null>(null)
    let bsModal: Modal | null = null
    const currentImageIndex = ref<number | null>(null)

    const filteredAnswers = computed(() => {
      if (!question.value || !question.value.answers) return []
      return question.value.answers.filter((answer) => answer.isDeleted !== true)
    })

    const currentImage = computed(() => {
      if (
        currentImageIndex.value !== null &&
        question.value &&
        question.value.imagesQues &&
        question.value.imagesQues.length > 0
      ) {
        return question.value.imagesQues[currentImageIndex.value]
      }
      return null
    })

    const fetchQuestion = async () => {
      try {
        const response = await axios.get<Response>('http://localhost:8080/question/' + questionId)
        if (response.data.status === 1) {
          question.value = response.data.data
        } else {
          error.value = response.data.message || 'Failed to fetch question'
        }
      } catch (err) {
        error.value = 'Error fetching question: ' + (err as Error).message
        console.error('Fetch Error:', err)
      } finally {
        loading.value = false
      }
    }

    const toggleAnswer = (answerId: number) => {
      if (expandedAnswers.value.includes(answerId)) {
        expandedAnswers.value = expandedAnswers.value.filter((id) => id !== answerId)
      } else {
        expandedAnswers.value.push(answerId)
      }
    }

    const formatDate = (date: string) => {
      return new Date(date).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
      })
    }

    const openImageViewer = (index: number) => {
      currentImageIndex.value = index
      if (bsModal) bsModal.show()
    }

    const nextImage = () => {
      if (question.value && currentImageIndex.value !== null) {
        currentImageIndex.value =
          (currentImageIndex.value + 1) % question.value.imagesQues.length
      }
    }

    const prevImage = () => {
      if (question.value && currentImageIndex.value !== null) {
        currentImageIndex.value =
          (currentImageIndex.value - 1 + question.value.imagesQues.length) %
          question.value.imagesQues.length
      }
    }

    onMounted(() => {
      fetchQuestion()
      if (imageModal.value) {
        bsModal = new Modal(imageModal.value)
      }
    })

    return {
      question,
      loading,
      error,
      expandedAnswers,
      filteredAnswers,
      toggleAnswer,
      formatDate,
      openImageViewer,
      nextImage,
      prevImage,
      currentImage,
      imageModal,
    }
  },
})
</script>

<style scoped>
.card {
  border-radius: 0.5rem;
}
.card-footer {
  background-color: #f8f9fa;
}
.border-top {
  border-top: 1px solid #dee2e6 !important;
}

.pt-3 {
  padding-top: 1rem !important;
}

.badge {
  font-size: 0.75rem;
  padding: 0.4em 0.6em;
  border-radius: 0.375rem;
}

</style>
