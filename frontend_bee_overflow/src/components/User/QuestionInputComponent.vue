<template>
  <div class="container my-5">
    <div class="card shadow-sm">
      <div class="card-header bg-primary text-white">
        <h4 class="mb-0">{{ isEditMode ? 'Edit Question' : 'Add a New Question' }}</h4>
      </div>
      <div class="card-body">
        <div v-if="successMessage" class="alert alert-success">{{ successMessage }}</div>
        <div v-if="errorMessage" class="alert alert-danger">{{ errorMessage }}</div>

        <form @submit.prevent="submitQuestion">
          <input type="hidden" v-model="form.id" />
          <div class="mb-3">
            <label for="title" class="form-label">Question Title</label>
            <input
              v-model="form.title"
              type="text"
              id="title"
              class="form-control"
              placeholder="Enter your question title"
              required
            />
          </div>

          <div class="mb-3">
            <label class="form-label">Question Detail</label>
            <InputDetail v-model:content="form.detail" />
          </div>

          <div class="mb-3">
            <label class="form-label">Upload Image (Optional)</label>
            <UploadImage @upload-complete="handleImageUpload" />
            <div v-if="form.images.length" class="mt-2 d-flex flex-wrap gap-3">
              <img
                v-for="(img, i) in form.images"
                :key="i"
                :src="img"
                alt="Image Preview"
                class="img-fluid rounded"
                style="max-width: 200px"
              />
            </div>
          </div>

          <div class="mb-3">
            <label class="form-label">Tags</label>
            <VueMultiselect
              v-model="form.tags"
              :options="availableTags"
              :multiple="true"
              :taggable="true"
              :searchable="true"
              :close-on-select="false"
              :clear-on-select="false"
              label="name"
              track-by="name"
              placeholder="Select or type tags"
              @tag="addNewTag"
            />
            <small class="form-text text-muted">Select or create tags, then press Enter.</small>
          </div>

          <button type="submit" class="btn btn-primary" :disabled="isSubmitting">
            {{
              isSubmitting
                ? isEditMode
                  ? 'Updating...'
                  : 'Submitting...'
                : isEditMode
                  ? 'Update Question'
                  : 'Submit Question'
            }}
          </button>
        </form>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'
import Cookies from 'js-cookie'

import InputDetail from './InputDetail.vue'
import UploadImage from './UploadImage.vue'
import VueMultiselect from 'vue-multiselect'

interface Tag {
  name: string
}

interface QuestionForm {
  id: string
  title: string
  detail: string
  images: string[]
  tags: Tag[]
}

const route = useRoute()
const router = useRouter()
const questionId = route.params.id
const isEditMode = computed(() => !!questionId)

const form = reactive<QuestionForm>({
  id: '',
  title: '',
  detail: '',
  images: [],
  tags: [],
})

const availableTags = ref<Tag[]>([
  { name: 'javascript' },
  { name: 'vue' },
  { name: 'coding' },
  { name: 'python' },
  { name: 'react' },
])

const isSubmitting = ref(false)
const successMessage = ref<string | null>(null)
const errorMessage = ref<string | null>(null)

const handleImageUpload = (uploadedUrls: string[]) => {
  const cleaned = uploadedUrls.map((url) => url.replace(/^"+|"+$/g, ''))
  form.images.push(...cleaned)
}

const addNewTag = (newTag: string) => {
  const tag = { name: newTag.trim() }
  if (tag.name && !availableTags.value.some((t) => t.name === tag.name)) {
    availableTags.value.push(tag)
  }
  form.tags.push(tag)
}

onMounted(async () => {
  if (isEditMode.value) {
    try {
      const token = Cookies.get('token')
      const response = await axios.get(`http://localhost:8080/question/${questionId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
        withCredentials: true,
      })

      const data = response.data.data
      form.id = data.id
      form.title = data.title
      form.detail = data.detail
      form.images = data.imagesQues?.map((img: any) => img.name) || []
      form.tags = data.tags || []

      console.log(form)
    } catch (err) {
      errorMessage.value = 'Failed to load question data.'
    }
  }
})

const submitQuestion = async () => {
  if (!form.title || !form.detail) {
    errorMessage.value = 'Please fill in the title and detail.'
    return
  }

  successMessage.value = null
  errorMessage.value = null
  isSubmitting.value = true

  try {
    const token = Cookies.get('token')
    if (!token) {
      errorMessage.value = 'You must be logged in to submit.'
      router.push('/login')
      return
    }

    const formData = new FormData()
    if (Number(form.id) > 0) {
      formData.append('id', form.id)
    }
    formData.append('title', form.title)
    formData.append('detail', form.detail)
    form.images.forEach((url) => formData.append('images', url))
    const tagNames = form.tags.map((tag) => tag.name)
    formData.append('name', JSON.stringify(tagNames))

    const url = isEditMode.value
      ? `http://localhost:8080/question/update`
      : 'http://localhost:8080/question/add'

    const method = 'post'

    const response = await axios({
      method,
      url,
      data: formData,
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: `Bearer ${token}`,
      },
      withCredentials: true,
    })
      .then
      // chuyển đến trang chi tiết câu hỏi
      ()

    const { status, message } = response.data
    if (status === 1) {
      successMessage.value = isEditMode.value
        ? 'Question updated successfully!'
        : 'Question submitted successfully!'
      if (!isEditMode.value) {
        form.title = ''
        form.detail = ''
        form.images = []
        form.tags = []
      } else {
        router.push(`/question/${questionId}`)
      }
    } else {
      errorMessage.value = message || 'Failed to submit question.'
    }
  } catch (err: any) {
    if (err.response?.status === 401) {
      errorMessage.value = 'Session expired. Please log in again.'
      Cookies.remove('token')
      router.push('/login')
    } else {
      errorMessage.value =
        err.response?.data?.message || err.response?.data?.error || 'Submission error.'
    }
  } finally {
    isSubmitting.value = false
  }
}
</script>

<style src="vue-multiselect/dist/vue-multiselect.css"></style>
<style scoped>
.card {
  border-radius: 0.5rem;
}
.form-label {
  font-weight: 500;
}
.multiselect__tags {
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  padding: 0.375rem 0.75rem;
}
.multiselect__tag {
  background: #007bff;
}
.multiselect__tag-icon:hover {
  background: #0056b3;
}
</style>
