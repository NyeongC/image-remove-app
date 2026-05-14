<template>
  <div class="landing">
    <!-- 네비게이션 -->
    <nav class="nav">
      <div class="nav-inner">
        <div class="nav-logo">
          <div class="logo-icon">✦</div>
          <span>Erasify</span>
        </div>
        <div class="nav-links">
          <RouterLink to="/app" class="nav-link">기능</RouterLink>
          <RouterLink to="/app/history" class="nav-link">갤러리</RouterLink>
          <a href="#pricing" class="nav-link">요금제</a>
          <RouterLink to="/app/lb-test" class="nav-link nav-link-dev">
            <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>
            </svg>
            LB 테스트
          </RouterLink>
        </div>
        <div class="nav-actions">
          <RouterLink to="/login" class="btn-ghost">로그인</RouterLink>
          <RouterLink to="/login" class="btn-primary">무료 시작</RouterLink>
        </div>
      </div>
    </nav>

    <!-- 히어로 섹션 -->
    <section class="hero">
      <div class="hero-bg">
        <div class="blob blob-1"></div>
        <div class="blob blob-2"></div>
        <div class="blob blob-3"></div>
        <div class="grid-overlay"></div>
      </div>

      <div class="hero-content">
        <div class="hero-badge">
          <span class="badge-dot"></span>
          AI 기반 배경 제거
        </div>
        <h1 class="hero-title">
          배경을 지워라,<br />
          <span class="gradient-text">순간을 남겨라</span>
        </h1>
        <p class="hero-desc">
          단 몇 초 만에 이미지 배경을 완벽하게 제거하세요.<br />
          AI가 자동으로 피사체를 인식하고 깔끔하게 처리합니다.
        </p>
        <div class="hero-actions">
          <RouterLink to="/login" class="btn-primary btn-lg">
            <span>지금 무료로 시작</span>
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <path d="M5 12h14M12 5l7 7-7 7"/>
            </svg>
          </RouterLink>
          <RouterLink to="/app" class="btn-secondary btn-lg">데모 보기</RouterLink>
        </div>
        <div class="hero-stats">
          <div class="stat">
            <strong>10M+</strong>
            <span>처리된 이미지</span>
          </div>
          <div class="stat-divider"></div>
          <div class="stat">
            <strong>0.5초</strong>
            <span>평균 처리 속도</span>
          </div>
          <div class="stat-divider"></div>
          <div class="stat">
            <strong>99.9%</strong>
            <span>정확도</span>
          </div>
        </div>
      </div>

      <!-- 데모 카드 -->
      <div class="hero-demo">
        <div class="demo-card">
          <div class="demo-header">
            <div class="demo-dots">
              <span></span><span></span><span></span>
            </div>
            <span class="demo-title">배경 제거 결과</span>
          </div>
          <div class="demo-images">
            <div class="demo-image before">
              <div class="demo-img-placeholder">
                <div class="demo-person"></div>
                <div class="demo-bg-pattern"></div>
              </div>
              <span class="demo-label">원본</span>
            </div>
            <div class="demo-arrow">
              <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M5 12h14M12 5l7 7-7 7"/>
              </svg>
            </div>
            <div class="demo-image after">
              <div class="demo-img-placeholder transparent">
                <div class="demo-person"></div>
                <div class="checkerboard"></div>
              </div>
              <span class="demo-label">결과</span>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 기능 섹션 -->
    <section class="features">
      <div class="section-inner">
        <div class="section-badge">기능</div>
        <h2 class="section-title">왜 Erasify인가요?</h2>
        <p class="section-desc">빠르고 정확하게, 누구나 쉽게 사용할 수 있습니다</p>

        <div class="features-grid">
          <div class="feature-card">
            <div class="feature-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z"/>
              </svg>
            </div>
            <h3>초고속 처리</h3>
            <p>업로드 즉시 AI가 분석하여 0.5초 이내에 배경을 제거합니다</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/><path d="M12 8v4l3 3"/>
              </svg>
            </div>
            <h3>히스토리 보관</h3>
            <p>처리된 모든 이미지를 클라우드에 안전하게 보관하고 언제든 다시 찾을 수 있습니다</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="3" y="3" width="18" height="18" rx="2"/><path d="M3 9h18M9 21V9"/>
              </svg>
            </div>
            <h3>전/후 비교</h3>
            <p>슬라이더로 원본과 결과를 나란히 비교하며 확인하세요</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z"/>
              </svg>
            </div>
            <h3>안전한 저장</h3>
            <p>AWS S3에 암호화하여 저장. 내 이미지는 오직 나만 볼 수 있습니다</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
              </svg>
            </div>
            <h3>고화질 다운로드</h3>
            <p>PNG 투명 배경으로 원본 해상도 그대로 다운로드</p>
          </div>
          <div class="feature-card">
            <div class="feature-icon">
              <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14M14 8h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
              </svg>
            </div>
            <h3>다양한 포맷</h3>
            <p>JPG, PNG, WEBP 등 모든 이미지 포맷을 지원합니다</p>
          </div>
        </div>
      </div>
    </section>

    <!-- 요금제 섹션 -->
    <section class="pricing" id="pricing">
      <div class="section-inner">
        <div class="section-badge">요금제</div>
        <h2 class="section-title">합리적인 가격</h2>
        <p class="section-desc">필요에 맞게 선택하세요</p>

        <div class="pricing-grid">
          <div class="pricing-card">
            <div class="pricing-name">무료</div>
            <div class="pricing-price">
              <strong>₩0</strong>
              <span>/월</span>
            </div>
            <ul class="pricing-features">
              <li><span class="check">✓</span> 월 10회 무료</li>
              <li><span class="check">✓</span> 최대 5MB 이미지</li>
              <li><span class="check">✓</span> 표준 화질</li>
              <li class="disabled"><span>✗</span> 히스토리 저장</li>
              <li class="disabled"><span>✗</span> 우선 처리</li>
            </ul>
            <RouterLink to="/login" class="btn-secondary w-full">시작하기</RouterLink>
          </div>

          <div class="pricing-card featured">
            <div class="pricing-badge">인기</div>
            <div class="pricing-name">Pro</div>
            <div class="pricing-price">
              <strong>₩9,900</strong>
              <span>/월</span>
            </div>
            <ul class="pricing-features">
              <li><span class="check">✓</span> 월 500회</li>
              <li><span class="check">✓</span> 최대 25MB 이미지</li>
              <li><span class="check">✓</span> 고화질 출력</li>
              <li><span class="check">✓</span> 무제한 히스토리</li>
              <li><span class="check">✓</span> 우선 처리</li>
            </ul>
            <RouterLink to="/login" class="btn-primary w-full">Pro 시작하기</RouterLink>
          </div>

          <div class="pricing-card">
            <div class="pricing-name">Enterprise</div>
            <div class="pricing-price">
              <strong>문의</strong>
            </div>
            <ul class="pricing-features">
              <li><span class="check">✓</span> 무제한 처리</li>
              <li><span class="check">✓</span> 무제한 용량</li>
              <li><span class="check">✓</span> API 접근</li>
              <li><span class="check">✓</span> 전용 서버</li>
              <li><span class="check">✓</span> 전담 지원</li>
            </ul>
            <button class="btn-secondary w-full">문의하기</button>
          </div>
        </div>
      </div>
    </section>

    <!-- CTA 섹션 -->
    <section class="cta-section">
      <div class="cta-inner">
        <div class="cta-blob"></div>
        <h2>지금 바로 시작하세요</h2>
        <p>신용카드 없이 무료로 10회 제공됩니다</p>
        <RouterLink to="/login" class="btn-primary btn-lg">무료로 시작하기</RouterLink>
      </div>
    </section>

    <!-- 푸터 -->
    <footer class="footer">
      <div class="footer-inner">
        <div class="footer-logo">
          <div class="logo-icon">✦</div>
          <span>Erasify</span>
        </div>
        <p class="footer-copy">© 2026 Erasify. All rights reserved.</p>
      </div>
    </footer>

    <!-- 개발용 빠른 이동 -->
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
</template>

