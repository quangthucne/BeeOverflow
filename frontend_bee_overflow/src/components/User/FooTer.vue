<script setup></script>

<template>
  <footer class="footer-section py-5 navbar-dark bg-dark">
    <div class="container">
      <div class="row mb-4">
        <div class="col-12 text-center">
          <div class="d-flex align-items-center justify-content-center mb-3">
            <img
              src="../../assets/image/beeoverflow.jpg"
              alt="Beeoverflow Logo"
              class="footer-logo"
            />
            <RouterLink to="/" class="nav-link d-flex align-items-center">
              <span class="brand-text text-white fw-bold fs-4">Bee</span>
              <span class="brand-text text-warning fw-bold fs-4">overflow</span>
            </RouterLink>
          </div>
        </div>
      </div>
      <div class="row g-4">
        <div class="col-lg-3 col-md-6">
          <h5 class="footer-title">Platform Navigation</h5>
          <ul class="list-unstyled footer-links">
            <li>
              <a href="#"><i class="bi bi-house-door me-2"></i>Home</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-clock-history me-2"></i>Recent Questions</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-people me-2"></i>Top Contributors</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-question-circle me-2"></i>Ask a Question</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-grid me-2"></i>Categories</a>
            </li>
          </ul>
        </div>
        <div class="col-lg-3 col-md-6">
          <h5 class="footer-title">Community</h5>
          <ul class="list-unstyled footer-links">
            <li>
              <a href="#"><i class="bi bi-info-circle me-2"></i>About Beeoverflow</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-file-text me-2"></i>Community Guidelines</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-question-circle me-2"></i>Help Center</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-shield-check me-2"></i>Terms of Service</a>
            </li>
            <li>
              <a href="#"><i class="bi bi-lock me-2"></i>Privacy Policy</a>
            </li>
          </ul>
        </div>
        <div class="col-lg-3 col-md-6">
          <h5 class="footer-title">Connect & Support</h5>
          <ul class="list-unstyled footer-links">
            <li>
              <a href="mailto:support@beeoverflow.com"
                ><i class="bi bi-envelope me-2"></i>support@beeoverflow.com</a
              >
            </li>
            <li>
              <a href="tel:+1234567890"><i class="bi bi-telephone me-2"></i>+1 (234) 567-890</a>
            </li>
          </ul>
          <div class="social-links mt-3">
            <a href="#" class="social-icon"><i class="bi bi-facebook"></i></a>
            <a href="#" class="social-icon"><i class="bi bi-twitter"></i></a>
            <a href="#" class="social-icon"><i class="bi bi-linkedin"></i></a>
            <a href="#" class="social-icon"><i class="bi bi-instagram"></i></a>
          </div>
        </div>
        <div class="col-lg-3 col-md-6">
          <h5 class="footer-title">Newsletter</h5>
          <p class="newsletter-text">Stay updated with the latest Q&As and community highlights!</p>
          <form class="newsletter-form">
            <div class="input-group mb-3">
              <input
                type="email"
                class="form-control"
                placeholder="Enter your email"
                aria-label="Email"
              />
              <button class="btn btn-warning" type="submit">Subscribe</button>
            </div>
          </form>
        </div>
      </div>
      <hr class="footer-divider" />
      <div class="row">
        <div class="col-12 text-center">
          <p class="copyright-text mb-0">
            © 2023 Beeoverflow. All Rights Reserved.
            <span class="trademark">Beeoverflow™ is a registered trademark.</span>
          </p>
        </div>
      </div>
    </div>
  </footer>
</template>

<style>
:root {
  --primary: #fdb515;
  --primary-foreground: #000000;
  --secondary: #4a4a4a;
  --secondary-foreground: #ffffff;
  --accent: #f5a623;
  --accent-foreground: #000000;
  --background: #ffffff;
  --foreground: #ffffff;
  --card: #2a2a2a;
  --card-foreground: #ffffff;
  --border: #333333;
  --input: #2a2a2a;
  --ring: #fdb515;
}

body {
  font-family: 'Inter', sans-serif;
  background-color: var(--background);
  color: var(--foreground);
}

.footer-section {
  background-color: var(--card);
  color: var(--card-foreground);
}

.footer-title {
  color: #fdb515; /* vàng sáng */
  font-weight: 600;
  margin-bottom: 1.5rem;
  font-size: 1.25rem;
}


.footer-links li {
  margin-bottom: 0.75rem;
}

.footer-links a {
  color: white;
  text-decoration: none;
  transition: color 0.3s ease;
  display: inline-flex;
  align-items: center;
}

.footer-links a:hover {
  color: #fdb515; /* màu vàng khi hover */
}

.social-links {
  display: flex;
  gap: 1rem;
}

.social-icon {
  color: white;  /* Mặc định màu trắng */
  font-size: 1.5rem;
  transition:
    color 0.3s ease,
    transform 0.3s ease;
}

.social-icon:hover {
  color: #fdb515; /* Màu vàng khi hover */
  transform: translateY(-2px);
}


.newsletter-text {
  color: var(--foreground);
  margin-bottom: 1rem;
}

.newsletter-form .form-control {
  background-color: var(--input);
  border: 1px solid var(--border);
  color: var(--foreground);
  padding: 0.75rem;
}

.newsletter-form .form-control:focus {
  box-shadow: 0 0 0 2px var(--ring);
  border-color: var(--primary);
}

.btn-subscribe {
  background-color: var(--primary);
  color: var(--primary-foreground);
  border: none;
  padding: 0.75rem 1.5rem;
  transition: background-color 0.3s ease;
}

.btn-subscribe:hover {
  background-color: var(--accent);
  color: var(--accent-foreground);
}

.footer-divider {
  border-color: var(--border);
  margin: 2rem 0;
}

.copyright-text {
  color: var(--foreground);
  font-size: 0.875rem;
}

.trademark {
  color: var(--muted-foreground);
  margin-left: 0.5rem;
}

.footer-logo {
  width: 50px;
  height: 50px;
  border-radius: 8px;
  object-fit: cover;
}

.brand-text {
  color: var(--primary);
  font-size: 1.8rem;
  font-weight: 700;
  letter-spacing: 0.5px;
}

@media (max-width: 768px) {
  .brand-text {
    font-size: 1.5rem;
  }

  .footer-logo {
    width: 40px;
    height: 40px;
  }

  .footer-title {
    margin-bottom: 1rem;
    font-size: 1.1rem;
  }

  .social-links {
    justify-content: center;
  }

  .copyright-text {
    font-size: 0.75rem;
  }

  .footer-links a {
    font-size: 0.9rem;
  }
}

</style>
