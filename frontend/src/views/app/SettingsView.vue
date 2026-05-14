<template>
  <div class="settings-view">
    <div class="settings-layout">
      <!-- 설정 사이드 탭 -->
      <div class="settings-tabs">
        <button
          v-for="tab in tabs"
          :key="tab.value"
          class="settings-tab"
          :class="{ active: activeTab === tab.value }"
          @click="activeTab = tab.value"
        >
          <component :is="tab.icon" />
          {{ tab.label }}
        </button>
      </div>

      <!-- 설정 컨텐츠 -->
      <div class="settings-content">
        <!-- 프로필 -->
        <section v-if="activeTab === 'profile'" class="settings-section">
          <div class="section-header">
            <h2>프로필</h2>
            <p>계정 정보를 관리합니다</p>
          </div>

          <div class="avatar-section">
            <div class="big-avatar">J</div>
            <div>
              <button class="btn-secondary">사진 변경</button>
              <p class="avatar-hint">JPG, PNG 권장 · 최대 2MB</p>
            </div>
          </div>

          <div class="form-grid">
            <div class="form-field">
              <label>이름</label>
              <input type="text" value="홍길동" class="field-input" />
            </div>
            <div class="form-field">
              <label>이메일</label>
              <input type="email" value="hong@example.com" class="field-input" />
            </div>
            <div class="form-field full">
              <label>소개</label>
              <textarea class="field-input" rows="3" placeholder="자신을 소개해보세요..."></textarea>
            </div>
          </div>

          <div class="section-actions">
            <button class="btn-primary">변경사항 저장</button>
          </div>
        </section>

        <!-- 보안 -->
        <section v-if="activeTab === 'security'" class="settings-section">
          <div class="section-header">
            <h2>보안</h2>
            <p>비밀번호 및 보안 설정</p>
          </div>

          <div class="settings-card">
            <div class="card-row">
              <div>
                <h4>비밀번호 변경</h4>
                <p>마지막 변경: 30일 전</p>
              </div>
              <button class="btn-secondary">변경</button>
            </div>
          </div>

          <div class="settings-card">
            <div class="card-row">
              <div>
                <h4>2단계 인증</h4>
                <p>현재 비활성화 상태</p>
              </div>
              <div class="toggle-switch" :class="{ on: twoFactor }" @click="twoFactor = !twoFactor">
                <div class="toggle-knob"></div>
              </div>
            </div>
          </div>

          <div class="settings-card danger">
            <div class="card-row">
              <div>
                <h4>계정 삭제</h4>
                <p>모든 데이터가 영구적으로 삭제됩니다</p>
              </div>
              <button class="btn-danger">계정 삭제</button>
            </div>
          </div>
        </section>

        <!-- API 키 -->
        <section v-if="activeTab === 'api'" class="settings-section">
          <div class="section-header">
            <h2>API 키</h2>
            <p>외부 서비스에서 Erasify를 사용하세요</p>
          </div>

          <div class="api-key-card">
            <div class="api-key-header">
              <span class="api-key-label">현재 API 키</span>
              <button class="btn-ghost" @click="showApiKey = !showApiKey">
                {{ showApiKey ? '숨기기' : '보이기' }}
              </button>
            </div>
            <div class="api-key-display">
              <code>{{ showApiKey ? 'er_live_sk_7fJ2kL9mN3pQ8rT5vW1xY6zA4bC0dE' : 'er_live_sk_••••••••••••••••••••••••••••••' }}</code>
              <button class="copy-btn" @click="copyKey">
                <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                  <rect x="9" y="9" width="13" height="13" rx="2"/><path d="M5 15H4a2 2 0 01-2-2V4a2 2 0 012-2h9a2 2 0 012 2v1"/>
                </svg>
                {{ copied ? '복사됨!' : '복사' }}
              </button>
            </div>
          </div>

          <div class="api-usage">
            <h4>API 사용량</h4>
            <div class="usage-stats">
              <div class="usage-stat">
                <span>오늘</span>
                <strong>12 / 100</strong>
              </div>
              <div class="usage-stat">
                <span>이번 달</span>
                <strong>342 / 1,000</strong>
              </div>
            </div>
            <div class="usage-bar-full">
              <div class="usage-fill" style="width: 34.2%"></div>
            </div>
          </div>

          <div class="docs-link">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M14 2H6a2 2 0 00-2 2v16a2 2 0 002 2h12a2 2 0 002-2V8z"/><polyline points="14 2 14 8 20 8"/>
            </svg>
            <span>API 문서 보기</span>
          </div>
        </section>

        <!-- 알림 -->
        <section v-if="activeTab === 'notifications'" class="settings-section">
          <div class="section-header">
            <h2>알림 설정</h2>
            <p>알림 수신 방식을 설정하세요</p>
          </div>

          <div class="notification-list">
            <div
              v-for="notification in notifications"
              :key="notification.key"
              class="notification-item"
            >
              <div class="notification-info">
                <h4>{{ notification.title }}</h4>
                <p>{{ notification.desc }}</p>
              </div>
              <div class="toggle-switch" :class="{ on: notification.enabled }" @click="notification.enabled = !notification.enabled">
                <div class="toggle-knob"></div>
              </div>
            </div>
          </div>
        </section>

        <!-- 플랜 -->
        <section v-if="activeTab === 'plan'" class="settings-section">
          <div class="section-header">
            <h2>플랜 및 결제</h2>
            <p>현재 사용 중인 플랜과 결제 정보</p>
          </div>

          <div class="current-plan-card">
            <div class="plan-info">
              <div class="plan-badge">무료 플랜</div>
              <p>월 10회 이미지 처리 가능</p>
              <div class="plan-usage">
                <span>3 / 10 사용</span>
                <div class="mini-bar">
                  <div class="mini-fill" style="width: 30%"></div>
                </div>
              </div>
            </div>
            <RouterLink to="/login" class="btn-primary">Pro로 업그레이드</RouterLink>
          </div>

          <div class="plan-comparison">
            <h3>플랜 비교</h3>
            <div class="compare-grid">
              <div class="compare-feature"></div>
              <div class="compare-header">무료</div>
              <div class="compare-header featured">Pro</div>

              <div class="compare-feature">월 처리 횟수</div>
              <div class="compare-value">10회</div>
              <div class="compare-value featured">500회</div>

              <div class="compare-feature">최대 파일 크기</div>
              <div class="compare-value">5MB</div>
              <div class="compare-value featured">25MB</div>

              <div class="compare-feature">히스토리 보관</div>
              <div class="compare-value">
                <span class="no">✗</span>
              </div>
              <div class="compare-value featured">
                <span class="yes">✓</span>
              </div>

              <div class="compare-feature">우선 처리</div>
              <div class="compare-value">
                <span class="no">✗</span>
              </div>
              <div class="compare-value featured">
                <span class="yes">✓</span>
              </div>

              <div class="compare-feature">가격</div>
              <div class="compare-value">₩0</div>
              <div class="compare-value featured">₩9,900/월</div>
            </div>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { RouterLink } from 'vue-router'

