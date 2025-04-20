<template>
  <div class="row mt-4">
    <div class="col-12">
      <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
          <h5 class="mb-0">Duyệt câu hỏi</h5>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-hover align-middle">
              <thead>
                <tr>
                  <th>Nội dung câu hỏi</th>
                  <th>Người hỏi</th>
                  <th>Thẻ</th>
                  <th>Hành động</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Làm sao để căn giữa một div trong CSS?</td>
                  <td>john_doe</td>
                  <td>
                    <span class="badge bg-secondary me-1">CSS</span>
                    <span class="badge bg-secondary me-1">Frontend</span>
                  </td>
                  <td>
                    <button class="btn btn-sm btn-success me-2"><i class="bi bi-check-circle"></i> Duyệt</button>
                    <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i> Xem</button>
                  </td>
                </tr>
                <tr>
                  <td>Sự khác nhau giữa var, let và const là gì?</td>
                  <td>alice_dev</td>
                  <td>
                    <span class="badge bg-secondary me-1">JavaScript</span>
                  </td>
                  <td>
                    <button class="btn btn-sm btn-success me-2"><i class="bi bi-check-circle"></i> Duyệt</button>
                    <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i> Xem</button>
                  </td>
                </tr>
                <tr>
                  <td>Làm sao để bảo mật REST APIs?</td>
                  <td>mark88</td>
                  <td>
                    <span class="badge bg-secondary me-1">API</span>
                    <span class="badge bg-secondary me-1">Bảo mật</span>
                  </td>
                  <td>
                    <button class="btn btn-sm btn-success me-2"><i class="bi bi-check-circle"></i> Duyệt</button>
                    <button class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i> Xem</button>
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