<script setup>
import { RouterLink } from 'vue-router'
</script>

<style scoped>
.landing {
  min-height: 100vh;
  overflow-x: hidden;
}

/* 네비게이션 */
.nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  padding: 0 24px;
  backdrop-filter: blur(20px);
  background: rgba(6, 6, 15, 0.8);
  border-bottom: 1px solid var(--border);
}

.nav-inner {
  max-width: 1200px;
  margin: 0 auto;
  height: 64px;
  display: flex;
  align-items: center;
  gap: 40px;
}

.nav-logo {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 20px;
  font-weight: 700;
  color: var(--text-primary);
}

.logo-icon {
  width: 32px;
  height: 32px;
  background: var(--gradient);
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 14px;
  color: white;
}

.nav-links {
  display: flex;
  gap: 4px;
  flex: 1;
}

.nav-link {
  padding: 8px 16px;
  color: var(--text-secondary);
  font-size: 14px;
  font-weight: 500;
  border-radius: var(--radius-sm);
  transition: all 0.2s;
}

.nav-link:hover {
  color: var(--text-primary);
  background: var(--bg-card);
}

.nav-link-dev {
  display: inline-flex;
  align-items: center;
  gap: 5px;
  color: #22d3ee;
  border: 1px solid rgba(6, 182, 212, 0.2);
  background: rgba(6, 182, 212, 0.06);
}

