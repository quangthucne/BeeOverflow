<script setup>

</script>

<template>
  <div class="custom-container mt-4">
    <div class="row">
      <!-- Danh sách câu hỏi -->
      <div class="col-md-8">
        <div class="card shadow p-4">
          <h3 class="mb-3">Câu hỏi nổi bật</h3>
          <div class="list-group">
            <div class="card question-card mb-3">
              <div class="d-flex">
                <div class="stats me-3">
                  <p class="mb-1">0 phiếu</p>
                  <p class="mb-1">0 trả lời</p>
                  <p class="mb-1">2 lượt xem</p>
                </div>
                <div>
                  <RouterLink to="/questions" class="nav-link">
                    <h5>Làm thế nào để căn giữa một div bằng Bootstrap 5?</h5>
                  </RouterLink>
                  <p class="mb-1 text-muted">Được hỏi bởi <strong>User123</strong> - 2 giờ trước</p>
                  <div>
                    <span class="badge bg-secondary me-2">bootstrap</span>
                    <span class="badge bg-secondary">css</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="card question-card mb-3">
              <div class="d-flex">
                <div class="stats me-3">
                  <p class="mb-1">3 phiếu</p>
                  <p class="mb-1">1 trả lời</p>
                  <p class="mb-1">10 lượt xem</p>
                </div>
                <div>
                  <h5>Sự khác nhau giữa let và var trong JavaScript là gì?</h5>
                  <p class="mb-1 text-muted">Được hỏi bởi <strong>DevGuru</strong> - 5 giờ trước</p>
                  <div>
                    <span class="badge bg-secondary me-2">javascript</span>
                    <span class="badge bg-secondary">es6</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="card question-card mb-3">
              <div class="d-flex">
                <div class="stats me-3">
                  <p class="mb-1">5 phiếu</p>
                  <p class="mb-1">2 trả lời</p>
                  <p class="mb-1">25 lượt xem</p>
                </div>
                <div>
                  <h5>Những thực hành tốt nhất cho xác thực REST API?</h5>
                  <p class="mb-1 text-muted">Được hỏi bởi <strong>APIExpert</strong> - 1 ngày trước</p>
                  <div>
                    <span class="badge bg-secondary me-2">api</span>
                    <span class="badge bg-secondary">authentication</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Thanh bên -->
      <div class="col-md-4">
        <div class="card shadow p-4">
          <h4 class="mb-3">Thẻ phổ biến</h4>
          <div class="d-flex flex-wrap">
            <span class="badge bg-primary m-1 p-2">JavaScript</span>
            <span class="badge bg-success m-1 p-2">Python</span>
            <span class="badge bg-danger m-1 p-2">Java</span>
            <span class="badge bg-warning text-dark m-1 p-2">C#</span>
            <span class="badge bg-info text-dark m-1 p-2">HTML</span>
            <span class="badge bg-dark m-1 p-2">CSS</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
  .custom-container {
    width: 80%;
    margin: 0 auto;
  }
  body {
    background-color: #f8f9fa;

  }
  .question-card {
    transition: transform 0.2s;
    border-left: 5px solid gray;
    padding: 15px;
  }
  .question-card:hover {
    transform: scale(1.02);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  .badge {
    cursor: pointer;
  }
  .badge:hover {
    opacity: 0.8;
  }
  .stats {
    width: 80px;
    text-align: center;
  }
</style>
