<template>
  <div class="container my-5">
    <div class="row justify-content-center">
      <div class="col-md-6" style="width: 60%">
        <div class="card shadow-lg">
          <div class="card-body p-4">
            <!-- Logo -->
            <div class="text-center mb-4">
              <img src="../../assets/image/beeoverflow.jpg" alt="Logo" width="80" class="mb-3" />
              <h2 class="fw-bold">Đăng ký tài khoản</h2>
            </div>

            <!-- Form đăng ký -->
            <form @submit.prevent="submitForm" novalidate>
              <!-- Username -->
              <div class="mb-3">
                <label for="username" class="form-label"
                  >Tên đăng nhập <span class="text-danger">*</span></label
                >
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  v-model="form.username"
                  :class="{ 'is-invalid': v$.username.$error }"
                  placeholder="Nhập tên đăng nhập"
                />
                <div class="invalid-feedback" v-if="v$.username.$error">
                  <span v-for="error in v$.username.$errors" :key="error.$uid"
                    >{{ error.$message }}<br
                  /></span>
                </div>
              </div>

              <!-- Full Name -->
              <div class="mb-3">
                <label for="fullname" class="form-label"
                  >Họ và tên <span class="text-danger">*</span></label
                >
                <input
                  type="text"
                  class="form-control"
                  id="fullname"
                  v-model="form.fullname"
                  :class="{ 'is-invalid': v$.fullname.$error }"
                  placeholder="Nhập họ và tên"
                />
                <div class="invalid-feedback" v-if="v$.fullname.$error">
                  <span>Vui lòng nhập họ và tên</span>
                </div>
              </div>

              <!-- Email -->
              <div class="mb-3">
                <label for="email" class="form-label"
                  >Email <span class="text-danger">*</span></label
                >
                <input
                  type="email"
                  class="form-control"
                  id="email"
                  v-model="form.email"
                  :class="{ 'is-invalid': v$.email.$error }"
                  placeholder="Nhập email"
                />
                <div class="invalid-feedback" v-if="v$.email.$error">
                  <span v-for="error in v$.email.$errors" :key="error.$uid"
                    >{{ error.$message }}<br
                  /></span>
                </div>
              </div>

              <!-- Phone -->
              <div class="mb-3">
                <label for="phone" class="form-label"
                  >Số điện thoại <span class="text-danger">*</span></label
                >
                <input
                  type="tel"
                  class="form-control"
                  id="phone"
                  v-model="form.phone"
                  :class="{ 'is-invalid': v$.phone.$error }"
                  placeholder="Nhập số điện thoại"
                />
                <div class="invalid-feedback" v-if="v$.phone.$error">
                  <span v-for="error in v$.phone.$errors" :key="error.$uid"
                    >{{ error.$message }}<br
                  /></span>
                </div>
              </div>

              <!-- Gender -->
              <div class="mb-3">
                <label class="form-label">Giới tính <span class="text-danger">*</span></label>
                <div class="d-flex gap-3">
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="male"
                      value="0"
                      v-model="form.gender"
                    />
                    <label class="form-check-label" for="male">Nam</label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="female"
                      value="1"
                      v-model="form.gender"
                    />
                    <label class="form-check-label" for="female">Nữ</label>
                  </div>
                  <div class="form-check">
                    <input
                      class="form-check-input"
                      type="radio"
                      id="other"
                      value="2"
                      v-model="form.gender"
                    />
                    <label class="form-check-label" for="other">Khác</label>
                  </div>
                </div>
                <div class="invalid-feedback d-block" v-if="v$.gender.$error">
                  <span>Vui lòng chọn giới tính</span>
                </div>
              </div>

              <!-- Password -->
              <div class="mb-3">
                <label for="password" class="form-label"
                  >Mật khẩu <span class="text-danger">*</span></label
                >
                <input
                  type="password"
                  class="form-control"
                  id="password"
                  v-model="form.password"
                  :class="{ 'is-invalid': v$.password.$error }"
                  placeholder="Nhập mật khẩu"
                />
                <div class="invalid-feedback" v-if="v$.password.$error">
                  <span v-for="error in v$.password.$errors" :key="error.$uid"
                    >{{ error.$message }}<br
                  /></span>
                </div>
              </div>

              <!-- Confirm Password -->
              <div class="mb-3">
                <label for="confirmPassword" class="form-label"
                  >Xác nhận mật khẩu <span class="text-danger">*</span></label
                >
                <input
                  type="password"
                  class="form-control"
                  id="confirmPassword"
                  v-model="form.confirmPassword"
                  :class="{ 'is-invalid': v$.confirmPassword.$error }"
                  placeholder="Nhập lại mật khẩu"
                />
                <div class="invalid-feedback" v-if="v$.confirmPassword.$error">
                  <span v-for="error in v$.confirmPassword.$errors" :key="error.$uid"
                    >{{ error.$message }}<br
                  /></span>
                </div>
              </div>

              <!-- Avatar Upload -->
              <div class="mb-4">
                <label class="form-label">Ảnh đại diện</label>
                <div class="d-flex align-items-center gap-3">
                  <div v-if="form.avatar" class="avatar-preview">
                    <img
                      :src="form.avatar"
                      alt="Preview"
                      class="img-thumbnail"
                      width="80"
                      height="80"
                    />
                  </div>
                  <UploadImage @upload-complete="(url) => (form.avatar = url)" />
                </div>
              </div>

              <!-- Submit Button -->
              <button type="submit" class="btn btn-primary w-100 py-2" :disabled="loading">
                <span v-if="!loading">Đăng ký</span>
                <span v-else class="spinner-border spinner-border-sm" role="status"></span>
              </button>
            </form>

            <!-- Social Login -->
            <div class="mt-4 text-center">
              <div class="d-flex align-items-center mb-3">
                <div class="flex-grow-1 border-bottom"></div>
                <span class="px-3 text-muted">Hoặc đăng ký bằng</span>
                <div class="flex-grow-1 border-bottom"></div>
              </div>
              <button
                type="button"
                class="btn btn-outline-danger w-100 mb-2"
                @click="loginWithGoogle"
              >
                <i class="bi bi-google me-2"></i> Google
              </button>
              <button
                type="button"
                class="btn btn-outline-primary w-100"
                @click="loginWithFacebook"
              >
                <i class="bi bi-facebook me-2"></i> Facebook
              </button>
            </div>

            <div class="mt-4 text-center">
              <p class="mb-0">
                Đã có tài khoản?
                <router-link to="/login" class="text-primary fw-bold">Đăng nhập ngay</router-link>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useVuelidate } from '@vuelidate/core'
