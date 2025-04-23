<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useVuelidate } from '@vuelidate/core'
import { required, minLength, helpers } from '@vuelidate/validators'
import axios from 'axios'
import Swal from 'sweetalert2'
import Cookies from 'js-cookie'

const router = useRouter()
const loading = ref(false)

const form = ref({
  username: '',
  password: '',
})

const rules = computed(() => ({
  username: {
    required: helpers.withMessage('Vui lòng nhập tên đăng nhập!', required),
  },
  password: {
    required: helpers.withMessage('Vui lòng nhập mật khẩu', required),
  },
}))

const v$ = useVuelidate(rules, form)

const submitLogin = async () => {
  // Kiểm tra validation mà không hiển thị lỗi bằng SweetAlert
  v$.value.$touch()

  // Nếu form không hợp lệ, dừng lại
  if (v$.value.$invalid) {
    return
  }

  loading.value = true

  try {
    const res = await axios.post('http://localhost:8080/account/login', {
      username: form.value.username,
      password: form.value.password,
    })
    console.log(res.data.status)
    let status = res.data.status
    // Lưu token vào cookie
    if (status === 1) {
      const token = res.data.data
      Cookies.set('token', token, { path: '/', expires: 7 }) // expires: 7 ngày

      Swal.fire({
        icon: 'success',
        title: 'Đăng nhập thành công!',
        text: 'Chào mừng bạn quay lại!',
      }).then(() => {
        router.push('/')
      })
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Đăng nhập thất bại',
        text: res.data.message,
      })
    }
  } catch (error: any) {
    Swal.fire({
      icon: 'error',
      title: 'Đăng nhập thất bại',
      text: error?.response?.data?.message || 'Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại.',
    })
  } finally {
    loading.value = false
  }
}

const googleLogin = () => {
  alert('Đang đăng nhập với Google (Cần tích hợp Google OAuth API)')
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
              <h2>Đăng nhập</h2>
            </div>

            <!-- Form đăng nhập -->
            <form @submit.prevent="submitLogin">
              <!-- username Input -->
              <div class="mb-3">
                <label for="username" class="form-label">Tên đăng nhập</label>
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  v-model="form.username"
                  :class="{ 'is-invalid': v$.username.$error }"
                  placeholder="Nhập tên đăng nhập"
                />
                <div class="invalid-feedback" v-if="v$.username.$error">
                  <span v-for="error in v$.username.$errors" :key="error.$uid">
                    {{ error.$message || 'Vui lòng nhập tên đăng nhập hợp lệ.' }}
                  </span>
                </div>
              </div>

              <!-- Password Input -->
              <div class="mb-3">
                <label for="password" class="form-label">Mật khẩu</label>
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  v-model="form.password"
                  :class="{ 'is-invalid': v$.password.$error }"
                  placeholder="Nhập mật khẩu"
                />
                <div class="invalid-feedback" v-if="v$.password.$error">
                  <span v-for="error in v$.password.$errors" :key="error.$uid">
                    {{ error.$message || 'Vui lòng nhập mật khẩu.' }}
                  </span>
                </div>
              </div>

              <!-- Submit Button -->
              <button type="submit" class="btn btn-warning w-100" :disabled="loading">
                <span v-if="!loading">Đăng nhập</span>
                <span v-else class="spinner-border spinner-border-sm" role="status"></span>
              </button>
            </form>

            <!-- Đăng nhập bằng Google -->
            <div class="mt-3 text-center">
              <button class="btn btn-outline-primary w-100" @click="googleLogin">
                <i class="bi bi-google"></i> Đăng nhập với Google
              </button>
            </div>

            <div class="mt-3 text-center">
              <p class="mb-0">
                Chưa có tài khoản?
                <RouterLink to="/register" class="text-warning">Đăng ký ngay</RouterLink>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
