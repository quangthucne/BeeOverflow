<script setup>
const googleSignUp = () => {
  // Đây là nơi bạn sẽ tích hợp Google OAuth API để thực hiện đăng ký với Google.
  alert('Đang đăng ký với Google (Cần tích hợp Google OAuth API)')
}
</script>

<template>
  <div class="container my-5">
    <div class="row justify-content-center">
      <div class="col-md-6" style="width: 60%">
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
              <h2>Đăng ký</h2>
            </div>

            <!-- Form đăng ký -->
            <form>
              <!-- Họ và tên -->
              <div class="mb-3">
                <label for="fullName" class="form-label">Họ và tên</label>
                <input
                  type="text"
                  class="form-control"
                  id="fullName"
                  placeholder="Nhập họ và tên"
                  required
                />
                <small class="text-danger" id="fullNameError">Vui lòng nhập họ và tên.</small>
              </div>

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
                <small class="text-danger" id="emailError">Vui lòng nhập một email hợp lệ.</small>
              </div>

              <!-- Số điện thoại -->
              <div class="mb-3">
                <label for="phone" class="form-label">Số điện thoại</label>
                <input
                  type="tel"
                  class="form-control"
                  id="phone"
                  placeholder="Nhập số điện thoại"
                  required
                />
                <small class="text-danger" id="phoneError"
                  >Vui lòng nhập số điện thoại hợp lệ.</small
                >
              </div>

              <!-- Giới tính -->
              <div class="mb-3">
                <label class="form-label">Giới tính</label>
                <div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gender"
                      id="genderMale"
                      value="male"
                      required
                    />
                    <label class="form-check-label" for="genderMale">Nam</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gender"
                      id="genderFemale"
                      value="female"
                    />
                    <label class="form-check-label" for="genderFemale">Nữ</label>
                  </div>
                  <div class="form-check form-check-inline">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="gender"
                      id="genderLGBT"
                      value="lgbt"
                    />
                    <label class="form-check-label" for="genderLGBT">LGBT</label>
                  </div>
                </div>
                <small class="text-danger" id="genderError">Vui lòng chọn giới tính.</small>
              </div>

              <!-- Mật khẩu -->
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

              <!-- Xác nhận mật khẩu -->
              <div class="mb-3">
                <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                <input
                  type="password"
                  class="form-control"
                  id="confirmPassword"
                  placeholder="Xác nhận mật khẩu"
                  required
                />
                <small class="text-danger" id="confirmPasswordError">Mật khẩu không khớp.</small>
              </div>

              <!-- Submit Button -->
              <button type="submit" class="btn btn-warning w-100">Đăng ký</button>
            </form>

            <!-- Đăng ký bằng Google -->
            <div class="mt-3 text-center">
              <button class="btn btn-outline-primary w-100" @click="googleSignUp">
                <i class="bi bi-google"></i> Đăng ký với Google
              </button>
            </div>

            <div class="mt-3 text-center">
              <p class="mb-0">
                Đã có tài khoản?
                <RouterLink to="/login" class="text-warning">Đăng nhập ngay</RouterLink>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
