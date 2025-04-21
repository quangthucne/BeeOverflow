<script setup>
import HeaDer from '@/components/User/HeaDer.vue'
import FooTer from '@/components/User/FooTer.vue'
import QuestionDetail from '@/components/User/QuestionDetail.vue';
</script>

<template>
  <header>
    <HeaDer />
  </header>
  <main>
    <section>
      <QuestionDetail />
    </section>
  </main>
  <footer>
    <FooTer />
  </footer>
</template>
