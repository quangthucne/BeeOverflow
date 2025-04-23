<script setup lang="ts">
import { ref, watch } from 'vue'
import InputDetail from '@/components/User/InputDetail.vue'
import UploadImage from '@/components/User/UploadImage.vue'
import axios from 'axios'
import Cookies from 'js-cookie'
import Swal from 'sweetalert2'

const props = defineProps({
  questionId: {
    type: Number,
    required: true,
  },
  answerId: {
    type: Number,
    default: null,
  },
  parentId: {
    type: Number,
    default: null,
  },
  initialContent: {
    type: String,
    default: '',
  },
})

const emit = defineEmits(['success', 'cancel'])

const content = ref(props.initialContent || '')
const contentError = ref('')
const isSubmitting = ref(false)
const imageFiles = ref<File[]>([])

watch(
  () => props.initialContent,
  (newVal) => {
    content.value = newVal || ''
  },
)

const handleImageUpload = (files: File[]) => {
  imageFiles.value = files
}

const submit = async () => {
  contentError.value = ''

  if (!content.value.trim()) {
    contentError.value = 'Vui lòng nhập nội dung câu trả lời!'
    return
  }

  isSubmitting.value = true

  try {
    const token = Cookies.get('token')
    if (!token) {
      Swal.fire({
        icon: 'error',
        title: 'Bạn chưa đăng nhập.',
        confirmButtonText: 'OK',
      })
      return
    }

    const formData = new FormData()
    if (props.answerId) {
      formData.append('id', props.answerId.toString())
    }
    formData.append('questionId', props.questionId.toString())
    formData.append('detail', content.value)

    if (props.parentId) {
      formData.append('parentId', props.parentId.toString())
    }

    imageFiles.value.forEach((file) => {
      formData.append('images', file)
    })

    const config = {
      headers: {
        Authorization: `Bearer ${token}`,
        'Content-Type': 'multipart/form-data',
      },
    }

    let response
    if (props.answerId) {
      response = await axios.post(`http://localhost:8080/answer/update`, formData, config)
      Swal.fire({
        icon: 'success',
        title: 'Cập nhật câu trả lời thành công!',
        confirmButtonText: 'OK',
      })
    } else {
      response = await axios.post('http://localhost:8080/answer/add', formData, config)
      Swal.fire({
        icon: 'success',
        title: 'Câu trả lời đã được gửi thành công!',
        confirmButtonText: 'OK',
      })
    }

    emit('success')
    content.value = ''
    imageFiles.value = []
  } catch (error) {
    console.error('Error submitting answer:', error)
    Swal.fire({
      icon: 'error',
      title: 'Lỗi khi gửi câu trả lời',
      text: error.response?.data?.message || 'Vui lòng thử lại sau',
      confirmButtonText: 'OK',
    })
  } finally {
    isSubmitting.value = false
  }
}
</script>

<template>
  <div class="container">
    <div class="comment-form card p-4 shadow-sm">
      <h4 class="mb-4">{{ answerId ? 'Chỉnh sửa câu trả lời' : 'Trả lời câu hỏi' }}</h4>

      <!-- Nội dung trả lời -->
      <div class="mb-3">
        <InputDetail v-model:content="content" :error="contentError" />
      </div>

      <!-- Hình ảnh -->
      <div class="mb-3">
        <UploadImage @upload-complete="handleImageUpload" />
        <div v-if="imageFiles.length" class="mt-2 d-flex flex-wrap gap-3">
          <img
            v-for="(file, i) in imageFiles"
            :key="i"
            :src="file"
            alt="Image Preview"
            class="img-fluid rounded"
            style="max-width: 150px; max-height: 150px; object-fit: cover"
          />
        </div>
      </div>

      <!-- Nút Gửi và Ẩn -->
      <div class="d-flex justify-content-end gap-2">
        <button class="btn btn-outline-secondary" @click="$emit('cancel')" :disabled="isSubmitting">
          Hủy
        </button>
        <button class="btn btn-primary" :disabled="isSubmitting || !content.trim()" @click="submit">
          <span v-if="isSubmitting" class="spinner-border spinner-border-sm me-1"></span>
          {{ answerId ? 'Cập nhật' : 'Gửi bình luận' }}
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.comment-form {
  border-radius: 12px;
  background-color: #fff;
}
</style>
