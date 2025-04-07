<script setup></script>

<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow">
      <div class="container">
        <a class="navbar-brand fw-bold d-flex align-items-center" href="#">
          <a class="nav-link" href="#"
                ><RouterLink to="/" class="nav-link"> Bee<span class="text-warning">overflow</span></RouterLink></a
              >
          <img
            src="../../assets/image/beeoverflow.jpg"
            alt=""
            width="50px"
            height="50px"
            class="ms-2"
          />
        </a>
        <div class="d-flex">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><RouterLink to="/" class="nav-link">Câu hỏi</RouterLink></a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><RouterLink to="/tag" class="nav-link">Thẻ</RouterLink></a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><RouterLink to="/document" class="nav-link">Tài liệu</RouterLink></a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><RouterLink to="/userinfo" class="nav-link">Tôi</RouterLink></a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"
                ><RouterLink to="/listuser" class="nav-link">Bảng xếp hạng</RouterLink></a
              >
            </li>
            <li class="nav-item">
              <RouterLink to="/" class="btn btn-outline-primary btn-outline-primary mt-2">Thêm câu hỏi</RouterLink>
            </li>
          </ul>
        </div>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
          <form class="d-flex me-3">
            <input
              class="form-control me-2"
              type="search"
              placeholder="Search..."
              aria-label="Search"
            />
            <button class="btn btn-outline-light" type="submit">Tìm </button>
          </form>
          <RouterLink to="/login" class="btn btn-outline-warning me-2">Đăng nhập</RouterLink>
          <RouterLink to="/register" class="btn btn btn-warning me-2">Đăng ký</RouterLink>

        </div>
      </div>
    </nav>
  </header>
</template>

