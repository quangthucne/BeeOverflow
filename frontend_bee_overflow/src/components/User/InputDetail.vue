<template>
  <div class="main-container">
    <div
      class="editor-container editor-container_classic-editor editor-container_include-fullscreen"
      ref="editorContainer"
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
</template>

<script lang="ts" setup>
import { computed, ref, watch, onMounted } from 'vue'
import { Ckeditor, useCKEditorCloud } from '@ckeditor/ckeditor5-vue'

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
</script>

<style scoped>
.editor-container {
  border: 1px solid #ced4da;
  border-radius: 6px;
  padding: 10px;
  background-color: #fff;
}
</style>
