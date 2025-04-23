<template>
  <div class="answer-node" :style="'padding-left: ' + level * 2 + 'rem'">
    <div class="d-flex align-items-start mb-3">
      <img
        :src="answer.account.avatar"
        alt="Avatar"
        class="rounded-circle me-3"
        :style="'width: ' + (40 - level * 4) + 'px; height: ' + (40 - level * 4) + 'px;'"
      />
      <div class="flex-grow-1">
        <!-- Thông tin -->
        <div class="info d-flex align-items-center mb-2">
          <h4 class="mb-0 text-warning me-2">{{ answer.account.fullname }}</h4>
          <div
            class="rank d-flex align-items-center"
            v-html="answer.account.reputation.rank.icon"
          />
        </div>

        <!-- Nội dung -->
        <div v-html="answer.detail" class="mb-2" />

        <!-- Ảnh -->
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

        <!-- Nút chỉnh sửa -->
        <div v-if="answer.account.id == currentAccountId" class="d-flex gap-2 mb-2">
          <button class="btn btn-sm btn-outline-secondary mt-2 me-2" @click="toggleEdit">
            {{ isEditing ? 'Ẩn form chỉnh sửa' : 'Chỉnh sửa' }}
          </button>
        </div>

        <!-- Toggle nested -->
        <button
          v-if="hasNestedAnswers"
          class="btn btn-link text-decoration-none p-0"
          @click="$emit('toggle', answer.id)"
        >
          <i :class="expanded.includes(answer.id) ? 'bi bi-chevron-up' : 'bi bi-chevron-down'"></i>
          {{ expanded.includes(answer.id) ? 'Ẩn' : 'Hiển thị' }} {{ nestedAnswersCount }} trả lời
        </button>

        <!-- Trả lời con -->
        <div v-if="expanded.includes(answer.id) && hasNestedAnswers" class="mt-2">
          <AnswerNode
            v-for="nestedAnswer in nestedAnswers"
            :key="nestedAnswer.id"
            :answer="nestedAnswer"
            :level="level + 1"
            :expanded="expanded"
            :current-account-id="currentAccountId"
            @toggle="$emit('toggle', $event)"
          />
        </div>
      </div>
    </div>
  </div>

  <!-- FORM CHỈNH SỬA TOÀN MÀN HÌNH -->
  <transition name="slide-up">
    <div v-if="isEditing" class="answer-form-fullscreen shadow-lg p-4 bg-white">
      <AnswerInputComponent
        :question-id="answer.quesId"
        :answer-id="answer.id"
        :is-editing="true"
        :initial-content="answer.detail"
        @submitted="toggleEdit"
        @cancel="toggleEdit"
      />
    </div>
  </transition>
</template>

<script lang="ts">
import { defineComponent, PropType, ref, computed } from 'vue'
import { Answer } from './types'
import AnswerInputComponent from './AnswerInputComponent.vue'

export default defineComponent({
  name: 'AnswerNode',
  components: { AnswerInputComponent },
  props: {
    answer: { type: Object as PropType<Answer>, required: true },
    level: { type: Number, required: true },
    expanded: { type: Array as PropType<number[]>, required: true },
    currentAccountId: { type: Number, required: true },
  },
  emits: ['toggle'],
  setup(props) {
    const isEditing = ref(false)

    const toggleEdit = () => {
      isEditing.value = !isEditing.value
    }

    const nestedAnswers = computed(() => {
      return props.answer.answersInParent?.filter((a) => !a.isDeleted) || []
    })

    const hasNestedAnswers = computed(() => nestedAnswers.value.length > 0)
    const nestedAnswersCount = computed(() => nestedAnswers.value.length)

    return {
      isEditing,
      toggleEdit,
      nestedAnswers,
      hasNestedAnswers,
      nestedAnswersCount,
    }
  },
})
</script>

<style scoped>
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
.answer-form-fullscreen {
  position: relative;
  left: 0;
  width: 100vw;
  max-width: 100vw;
  margin-left: calc(-1 * var(--bs-gutter-x, 1.5rem));
  padding-left: var(--bs-gutter-x, 1.5rem);
  padding-right: var(--bs-gutter-x, 1.5rem);
  z-index: 10;
}
.slide-up-enter-active,
.slide-up-leave-active {
  transition: all 0.3s ease;
}

.slide-up-enter-from,
.slide-up-leave-to {
  opacity: 0;
  transform: translateY(20px);
}

.slide-up-enter-to,
.slide-up-leave-from {
  opacity: 1;
  transform: translateY(0);
}
</style>
