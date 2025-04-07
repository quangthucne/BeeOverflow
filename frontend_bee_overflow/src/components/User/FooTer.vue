<script setup></script>

<template>
<footer class="bg-dark text-white pt-3 pb-2 mt-4">
    <div class="container">
      <div class="row align-items-center">

        <!-- Logo + brand -->
        <div class="col-md-4 mb-4 mb-md-0 text-center text-md-start">
          <img
            src="../../assets/image/beeoverflow.jpg"
            alt=""
            width="50px"
            height="50px"
            class="ms-4"
          />
          <a>
          <RouterLink to="/" class="nav-link fw-bold"> Bee<span class="text-warning fw-bold">overflow</span></RouterLink></a
              >
        </div>

        <!-- Quote + Hashtag -->
        <div class="col-md-4 mb-4 mb-md-0 text-center">
          <p class="mb-2">“Beeoverflow – Chia sẻ để dẫn đầu”</p>
          <p class="mb-0"><strong>#</strong>beeoverflow</p>
        </div>

        <!-- Social links -->
        <div class="col-md-4 text-center text-md-end">
          <p class="mb-2">Kết nối với chúng tôi</p>
          <a href="" class="text-white me-3">
            <i class="bi bi-facebook"></i>
          </a>
          <a href="#" class="text-white me-3">
            <i class="bi bi-github"></i>
          </a>
          <a href="#" class="text-white">
            <i class="bi bi-linkedin"></i>
          </a>
        </div>
      </div>

      <hr class="border-secondary my-3" />

      <div class="row">
        <div class="col text-center">
          <a href="#" class="text-white me-3">Giới thiệu</a>
          <a href="#" class="text-white me-3">Liên hệ</a>
          <a href="#" class="text-white">Chính sách bảo mật</a>
        </div>
      </div>
    </div>
  </footer>
</template>
