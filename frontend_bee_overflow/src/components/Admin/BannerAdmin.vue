<template>
  <div class="wrapper">
    <nav id="sidebar">
      <div class="sidebar-header">
        <div class="d-flex align-items-center justify-content-center py-3">
          <i class="bi bi-shield-lock fs-3 me-2"></i>
          <h4 class="mb-0">Admin Panel</h4>
        </div>
      </div>
      <ul class="list-unstyled components">
        <li class="active">
          <RouterLink to="/checkquestion" class="nav-link">
            <i class="bi bi-patch-check-fill"></i>
            <span>Duyệt câu hỏi</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/documentadmin" class="nav-link">
            <i class="bi bi-file-earmark-text"></i>
            <span>Tài liệu</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/statisticstable" class="nav-link">
            <i class="bi bi-bar-chart-line"></i>
            <span>Thống kê</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/blacklist/users" class="nav-link">
            <i class="bi bi-people-fill me-2"></i>
            <span>Danh sách người dùng đen</span>
          </RouterLink>
        </li>
        <li>
          <RouterLink to="/blacklist/questions" class="nav-link">
            <i class="bi bi-question-circle-fill me-2"></i>
            <span>Danh sách câu hỏi đen</span>
          </RouterLink>
        </li>
      </ul>
      <div class="sidebar-footer">
        <div class="user-info">
          <i class="bi bi-person-circle fs-4"></i>
          <div class="user-details">
            <span class="username">Admin</span>
            <span class="role">Quản trị viên</span>
          </div>
        </div>
      </div>
    </nav>
    <div id="content">
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
          <div class="ms-auto">
            <div class="dropdown">
              <button
                class="btn btn-link dropdown-toggle"
                type="button"
                id="dropdownMenuButton"
                data-bs-toggle="dropdown"
              >
                <i class="bi bi-person-circle"></i> Admin
              </button>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <a class="dropdown-item" href="#"><i class="bi bi-person me-2"></i>Profile</a>
                </li>
                <li>
                  <a class="dropdown-item" href="#"
                    ><i class="bi bi-box-arrow-right me-2"></i>Logout</a
                  >
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
      <!-- Nội dung chính sẽ được đặt ở đây -->
    </div>
  </div>
</template>

<style>
:root {
  --primary: #0d6efd;
  --primary-dark: #0b5ed7;
  --primary-foreground: #ffffff;
  --secondary: #6c757d;
  --secondary-foreground: #ffffff;
  --accent: #e9ecef;
  --accent-foreground: #212529;
  --background: #ffffff;
  --foreground: #212529;
  --card: #ffffff;
  --card-foreground: #212529;
  --border: #dee2e6;
  --input: #dee2e6;
  --ring: #0d6efd;
}

body {
  background-color: var(--accent);
  color: var(--foreground);
}

.wrapper {
  display: flex;
  width: 100%;
  align-items: stretch;
  min-height: 100vh;
}

/* Sidebar mới */
#sidebar {
  min-width: 280px;
  background: var(--primary);
  color: var(--primary-foreground);
  display: flex;
  flex-direction: column;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
}

.sidebar-header {
  padding: 20px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar-header h4 {
  color: white;
  font-weight: 600;
}

#sidebar ul.components {
  padding: 20px 0;
  flex-grow: 1;
}

#sidebar ul li {
  margin: 5px 15px;
  border-radius: 8px;
  overflow: hidden;
}

#sidebar ul li.active {
  background: rgba(255, 255, 255, 0.2);
}

#sidebar ul li a.nav-link {
  padding: 12px 20px;
  color: var(--primary-foreground);
  text-decoration: none;
  display: flex;
  align-items: center;
  transition: all 0.3s;
}

#sidebar ul li a.nav-link:hover {
  background: rgba(255, 255, 255, 0.1);
}

#sidebar ul li a.nav-link i {
  font-size: 1.2rem;
  margin-right: 12px;
  width: 24px;
  text-align: center;
}

#sidebar ul li a.nav-link span {
  font-size: 1rem;
}

/* Footer sidebar */
.sidebar-footer {
  padding: 15px;
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

.user-info {
  display: flex;
  align-items: center;
  color: white;
}

.user-info i {
  margin-right: 12px;
}

.user-details {
  display: flex;
  flex-direction: column;
}

.user-details .username {
  font-weight: 500;
}

.user-details .role {
  font-size: 0.8rem;
  opacity: 0.8;
}

/* Content area */
#content {
  width: 100%;
  min-height: 100vh;
  background: var(--accent);
}

/* Responsive */
@media (max-width: 768px) {
  #sidebar {
    min-width: 70px;
    max-width: 70px;
  }

  #sidebar .sidebar-header h4,
  #sidebar ul li a.nav-link span,
  .user-details {
    display: none;
  }

  #sidebar ul li a.nav-link {
    justify-content: center;
  }

  #sidebar ul li a.nav-link i {
    margin-right: 0;
    font-size: 1.4rem;
  }
}

/* Các style khác giữ nguyên */
.card {
  border: none;
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.bi {
  margin-right: 10px;
}
/* Đảm bảo dropdown nằm trên sidebar */
#sidebar .dropdown-menu {
  position: static;
  float: none;
  box-shadow: none;
  background-color: rgba(255, 255, 255, 0.05);
  padding-left: 20px;
}

#sidebar .dropdown-menu .dropdown-item {
  color: var(--primary-foreground);
  padding: 10px 15px;
}

#sidebar .dropdown-menu .dropdown-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
}
</style>
