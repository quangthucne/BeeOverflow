<script setup>
import HeaDer from '@/components/User/HeaDer.vue'
import FooTer from '@/components/User/FooTer.vue'
import DocComponent from '@/components/User/DocComponent.vue';
</script>

<template>
  <header>
    <HeaDer></HeaDer>
  </header>

  <main>
<DocComponent />
  </main>

  <footer>
    <FooTer></FooTer>
  </footer>
</template>
