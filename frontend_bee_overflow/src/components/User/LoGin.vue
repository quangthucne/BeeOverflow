<script setup>
</script>

<template>
  <div class="container my-5">
    <div class="row justify-content-center">
      <div class="col-md-6" style="width: 60%;">
        <div class="card shadow-lg">
          <div class="card-body p-4">
            <!-- Logo -->
            <div class="text-center mb-4">
              <img
                src="../../assets/image/beeoverflow.jpg"
                alt="Beeoverflow Logo"
                width="80px"
                height="80px"
                class="mb-3"
              />
              <h2>Đăng nhập</h2>
            </div>

            <!-- Form đăng nhập -->
            <form>
              <!-- Email Input -->
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  placeholder="Nhập email"
                  required
                />
                <small class="text-danger" id="emailError">Vui lòng nhập email hợp lệ.</small>
              </div>

              <!-- Password Input -->
              <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  placeholder="Nhập mật khẩu"
                  required
                />
                <small class="text-danger" id="passwordError">Vui lòng nhập mật khẩu.</small>
              </div>

              <!-- Submit Button -->
              <button type="submit" class="btn btn-warning w-100">Đăng nhập</button>
            </form>

            <!-- Đăng nhập bằng Google -->
            <div class="mt-3 text-center">
              <button class="btn btn-outline-primary w-100" @click="googleLogin">
                <i class="bi bi-google"></i> Đăng nhập với Google
              </button>
            </div>

            <div class="mt-3 text-center">
              <p class="mb-0">Chưa có tài khoản? <RouterLink to="/register" class="text-warning">Đăng ký ngay</RouterLink></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const googleLogin = () => {
  // Đăng nhập với Google sẽ cần xử lý với Google OAuth API
  alert("Đang đăng nhập với Google (Cần tích hợp Google OAuth API)");
};
</script>
