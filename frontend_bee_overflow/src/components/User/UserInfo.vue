<script setup></script>

<template>
  <div class="container py-5">
    <div class="row">
      <div class="col-lg-4">
        <div class="card shadow">
          <div class="card-body text-center">
            <div class="position-relative d-inline-block mb-4">
              <img
                src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde"
                class="rounded-circle user-avatar"
                alt="User Profile"
              />
              <button
                class="btn btn-sm btn-primary edit-avatar-btn"
                aria-label="Edit Profile Picture"
              >
                <i class="bi bi-pencil"></i>
              </button>
            </div>
            <h4 class="mb-3">John Smith</h4>
            <p class="text-muted mb-1">Software Developer</p>
            <p class="text-muted">San Francisco, CA</p>
            <button
              class="btn btn-primary mt-3"
              data-bs-toggle="modal"
              data-bs-target="#editProfileModal"
            >
              Edit Profile
            </button>
          </div>
        </div>
        <div class="card mt-4 shadow">
          <div class="card-body">
            <div class="d-flex justify-content-between align-items-center mb-3">
              <h6 class="mb-0">Personal Details</h6>
            </div>
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Email</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">john.smith@example.com</p>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Phone</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">(123) 456-7890</p>
              </div>
            </div>
            <hr />
            <div class="row">
              <div class="col-sm-3">
                <p class="mb-0">Joined</p>
              </div>
              <div class="col-sm-9">
                <p class="text-muted mb-0">January 15, 2023</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card shadow mb-4">
          <div class="card-header">
            <h5 class="mb-0">Questions Asked</h5>
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>Date</th>
                    <th>Question</th>
                    <th>Status</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Jan 20, 2024</td>
                    <td>How to implement authentication in React?</td>
                    <td><span class="badge bg-success">Answered</span></td>
                  </tr>
                  <tr>
                    <td>Jan 18, 2024</td>
                    <td>Best practices for REST API design?</td>
                    <td><span class="badge bg-warning">Pending</span></td>
                  </tr>
                </tbody>
              </table>
            </div>
            <nav aria-label="Questions pagination">
              <ul class="pagination justify-content-end mb-0">
                <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
              </ul>
            </nav>
          </div>
        </div>
        <div class="card shadow">
          <div class="card-header">
            <h5 class="mb-0">Answers Provided</h5>
          </div>
          <div class="card-body">
            <div class="table-responsive">
              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>Date</th>
                    <th>Question</th>
                    <th>Answer</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Jan 19, 2024</td>
                    <td>Docker vs Kubernetes?</td>
                    <td>Docker is a containerization platform while Kubernetes...</td>
                  </tr>
                  <tr>
                    <td>Jan 17, 2024</td>
                    <td>JavaScript Promise handling?</td>
                    <td>Promises can be handled using async/await...</td>
                  </tr>
                </tbody>
              </table>
            </div>
            <nav aria-label="Answers pagination">
              <ul class="pagination justify-content-end mb-0">
                <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item active"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div
    class="modal fade"
    id="editProfileModal"
    tabindex="-1"
    aria-labelledby="editProfileModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="editProfileModalLabel">Edit Profile</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body">
          <form id="editProfileForm">
            <div class="mb-3">
              <label for="name" class="form-label">Name</label>
              <input type="text" class="form-control" id="name" value="John Smith" required />
            </div>
            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input
                type="email"
                class="form-control"
                id="email"
                value="john.smith@example.com"
                required
              />
            </div>
            <div class="mb-3">
              <label for="phone" class="form-label">Phone</label>
              <input type="tel" class="form-control" id="phone" value="(123) 456-7890" />
            </div>
            <div class="mb-3">
              <label for="location" class="form-label">Location</label>
              <input type="text" class="form-control" id="location" value="San Francisco, CA" />
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
          <button type="submit" form="editProfileForm" class="btn btn-primary">Save Changes</button>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
:root {
  --primary: #0077cc;
  --primary-foreground: #ffffff;
  --secondary: #6c757d;
  --secondary-foreground: #ffffff;
  --accent: #f8f9fa;
  --accent-foreground: #343a40;
  --background: #ffffff;
  --foreground: #212529;
  --card: #ffffff;
  --card-foreground: #212529;
  --border: #dee2e6;
  --input: #ced4da;
  --ring: rgba(0, 119, 204, 0.3);
  --radius: 0.5rem;
  --shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
}

body {
  background-color: var(--accent);
  color: var(--foreground);
  font-family:
    system-ui,
    -apple-system,
    'Segoe UI',
    Roboto,
    'Helvetica Neue',
    Arial;
}

.card {
  background: var(--card);
  color: var(--card-foreground);
  border-radius: var(--radius);
  border: 1px solid var(--border);
}

.user-avatar {
  width: 150px;
  height: 150px;
  object-fit: cover;
  border: 3px solid var(--primary);
}

.edit-avatar-btn {
  position: absolute;
  bottom: 0;
  right: 0;
  padding: 0.5rem;
  border-radius: 50%;
  background: var(--primary);
  color: var(--primary-foreground);
}

.table {
  margin-bottom: 0;
}

.badge {
  font-weight: 500;
}

.modal-content {
  background: var(--background);
  border-radius: var(--radius);
}

.form-control:focus {
  border-color: var(--primary);
  box-shadow: 0 0 0 0.25rem var(--ring);
}

.btn-primary {
  background-color: var(--primary);
  border-color: var(--primary);
  color: var(--primary-foreground);
}

.btn-primary:hover {
  filter: brightness(90%);
}

.pagination .page-link {
  color: var(--primary);
}

.pagination .active .page-link {
  background-color: var(--primary);
  border-color: var(--primary);
  color: var(--primary-foreground);
}

@media (max-width: 768px) {
  .user-avatar {
    width: 120px;
    height: 120px;
  }

  .table-responsive {
    margin: 0 -1rem;
  }
}
</style>
