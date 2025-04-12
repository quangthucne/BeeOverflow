<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const questions = ref([])
const maxVisibleImages = 4

function getImageUrl(name) {
  return `http://localhost:8080/api/image/${name}`
}

function visibleImages(images) {
  return images.slice(0, maxVisibleImages)
}

function getAllQuestions() {
  axios
    .get('http://localhost:8080/question', {
      headers: {
        Cookie: 'accountId=1; role=1',
      },
    })
    .then((response) => {
      questions.value = response.data.data
    })
    .catch((error) => {
      console.error(error)
    })
}

onMounted(() => {
  getAllQuestions()
})
</script>

<template>
  <div class="custom-container mt-4">
    <div class="card p-4 mb-3" v-for="(question, index) in questions" :key="question.id">
      <h3>{{ question.title }}</h3>
      <p class="text-muted">
        Được hỏi bởi <strong v-if="question.account">User #{{ question.account.fullname }}</strong>
      </p>
      <p class="lead">
        {{ question.detail }}
      </p>

      <!-- Hình ảnh kiểu Facebook -->
      <div v-if="question.imagesQues && question.imagesQues.length > 0" class="image-grid mt-3">
        <div
          v-for="(img, i) in visibleImages(question.imagesQues)"
          :key="img.id"
          class="image-wrapper"
        >
          <img :src="getImageUrl(img.name)" class="img-thumbnail" alt="Ảnh câu hỏi" />
          <div
            v-if="i === maxVisibleImages - 1 && question.imagesQues.length > maxVisibleImages"
            class="more-overlay"
          >
            +{{ question.imagesQues.length - maxVisibleImages }}
          </div>
        </div>
      </div>

      <div>
        <span class="badge bg-secondary">#question</span>
        <span class="badge bg-warning text-dark" v-if="question.isCheck">Đã duyệt</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.custom-container {
  width: 80%;
  margin: 0 auto;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
  gap: 8px;
}

.image-wrapper {
  position: relative;
}

.image-wrapper img {
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 8px;
}

.more-overlay {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  color: white;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 8px;
}
</style>