.nav-link-dev:hover {
  background: rgba(6, 182, 212, 0.12);
  color: #67e8f9;
}

.nav-actions {
  display: flex;
  align-items: center;
  gap: 8px;
}

/* 히어로 섹션 */
.hero {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 120px 24px 80px;
  position: relative;
  text-align: center;
}

.hero-bg {
  position: absolute;
  inset: 0;
  overflow: hidden;
  pointer-events: none;
}

.blob {
  position: absolute;
  border-radius: 50%;
  filter: blur(80px);
  opacity: 0.15;
}

.blob-1 {
  width: 600px;
  height: 600px;
  background: radial-gradient(circle, #7c3aed, transparent);
  top: -100px;
  left: -100px;
  animation: float 8s ease-in-out infinite;
}

.blob-2 {
  width: 500px;
  height: 500px;
  background: radial-gradient(circle, #4338ca, transparent);
  bottom: -50px;
  right: -50px;
  animation: float 10s ease-in-out infinite reverse;
}

.blob-3 {
  width: 400px;
  height: 400px;
  background: radial-gradient(circle, #06b6d4, transparent);
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  animation: float 6s ease-in-out infinite 2s;
}

.grid-overlay {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(rgba(255,255,255,0.03) 1px, transparent 1px),
    linear-gradient(90deg, rgba(255,255,255,0.03) 1px, transparent 1px);
  background-size: 60px 60px;
}

@keyframes float {
  0%, 100% { transform: translateY(0) scale(1); }
  50% { transform: translateY(-30px) scale(1.05); }
}

.hero-content {
  position: relative;
  z-index: 1;
  max-width: 720px;
}

.hero-badge {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 6px 16px;
  background: rgba(124, 58, 237, 0.15);
  border: 1px solid rgba(124, 58, 237, 0.3);
  border-radius: 100px;
  font-size: 13px;
  color: #a78bfa;
  margin-bottom: 32px;
}

.badge-dot {
  width: 6px;
  height: 6px;
  background: #a78bfa;
  border-radius: 50%;
  animation: pulse 2s ease-in-out infinite;
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.3; }
}

.hero-title {
  font-size: clamp(40px, 6vw, 72px);
  font-weight: 800;
  line-height: 1.15;
  letter-spacing: -0.03em;
  margin-bottom: 24px;
  color: var(--text-primary);
}

.gradient-text {
  background: linear-gradient(135deg, #a78bfa, #60a5fa, #34d399);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.hero-desc {
  font-size: 18px;
  color: var(--text-secondary);
  line-height: 1.7;
  margin-bottom: 40px;
}

.hero-actions {
  display: flex;
  gap: 12px;
  justify-content: center;
  flex-wrap: wrap;
  margin-bottom: 48px;
}

.btn-lg {
  padding: 16px 32px;
  font-size: 16px;
  border-radius: var(--radius-lg);
}

.hero-stats {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 32px;
}

.stat {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 4px;
}

.stat strong {
  font-size: 24px;
  font-weight: 700;
  color: var(--text-primary);
}

.stat span {
  font-size: 13px;
  color: var(--text-muted);
}

.stat-divider {
  width: 1px;
  height: 40px;
  background: var(--border);
}

/* 데모 카드 */
.hero-demo {
  position: relative;
  z-index: 1;
  margin-top: 64px;
  width: 100%;
  max-width: 560px;
}

.demo-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-xl);
  overflow: hidden;
  backdrop-filter: blur(20px);
  box-shadow: var(--shadow-lg), inset 0 1px 0 rgba(255,255,255,0.05);
}

.demo-header {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px 20px;
  border-bottom: 1px solid var(--border);
}

.demo-dots {
  display: flex;
  gap: 6px;
}

.demo-dots span {
  width: 10px;
  height: 10px;
  border-radius: 50%;
  background: var(--border);
}

.demo-dots span:first-child { background: #ff5f57; }
.demo-dots span:nth-child(2) { background: #febc2e; }
.demo-dots span:last-child { background: #28c840; }

.demo-title {
  font-size: 13px;
  color: var(--text-muted);
  flex: 1;
  text-align: center;
}

.demo-images {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 24px;
}

.demo-image {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.demo-img-placeholder {
  width: 100%;
  aspect-ratio: 1;
  border-radius: var(--radius-md);
  background: rgba(124, 58, 237, 0.1);
  border: 1px solid var(--border);
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
}

.demo-img-placeholder.transparent {
  background: transparent;
}

.checkerboard {
  position: absolute;
  inset: 0;
  background-image:
    linear-gradient(45deg, #1a1a2e 25%, transparent 25%),
    linear-gradient(-45deg, #1a1a2e 25%, transparent 25%),
    linear-gradient(45deg, transparent 75%, #1a1a2e 75%),
    linear-gradient(-45deg, transparent 75%, #1a1a2e 75%);
  background-size: 16px 16px;
  background-position: 0 0, 0 8px, 8px -8px, -8px 0;
  opacity: 0.5;
}

.demo-person {
  width: 60%;
  height: 80%;
  background: var(--gradient);
  border-radius: 40% 40% 30% 30% / 50% 50% 30% 30%;
  position: absolute;
  bottom: 0;
  opacity: 0.7;
}

.demo-bg-pattern {
  position: absolute;
  inset: 0;
  background: linear-gradient(180deg, rgba(67,56,202,0.3) 0%, rgba(124,58,237,0.2) 100%);
}

.demo-arrow {
  color: var(--text-muted);
  flex-shrink: 0;
}

.demo-label {
  font-size: 12px;
  color: var(--text-muted);
  font-weight: 500;
}

/* 기능 섹션 */
.features {
  padding: 120px 24px;
}

.section-inner {
  max-width: 1100px;
  margin: 0 auto;
  text-align: center;
}

.section-badge {
  display: inline-block;
  padding: 4px 14px;
  background: rgba(124, 58, 237, 0.15);
  border: 1px solid rgba(124, 58, 237, 0.3);
  border-radius: 100px;
  font-size: 12px;
  font-weight: 600;
  color: #a78bfa;
  letter-spacing: 0.05em;
  text-transform: uppercase;
  margin-bottom: 20px;
}

.section-title {
  font-size: clamp(28px, 4vw, 48px);
  font-weight: 700;
  letter-spacing: -0.02em;
  color: var(--text-primary);
  margin-bottom: 16px;
}

.section-desc {
  font-size: 17px;
  color: var(--text-secondary);
  margin-bottom: 64px;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
  text-align: left;
}

.feature-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  padding: 28px;
  transition: all 0.3s;
}

.feature-card:hover {
  background: var(--bg-card-hover);
  border-color: rgba(124, 58, 237, 0.3);
  transform: translateY(-4px);
  box-shadow: var(--shadow-md);
}

.feature-icon {
  width: 48px;
  height: 48px;
  background: var(--gradient-bg);
  border: 1px solid rgba(124, 58, 237, 0.2);
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #a78bfa;
  margin-bottom: 20px;
}

.feature-card h3 {
  font-size: 17px;
  font-weight: 600;
  color: var(--text-primary);
  margin-bottom: 10px;
}

.feature-card p {
  font-size: 14px;
  color: var(--text-secondary);
  line-height: 1.7;
}

/* 요금제 */
.pricing {
  padding: 120px 24px;
  background: linear-gradient(180deg, transparent, rgba(13, 13, 31, 0.8));
}

.pricing-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
  gap: 24px;
  text-align: left;
}

.pricing-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-xl);
  padding: 32px;
  position: relative;
  transition: all 0.3s;
}

.pricing-card.featured {
  border-color: rgba(124, 58, 237, 0.5);
  background: linear-gradient(135deg, rgba(124,58,237,0.08), rgba(67,56,202,0.08));
  box-shadow: 0 0 40px rgba(124, 58, 237, 0.1);
}

.pricing-badge {
  position: absolute;
  top: -12px;
  left: 50%;
  transform: translateX(-50%);
  padding: 4px 16px;
  background: var(--gradient);
  border-radius: 100px;
  font-size: 12px;
  font-weight: 600;
  color: white;
}

.pricing-name {
  font-size: 18px;
  font-weight: 700;
  color: var(--text-primary);
  margin-bottom: 16px;
}

.pricing-price {
  margin-bottom: 28px;
}

.pricing-price strong {
  font-size: 36px;
  font-weight: 800;
  color: var(--text-primary);
}

.pricing-price span {
  font-size: 15px;
  color: var(--text-muted);
}

.pricing-features {
  list-style: none;
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 28px;
}

.pricing-features li {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 14px;
  color: var(--text-secondary);
}

.pricing-features li.disabled {
  opacity: 0.4;
}

.check {
  color: #34d399;
  font-weight: 700;
}

.w-full {
  width: 100%;
  justify-content: center;
}

/* CTA */
.cta-section {
  padding: 120px 24px;
  text-align: center;
}

.cta-inner {
  position: relative;
  max-width: 600px;
  margin: 0 auto;
}

.cta-blob {
  position: absolute;
  width: 400px;
  height: 400px;
  background: radial-gradient(circle, rgba(124,58,237,0.2), transparent);
  border-radius: 50%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  filter: blur(60px);
  pointer-events: none;
}

.cta-inner h2 {
  font-size: 40px;
  font-weight: 700;
  letter-spacing: -0.02em;
  color: var(--text-primary);
  margin-bottom: 16px;
}

.cta-inner p {
  font-size: 17px;
  color: var(--text-secondary);
  margin-bottom: 40px;
}

/* 푸터 */
.footer {
  padding: 32px 24px;
  border-top: 1px solid var(--border);
}

.footer-inner {
  max-width: 1100px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.footer-logo {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 16px;
  font-weight: 700;
  color: var(--text-secondary);
}

.footer-copy {
  font-size: 13px;
  color: var(--text-muted);
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
</style>
