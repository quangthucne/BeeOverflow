<template>
  <div class="answer-node" :style="'padding-left: ' + level * 2 + 'rem'">
    <!-- Nội dung câu trả lời -->
    <div class="d-flex align-items-start mb-3">
      <img
        :src="answer.account.avatar"
        alt="Avatar"
        class="rounded-circle me-3"
        :style="'width: ' + (40 - level * 4) + 'px; height: ' + (40 - level * 4) + 'px;'"
      />
      <div class="flex-grow-1">
        <!-- Hiển thị thông tin người trả lời -->
        <div class="info d-flex align-items-center mb-2">
          <h4 class="mb-0 text-warning me-2">{{ answer.account.fullname }}</h4>
          <div
            class="rank d-flex align-items-center"
            v-html="answer.account.reputation.rank.icon"
          ></div>
        </div>

        <!-- Nội dung câu trả lời -->
        <div v-html="answer.detail"></div>

        <!-- Hiển thị ảnh nếu có -->
        <div v-if="answer.imagesAns?.length" class="d-flex flex-wrap gap-2 mb-2">
          <img
            v-for="image in answer.imagesAns"
            :key="image.id"
            :src="image.name"
            alt="Answer Image"
            class="img-fluid rounded"
            style="width: 80px; height: 80px; object-fit: cover"
          />
        </div>

        <!-- Nút toggle cho câu trả lời con -->
        <button
          v-if="hasNestedAnswers"
          class="btn btn-link text-decoration-none p-0"
          @click="$emit('toggle', answer.id)"
        >
          <i :class="expanded.includes(answer.id) ? 'bi bi-chevron-up' : 'bi bi-chevron-down'"></i>
          {{ expanded.includes(answer.id) ? 'Ẩn' : 'Hiển thị' }} {{ nestedAnswersCount }} trả lời
        </button>

        <!-- Hiển thị câu trả lời con nếu được mở rộng -->
        <div v-if="expanded.includes(answer.id) && hasNestedAnswers" class="mt-2">
          <AnswerNode
            v-for="nestedAnswer in nestedAnswers"
            :key="nestedAnswer.id"
            :answer="nestedAnswer"
            :level="level + 1"
            :expanded="expanded"
            @toggle="$emit('toggle', $event)"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, PropType, computed } from 'vue'
import { Answer } from './types'

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
    expanded: {
      type: Array as PropType<number[]>,
      required: true,
    },
  },
  emits: ['toggle'],
  setup(props) {
    const nestedAnswers = computed(() => {
      return props.answer.answersInParent?.filter((a) => !a.isDeleted) || []
    })

    const hasNestedAnswers = computed(() => {
      return nestedAnswers.value.length > 0
    })

    const nestedAnswersCount = computed(() => {
      return nestedAnswers.value.length
    })

    return { nestedAnswers, hasNestedAnswers, nestedAnswersCount }
  },
})
</script>

<style scoped>
/* Giữ nguyên phần style như cũ */
.answer-node {
  position: relative;
}

.tree-line {
  content: '';
  position: absolute;
  top: 0;
  bottom: 0;
  width: 2px;
  background-color: #dee2e6;
}

.answer-node::before {
  content: '';
  position: absolute;
  top: 1.5rem;
  left: 0;
  width: 1rem;
  height: 2px;
  background-color: #dee2e6;
}

.answer-node[level='0']::before {
  display: none;
}
</style>
