<script setup>

</script>

<template>
  <div class="custom-container mt-4">
    <div class="row">
      <div class="col-md-4">
        <div class="card p-4">
          <h4>Hồ sơ người dùng</h4>
          <p><strong>Tên người dùng:</strong> User123</p>
          <p><strong>Uy tín:</strong> 150</p>
          <p><strong>Thành viên từ:</strong> Tháng 1, 2023</p>
          <button class="btn btn-primary">Chỉnh sửa hồ sơ</button>
        </div>
      </div>
      <div class="col-md-8">
        <div class="card p-4">
          <h4>Các câu hỏi đã hỏi</h4>
          <ul>
            <li><a href="#">Cách căn giữa div với Bootstrap 5?</a></li>
            <li><a href="#">Những phương pháp tốt nhất để xác thực REST API?</a></li>
            <li><a href="#">Cách khắc phục lỗi render template trong Django?</a></li>
          </ul>
        </div>
        <div class="card p-4 mt-3">
          <h4>Các câu trả lời đã đưa</h4>
          <ul>
            <li><a href="#">Bạn có thể sử dụng class="d-flex justify-content-center align-items-center"...</a></li>
            <li><a href="#">Hãy thử sử dụng mx-auto trên div với chiều rộng đã định...</a></li>
            <li><a href="#">Kiểm tra cài đặt Django của bạn cho TEMPLATE_DIRS...</a></li>
          </ul>
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
</style>