const activeTab = ref('profile')
const twoFactor = ref(false)
const showApiKey = ref(false)
const copied = ref(false)

const tabs = [
  { value: 'profile', label: '프로필', icon: 'span' },
  { value: 'security', label: '보안', icon: 'span' },
  { value: 'api', label: 'API 키', icon: 'span' },
  { value: 'notifications', label: '알림', icon: 'span' },
  { value: 'plan', label: '플랜', icon: 'span' },
]

const notifications = ref([
  { key: 'complete', title: '처리 완료 알림', desc: '이미지 배경 제거가 완료되면 알림을 받습니다', enabled: true },
  { key: 'usage', title: '사용량 경고', desc: '월 사용량이 80%에 도달하면 알림을 받습니다', enabled: true },
  { key: 'update', title: '서비스 업데이트', desc: '새로운 기능 및 업데이트 소식을 받습니다', enabled: false },
  { key: 'promo', title: '프로모션 및 혜택', desc: '특별 할인 및 이벤트 정보를 받습니다', enabled: false },
])

function copyKey() {
  copied.value = true
  setTimeout(() => { copied.value = false }, 2000)
}
</script>

<style scoped>
.settings-view {
  max-width: 900px;
}

.settings-layout {
  display: grid;
  grid-template-columns: 200px 1fr;
  gap: 32px;
  align-items: start;
}

