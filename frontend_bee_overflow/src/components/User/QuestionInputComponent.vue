<template>
  <div class="container my-5">
    <div class="card shadow-sm">
      <div class="card-header bg-primary text-white">
        <h4 class="mb-0">{{ isEditMode ? 'Edit Question' : 'Add a New Question' }}</h4>
      </div>
      <div class="card-body">
        <form @submit.prevent="submitQuestion">
          <input type="hidden" v-model="form.id" />
          <div class="mb-3">
            <label class="form-label">Question Title</label>
            <input
              v-model="form.title"
              type="text"
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
import Swal from 'sweetalert2'

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
    } catch (err) {
      Swal.fire('Lỗi', 'Không thể tải dữ liệu câu hỏi.', 'error')
    }
  }
})

const submitQuestion = async () => {
  if (!form.title || !form.detail) {
    Swal.fire('Thiếu thông tin', 'Vui lòng điền đầy đủ tiêu đề và nội dung.', 'warning')
    return
  }

  isSubmitting.value = true

  try {
    const token = Cookies.get('token')
    if (!token) {
      Swal.fire('Phiên đăng nhập hết hạn', 'Vui lòng đăng nhập lại.', 'warning')
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

    const response = await axios.post(url, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: `Bearer ${token}`,
      },
      withCredentials: true,
    })

    const { status, message } = response.data
    if (status === 1) {
      await Swal.fire({
        icon: 'success',
        title: isEditMode.value ? 'Đã cập nhật' : 'Đã đăng câu hỏi',
        text: isEditMode.value
          ? 'Câu hỏi đã được cập nhật thành công.'
          : 'Câu hỏi đã được đăng thành công.',
        timer: 2000,
        showConfirmButton: false,
      })
      console.log(isEditMode.value)
      router.push(isEditMode.value ? `/question/detail/${questionId}` : '/questions')
    } else {
      Swal.fire('Thất bại', message || 'Không thể gửi câu hỏi.', 'error')
    }
  } catch (err: any) {
    if (err.response?.status === 401) {
      Swal.fire('Hết phiên đăng nhập', 'Vui lòng đăng nhập lại.', 'warning')
      Cookies.remove('token')
      router.push('/login')
    } else {
      Swal.fire('Lỗi', err.response?.data?.message || 'Đã xảy ra lỗi khi gửi.', 'error')
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
