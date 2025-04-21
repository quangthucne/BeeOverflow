<script setup>
import BannerAdmin from '@/components/Admin/BannerAdmin.vue'
import QuestionBlackList from '@/components/Admin/QuestionBlackList.vue'
</script>

<template>
  <div class="admin-layout">
    <div class="admin-sidebar">
      <BannerAdmin />
    </div>

    <div class="admin-content">
      <QuestionBlackList />
    </div>
  </div>
</template>

<style>
.admin-layout {
  display: flex;
  min-height: 100vh;
}

.admin-sidebar {
  width: 250px; /* Hoặc kích thước bạn muốn */
  flex-shrink: 0;
  background: #f8f9fa; /* Màu nền nếu cần */
}

.admin-content {
  flex-grow: 1;
  padding: 20px;
  background: #ffffff; /* Màu nền nếu cần */
}

/* Responsive - trên mobile sẽ chuyển thành stack */
@media (max-width: 768px) {
  .admin-layout {
    flex-direction: column;
  }

  .admin-sidebar {
    width: 100%;
  }
}
</style>
