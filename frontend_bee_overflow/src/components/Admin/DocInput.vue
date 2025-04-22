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

<template>
        <div class="row justify-content-center mt-4">
          <div class="col-12" style="max-width: 100%;">
                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white">
                        <h3 class="mb-0">Add New Document</h3>
                    </div>
                    <div class="card-body">
                        <form id="documentForm" class="needs-validation" novalidate>
                            <div class="mb-3">
                                <label for="documentName" class="form-label">Document Name</label>
                                <input type="text" class="form-control" id="documentName" placeholder="Enter document name" maxlength="100" required>
                                <div class="invalid-feedback">Please enter a document name</div>
                            </div>

                            <div class="mb-3">
                                <label for="description" class="form-label">Description</label>
                                <textarea class="form-control" id="description" rows="3" placeholder="Provide brief document description" maxlength="250"></textarea>
                                <div class="char-count text-end text-muted"><small>0/250</small></div>
                            </div>

                            <div class="mb-3">
                                <label for="price" class="form-label">Price</label>
                                <div class="input-group">
                                    <span class="input-group-text">$</span>
                                    <input type="number" class="form-control" id="price" placeholder="Enter document price" min="0" step="0.01" required>
                                </div>
                                <div class="invalid-feedback">Please enter a valid price</div>
                            </div>

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

                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <button type="reset" class="btn btn-secondary me-md-2">Clear</button>
                                <button type="submit" class="btn btn-primary">Submit</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

</template>

<style>
:root {
    --primary: #0d6efd;
    --primary-foreground: #ffffff;
    --secondary: #6c757d;
    --secondary-foreground: #ffffff;
    --accent: #e9ecef;
    --accent-foreground: #212529;
    --background: #ffffff;
    --foreground: #212529;
    --card: #ffffff;
    --card-foreground: #212529;
    --border: #dee2e6;
    --input: #dee2e6;
    --ring: rgba(13, 110, 253, 0.25);
    --radius: 0.375rem;
    --font-family: system-ui, -apple-system, 'Segoe UI', Roboto, 'Helvetica Neue', Arial;
}

body {
    background-color: var(--background);
    font-family: var(--font-family);
    color: var(--foreground);
}

.card {
    border-radius: var(--radius);
    border-color: var(--border);
    background-color: var(--card);
    color: var(--card-foreground);
}

.card-header {
    background-color: var(--primary);
    color: var(--primary-foreground);
    border-top-left-radius: var(--radius);
    border-top-right-radius: var(--radius);
}

.form-control:focus {
    border-color: var(--primary);
    box-shadow: 0 0 0 0.25rem var(--ring);
}

.btn-primary {
    background-color: var(--primary);
    border-color: var(--primary);
    color: var(--primary-foreground);
}

.btn-secondary {
    background-color: var(--secondary);
    border-color: var(--secondary);
    color: var(--secondary-foreground);
}

.form-control {
    border-color: var(--input);
    border-radius: var(--radius);
}

.form-control:hover {
    border-color: var(--primary);
}

.invalid-feedback {
    color: var(--destructive);
}

.form-check-input:checked {
    background-color: var(--primary);
    border-color: var(--primary);
}

.input-group-text {
    background-color: var(--accent);
    color: var(--accent-foreground);
    border-color: var(--input);
}

@media (max-width: 768px) {
    .card {
        margin: 0 10px;
    }


  }
  .upload-container {
  max-width: 100%;
  padding: 20px;
  border: 1px dashed var(--border);
  border-radius: var(--radius);
  background-color: var(--accent);
  margin-bottom: 1rem;
}

.file-input {
  display: block;
  margin-bottom: 10px;
  padding: 6px;
  border: 1px solid var(--border);
  border-radius: var(--radius);
  width: 100%;
}

.upload-button {
  background-color: var(--primary);
  color: var(--primary-foreground);
  border: none;
  padding: 10px 20px;
  border-radius: var(--radius);
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.3s;
}

.upload-button:hover {
  background-color: #0b5ed7;
}

.upload-button:disabled {
  background-color: #6c757d;
  cursor: not-allowed;
}

.progress-bar {
  margin-top: 10px;
  height: 8px;
  background-color: #dee2e6;
  border-radius: 10px;
  overflow: hidden;
}

.progress-bar div {
  height: 100%;
  background-color: #198754;
  transition: width 0.4s ease;
}

.error-message {
  margin-top: 10px;
  color: #dc3545;
  font-weight: 500;
}

.success-message {
  margin-top: 15px;
  padding: 12px;
  background-color: #d1e7dd;
  color: #0f5132;
  border-radius: var(--radius);
  border: 1px solid #badbcc;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
}

.view-link {
  color: var(--primary);
  text-decoration: underline;
  margin-right: 15px;
}

.copy-button {
  background-color: #f8f9fa;
  border: 1px solid #ced4da;
  padding: 6px 12px;
  border-radius: var(--radius);
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.copy-button:hover {
  background-color: #e2e6ea;
}
</style>
