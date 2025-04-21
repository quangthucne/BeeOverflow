<!-- src/components/AnswerNode.vue -->
<template>
  <div class="answer-node position-relative" :style="'padding-left: ' + level * 2 + 'rem'">
    <!-- Connecting lines -->
    <div
      v-if="level > 0"
      class="tree-line position-absolute"
      :style="'left: ' + ((level - 1) * 2 + 1) + 'rem'"
    ></div>
    <div class="d-flex align-items-start mb-3">
      <img
        :src="accountAvatar"
        alt="Avatar"
        class="rounded-circle me-3"
        :style="'width: ' + (40 - level * 4) + 'px; height: ' + (40 - level * 4) + 'px;'"
      />
      <div class="flex-grow-1">
        <p class="mb-2 text-muted small">tar</p>
        <p class="mb-2">{{ answer.detail }}</p>
        <div v-if="answer.imagesAns.length" class="d-flex flex-wrap gap-2 mb-2">
          <img
            v-for="image in answer.imagesAns"
            :key="image.id"
            :src="image.name"
            alt="Answer Image"
            class="img-fluid rounded"
            style="width: 80px; height: 80px; object-fit: cover"
          />
        </div>
        <button
          v-if="answer.answers.length"
          class="btn btn-link text-decoration-none p-0"
          @click="$emit('toggle', answer.id)"
        >
          <i :class="expanded.includes(answer.id) ? 'bi bi-chevron-up' : 'bi bi-chevron-down'"></i>
          {{ expanded.includes(answer.id) ? 'Hide' : 'Show' }} {{ answer.answers.length }}
          {{ answer.answers.length === 1 ? 'reply' : 'replies' }}
        </button>
        <div v-if="expanded.includes(answer.id)" class="mt-2">
          <AnswerNode
            v-for="nestedAnswer in answer.answers.filter((a) => a.isDeleted !== true)"
            :key="nestedAnswer.id"
            :answer="nestedAnswer"
            :level="level + 1"
            :account-avatar="accountAvatar"
            :expanded="expanded"
            @toggle="$emit('toggle', $event)"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue'
import { Answer } from './types' // Import types if defined separately

export default defineComponent({
  name: 'AnswerNode',
  props: {
    answer: {
      type: Object as PropType<Answer>,
      required: true,
    },
    level: {
      type: Number,
      required: true,
    },
    accountAvatar: {
      type: String,
      default: 'https://via.placeholder.com/40',
    },
    expanded: {
      type: Array as PropType<number[]>,
      required: true,
    },
  },
  emits: ['toggle'],
})
</script>

<style scoped>
.answer-node {
  position: relative;
}

/* Vertical connecting line for nested answers */
.tree-line {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  width: 2px;
  background-color: #dee2e6;
}

/* Horizontal connecting line for each answer */
.answer-node::before {
  content: '';
  position: absolute;
  top: 1.5rem; /* Align with avatar center */
  left: 0;
  width: 1rem;
  height: 2px;
  background-color: #dee2e6;
}

/* Remove horizontal line for top-level answers */
.answer-node[level='0']::before {
  display: none;
}
</style>
