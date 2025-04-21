<template>
  <div class="pdf-viewer">
    <!-- Thanh công cụ -->
    <div class="toolbar">
      <button @click="zoomOut" :disabled="scale <= 0.5">-</button>
      <span>{{ Math.round(scale * 100) }}%</span>
      <button @click="zoomIn" :disabled="scale >= 2">+</button>

      <button @click="prevPage" :disabled="pageNumber <= 1">Trang trước</button>
      <input v-model.number="pageNumber" type="number" min="1" :max="numPages" @change="goToPage" />
      <span>/ {{ numPages }}</span>
      <button @click="nextPage" :disabled="pageNumber >= numPages">Trang sau</button>

      <input v-model="searchText" placeholder="Tìm kiếm..." @keyup.enter="search" />
    </div>

    <!-- Hiển thị PDF -->
    <!-- <div class="canvas-container" ref="container">
      <canvas
        v-for="page in visiblePages"
        :key="page.pageNumber"
        :ref="(el) => setCanvasRef(el, page.pageNumber - 1)"
        class="pdf-page"
      ></canvas>
    </div> -->

    <!-- Tải trang nền -->
    <div v-if="loading" class="loading">
      <div class="spinner"></div>
      <p>Đang tải trang {{ pageNumber }}...</p>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, watch } from 'vue'
import * as pdfjsLib from 'pdfjs-dist/build/pdf'

const props = defineProps({
  pdfUrl: {
    type: String,
    required: true,
  },
  initialPage: {
    type: Number,
    default: 1,
  },
  initialScale: {
    type: Number,
    default: 1.0,
  },
})

// Biến trạng thái
const container = ref(null)
const canvases = ref([])
const loading = ref(false)
const pageNumber = ref(props.initialPage)
const numPages = ref(0)
const scale = ref(props.initialScale)
const searchText = ref('')
const visiblePages = ref([]) // Hiển thị nhiều trang cùng lúc (ví dụ: trang hiện tại ±1)
let pdfDoc = null
let renderTask = null

// // Khởi tạo PDF.js
pdfjsLib.GlobalWorkerOptions.workerSrc =
  'https://cdnjs.cloudflare.com/ajax/libs/pdf.js/5.0.375/pdf.worker.min.mjs'
// Tải PDF
const loadPdf = async () => {
  try {
    loading.value = true
    const loadingTask = pdfjsLib.getDocument(props.pdfUrl)
    pdfDoc = await loadingTask.promise
    numPages.value = pdfDoc.numPages

    // Hiển thị trang đầu tiên
    updateVisiblePages()
  } catch (err) {
    console.error('Lỗi tải PDF:', err)
  } finally {
    loading.value = false
  }
}

// Render trang
const renderPage = async (pageNum) => {
  if (!pdfDoc || pageNum < 1 || pageNum > numPages.value) return

  try {
    if (renderTask) await renderTask // Đợi render trước đó hoàn thành

    const page = await pdfDoc.getPage(pageNum)
    const viewport = page.getViewport({ scale: scale.value })
    const canvas = canvases.value[pageNum - 1]

    if (!canvas) return

    canvas.height = viewport.height
    canvas.width = viewport.width

    renderTask = page.render({
      canvasContext: canvas.getContext('2d'),
      viewport: viewport,
    })

    await renderTask
  } catch (err) {
    console.error(`Lỗi render trang ${pageNum}:`, err)
  }
}

// Cập nhật các trang cần hiển thị (ví dụ: trang hiện tại ±1)
const updateVisiblePages = () => {
  const pages = []
  const range = 1 // Số trang xung quanh trang hiện tại

  for (
    let i = Math.max(1, pageNumber.value - range);
    i <= Math.min(numPages.value, pageNumber.value + range);
    i++
  ) {
    pages.push({ pageNumber: i })
    renderPage(i)
  }

  visiblePages.value = pages
}

// Điều hướng trang
const prevPage = () => {
  pageNumber.value = Math.max(1, pageNumber.value - 1)
}
const nextPage = () => {
  pageNumber.value = Math.min(numPages.value, pageNumber.value + 1)
}
const goToPage = () => {
  pageNumber.value = Math.max(1, Math.min(numPages.value, pageNumber.value))
}

// Phóng to/thu nhỏ
const zoomIn = () => {
  scale.value = Math.min(2, scale.value + 0.1)
}
const zoomOut = () => {
  scale.value = Math.max(0.5, scale.value - 0.1)
}

// Tìm kiếm văn bản
const search = async () => {
  if (!searchText.value.trim()) return

  try {
    const page = await pdfDoc.getPage(pageNumber.value)
    const textContent = await page.getTextContent()

    // Highlight kết quả tìm kiếm (cần thêm CSS)
    const textItems = textContent.items.filter((item) =>
      item.str.toLowerCase().includes(searchText.value.toLowerCase()),
    )

    console.log('Kết quả tìm kiếm:', textItems)
  } catch (err) {
    console.error('Lỗi tìm kiếm:', err)
  }
}

// Theo dõi thay đổi
watch(pageNumber, updateVisiblePages)
watch(scale, updateVisiblePages)

// Refs cho canvas
const setCanvasRef = (el, index) => {
  canvases.value[index] = el
}

// Lifecycle
onMounted(loadPdf)
onUnmounted(() => {
  if (renderTask) renderTask.cancel()
})
</script>

<style scoped>
.pdf-viewer {
  font-family: Arial, sans-serif;
  max-width: 800px;
  margin: 0 auto;
}

.toolbar {
  display: flex;
  gap: 10px;
  align-items: center;
  padding: 10px;
  background: #f5f5f5;
  border-radius: 4px;
  margin-bottom: 10px;
  flex-wrap: wrap;
}

.toolbar input[type='number'] {
  width: 50px;
  text-align: center;
}

.canvas-container {
  border: 1px solid #ddd;
  overflow: auto;
  max-height: 80vh;
}

.pdf-page {
  margin: 0 auto 20px;
  display: block;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.loading {
  text-align: center;
  padding: 20px;
}

.spinner {
  border: 4px solid rgba(0, 0, 0, 0.1);
  border-radius: 50%;
  border-top: 4px solid #3b82f6;
  width: 40px;
  height: 40px;
  animation: spin 1s linear infinite;
  margin: 0 auto 10px;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
