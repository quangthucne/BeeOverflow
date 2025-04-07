<script setup>

</script>

<template>
<div class="custom-container my-4">
    <div class="row mb-3">
        <div class="col-md-6">
            <input type="text" class="form-control" placeholder="Lọc theo tên thẻ">
        </div>
        <div class="col-md-6 text-end">
            <button class="btn btn-outline-primary me-2">Phổ biến</button>
            <button class="btn btn-outline-secondary me-2">Tên</button>
            <button class="btn btn-outline-dark">Mới nhất</button>
        </div>
    </div>

    <div class="row">
        <!-- Thẻ mẫu -->
        <div class="col-md-4 mb-3">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">JavaScript</h5>
                    <p class="card-text">Các câu hỏi về lập trình JavaScript trong chuẩn ECMAScript...</p>
                    <p class="text-muted">2.397.040 câu hỏi • 63 hỏi hôm nay</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Python</h5>
                    <p class="card-text">Python là ngôn ngữ lập trình đa mục đích, kiểu động...</p>
                    <p class="text-muted">2.223.330 câu hỏi • 122 hỏi hôm nay</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Java</h5>
                    <p class="card-text">Java là ngôn ngữ lập trình hướng đối tượng cấp cao...</p>
                    <p class="text-muted">1.924.459 câu hỏi • 56 hỏi hôm nay</p>
                </div>
            </div>
        </div>

        <!-- Có thể thêm nhiều thẻ khác -->
    </div>
</div>
</template>

<style>
  .custom-container {
    width: 80%;
    margin: 0 auto;
  }
</style>
