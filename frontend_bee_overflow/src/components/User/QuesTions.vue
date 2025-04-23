<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Cookies from 'js-cookie'
import router from '@/router'

// State
const questions = ref([])
const accountId = ref(null)
const selectedImage = ref('')
const activeDropdown = ref(null)

// Methods
function openImage(imageUrl) {
  selectedImage.value = imageUrl
}

function formatDate(date) {
  return new Date(date).toLocaleString('vi-VN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  })
}

async function getAllQuestions() {
  try {
    const response = await axios.get('http://localhost:8080/question')
    questions.value = response.data.data
  } catch (error) {
    console.error('Error fetching questions:', error)
  }
}

function getAccountIdFromToken() {
  const token = Cookies.get('token')
  if (!token) return null
  const payload = JSON.parse(atob(token.split('.')[1]))
  return payload.accountId || payload.id
}

function handleEdit(questionId) {
  console.log('Edit question', questionId)
  // Thêm logic edit tại đây
  router.push('/question/edit/' + questionId)
}

function handleDelete(questionId) {
  console.log('Delete question', questionId)
  // Thêm logic delete tại đây
}

function toggleDropdown(questionId) {
  activeDropdown.value = activeDropdown.value === questionId ? null : questionId
}

function closeDropdowns() {
  activeDropdown.value = null
}

function goToQuestionDetail(questionId) {
  router.push(`/question/detail/${questionId}`)
}

// Lifecycle Hooks
onMounted(() => {
  accountId.value = getAccountIdFromToken()
  getAllQuestions()

  // Đóng dropdown khi click ra ngoài
  document.addEventListener('click', (e) => {
    if (!e.target.closest('.dropdown')) {
      closeDropdowns()
    }
  })
})
</script>

<template>
  <div class="custom-container mt-4">
    <div class="card mb-4 shadow-sm rounded-4" v-for="question in questions" :key="question.id">
      <div class="card-body p-4 position-relative">
        <!-- Avatar & User Info -->
        <div class="d-flex align-items-center mb-3">
          <img
            :src="question.account.avatar || 'https://via.placeholder.com/50'"
            alt="Avatar"
            class="rounded-circle me-3 border"
            style="width: 45px; height: 45px; object-fit: cover"
          />
          <div>
            <h6 class="mb-0 fw-bold">
              {{ question.account.fullname }} (@{{ question.account.username }})
            </h6>
            <small class="text-muted">
              {{ question.createdDate ? formatDate(question.createdDate) : 'No date available' }}
            </small>
          </div>
        </div>

        <!-- Dropdown Menu -->
        <div
          v-if="question.account.id == accountId"
          class="position-absolute top-0 end-0 m-3 dropdown"
        >
          <button
            class="btn btn-light btn-sm dropdown-toggle"
            type="button"
            @click.stop="toggleDropdown(question.id)"
            aria-expanded="false"
          >
            <i class="fas fa-ellipsis-v"></i>
          </button>
          <ul
            class="dropdown-menu"
            :class="{ show: activeDropdown === question.id }"
            :style="{
              position: 'absolute',
              inset: '0px auto auto 0px',
              margin: '0px',
              transform: 'translate(0px, 40px)',
            }"
          >
            <li>
              <a class="dropdown-item" href="#" @click.prevent="handleEdit(question.id)">
                <i class="fas fa-edit me-2"></i>Chỉnh sửa
              </a>
            </li>
            <li>
              <a
                class="dropdown-item text-danger"
                href="#"
                @click.prevent="handleDelete(question.id)"
              >
                <i class="fas fa-trash me-2"></i>Xoá
              </a>
            </li>
          </ul>
        </div>

        <!-- Question Content -->
        <h5
          class="text-primary fw-semibold mb-2"
          @click="goToQuestionDetail(question.id)"
          style="cursor: pointer"
        >
          {{ question.title }}
        </h5>

        <div
          class="detail mb-3"
          v-html="question.detail"
          @click="goToQuestionDetail(question.id)"
          style="cursor: pointer"
        ></div>

        <!-- Images Grid -->
        <div v-if="question.imagesQues?.length" class="image-grid mb-3">
          <div
            v-for="(image, idx) in question.imagesQues.slice(0, 3)"
            :key="image.id"
            class="image-box position-relative"
            @click="
              idx === 2 && question.imagesQues.length > 3
                ? goToQuestionDetail(question.id)
                : openImage(image.name)
            "
          >
            <img
              :src="image.name"
              alt="Question Image"
              class="img-fluid rounded border"
              style="width: 100%; height: 100%; object-fit: cover"
            />
            <div v-if="idx === 2 && question.imagesQues.length > 3" class="overlay">
              +{{ question.imagesQues.length - 3 }} ảnh
            </div>
          </div>
        </div>

        <!-- Image Modal -->
        <div
          class="modal fade"
          id="imageModal"
          tabindex="-1"
          aria-labelledby="imageModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content bg-dark">
              <div class="modal-body p-0">
                <img :src="selectedImage" class="img-fluid w-100" />
              </div>
              <div class="modal-footer justify-content-center border-0">
                <button type="button" class="btn btn-light btn-sm" data-bs-dismiss="modal">
                  Đóng
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- Footer with Tags and Actions -->
        <div class="border-top pt-3 d-flex flex-wrap justify-content-between align-items-center">
          <!-- Tags -->
          <div v-if="question.tags?.length" class="mb-2 mb-md-0">
            <span class="text-muted">Tags:</span>
            <span
              v-for="tag in question.tags"
              :key="tag.id"
              class="badge bg-primary text-white ms-2"
            >
              {{ tag.name }}
            </span>
          </div>

          <!-- Voting and Comment -->
          <div class="d-flex align-items-center gap-2">
            <button class="btn btn-outline-success btn-sm d-flex align-items-center gap-1">
              <i class="fas fa-arrow-up"></i> Upvote
            </button>
            <button class="btn btn-outline-danger btn-sm d-flex align-items-center gap-1">
              <i class="fas fa-arrow-down"></i> Downvote
            </button>
            <span class="text-muted">Điểm: {{ question.votes || 0 }}</span>

            <button
              class="btn btn-outline-primary btn-sm d-flex align-items-center gap-1"
              @click="goToQuestionDetail(question.id)"
            >
              <i class="fas fa-comment-alt"></i> Bình luận
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.custom-container {
  max-width: 800px;
  margin: 0 auto;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
  gap: 10px;
}

.image-box {
  width: 100%;
  aspect-ratio: 1 / 1;
  position: relative;
  cursor: pointer;
  overflow: hidden;
  border-radius: 8px;
  transition: transform 0.2s;
}

.image-box:hover {
  transform: scale(1.03);
}

.overlay {
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.6);
  color: #fff;
  width: 100%;
  height: 100%;
  display: flex;
  font-weight: bold;
  font-size: 1rem;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
}

.dropdown-menu {
  z-index: 1000;
}

.card {
  transition: box-shadow 0.3s ease;
}

.card:hover {
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

.detail {
  max-height: 150px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 4;
  -webkit-box-orient: vertical;
}
</style>