import {
  required,
  minLength,
  maxLength,
  email,
  numeric,
  sameAs,
  helpers,
} from '@vuelidate/validators'
import UploadImage from './UploadImage.vue'
import axios from 'axios'
import Swal from 'sweetalert2'
import { useRouter } from 'vue-router'

const form = ref({
  username: '',
  fullname: '',
  email: '',
  phone: '',
  gender: '',
  password: '',
  confirmPassword: '',
  avatar: '',
})

const loading = ref(false)

const rules = computed(() => ({
  username: {
    required: helpers.withMessage('Tên đăng nhập là bắt buộc', required),
    minLength: helpers.withMessage('Tối thiểu 4 ký tự', minLength(4)),
    maxLength: helpers.withMessage('Tối đa 20 ký tự', maxLength(20)),
    alphaNum: helpers.withMessage(
      'Chỉ cho phép chữ cái, số và dấu gạch dưới',
      helpers.regex(/^[a-zA-Z0-9_]+$/),
    ),
  },
  fullname: {
    required: helpers.withMessage('Họ tên là bắt buộc', required),
    minLength: helpers.withMessage('Tối thiểu 2 ký tự', minLength(2)),
    maxLength: helpers.withMessage('Tối đa 50 ký tự', maxLength(50)),
  },
  email: {
    required: helpers.withMessage('Email là bắt buộc', required),
    email: helpers.withMessage('Email không hợp lệ', email),
  },
  phone: {
    required: helpers.withMessage('Số điện thoại là bắt buộc', required),
    numeric: helpers.withMessage('Chỉ được nhập số', numeric),
    minLength: helpers.withMessage('Tối thiểu 10 số', minLength(10)),
    maxLength: helpers.withMessage('Tối đa 11 số', maxLength(11)),
  },
  gender: {
    required: helpers.withMessage('Vui lòng chọn giới tính', required),
  },
  password: {
    required: helpers.withMessage('Mật khẩu là bắt buộc', required),
    minLength: helpers.withMessage('Tối thiểu 6 ký tự', minLength(6)),
    strongPassword: helpers.withMessage(
      'Mật khẩu phải chứa ít nhất 1 chữ hoa, 1 chữ thường và 1 số',
      helpers.regex(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).+$/),
    ),
  },
  confirmPassword: {
    required: helpers.withMessage('Vui lòng xác nhận mật khẩu', required),
    sameAsPassword: helpers.withMessage(
      'Mật khẩu không khớp',
      sameAs(computed(() => form.value.password)),
    ),
  },
}))

const v$ = useVuelidate(rules, form)

const router = useRouter()

const submitForm = async () => {
  v$.value.$touch()
  if (!v$.value.$invalid) {
    loading.value = true

    const payload = {
      username: form.value.username,
      fullname: form.value.fullname,
      email: form.value.email,
      phone: form.value.phone,
      gender: form.value.gender,
      password: form.value.password,
      avatar: form.value.avatar,
    }

    try {
      const formData = new FormData()
      Object.entries(payload).forEach(([key, value]) => formData.append(key, value))

      await axios.post('http://localhost:8080/account/register', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })

      Swal.fire({
        icon: 'success',
        title: 'Đăng ký thành công!',
        text: 'Tài khoản của bạn đã được tạo.',
        confirmButtonText: 'Đăng nhập ngay',
      }).then(() => {
        router.push('/login')
      })
    } catch (error: any) {
      Swal.fire({
        icon: 'error',
        title: 'Đăng ký thất bại',
        text: error?.response?.data?.message || 'Đã có lỗi xảy ra. Vui lòng thử lại.',
      })
    } finally {
      loading.value = false
    }
  } else {
    Swal.fire({
      icon: 'warning',
      title: 'Vui lòng kiểm tra lại',
      text: 'Một số trường thông tin chưa hợp lệ.',
    })
  }
}
</script>

<style scoped>
.avatar-preview {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  overflow: hidden;
  border: 1px solid #dee2e6;
}
</style>
