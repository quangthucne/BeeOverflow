<template>
  <div class="main-container">
    <div
      class="editor-container editor-container_classic-editor editor-container_include-fullscreen"
      ref="editorContainerElement"
    >
      <div class="editor-container__editor">
        <div ref="editorElement">
          <ckeditor
            v-if="editor && config"
            :modelValue="config.initialData"
            :editor="editor"
            :config="config"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
/**
 * This configuration was generated using the CKEditor 5 Builder. You can modify it anytime using this link:
 * https://ckeditor.com/ckeditor-5/builder/?redirect=portal#installation/NoFgNARATAdArDADBSBGKqCccDsUc4BsiAzABybEhapyGZlklS6Ho6qEggmJGMoIANwCWKRGGCowEidLkBdSIgAmqMlEwBDCAqA=
 */

import { computed, ref, onMounted } from 'vue'
import { Ckeditor, useCKEditorCloud } from '@ckeditor/ckeditor5-vue'

const LICENSE_KEY =
  'eyJhbGciOiJFUzI1NiJ9.eyJleHAiOjE3NDU4ODQ3OTksImp0aSI6IjNmYzEwMTc1LWRiYTgtNGU3Ny1hODM5LTJjMzgzMjc4NmFiMCIsInVzYWdlRW5kcG9pbnQiOiJodHRwczovL3Byb3h5LWV2ZW50LmNrZWRpdG9yLmNvbSIsImRpc3RyaWJ1dGlvbkNoYW5uZWwiOlsiY2xvdWQiLCJkcnVwYWwiLCJzaCJdLCJ3aGl0ZUxhYmVsIjp0cnVlLCJsaWNlbnNlVHlwZSI6InRyaWFsIiwiZmVhdHVyZXMiOlsiKiJdLCJ2YyI6IjE1ZjViZmNlIn0.5AI8Asa1_5Xg5DcHksANVWtnR8LUp1CxOz7zaDm9VdigwudTaA8HoREWDc44IAwbNTPzebLlkJjuAZazo-oQdA'

const cloud = useCKEditorCloud({ version: '45.0.0', translations: ['vi'] })

const isLayoutReady = ref(false)

const editor = computed(() => {
  if (!cloud.data.value) {
    return null
  }

  return cloud.data.value.CKEditor.ClassicEditor
})

const config = computed(() => {
  if (!isLayoutReady.value) {
    return null
  }

  if (!cloud.data.value) {
    return null
  }

  const {
    Alignment,
    Autoformat,
    AutoLink,
    Autosave,
    Bold,
    Code,
    CodeBlock,
    Essentials,
    FindAndReplace,
    FontBackgroundColor,
    FontColor,
    FontFamily,
    FontSize,
    FullPage,
    Fullscreen,
    GeneralHtmlSupport,
    Heading,
    Highlight,
    HtmlComment,
    HtmlEmbed,
    Italic,
    Link,
    List,
    ListProperties,
    Paragraph,
    PlainTableOutput,
    RemoveFormat,
    ShowBlocks,
    SourceEditing,
    SpecialCharacters,
    SpecialCharactersArrows,
    SpecialCharactersCurrency,
    SpecialCharactersEssentials,
    SpecialCharactersLatin,
    SpecialCharactersMathematical,
    SpecialCharactersText,
    Subscript,
    Superscript,
    Table,
    TableCaption,
    TableCellProperties,
    TableColumnResize,
    TableLayout,
    TableProperties,
    TableToolbar,
    TextTransformation,
    TodoList,
    Underline,
  } = cloud.data.value.CKEditor

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
      Alignment,
      Autoformat,
      AutoLink,
      Autosave,
      Bold,
      Code,
      CodeBlock,
      Essentials,
      FindAndReplace,
      FontBackgroundColor,
      FontColor,
      FontFamily,
      FontSize,
      FullPage,
      Fullscreen,
      GeneralHtmlSupport,
      Heading,
      Highlight,
      HtmlComment,
      HtmlEmbed,
      Italic,
      Link,
      List,
      ListProperties,
      Paragraph,
      PlainTableOutput,
      RemoveFormat,
      ShowBlocks,
      SourceEditing,
      SpecialCharacters,
      SpecialCharactersArrows,
      SpecialCharactersCurrency,
      SpecialCharactersEssentials,
      SpecialCharactersLatin,
      SpecialCharactersMathematical,
      SpecialCharactersText,
      Subscript,
      Superscript,
      Table,
      TableCaption,
      TableCellProperties,
      TableColumnResize,
      TableLayout,
      TableProperties,
      TableToolbar,
      TextTransformation,
      TodoList,
      Underline,
    ],
    fontFamily: {
      supportAllValues: true,
    },
    fontSize: {
      options: [10, 12, 14, 'default', 18, 20, 22],
      supportAllValues: true,
    },
    fullscreen: {
      onEnterCallback: (container) =>
        container.classList.add(
          'editor-container',
          'editor-container_classic-editor',
          'editor-container_include-fullscreen',
          'main-container',
        ),
    },
    heading: {
      options: [
        {
          model: 'paragraph',
          title: 'Paragraph',
          class: 'ck-heading_paragraph',
        },
        {
          model: 'heading1',
          view: 'h1',
          title: 'Heading 1',
          class: 'ck-heading_heading1',
        },
        {
          model: 'heading2',
          view: 'h2',
          title: 'Heading 2',
          class: 'ck-heading_heading2',
        },
        {
          model: 'heading3',
          view: 'h3',
          title: 'Heading 3',
          class: 'ck-heading_heading3',
        },
        {
          model: 'heading4',
          view: 'h4',
          title: 'Heading 4',
          class: 'ck-heading_heading4',
        },
        {
          model: 'heading5',
          view: 'h5',
          title: 'Heading 5',
          class: 'ck-heading_heading5',
        },
        {
          model: 'heading6',
          view: 'h6',
          title: 'Heading 6',
          class: 'ck-heading_heading6',
        },
      ],
    },
    htmlSupport: {
      allow: [
        {
          name: /^.*$/,
          styles: true,
          attributes: true,
          classes: true,
        },
      ],
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
          attributes: {
            download: 'file',
          },
        },
      },
    },
    list: {
      properties: {
        styles: true,
        startIndex: true,
        reversed: true,
      },
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

onMounted(() => {
  isLayoutReady.value = true
})
</script>
