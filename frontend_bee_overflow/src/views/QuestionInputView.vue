<script setup>
import HeaDer from '@/components/User/HeaDer.vue'
import FooTer from '@/components/User/FooTer.vue'
import InputDetail from '@/components/User/InputDetail.vue'
import QuestionInputComponent from '@/components/User/QuestionInputComponent.vue'
</script>

<template>
  <header>
    <HeaDer />
  </header>
  <main>
    <section>
      <InputDetail />
    </section>
    <section>
      <QuestionInputComponent />
    </section>
  </main>
  <footer>
    <FooTer />
  </footer>
</template>
