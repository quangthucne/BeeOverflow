<script setup>
</script>

<template>
  <div class="row mt-4">
    <div class="col-12">
      <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
          <h5 class="mb-0">Danh sách Tài liệu</h5>
          <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addDocumentModal">
            <i class="bi bi-plus-lg"></i> Thêm Tài liệu
          </button>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>Tên tài liệu</th>
                  <th>Ngôn ngữ</th>
                  <th>Giá</th>
                  <th>Ngày đăng</th>
                  <th>Hành động</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Tài liệu kỹ thuật</td>
                  <td>Tiếng Anh</td>
                  <td>$29.99</td>
                  <td>2024-01-15</td>
                  <td>
                    <button class="btn btn-sm btn-info me-2"><i class="bi bi-pencil"></i></button>
                    <button class="btn btn-sm btn-danger"><i class="bi bi-trash"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>Hướng dẫn sử dụng</td>
                  <td>Tiếng Tây Ban Nha</td>
                  <td>$19.99</td>
                  <td>2024-01-14</td>
                  <td>
                    <button class="btn btn-sm btn-info me-2"><i class="bi bi-pencil"></i></button>
                    <button class="btn btn-sm btn-danger"><i class="bi bi-trash"></i></button>
                  </td>
                </tr>
                <tr>
                  <td>Tài liệu API</td>
                  <td>Tiếng Pháp</td>
                  <td>$39.99</td>
                  <td>2024-01-13</td>
                  <td>
                    <button class="btn btn-sm btn-info me-2"><i class="bi bi-pencil"></i></button>
                    <button class="btn btn-sm btn-danger"><i class="bi bi-trash"></i></button>
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
:root {
  --primary: #0d6efd;
  --primary-foreground: #ffffff;
  --accent: #e9ecef;
  --accent-foreground: #212529;
  --foreground: #212529;
}

body {
  color: var(--foreground);
}

.card {
  border: none;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.card-header {
  padding: 1rem 1.25rem;
  background-color: #fff;
  border-bottom: 1px solid rgba(0, 0, 0, 0.125);
}

.card-header h5 {
  margin-bottom: 0;
  font-weight: bold;
}

.btn {
  display: inline-block;
  font-weight: 400;
  text-align: center;
  text-decoration: none;
  vertical-align: middle;
  cursor: pointer;
  border: 1px solid transparent;
  padding: 0.375rem 0.75rem;
  border-radius: 0.375rem;
}

.btn-primary {
  background-color: var(--primary);
  color: var(--primary-foreground);
}

.btn-info {
  background-color: #0dcaf0;
  color: #fff;
}

.btn-danger {
  background-color: #dc3545;
  color: #fff;
}

.btn-sm {
  padding: 0.25rem 0.5rem;
  font-size: 0.875rem;
  line-height: 1.5;
  border-radius: 0.2rem;
}

.table {
  width: 100%;
  margin-bottom: 0;
  color: var(--foreground);
  border-collapse: collapse;
}

.table thead th {
  border-top: none;
  background-color: var(--accent);
  color: var(--accent-foreground);
  font-weight: 600;
  padding: 0.75rem;
  text-align: left;
}

.table tbody td {
  vertical-align: middle;
  padding: 0.75rem;
}

.table-hover tbody tr:hover {
  background-color: rgba(0, 0, 0, 0.05);
}

.me-2 {
  margin-right: 0.5rem;
}
</style>
