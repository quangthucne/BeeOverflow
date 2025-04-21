<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Cookies from 'js-cookie'

const questions = ref([])
const maxVisibleImages = 4
const accountId = ref(null)

function getImageUrl(name) {
  return `${name}`
}

function visibleImages(images) {
  return images.slice(0, maxVisibleImages)
}

function formatDate(date) {
  return new Date(date).toLocaleString('en-US', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
  })
}

function getAllQuestions() {
  axios
    .get('http://localhost:8080/question')
    .then((response) => {
      questions.value = response.data.data
    })
    .catch((error) => {
      console.error(error)
    })
}

function getAccountIdFromToken() {
  const token = Cookies.get('token')
  if (!token) return null

  const payload = JSON.parse(atob(token.split('.')[1]))
  console.log(payload.accountId || payload.id)
  return payload.accountId || payload.id // tuỳ cấu trúc JWT
}

function handleEdit(questionId) {
  console.log('Edit question', questionId)
  // Add your navigation or modal logic here
}

function handleDelete(questionId) {
  console.log('Delete question', questionId)
  // Add confirmation and API call here
}

onMounted(() => {
  accountId.value = getAccountIdFromToken()
  getAllQuestions()
})
</script>

<template>
  <div class="custom-container mt-4">
    <div class="card p-4 mb-3" v-for="(question, index) in questions" :key="question.id">
      <div class="card mb-4 shadow-sm">
        <div class="card-body position-relative">
          <!-- Avatar & Name -->
          <div class="d-flex align-items-center mb-3">
            <img
              :src="question.account.avatar || 'https://via.placeholder.com/50'"
              alt="Avatar"
              class="rounded-circle me-3"
              style="width: 40px; height: 40px"
            />
            <div>
              <h6 class="mb-0">
                {{ question.account.fullname }} (@{{ question.account.username }})
              </h6>
              <small class="text-muted">
                {{ question.createdDate ? formatDate(question.createdDate) : 'No date available' }}
              </small>
            </div>
          </div>
          <small v-text="question.account.id"></small>
          <!-- Dropdown menu -->
          <div
            v-if="question.account.id == accountId"
            class="position-absolute top-0 end-0 m-3 dropdown"
          >
            <button
              class="btn btn-light btn-sm dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            ></button>
            <ul class="dropdown-menu">
              <li>
                <a class="dropdown-item" href="#" @click.prevent="handleEdit(question.id)"
                  >Chỉnh sửa</a
                >
              </li>
              <li>
                <a
                  class="dropdown-item text-danger"
                  href="#"
                  @click.prevent="handleDelete(question.id)"
                  >Xoá</a
                >
              </li>
            </ul>
          </div>

          <h3 class="card-title text-primary mb-3 ms-3">{{ question.title }}</h3>
          <div class="detail" v-html="question.detail"></div>
          <div v-if="question.imagesQues.length" class="d-flex flex-wrap gap-2 mt-3 ms-3">
            <img
              v-for="image in question.imagesQues"
              :key="image.id"
              :src="image.name"
              alt="Question Image"
              class="img-fluid rounded"
              style="width: 500px; height: 500px; object-fit: cover"
            />
          </div>
        </div>

        <div v-if="question.tags.length" class="card-footer bg-light">
          <span class="text-muted">Tags:</span>
          <span v-for="tag in question.tags" :key="tag.id" class="badge bg-primary text-white ms-2">
            {{ tag.name }}
          </span>
        </div>
      </div>
    </div>
  </div>
</template>
