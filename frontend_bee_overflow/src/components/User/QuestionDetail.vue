<!-- src/components/QuestionDetail.vue -->
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
</template>

<script lang="ts">
import { defineComponent, ref, onMounted, computed } from 'vue'
import axios from 'axios'
import AnswerNode from './AnswerNode.vue'
import { QuestionDTO, Response } from './types'

export default defineComponent({
  name: 'QuestionDetail',
  components: {
    AnswerNode,
  },
  setup() {
    const question = ref<QuestionDTO | null>(null)
    const loading = ref(true)
    const error = ref<string | null>(null)
    const expandedAnswers = ref<number[]>([])

    // Compute filtered answers to handle isDeleted logic
    const filteredAnswers = computed(() => {
      if (!question.value || !question.value.answers) return []
      return question.value.answers.filter((answer) => answer.isDeleted !== true)
    })

    // Fetch question data from API
    const fetchQuestion = async () => {
      try {
        const response = await axios.get<Response>('http://localhost:8080/question/37')
        console.log('API Response:', response.data) // Debug log
        if (response.data.status === 1) {
          question.value = response.data.data
          console.log('Question Answers:', question.value?.answers) // Debug log
        } else {
          error.value = response.data.message || 'Failed to fetch question'
        }
      } catch (err) {
        error.value = 'Error fetching question: ' + (err as Error).message
        console.error('Fetch Error:', err) // Debug log
      } finally {
        loading.value = false
      }
    }

    // Toggle answer expansion
    const toggleAnswer = (answerId: number) => {
      if (expandedAnswers.value.includes(answerId)) {
        expandedAnswers.value = expandedAnswers.value.filter((id) => id !== answerId)
      } else {
        expandedAnswers.value.push(answerId)
      }
    }

    // Format date using native JavaScript
    const formatDate = (date: string) => {
      return new Date(date).toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
      })
    }

    // Fetch data when component is mounted
    onMounted(fetchQuestion)

    return {
      question,
      loading,
      error,
      expandedAnswers,
      filteredAnswers,
      toggleAnswer,
      formatDate,
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
</style>
