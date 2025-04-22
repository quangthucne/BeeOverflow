<script setup>
// Không cần logic JS nếu chỉ là header tĩnh
</script>

<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
      <div class="container d-flex align-items-center justify-content-between">
        <!-- Logo & Brand -->
        <RouterLink
          to="/"
          class="navbar-brand fw-bold d-flex align-items-center text-white text-decoration-none"
        >
          Bee<span class="text-warning">overflow</span>
          <img
            src="../../assets/image/beeoverflow.jpg"
            alt="Logo"
            width="50"
            height="50"
            class="ms-2 rounded"
          />
        </RouterLink>

        <!-- Toggle for mobile -->
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Nav items -->
        <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
          <ul class="navbar-nav me-auto d-flex align-items-center gap-3">
            <li class="nav-item">
              <RouterLink to="/" class="nav-link">Trang chủ</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/questions" class="nav-link">Câu hỏi</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/tag" class="nav-link">Thẻ</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/doc" class="nav-link">Tài liệu</RouterLink>
            </li>
            <li class="nav-item">
              <RouterLink to="/listuser" class="nav-link">Bảng xếp hạng</RouterLink>
            </li>
          </ul>

          <!-- Thêm câu hỏi -->
          <RouterLink to="/question/add" class="btn btn-outline-primary me-3">
            Thêm câu hỏi
          </RouterLink>

          <!-- Search form -->
          <form class="d-flex me-3" role="search">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search..."
              aria-label="Search"
            />
            <button class="btn btn-outline-light" type="submit">Tìm</button>
          </form>

          <!-- Auth buttons -->
          <RouterLink to="/login" class="btn btn-outline-warning me-2">Đăng nhập</RouterLink>
          <RouterLink to="/register" class="btn btn-warning">Đăng ký</RouterLink>
        </div>
      </div>
    </nav>
  </header>
</template>
<style>
.navbar {
  padding-top: 1.5rem; /* Giảm khoảng cách trên */
  padding-bottom: 1.5rem; /* Giảm khoảng cách dưới */
}

.navbar-brand {
  font-size: 1.8rem; /* Tăng kích thước chữ */
  font-weight: 700; /* In đậm */
  white-space: nowrap; /* Ngăn không cho chữ bị xuống dòng */
  padding-top: 0; /* Loại bỏ padding thừa nếu có */
  padding-bottom: 0; /* Loại bỏ padding thừa nếu có */
}

.nav-link {
  font-size: 1.1rem; /* Tăng kích thước chữ */
  font-weight: 600; /* In đậm một chút */
  white-space: nowrap; /* Ngăn không cho chữ bị xuống dòng */
  padding-top: 0.5rem; /* Điều chỉnh khoảng cách trên của các link */
  padding-bottom: 0.5rem; /* Điều chỉnh khoảng cách dưới của các link */
}

.btn {
  font-size: 1.1rem; /* Tăng kích thước chữ */
  font-weight: 600; /* In đậm */
  white-space: nowrap; /* Ngăn không cho chữ bị xuống dòng */
  padding-top: 0.5rem; /* Điều chỉnh padding cho nút */
  padding-bottom: 0.5rem; /* Điều chỉnh padding cho nút */
}

/* Đảm bảo navbar không bị bể layout trên màn hình nhỏ */
@media (max-width: 768px) {
  .navbar-nav {
    flex-wrap: nowrap; /* Giữ các phần tử navbar không bị xuống dòng */
  }

  .navbar-collapse {
    justify-content: space-between; /* Căn chỉnh các phần tử trong navbar */
  }
}

html, body {
  height: 100%; /* Đảm bảo chiều cao toàn bộ trang chiếm 100% */
  margin: 0; /* Loại bỏ margin mặc định */
}

body {
  display: flex;
  flex-direction: column; /* Sắp xếp các phần tử theo chiều dọc */
  font-family: 'Inter', sans-serif;
  background-color: var(--background);
  color: var(--foreground);
}

.main-content {
  flex-grow: 1; /* Giúp phần nội dung chiếm không gian còn lại */
}

.footer-section {
  background-color: var(--card);
  color: var(--card-foreground);
  position: relative;
  width: 100%;
  padding-top: 5rem; /* Điều chỉnh khoảng cách trên của footer */
  padding-bottom: 5rem; /* Điều chỉnh khoảng cách dưới của footer */
  margin-top: auto; /* Đảm bảo footer luôn ở dưới cùng */
}

.footer-title {
  color: #fdb515; /* vàng sáng */
  font-weight: 600;
  margin-bottom: 1.5rem;
  font-size: 1.25rem;
}

</style>
