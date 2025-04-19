<template>
  <div class="upload-container">
    <input
      type="file"
      ref="fileInput"
      accept=".pdf"
      @change="handleFileChange"
      class="file-input"
    />
    <button @click="uploadFile" :disabled="!selectedFile || isUploading" class="upload-button">
      {{ isUploading ? 'Uploading...' : 'Upload PDF' }}
    </button>

    <div v-if="uploadProgress > 0" class="progress-bar">
      <div :style="{ width: uploadProgress + '%' }"></div>
    </div>

    <div v-if="error" class="error-message">
      {{ error }}
    </div>

    <div v-if="pdfUrl" class="success-message">
      <p>Upload thành công!</p>
      <a :href="pdfUrl" target="_blank" class="view-link">Xem PDF</a>
      <button @click="copyLink" class="copy-button">Copy link</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { supabase } from '@/config/supabase'

const selectedFile = ref(null)
const pdfUrl = ref('')
const isUploading = ref(false)
const uploadProgress = ref(0)
const error = ref('')

const handleFileChange = (e) => {
  selectedFile.value = e.target.files[0]
  error.value = ''
}

const uploadFile = async () => {
  if (!selectedFile.value) return

  isUploading.value = true
  uploadProgress.value = 0
  error.value = ''

  try {
    // Tạo tên file unique
    const fileExt = selectedFile.value.name.split('.').pop()
    const fileName = `${Math.random().toString(36).substring(2)}.${fileExt}`
    const filePath = `public/pdfs/${fileName}`

    // Upload file lên Supabase Storage
    const { data, error: uploadError } = await supabase.storage
      .from('pdf-bucket') // Thay bằng tên bucket của bạn
      .upload(filePath, selectedFile.value, {
        cacheControl: '3600',
        upsert: false,
        contentType: 'application/pdf',
        onUploadProgress: (progressEvent) => {
          uploadProgress.value = Math.round((progressEvent.loaded / progressEvent.total) * 100)
        },
      })

    if (uploadError) throw uploadError

    // Lấy public URL
    const { data: urlData } = supabase.storage.from('pdf-bucket').getPublicUrl(filePath)

    pdfUrl.value = urlData.publicUrl

    console.log(pdfUrl)
  } catch (err) {
    error.value = err.message || 'Upload thất bại'
    console.error('Upload error:', err)
  } finally {
    isUploading.value = false
  }
}

const copyLink = () => {
  navigator.clipboard.writeText(pdfUrl.value)
  alert('Đã copy link vào clipboard!')
}
</script>

<style scoped>
.upload-container {
  max-width: 500px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
}

.file-input {
  display: block;
  margin-bottom: 15px;
  width: 100%;
}

.upload-button {
  background-color: #3b82f6;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.upload-button:hover {
  background-color: #2563eb;
}

.upload-button:disabled {
  background-color: #9ca3af;
  cursor: not-allowed;
}

.progress-bar {
  margin-top: 10px;
  height: 5px;
  background-color: #e5e7eb;
  border-radius: 3px;
  overflow: hidden;
}

.progress-bar div {
  height: 100%;
  background-color: #10b981;
  transition: width 0.3s;
}

.error-message {
  margin-top: 10px;
  color: #ef4444;
}

.success-message {
  margin-top: 15px;
  padding: 10px;
  background-color: #ecfdf5;
  border-radius: 4px;
}

.view-link {
  color: #3b82f6;
  text-decoration: none;
  margin-right: 10px;
}

.copy-button {
  background-color: #f3f4f6;
  border: 1px solid #d1d5db;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
}
</style>
