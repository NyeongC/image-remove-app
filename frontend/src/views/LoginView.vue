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
        <!-- 탭 -->
        <div class="form-tabs">
          <button
            class="form-tab"
            :class="{ active: activeTab === 'login' }"
            @click="activeTab = 'login'"
          >
            로그인
          </button>
          <button
            class="form-tab"
            :class="{ active: activeTab === 'signup' }"
            @click="activeTab = 'signup'"
          >
            회원가입
          </button>
          <div class="tab-indicator" :class="{ right: activeTab === 'signup' }"></div>
        </div>

        <!-- 로그인 폼 -->
        <Transition name="form-slide" mode="out-in">
          <form v-if="activeTab === 'login'" class="auth-form" key="login" @submit.prevent="handleLogin">
            <div class="form-header">
              <h1>다시 오셨군요!</h1>
              <p>계정에 로그인하세요</p>
            </div>

            <!-- 소셜 로그인 -->
            <div class="social-buttons">
              <button type="button" class="social-btn">
                <svg width="18" height="18" viewBox="0 0 24 24">
                  <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
                  <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
                  <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/>
                  <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
                </svg>
                Google로 로그인
              </button>
            </div>

            <div class="divider">
              <span>또는 이메일로 로그인</span>
            </div>

            <div class="form-group">
              <label>이메일</label>
              <div class="input-wrapper">
                <svg class="input-icon" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/><polyline points="22,6 12,13 2,6"/>
                </svg>
                <input
                  v-model="loginForm.email"
                  type="email"
                  placeholder="hello@example.com"
                  required
                />
              </div>
            </div>

            <div class="form-group">
              <div class="label-row">
                <label>비밀번호</label>
                <a href="#" class="forgot-link">비밀번호 찾기</a>
              </div>
              <div class="input-wrapper">
                <svg class="input-icon" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/><path d="M7 11V7a5 5 0 0110 0v4"/>
                </svg>
                <input
                  v-model="loginForm.password"
                  :type="showPassword ? 'text' : 'password'"
                  placeholder="••••••••"
                  required
                />
                <button type="button" class="toggle-pw" @click="showPassword = !showPassword">
                  <svg v-if="!showPassword" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>
                  </svg>
                  <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/>
                  </svg>
                </button>
              </div>
            </div>

            <button type="submit" class="btn-primary submit-btn" @click="goToApp">
              로그인
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M5 12h14M12 5l7 7-7 7"/>
              </svg>
            </button>
          </form>

          <!-- 회원가입 폼 -->
          <form v-else class="auth-form" key="signup" @submit.prevent="handleSignup">
            <div class="form-header">
              <h1>시작해봐요!</h1>
              <p>무료 계정을 만들어보세요</p>
            </div>

            <div class="social-buttons">
              <button type="button" class="social-btn">
                <svg width="18" height="18" viewBox="0 0 24 24">
                  <path d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" fill="#4285F4"/>
                  <path d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" fill="#34A853"/>
                  <path d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.07H2.18C1.43 8.55 1 10.22 1 12s.43 3.45 1.18 4.93l2.85-2.22.81-.62z" fill="#FBBC05"/>
                  <path d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.07l3.66 2.84c.87-2.6 3.3-4.53 6.16-4.53z" fill="#EA4335"/>
                </svg>
                Google로 가입
              </button>
            </div>

            <div class="divider">
              <span>또는 이메일로 가입</span>
            </div>

            <div class="form-group">
              <label>이름</label>
              <div class="input-wrapper">
                <svg class="input-icon" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M20 21v-2a4 4 0 00-4-4H8a4 4 0 00-4 4v2"/><circle cx="12" cy="7" r="4"/>
                </svg>
                <input v-model="signupForm.name" type="text" placeholder="홍길동" required />
              </div>
            </div>

            <div class="form-group">
              <label>이메일</label>
              <div class="input-wrapper">
                <svg class="input-icon" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <path d="M4 4h16c1.1 0 2 .9 2 2v12c0 1.1-.9 2-2 2H4c-1.1 0-2-.9-2-2V6c0-1.1.9-2 2-2z"/><polyline points="22,6 12,13 2,6"/>
                </svg>
                <input v-model="signupForm.email" type="email" placeholder="hello@example.com" required />
              </div>
            </div>

            <div class="form-group">
              <label>비밀번호</label>
              <div class="input-wrapper">
                <svg class="input-icon" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="3" y="11" width="18" height="11" rx="2" ry="2"/><path d="M7 11V7a5 5 0 0110 0v4"/>
                </svg>
                <input v-model="signupForm.password" :type="showPassword ? 'text' : 'password'" placeholder="8자 이상 입력" required />
                <button type="button" class="toggle-pw" @click="showPassword = !showPassword">
                  <svg v-if="!showPassword" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>
                  </svg>
                  <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/>
                  </svg>
                </button>
              </div>
            </div>

            <p class="terms-text">
              가입하면 <a href="#">서비스 이용약관</a> 및 <a href="#">개인정보처리방침</a>에 동의하는 것으로 간주합니다.
            </p>

            <button type="submit" class="btn-primary submit-btn" @click="goToApp">
              무료 계정 만들기
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
                <path d="M5 12h14M12 5l7 7-7 7"/>
              </svg>
            </button>
          </form>
        </Transition>
      </div>

      <!-- 개발용 네비게이션 -->
      <div class="dev-nav">
        <span class="dev-nav-label">페이지 이동</span>
        <RouterLink to="/">랜딩</RouterLink>
        <RouterLink to="/login">로그인</RouterLink>
        <RouterLink to="/app">배경제거</RouterLink>
        <RouterLink to="/app/history">히스토리</RouterLink>
        <RouterLink to="/app/settings">설정</RouterLink>
        <RouterLink to="/app/lb-test">LB테스트</RouterLink>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { RouterLink, useRouter } from 'vue-router'

