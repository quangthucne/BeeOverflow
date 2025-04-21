<template>
  <div class="container mt-4">
    <h2 class="text-center mb-4">Mua và Bán Tài Liệu Trực Tuyến</h2>

    <!-- Bộ lọc -->
    <div class="d-flex justify-content-end mb-3">
      <select v-model="filter" class="form-select w-auto">
        <option value="all">Tất cả</option>
        <option value="free">Miễn phí</option>
        <option value="paid">Trả phí</option>
      </select>
    </div>

    <!-- Danh sách tài liệu -->
    <div class="row">
      <div
        v-for="doc in filteredDocuments"
        :key="doc.id"
        class="col-md-4 mb-4 d-flex align-items-stretch"
      >
        <div class="card shadow-sm w-100">
          <img
            src="https://cdn-icons-png.flaticon.com/512/337/337946.png"
            class="card-img-top p-3"
            alt="Tài liệu"
            style="height: 180px; object-fit: contain"
          />
          <div class="card-body">
            <h5 class="card-title">{{ doc.nameDoc }}</h5>
            <p class="card-text text-muted">{{ doc.description }}</p>
            <div class="d-flex justify-content-between align-items-center">
              <span class="text-primary fw-bold">
                {{ formatPrice(doc.price, doc.free) }}
              </span>
              <div>
                <button
                  class="btn btn-outline-primary btn-sm me-2"
                  @click="openPreview(doc.docSrc)"
                >
                  Xem Trước
                </button>
                <a
                  :href="doc.docSrc"
                  target="_blank"
                  class="btn"
                  :class="doc.free ? 'btn-primary btn-sm' : 'btn-success btn-sm'"
                >
                  {{ doc.free ? 'Xem Ngay' : 'Mua Ngay' }}
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal xem trước -->
    <div
      v-if="showModal"
      class="modal fade show d-block"
      tabindex="-1"
      style="background-color: rgba(0, 0, 0, 0.5)"
    >
      <div class="modal-dialog modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Xem Trước Tài Liệu</h5>
            <button type="button" class="btn-close" @click="closePreview"></button>
          </div>
          <div class="modal-body" style="height: 80vh">
            <iframe
              :src="previewUrl"
              width="100%"
              height="100%"
              style="border: none"
            ></iframe>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import axios from 'axios'

interface DocumentItem {
  id: number
  nameDoc: string
  description: string
  docSrc: string
  price: number
  free: boolean
  isDeleted: boolean
}

const documents = ref<DocumentItem[]>([])
const filter = ref<'all' | 'free' | 'paid'>('all')

// Modal state
const showModal = ref(false)
const previewUrl = ref<string | null>(null)

const fetchDocuments = async () => {
  try {
    const res = await axios.get('http://localhost:8080/document')
    if (res.data.status === 1) {
      documents.value = res.data.data
    }
  } catch (err) {
    console.error('Lỗi khi lấy tài liệu:', err)
  }
}

const formatPrice = (price: number, free: boolean) => {
  if (free) return 'Miễn phí'
  return new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND',
  }).format(price)
}

// Bộ lọc tài liệu theo lựa chọn
const filteredDocuments = computed(() => {
  if (filter.value === 'free') {
    return documents.value.filter(doc => doc.free)
  }
  if (filter.value === 'paid') {
    return documents.value.filter(doc => !doc.free)
  }
  return documents.value
})

const openPreview = (url: string) => {
  previewUrl.value = url
  showModal.value = true
}

const closePreview = () => {
  showModal.value = false
  previewUrl.value = null
}

onMounted(fetchDocuments)
</script>

<style scoped>
.modal.fade {
  display: block;
}
</style>
