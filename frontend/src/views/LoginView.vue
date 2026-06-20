<template>
  <div class="login-page">
    <!-- 좌측 브랜딩 패널 -->
    <div class="brand-panel">
      <div class="brand-bg">
        <div class="brand-blob blob-1"></div>
        <div class="brand-blob blob-2"></div>
        <div class="grid-lines"></div>
      </div>

      <RouterLink to="/" class="brand-logo">
        <div class="logo-icon">✦</div>
        <span>Erasify</span>
      </RouterLink>

      <div class="brand-content">
        <h2>AI 기반 배경 제거<br />서비스</h2>
        <p>단 몇 초 만에 전문가 수준의 배경 제거를 경험하세요.</p>

        <div class="brand-steps">
          <div class="step">
            <div class="step-icon">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
              </svg>
            </div>
            <div>
              <strong>이미지 업로드</strong>
              <span>JPG, PNG, WEBP 모두 가능</span>
            </div>
          </div>
          <div class="step">
            <div class="step-icon">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z"/>
              </svg>
            </div>
            <div>
              <strong>AI 자동 처리</strong>
              <span>0.5초 이내 배경 제거</span>
            </div>
          </div>
          <div class="step">
            <div class="step-icon">
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
                <path d="M12 15v6m-3-3h6" stroke-width="0"/>
                <polyline points="8 12 12 16 16 12"/><line x1="12" y1="3" x2="12" y2="16"/>
              </svg>
            </div>
            <div>
              <strong>PNG 다운로드</strong>
              <span>투명 배경 고화질 저장</span>
            </div>
          </div>
        </div>
      </div>

      <div class="brand-footer">
        <p>© 2026 Erasify. All rights reserved.</p>
      </div>
    </div>

    <!-- 우측 폼 패널 -->
    <div class="form-panel">
      <div class="form-container">
        <div class="form-header">
          <h1>시작하기</h1>
          <p>Google 계정으로 로그인하세요</p>
        </div>

        <button class="social-btn" @click="loginWithGoogle">
          <svg width="18" height="18" viewBox="0 0 24 24">
            <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
            <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
            <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/>
            <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
          </svg>
          Google로 계속하기
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
function loginWithGoogle() {
  window.location.href = '/oauth2/authorization/google'
}
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  display: grid;
  grid-template-columns: 480px 1fr;
}

/* 브랜딩 패널 */
.brand-panel {
  position: relative;
  background: var(--bg-surface);
  display: flex;
  flex-direction: column;
  padding: 40px;
  overflow: hidden;
  border-right: 1px solid var(--border);
}

.brand-bg {
  position: absolute;
  inset: 0;
  pointer-events: none;
}

.brand-blob {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.2;
}

.brand-blob.blob-1 {
  width: 400px;
  height: 400px;
  background: radial-gradient(circle, #7c3aed, transparent);
  top: -100px;
  right: -100px;
}

.brand-blob.blob-2 {
  width: 300px;
  height: 300px;
  background: radial-gradient(circle, #4338ca, transparent);
  bottom: 0;
  left: -50px;
}

.grid-lines {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(255,255,255,0.03) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255,255,255,0.03) 1px, transparent 1px);
  background-size: 40px 40px;
}

.brand-logo {
  position: relative;
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 22px;
  font-weight: 700;
  color: var(--text-primary);
  text-decoration: none;
}

.logo-icon {
  width: 36px;
  height: 36px;
  background: var(--gradient);
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  color: white;
}

.brand-content {
  position: relative;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.brand-content h2 {
  font-size: 36px;
  font-weight: 700;
  line-height: 1.3;
  letter-spacing: -0.02em;
  color: var(--text-primary);
  margin-bottom: 16px;
}

.brand-content > p {
  font-size: 16px;
  color: var(--text-secondary);
  margin-bottom: 48px;
}

.brand-steps {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.step {
  display: flex;
  align-items: center;
  gap: 16px;
}

.step-icon {
  width: 40px;
  height: 40px;
  background: rgba(124, 58, 237, 0.15);
  border: 1px solid rgba(124, 58, 237, 0.25);
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #a78bfa;
  flex-shrink: 0;
}

.step > div {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.step strong {
  font-size: 14px;
  font-weight: 600;
  color: var(--text-primary);
}

.step span {
  font-size: 13px;
  color: var(--text-muted);
}

.brand-footer {
  position: relative;
}

.brand-footer p {
  font-size: 12px;
  color: var(--text-muted);
}

/* 폼 패널 */
.form-panel {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px;
  background: var(--bg-base);
}

.form-container {
  width: 100%;
  max-width: 420px;
  display: flex;
  flex-direction: column;
  gap: 28px;
}

.form-header h1 {
  font-size: 28px;
  font-weight: 700;
  letter-spacing: -0.02em;
  color: var(--text-primary);
  margin-bottom: 8px;
}

.form-header p {
  font-size: 15px;
  color: var(--text-secondary);
}

.social-btn {
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  padding: 14px 20px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  color: var(--text-primary);
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
}

.social-btn:hover {
  background: var(--bg-card-hover);
  border-color: var(--border-hover);
}

@media (max-width: 900px) {
  .login-page {
    grid-template-columns: 1fr;
  }

  .brand-panel {
    display: none;
  }
}
</style>
