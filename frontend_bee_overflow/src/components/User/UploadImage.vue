<template>
  <div class="upload-widget-container">
    <button
      type="button"
      @click="open"
      :disabled="!widgetReady"
      class="btn btn-secondary upload-button"
    >
      <span v-if="!isUploading">Tải ảnh lên</span>
      <span v-else class="spinner-border spinner-border-sm" role="status"></span>
    </button>

    <!-- <div v-if="uploadedImages.length" class="image-preview-container">
      <div class="image-grid">
        <div v-for="(img, index) in uploadedImages" :key="index" class="image-item">
          <img :src="img" class="uploaded-image" />
        </div>
      </div>
    </div> -->
  </div>
</template>

<script setup>
import { ref } from 'vue'

const emit = defineEmits(['upload-complete'])

const widget = ref(null)
const widgetReady = ref(false)
const isUploading = ref(false)
const uploadedImages = ref([])

const open = () => {
  if (widget.value) {
    isUploading.value = true
    widget.value.open()
  }
}

const loadScript = (src) => {
  return new Promise((resolve, reject) => {
    if (document.querySelector(`script[src="${src}"]`)) {
      resolve()
      return
    }

    const script = document.createElement('script')
    script.src = src
    script.async = true
    script.onload = resolve
    script.onerror = reject
    document.head.appendChild(script)
  })
}

;(async () => {
  try {
    await loadScript('https://widget.cloudinary.com/v2.0/global/all.js')

    widget.value = window.cloudinary.createUploadWidget(
      {
        cloudName: 'dbrftgkrp',
        uploadPreset: 'r5zszied',
        multiple: true, // Chỉ cho phép upload 1 ảnh
      },
      (error, result) => {
        isUploading.value = false

        if (error) {
          console.error('Lỗi khi upload:', error)
          return
        }
        if (result && result.event === 'success') {
          uploadedImages.value.push(result.info.secure_url)
          emit('upload-complete', [...uploadedImages.value])
        }
      },
    )

    widgetReady.value = true
  } catch (err) {
    console.error('Không thể tải Cloudinary widget:', err)
    isUploading.value = false
  }
})()
</script>

<style scoped>
/* Giữ nguyên các style như trước */
</style>
