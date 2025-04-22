<template>
  <div class="container">
    <div class="comment-form card p-4 shadow-sm">
      <h4 class="mb-4">Trả lời câu hỏi</h4>

      <div class="main-container">
        <div
          class="editor-container editor-container_classic-editor editor-container_include-fullscreen"
          ref="editorContainer"
          style="width: 1200px"
        >
          <!-- :model-value="modelValue" -->
          <ckeditor
            v-if="editor && config"
            :editor="editor"
            :config="config"
            :content="content"
            @update:modelValue="handleChange"
            @ready="onReady"
          />
        </div>
      </div>
      <div class="upload">
        <UploadImage />
      </div>
      <div class="d-grid">
        <button class="btn btn-primary">Gửi bình luận</button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, ref, watch, onMounted } from 'vue'
import { Ckeditor, useCKEditorCloud } from '@ckeditor/ckeditor5-vue'
import UploadImage from './UploadImage.vue'

const props = defineProps<{
  modelValue: string
  content: string
}>()

const emit = defineEmits<{
  (e: 'update:modelValue', value: string): void
  (e: 'update:content', value: string): void
}>()

const LICENSE_KEY =
  'eyJhbGciOiJFUzI1NiJ9.eyJleHAiOjE3NDU4ODQ3OTksImp0aSI6IjNmYzEwMTc1LWRiYTgtNGU3Ny1hODM5LTJjMzgzMjc4NmFiMCIsInVzYWdlRW5kcG9pbnQiOiJodHRwczovL3Byb3h5LWV2ZW50LmNrZWRpdG9yLmNvbSIsImRpc3RyaWJ1dGlvbkNoYW5uZWwiOlsiY2xvdWQiLCJkcnVwYWwiLCJzaCJdLCJ3aGl0ZUxhYmVsIjp0cnVlLCJsaWNlbnNlVHlwZSI6InRyaWFsIiwiZmVhdHVyZXMiOlsiKiJdLCJ2YyI6IjE1ZjViZmNlIn0.5AI8Asa1_5Xg5DcHksANVWtnR8LUp1CxOz7zaDm9VdigwudTaA8HoREWDc44IAwbNTPzebLlkJjuAZazo-oQdA'

const cloud = useCKEditorCloud({ version: '45.0.0', translations: ['vi'] })

const isLayoutReady = ref(false)
const editorInstance = ref<any>(null)
const editorContainer = ref<HTMLElement | null>(null)

const editor = computed(() => cloud.data.value?.CKEditor.ClassicEditor ?? null)

const config = computed(() => {
  const CK = cloud.data.value?.CKEditor
  if (!isLayoutReady.value || !CK) return null

  return {
    toolbar: {
      items: [
        'sourceEditing',
        'showBlocks',
        'findAndReplace',
        'fullscreen',
        '|',
        'heading',
        '|',
        'fontSize',
        'fontFamily',
        'fontColor',
        'fontBackgroundColor',
        '|',
        'bold',
        'italic',
        'underline',
        'subscript',
        'superscript',
        'code',
        'removeFormat',
        '|',
        'specialCharacters',
        'link',
        'insertTable',
        'insertTableLayout',
        'highlight',
        'codeBlock',
        'htmlEmbed',
        '|',
        'alignment',
        '|',
        'bulletedList',
        'numberedList',
        'todoList',
      ],
      shouldNotGroupWhenFull: false,
    },
    plugins: [
      CK.Alignment,
      CK.Autoformat,
      CK.AutoLink,
      CK.Autosave,
      CK.Bold,
      CK.Code,
      CK.CodeBlock,
      CK.Essentials,
      CK.FindAndReplace,
      CK.FontBackgroundColor,
      CK.FontColor,
      CK.FontFamily,
      CK.FontSize,
      CK.Fullscreen,
      CK.GeneralHtmlSupport,
      CK.Heading,
      CK.Highlight,
      CK.HtmlComment,
      CK.HtmlEmbed,
      CK.Italic,
      CK.Link,
      CK.List,
      CK.ListProperties,
      CK.Paragraph,
      CK.PlainTableOutput,
      CK.RemoveFormat,
      CK.ShowBlocks,
      CK.SourceEditing,
      CK.SpecialCharacters,
      CK.SpecialCharactersArrows,
      CK.SpecialCharactersCurrency,
      CK.SpecialCharactersEssentials,
      CK.SpecialCharactersLatin,
      CK.SpecialCharactersMathematical,
      CK.SpecialCharactersText,
      CK.Subscript,
      CK.Superscript,
      CK.Table,
      CK.TableCaption,
      CK.TableCellProperties,
      CK.TableColumnResize,
      CK.TableLayout,
      CK.TableProperties,
      CK.TableToolbar,
      CK.TextTransformation,
      CK.TodoList,
      CK.Underline,
    ],
    fontFamily: { supportAllValues: true },
    fontSize: { options: [10, 12, 14, 'default', 18, 20, 22], supportAllValues: true },
    fullscreen: {
      onEnterCallback: (container: HTMLElement) => {
        container.classList.add(
          'editor-container',
          'editor-container_classic-editor',
          'editor-container_include-fullscreen',
          'main-container',
        )
      },
    },
    heading: {
      options: Array.from({ length: 6 }, (_, i) => ({
        model: `heading${i + 1}`,
        view: `h${i + 1}`,
        title: `Heading ${i + 1}`,
        class: `ck-heading_heading${i + 1}`,
      })).concat({ model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' }),
    },
    htmlSupport: {
      allow: [{ name: /^.*$/, styles: true, attributes: true, classes: true }],
    },
    initialData: '',
    language: 'vi',
    licenseKey: LICENSE_KEY,
    link: {
      addTargetToExternalLinks: true,
      defaultProtocol: 'https://',
      decorators: {
        toggleDownloadable: {
          mode: 'manual',
          label: 'Downloadable',
          attributes: { download: 'file' },
        },
      },
    },
    list: {
      properties: { styles: true, startIndex: true, reversed: true },
    },
    placeholder: 'Mô tả chi tiết câu hỏi...',
    table: {
      contentToolbar: [
        'tableColumn',
        'tableRow',
        'mergeTableCells',
        'tableProperties',
        'tableCellProperties',
      ],
    },
  }
})

const handleChange = (content: string) => {
  emit('update:content', content)
  emit('update:modelValue', content)
}

const onReady = (editor: any) => {
  editorInstance.value = editor
}

watch(
  () => props.content,
  (newVal) => {
    if (editorInstance.value && editorInstance.value.getData() !== newVal) {
      editorInstance.value.setData(newVal)
    }
  },
)

onMounted(() => {
  isLayoutReady.value = true
})

import { ref } from 'vue'
import { supabase } from '@/config/supabase'
import UploadImage from './UploadImage.vue'

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
.comment-form {
  max-width: 2200px; /* hoặc 100%, nếu bạn muốn full màn hình */
  margin: 0 auto;
  background-color: #fff;
  border-radius: 10px;
  padding: 20px;
}

.editor-container {
  border: 1px solid #ced4da;
  border-radius: 6px;
  padding: 10px;
  background-color: #fff;
  margin-bottom: 20px;
}

.upload-container {
  width: 100%;
  margin: 20px auto;
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
