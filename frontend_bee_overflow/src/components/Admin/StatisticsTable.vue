<template>
  <div class="container-fluid p-4">
    <div class="row">
      <div class="col-md-3 mb-4">
        <div class="card bg-info text-white">
          <div class="card-body">
            <h5 class="card-title"><i class="bi bi-eye"></i> Lượt xem câu hỏi</h5>
            <h2>2,543</h2>
            <p class="mb-0 small">↑ 12.5% trong tuần này</p>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card bg-danger text-white">
          <div class="card-body">
            <h5 class="card-title"><i class="bi bi-exclamation-triangle"></i> Báo cáo spam</h5>
            <h2>47</h2>
            <p class="mb-0 small">↓ 3% trong tuần này</p>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card bg-success text-white">
          <div class="card-body">
            <h5 class="card-title"><i class="bi bi-cart-check"></i> Tài liệu đã bán</h5>
            <h2>186</h2>
            <p class="mb-0 small">↑ 8.2% trong tuần này</p>
          </div>
        </div>
      </div>
      <div class="col-md-3 mb-4">
        <div class="card bg-primary text-white">
          <div class="card-body">
            <h5 class="card-title"><i class="bi bi-chat-dots"></i> Bình luận</h5>
            <h2>892</h2>
            <p class="mb-0 small">↑ 15.3% trong tuần này</p>
          </div>
        </div>
      </div>
    </div>

    <div class="row mt-4">
      <div class="col-md-6 mb-4">
        <div class="card">
          <div class="card-header">
            <h5 class="mb-0">Câu hỏi được bình chọn nhiều nhất</h5>
          </div>
          <div class="card-body">
            <div class="list-group">
              <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                Cách triển khai xác thực trong React?
                <span class="badge bg-primary rounded-pill">245 phiếu</span>
              </a>
              <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                Các phương pháp tốt nhất cho bảo mật API
                <span class="badge bg-primary rounded-pill">198 phiếu</span>
              </a>
              <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                So sánh Docker và Kubernetes
                <span class="badge bg-primary rounded-pill">167 phiếu</span>
              </a>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-6 mb-4">
        <div class="card">
          <div class="card-header">
            <h5 class="mb-0">Câu hỏi có nhiều bình luận nhất</h5>
          </div>
          <div class="card-body">
            <div class="list-group">
              <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                Hướng dẫn async/await trong JavaScript
                <span class="badge bg-info rounded-pill">89 bình luận</span>
              </a>
              <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                So sánh Python và JavaScript
                <span class="badge bg-info rounded-pill">76 bình luận</span>
              </a>
              <a href="#" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                Các phương pháp hay khi dùng MongoDB
                <span class="badge bg-info rounded-pill">65 bình luận</span>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
