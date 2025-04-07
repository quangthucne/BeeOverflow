<script setup>

</script>

<template>
  <div class="custom-container my-4">
    <h2>Người dùng</h2>
    <div class="mb-3">
        <input type="text" class="form-control" placeholder="Lọc theo người dùng">
    </div>

    <div class="d-flex justify-content-end mb-3">
        <button class="btn btn-outline-primary">Điểm uy tín</button>
        <button class="btn btn-outline-secondary ms-2">Người dùng mới</button>
        <button class="btn btn-outline-dark ms-2">Bình chọn nhiều</button>
        <button class="btn btn-outline-info ms-2">Chỉnh sửa nhiều</button>
    </div>

    <div class="row">
        <div class="col-md-6">
            <div class="user-card">
                <div class="user-avatar"></div>
                <div class="user-info">
                    <div class="user-name">VonC</div>
                    <div class="user-location">Pháp</div>
                    <div class="user-tags">git, github, docker</div>
                </div>
                <span class="badge bg-primary">790</span>
            </div>

            <div class="user-card">
                <div class="user-avatar"></div>
                <div class="user-info">
                    <div class="user-name">Jon Skeet</div>
                    <div class="user-location">Reading, Vương quốc Anh</div>
                    <div class="user-tags">c#, java, .net</div>
                </div>
                <span class="badge bg-primary">490</span>
            </div>

            <div class="user-card">
                <div class="user-avatar"></div>
                <div class="user-info">
                    <div class="user-name">Remy Lebeau</div>
                    <div class="user-location">Hoa Kỳ</div>
                    <div class="user-tags">delphi, c++, winapi</div>
                </div>
                <span class="badge bg-primary">333</span>
            </div>
        </div>

        <div class="col-md-6">
            <div class="user-card">
                <div class="user-avatar"></div>
                <div class="user-info">
                    <div class="user-name">Raghavendra N</div>
                    <div class="user-location">Mysore, Ấn Độ</div>
                    <div class="user-tags">javascript, php, jquery</div>
                </div>
                <span class="badge bg-primary">610</span>
            </div>

            <div class="user-card">
                <div class="user-avatar"></div>
                <div class="user-info">
                    <div class="user-name">Basil Bourque</div>
                    <div class="user-location">Java, ngày, thời gian</div>
                    <div class="user-tags">java, date, datetime</div>
                </div>
                <span class="badge bg-primary">274</span>
            </div>

            <div class="user-card">
                <div class="user-avatar"></div>
                <div class="user-info">
                    <div class="user-name">Mark</div>
                    <div class="user-location">Hoa Kỳ</div>
                    <div class="user-tags">vscode-extensions, snippets</div>
                </div>
                <span class="badge bg-primary">235</span>
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

  .user-card {
      display: flex;
      align-items: center;
      gap: 10px;
      padding: 10px;
      border-bottom: 1px solid #ddd;
  }
  .user-avatar {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      background-color: #ddd;
  }
  .user-info {
      flex-grow: 1;
  }
  .user-name {
      font-weight: bold;
      margin-bottom: 2px;
  }
  .user-location {
      font-size: 0.9em;
      color: gray;
  }
  .user-tags {
      font-size: 0.85em;
      color: #555;
  }
</style>