const router = useRouter()
const activeTab = ref('login')
const showPassword = ref(false)

const loginForm = ref({ email: '', password: '' })
const signupForm = ref({ name: '', email: '', password: '' })

function handleLogin() {
  router.push('/app')
}

function handleSignup() {
  router.push('/app')
}

function goToApp() {
  router.push('/app')
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
  position: relative;
}

.form-container {
  width: 100%;
  max-width: 420px;
}

/* 탭 */
.form-tabs {
  display: flex;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  padding: 4px;
  margin-bottom: 36px;
  position: relative;
}

.form-tab {
  flex: 1;
  padding: 10px;
  font-size: 14px;
  font-weight: 600;
  color: var(--text-muted);
  background: transparent;
  border: none;
  border-radius: var(--radius-md);
  cursor: pointer;
  transition: color 0.2s;
  position: relative;
  z-index: 1;
}

.form-tab.active {
  color: var(--text-primary);
}

.tab-indicator {
  position: absolute;
  top: 4px;
  left: 4px;
  width: calc(50% - 4px);
  height: calc(100% - 8px);
  background: var(--bg-surface);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  transition: transform 0.25s cubic-bezier(0.4, 0, 0.2, 1);
}

.tab-indicator.right {
  transform: translateX(100%);
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
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

/* 소셜 로그인 */
.social-buttons {
  display: flex;
  gap: 10px;
}

.social-btn {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  padding: 11px 20px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  color: var(--text-primary);
  font-size: 14px;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.2s;
}

.social-btn:hover {
  background: var(--bg-card-hover);
  border-color: var(--border-hover);
}

.divider {
  display: flex;
  align-items: center;
  gap: 16px;
  color: var(--text-muted);
  font-size: 13px;
}

.divider::before,
.divider::after {
  content: '';
  flex: 1;
  height: 1px;
  background: var(--border);
}

/* 폼 그룹 */
.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  font-size: 13px;
  font-weight: 600;
  color: var(--text-secondary);
}

.label-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.forgot-link {
  font-size: 13px;
  color: #a78bfa;
  text-decoration: none;
}

.forgot-link:hover {
  color: #c4b5fd;
}

.input-wrapper {
  position: relative;
  display: flex;
  align-items: center;
}

.input-icon {
  position: absolute;
  left: 14px;
  color: var(--text-muted);
  pointer-events: none;
}

.input-wrapper input {
  width: 100%;
  padding: 12px 14px 12px 42px;
  background: var(--bg-input);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  color: var(--text-primary);
  font-size: 14px;
  transition: all 0.2s;
}

.input-wrapper input::placeholder {
  color: var(--text-muted);
}

.input-wrapper input:focus {
  border-color: var(--border-focus);
  background: rgba(124, 58, 237, 0.06);
  box-shadow: 0 0 0 3px rgba(124, 58, 237, 0.12);
}

.toggle-pw {
  position: absolute;
  right: 14px;
  background: transparent;
  border: none;
  color: var(--text-muted);
  cursor: pointer;
  padding: 0;
  display: flex;
  align-items: center;
}

.toggle-pw:hover {
  color: var(--text-secondary);
}

.terms-text {
  font-size: 12px;
  color: var(--text-muted);
  line-height: 1.7;
}

.terms-text a {
  color: #a78bfa;
  text-decoration: none;
}

.terms-text a:hover {
  text-decoration: underline;
}

.submit-btn {
  width: 100%;
  justify-content: center;
  padding: 14px;
  font-size: 15px;
  margin-top: 4px;
}

/* 폼 전환 애니메이션 */
.form-slide-enter-active,
.form-slide-leave-active {
  transition: all 0.2s ease;
}

.form-slide-enter-from {
  opacity: 0;
  transform: translateX(10px);
}

.form-slide-leave-to {
  opacity: 0;
  transform: translateX(-10px);
}

/* 개발용 네비게이션 */
.dev-nav {
  position: fixed;
  bottom: 24px;
  right: 24px;
  display: flex;
  align-items: center;
  gap: 8px;
  background: rgba(13, 13, 31, 0.95);
  border: 1px solid rgba(124, 58, 237, 0.4);
  border-radius: 100px;
  padding: 8px 16px;
  backdrop-filter: blur(20px);
  box-shadow: var(--shadow-md);
  z-index: 999;
}

.dev-nav-label {
  font-size: 11px;
  color: var(--text-muted);
  font-weight: 600;
  letter-spacing: 0.05em;
  text-transform: uppercase;
  padding-right: 4px;
  border-right: 1px solid var(--border);
  margin-right: 4px;
}

.dev-nav a {
  font-size: 13px;
  font-weight: 500;
  color: var(--text-secondary);
  padding: 4px 10px;
  border-radius: 100px;
  transition: all 0.2s;
}

.dev-nav a:hover,
.dev-nav a.router-link-active {
  color: white;
  background: rgba(124, 58, 237, 0.3);
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
