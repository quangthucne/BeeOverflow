<script setup>
import UploadImage from '@/components/User/UploadImage.vue'
import PdfUploader from '@/components/User/PdfUploader.vue'
import PdfFrame from '@/components/User/PdfFrame.vue'
import QuestionDetail from '@/components/User/QuestionDetail.vue'
</script>
<template>
  <header>
    <PdfUploader />
  </header>
  <main>
    <section>
      <PdfFrame
        pdfUrl="https://qwomlwcudldjsbtceasu.supabase.co/storage/v1/object/public/pdf-bucket/public/pdfs/tjth8tgchb.pdf"
        :initialPage="1"
        :initialScale="1.2"
      />
    </section>
    <section>
      <UploadImage />
    </section>

    <section>
      <QuestionDetail :questions="questionData" />
    </section>
  </main>
</template>
