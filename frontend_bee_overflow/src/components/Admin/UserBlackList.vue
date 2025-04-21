<script setup>

</script>

<template>
    <div class="row mt-4">
      <div class="col-12">
          <div class="card">
              <div class="card-header d-flex justify-content-between align-items-center">
                  <h5 class="mb-0">Danh sách người dùng bị chặn</h5>
              </div>
              <div class="card-body">
                  <div class="table-responsive">
                      <table class="table table-hover align-middle">
                          <thead>
                              <tr>
                                  <th>Thông tin người dùng</th>
                                  <th>Vi phạm</th>
                                  <th>Hành động</th>
                              </tr>
                          </thead>
                          <tbody>
                              <tr>
                                  <td>
                                      <div class="d-flex align-items-center">
                                          <img src="https://via.placeholder.com/40" class="rounded-circle me-2" alt="Avatar">
                                          <div>
                                              <strong>minhtrung01</strong><br>
                                              <small>minhtrung@gmail.com</small>
                                          </div>
                                      </div>
                                  </td>
                                  <td>Spam nhiều bình luận không liên quan trong tài liệu</td>
                                  <td>
                                      <button class="btn btn-sm btn-success me-2"><i class="bi bi-check-circle"></i> Duyệt</button>
                                      <button class="btn btn-sm btn-outline-primary me-2"><i class="bi bi-eye"></i> Xem</button>
                                      <button class="btn btn-sm btn-danger"><i class="bi bi-trash"></i> Xóa</button>
                                  </td>
                              </tr>
                              <tr>
                                  <td>
                                      <div class="d-flex align-items-center">
                                          <img src="https://via.placeholder.com/40" class="rounded-circle me-2" alt="Avatar">
                                          <div>
                                              <strong>hoangdev</strong><br>
                                              <small>hoangdev@example.com</small>
                                          </div>
                                      </div>
                                  </td>
                                  <td>Sử dụng ngôn từ xúc phạm trong bình luận công khai</td>
                                  <td>
                                      <button class="btn btn-sm btn-success me-2"><i class="bi bi-check-circle"></i> Duyệt</button>
                                      <button class="btn btn-sm btn-outline-primary me-2"><i class="bi bi-eye"></i> Xem</button>
                                      <button class="btn btn-sm btn-danger"><i class="bi bi-trash"></i> Xóa</button>
                                  </td>
                              </tr>
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
      </div>
  </div>

</template>

<style>

</style>