/* 탭 네비게이션 */
.settings-tabs {
  display: flex;
  flex-direction: column;
  gap: 2px;
  position: sticky;
  top: 96px;
}

.settings-tab {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 12px;
  background: transparent;
  border: none;
  border-radius: var(--radius-md);
  font-size: 14px;
  font-weight: 500;
  color: var(--text-secondary);
  cursor: pointer;
  transition: all 0.2s;
  text-align: left;
}

.settings-tab:hover {
  background: var(--bg-card);
  color: var(--text-primary);
}

.settings-tab.active {
  background: rgba(124, 58, 237, 0.12);
  color: #c4b5fd;
  border: 1px solid rgba(124, 58, 237, 0.2);
}

/* 컨텐츠 */
.settings-section {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

.section-header {
  padding-bottom: 20px;
  border-bottom: 1px solid var(--border);
}

.section-header h2 {
  font-size: 22px;
  font-weight: 700;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.section-header p {
  font-size: 14px;
  color: var(--text-secondary);
}

/* 아바타 */
.avatar-section {
  display: flex;
  align-items: center;
  gap: 20px;
}

.big-avatar {
  width: 72px;
  height: 72px;
  background: var(--gradient);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 28px;
  font-weight: 700;
  color: white;
  flex-shrink: 0;
}

.avatar-hint {
  font-size: 12px;
  color: var(--text-muted);
  margin-top: 6px;
}

/* 폼 */
.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
}

.form-field {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-field.full {
  grid-column: 1 / -1;
}

.form-field label {
  font-size: 13px;
  font-weight: 600;
  color: var(--text-secondary);
}

.field-input {
  padding: 11px 14px;
  background: var(--bg-input);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  color: var(--text-primary);
  font-size: 14px;
  transition: all 0.2s;
  resize: vertical;
}

.field-input::placeholder {
  color: var(--text-muted);
}

.field-input:focus {
  border-color: var(--border-focus);
  box-shadow: 0 0 0 3px rgba(124, 58, 237, 0.1);
}

.section-actions {
  display: flex;
  justify-content: flex-start;
}

/* 설정 카드 */
.settings-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  padding: 20px;
}

.settings-card.danger {
  border-color: rgba(239, 68, 68, 0.2);
  background: rgba(239, 68, 68, 0.04);
}

.card-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
}

.card-row h4 {
  font-size: 14px;
  font-weight: 600;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.card-row p {
  font-size: 13px;
  color: var(--text-muted);
}

.toggle-switch {
  width: 44px;
  height: 24px;
  background: var(--border);
  border-radius: 12px;
  position: relative;
  cursor: pointer;
  transition: background 0.2s;
  flex-shrink: 0;
}

.toggle-switch.on {
  background: var(--purple);
}

.toggle-knob {
  position: absolute;
  top: 2px;
  left: 2px;
  width: 20px;
  height: 20px;
  background: white;
  border-radius: 50%;
  transition: transform 0.2s;
  box-shadow: var(--shadow-sm);
}

.toggle-switch.on .toggle-knob {
  transform: translateX(20px);
}

.btn-danger {
  display: inline-flex;
  align-items: center;
  padding: 8px 16px;
  background: rgba(239, 68, 68, 0.12);
  border: 1px solid rgba(239, 68, 68, 0.3);
  border-radius: var(--radius-md);
  font-size: 13px;
  font-weight: 600;
  color: #f87171;
  cursor: pointer;
  transition: all 0.2s;
}

.btn-danger:hover {
  background: rgba(239, 68, 68, 0.2);
}

/* API 키 */
.api-key-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  padding: 20px;
}

.api-key-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 12px;
}

.api-key-label {
  font-size: 13px;
  font-weight: 600;
  color: var(--text-secondary);
}

.api-key-display {
  display: flex;
  align-items: center;
  gap: 12px;
  background: var(--bg-input);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  padding: 12px 14px;
}

