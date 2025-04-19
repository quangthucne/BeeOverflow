<template>
  <div class="uw">
    <h3>Upload Widget Example</h3>
    <button @click="open" :disabled="!widgetReady" id="upload_widget" class="cloudinary-button">
      Upload files
    </button>

    <img id="uploadedimage" width="150" />

    <p>
      <a href="https://cloudinary.com/documentation/upload_widget" target="_blank">
        Upload Widget User Guide
      </a>
    </p>
    <p>
      <a href="https://cloudinary.com/documentation/upload_widget_reference" target="_blank">
        Upload Widget Reference
      </a>
    </p>

    <div v-if="uploadedImages.length" style="margin-top: 20px">
      <h4>Ảnh đã upload:</h4>
      <ul>
        <li v-for="(img, index) in uploadedImages" :key="index">
          <img :src="img" style="max-width: 150px; margin: 8px 0" />
          <p>{{ img }}</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'UploadWidget',
  data() {
    return {
      widget: null,
      widgetReady: false,
      uploadedImages: [], // Mảng lưu link ảnh
    }
  },
  methods: {
    open() {
      if (this.widget) {
        console.log('🚀 Mở widget...')
        this.widget.open()
      } else {
        console.warn('⚠️ Widget chưa sẵn sàng')
      }
    },
    loadScript(src) {
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
    },
  },
  async mounted() {
    try {
      await this.loadScript('https://widget.cloudinary.com/v2.0/global/all.js')
      console.log('✅ Cloudinary widget đã được load')

      this.widget = window.cloudinary.createUploadWidget(
        {
          cloudName: 'dbrftgkrp',
          uploadPreset: 'r5zszied', // nhớ để unsigned!
          // Bạn có thể bật các tuỳ chọn khác tại đây nếu cần
        },
        (error, result) => {
          if (error) {
            console.error('❌ Lỗi widget:', error)
          }
          if (result && result.event === 'success') {
            const url = result.info.secure_url
            console.log('✅ Upload thành công:', url)

            // Gán ảnh chính
            document.getElementById('uploadedimage').src = url

            // Thêm vào mảng
            this.uploadedImages.push(url)
          }
        },
      )

      this.widgetReady = true
    } catch (err) {
      console.error('❌ Không thể tải Cloudinary widget:', err)
    }
  },
}
</script>

<style scoped>
.cloudinary-button {
  background-color: #3448c5;
  color: white;
  padding: 10px 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
.cloudinary-button:disabled {
  background-color: gray;
  cursor: not-allowed;
}
</style>