.api-key-display code {
  flex: 1;
  font-family: 'JetBrains Mono', 'Fira Code', monospace;
  font-size: 13px;
  color: var(--text-primary);
  word-break: break-all;
}

.copy-btn {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 6px 12px;
  background: var(--gradient-bg);
  border: 1px solid rgba(124, 58, 237, 0.25);
  border-radius: var(--radius-sm);
  font-size: 12px;
  font-weight: 600;
  color: #a78bfa;
  cursor: pointer;
  white-space: nowrap;
  transition: all 0.2s;
}

.copy-btn:hover {
  background: rgba(124, 58, 237, 0.2);
}

.api-usage {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.api-usage h4 {
  font-size: 14px;
  font-weight: 600;
  color: var(--text-primary);
}

.usage-stats {
  display: flex;
  gap: 32px;
}

.usage-stat {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.usage-stat span {
  font-size: 12px;
  color: var(--text-muted);
}

.usage-stat strong {
  font-size: 18px;
  font-weight: 700;
  color: var(--text-primary);
}

.usage-bar-full {
  height: 6px;
  background: var(--border);
  border-radius: 3px;
  overflow: hidden;
}

.usage-fill {
  height: 100%;
  background: var(--gradient);
  border-radius: 3px;
}

.docs-link {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
  font-weight: 500;
  color: #a78bfa;
  cursor: pointer;
  transition: color 0.2s;
}

.docs-link:hover {
  color: #c4b5fd;
}

/* 알림 */
.notification-list {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.notification-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 18px 20px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  gap: 20px;
  transition: border-color 0.2s;
}

.notification-item:hover {
  border-color: var(--border-hover);
}

.notification-info h4 {
  font-size: 14px;
  font-weight: 600;
  color: var(--text-primary);
  margin-bottom: 4px;
}

.notification-info p {
  font-size: 13px;
  color: var(--text-muted);
}

/* 플랜 */
.current-plan-card {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 24px;
  background: var(--gradient-bg);
  border: 1px solid rgba(124, 58, 237, 0.25);
  border-radius: var(--radius-lg);
  gap: 20px;
}

.plan-badge {
  display: inline-block;
  padding: 4px 12px;
  background: rgba(124, 58, 237, 0.2);
  border: 1px solid rgba(124, 58, 237, 0.35);
  border-radius: 100px;
  font-size: 12px;
  font-weight: 700;
  color: #a78bfa;
  margin-bottom: 8px;
}

.plan-info p {
  font-size: 14px;
  color: var(--text-secondary);
  margin-bottom: 12px;
}

.plan-usage {
  display: flex;
  align-items: center;
  gap: 12px;
}

.plan-usage span {
  font-size: 13px;
  color: var(--text-secondary);
  white-space: nowrap;
}

.mini-bar {
  flex: 1;
  height: 4px;
  background: rgba(255,255,255,0.1);
  border-radius: 2px;
  overflow: hidden;
  min-width: 80px;
}

.mini-fill {
  height: 100%;
  background: var(--gradient);
  border-radius: 2px;
}

.plan-comparison {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.plan-comparison h3 {
  font-size: 16px;
  font-weight: 600;
  color: var(--text-primary);
}

.compare-grid {
  display: grid;
  grid-template-columns: 1fr 120px 120px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  overflow: hidden;
}

.compare-header,
.compare-value,
.compare-feature {
  padding: 14px 16px;
  font-size: 13px;
  border-bottom: 1px solid var(--border);
}

.compare-header {
  font-weight: 700;
  color: var(--text-primary);
  background: rgba(255,255,255,0.03);
  text-align: center;
}

.compare-header.featured {
  background: rgba(124, 58, 237, 0.1);
  color: #a78bfa;
}

.compare-feature {
  color: var(--text-secondary);
}

.compare-value {
  text-align: center;
  color: var(--text-secondary);
  background: rgba(255,255,255,0.02);
}

.compare-value.featured {
  background: rgba(124, 58, 237, 0.05);
}

.yes { color: #34d399; font-weight: 700; }
.no { color: var(--text-muted); }
</style>
