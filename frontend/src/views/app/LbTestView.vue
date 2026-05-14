<template>
  <div class="lb-view">
    <div class="lb-header">
      <div class="lb-title-area">
        <div class="lb-badge">
          <span class="badge-dot"></span>
          서버 인프라
        </div>
        <h2>로드밸런싱 테스트</h2>
        <p>요청마다 어느 서버 인스턴스가 응답하는지 확인합니다</p>
      </div>
      <button
        class="btn-primary request-btn"
        :disabled="loading"
        @click="testLoadBalancer"
      >
        <span v-if="loading" class="spinner"></span>
        <svg v-else width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M5 12h14M12 5l7 7-7 7"/>
        </svg>
        {{ loading ? '요청 중...' : 'API 요청 보내기' }}
      </button>
    </div>

    <!-- 에러 -->
    <div v-if="error" class="error-banner">
      <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
        <circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/>
      </svg>
      {{ error }}
    </div>

    <!-- 응답 결과 -->
    <Transition name="slide-up">
      <div v-if="serverInfo" class="result-card">
        <div class="result-header">
          <div class="result-status">
            <div class="status-dot"></div>
            <span>응답 수신 완료</span>
          </div>
          <span class="result-time">{{ formattedTime }}</span>
        </div>

        <div class="result-body">
          <div class="result-row">
            <div class="row-label">
              <svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <rect x="2" y="3" width="20" height="14" rx="2"/><path d="M8 21h8M12 17v4"/>
              </svg>
              hostname
            </div>
            <code class="row-value highlight">{{ serverInfo.hostname }}</code>
          </div>

          <div class="result-row">
            <div class="row-label">
              <svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2z"/>
              </svg>
              port
            </div>
            <code class="row-value">{{ serverInfo.port }}</code>
          </div>

          <div class="result-row">
            <div class="row-label">
              <svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/><path d="M2 12h20M12 2a15.3 15.3 0 010 20M12 2a15.3 15.3 0 000 20"/>
              </svg>
              ip
            </div>
            <code class="row-value">{{ serverInfo.ip }}</code>
          </div>

          <div class="result-row no-border">
            <div class="row-label">
              <svg width="13" height="13" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                <circle cx="12" cy="12" r="10"/><path d="M12 6v6l4 2"/>
              </svg>
              timestamp
            </div>
            <span class="row-value muted">{{ formattedTime }}</span>
          </div>
        </div>
      </div>
    </Transition>

    <!-- 히스토리 -->
    <div v-if="history.length > 0" class="history-section">
      <div class="history-header-row">
        <h3>요청 히스토리</h3>
        <button class="btn-ghost clear-btn" @click="history = []">
          <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="3 6 5 6 21 6"/><path d="M19 6l-1 14a2 2 0 01-2 2H8a2 2 0 01-2-2L5 6"/>
          </svg>
          초기화
        </button>
      </div>
      <div class="history-list">
        <div
          v-for="(item, idx) in history"
          :key="idx"
          class="history-item"
          :class="{ latest: idx === 0 }"
        >
          <div class="history-index">#{{ history.length - idx }}</div>
          <div class="history-info">
            <code class="history-hostname">{{ item.hostname }}</code>
            <span class="history-ip">{{ item.ip }}:{{ item.port }}</span>
          </div>
          <span class="history-time">{{ item.timeLabel }}</span>
        </div>
      </div>
    </div>

    <!-- 빈 상태 안내 -->
    <div v-if="!serverInfo && !loading && !error" class="empty-guide">
      <div class="guide-grid">
        <div class="guide-card">
          <div class="guide-icon">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M5 12h14M12 5l7 7-7 7"/>
            </svg>
          </div>
          <h4>요청 전송</h4>
          <p>버튼을 눌러 <code>/api/lb-test</code> 엔드포인트에 요청을 보냅니다</p>
        </div>
        <div class="guide-card">
          <div class="guide-icon">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="2" y="3" width="20" height="14" rx="2"/><path d="M8 21h8M12 17v4"/>
            </svg>
          </div>
          <h4>서버 확인</h4>
          <p>로드밸런서가 어느 인스턴스로 라우팅했는지 hostname·IP로 확인합니다</p>
        </div>
        <div class="guide-card">
          <div class="guide-icon">
            <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>
            </svg>
          </div>
          <h4>분산 확인</h4>
          <p>여러 번 요청해서 hostname이 바뀌면 로드밸런싱이 정상 동작 중입니다</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const serverInfo = ref(null)
const loading = ref(false)
const error = ref(null)
const history = ref([])

const formattedTime = computed(() => {
  if (!serverInfo.value?.timestamp) return ''
  return new Date(serverInfo.value.timestamp).toLocaleString('ko-KR')
})

async function testLoadBalancer() {
  loading.value = true
  error.value = null
  try {
    const res = await fetch('/api/lb-test')
    if (!res.ok) throw new Error(`HTTP ${res.status}`)
    const data = await res.json()
    serverInfo.value = data
    history.value.unshift({
      ...data,
      timeLabel: new Date(data.timestamp).toLocaleTimeString('ko-KR'),
    })
    if (history.value.length > 20) history.value.pop()
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false
  }
}
</script>

<style scoped>
.lb-view {
  max-width: 760px;
  display: flex;
  flex-direction: column;
  gap: 24px;
}

/* 헤더 */
.lb-header {
  display: flex;
  align-items: flex-end;
  justify-content: space-between;
  gap: 20px;
  flex-wrap: wrap;
}

.lb-title-area {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.lb-badge {
  display: inline-flex;
  align-items: center;
  gap: 7px;
  padding: 4px 12px;
  background: rgba(6, 182, 212, 0.12);
  border: 1px solid rgba(6, 182, 212, 0.25);
  border-radius: 100px;
  font-size: 12px;
  font-weight: 600;
  color: #22d3ee;
  width: fit-content;
}

.badge-dot {
  width: 6px;
  height: 6px;
  background: #22d3ee;
  border-radius: 50%;
  animation: pulse 2s ease-in-out infinite;
}

@keyframes pulse {
  0%, 100% { opacity: 1; }
  50% { opacity: 0.3; }
}

.lb-title-area h2 {
  font-size: 22px;
  font-weight: 700;
  color: var(--text-primary);
  margin: 0;
}

.lb-title-area p {
  font-size: 14px;
  color: var(--text-secondary);
}

.request-btn {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px 24px;
  font-size: 15px;
  flex-shrink: 0;
}

.request-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.spinner {
  width: 16px;
  height: 16px;
  border: 2px solid rgba(255,255,255,0.3);
  border-top-color: white;
  border-radius: 50%;
  animation: spin 0.7s linear infinite;
  flex-shrink: 0;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

/* 에러 */
.error-banner {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 14px 18px;
  background: rgba(239, 68, 68, 0.1);
  border: 1px solid rgba(239, 68, 68, 0.25);
  border-radius: var(--radius-lg);
  color: #f87171;
  font-size: 14px;
  font-weight: 500;
}

/* 결과 카드 */
.result-card {
  background: var(--bg-card);
  border: 1px solid rgba(124, 58, 237, 0.3);
  border-radius: var(--radius-xl);
  overflow: hidden;
  box-shadow: 0 0 30px rgba(124, 58, 237, 0.08);
}

.result-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 20px;
  border-bottom: 1px solid var(--border);
  background: rgba(124, 58, 237, 0.06);
}

.result-status {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 13px;
  font-weight: 600;
  color: #34d399;
}

.status-dot {
  width: 8px;
  height: 8px;
  background: #34d399;
  border-radius: 50%;
  box-shadow: 0 0 8px #34d399;
}

.result-time {
  font-size: 12px;
  color: var(--text-muted);
}

.result-body {
  padding: 8px 0;
}

.result-row {
  display: flex;
  align-items: center;
  gap: 16px;
  padding: 14px 20px;
  border-bottom: 1px solid var(--border);
  transition: background 0.15s;
}

.result-row:hover {
  background: rgba(255,255,255,0.02);
}

.result-row.no-border {
  border-bottom: none;
}

.row-label {
  display: flex;
  align-items: center;
  gap: 6px;
  width: 110px;
  font-size: 11px;
  font-weight: 700;
  color: var(--text-muted);
  text-transform: uppercase;
  letter-spacing: 0.07em;
  flex-shrink: 0;
}

.row-label svg {
  color: var(--text-muted);
  flex-shrink: 0;
}

.row-value {
  font-family: 'JetBrains Mono', 'Fira Code', 'Courier New', monospace;
  font-size: 14px;
  color: var(--text-secondary);
}

.row-value.highlight {
  color: #a78bfa;
  font-weight: 600;
  font-size: 15px;
}

.row-value.muted {
  font-family: inherit;
  font-size: 13px;
  color: var(--text-muted);
}

/* 히스토리 */
.history-section {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.history-header-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.history-header-row h3 {
  font-size: 15px;
  font-weight: 600;
  color: var(--text-primary);
}

.clear-btn {
  font-size: 13px;
  padding: 6px 12px;
  color: var(--text-muted);
  display: flex;
  align-items: center;
  gap: 6px;
}

.clear-btn:hover {
  color: #f87171;
  background: rgba(239, 68, 68, 0.08);
}

.history-list {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.history-item {
  display: flex;
  align-items: center;
  gap: 14px;
  padding: 12px 16px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  transition: all 0.2s;
}

.history-item.latest {
  border-color: rgba(124, 58, 237, 0.3);
  background: rgba(124, 58, 237, 0.05);
}

.history-index {
  font-size: 12px;
  font-weight: 700;
  color: var(--text-muted);
  width: 28px;
  flex-shrink: 0;
}

.history-item.latest .history-index {
  color: #a78bfa;
}

.history-info {
  flex: 1;
  display: flex;
  align-items: center;
  gap: 16px;
  min-width: 0;
}

.history-hostname {
  font-family: 'JetBrains Mono', 'Fira Code', monospace;
  font-size: 13px;
  color: var(--text-primary);
  font-weight: 600;
}

.history-item.latest .history-hostname {
  color: #a78bfa;
}

.history-ip {
  font-family: 'JetBrains Mono', 'Fira Code', monospace;
  font-size: 12px;
  color: var(--text-muted);
}

.history-time {
  font-size: 12px;
  color: var(--text-muted);
  flex-shrink: 0;
}

/* 안내 가이드 */
.empty-guide {
  margin-top: 8px;
}

.guide-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 14px;
}

.guide-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  padding: 20px;
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.guide-icon {
  width: 40px;
  height: 40px;
  background: rgba(6, 182, 212, 0.1);
  border: 1px solid rgba(6, 182, 212, 0.2);
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #22d3ee;
}

.guide-card h4 {
  font-size: 14px;
  font-weight: 600;
  color: var(--text-primary);
}

.guide-card p {
  font-size: 13px;
  color: var(--text-secondary);
  line-height: 1.6;
}

.guide-card code {
  font-family: 'JetBrains Mono', 'Fira Code', monospace;
  font-size: 12px;
  color: #22d3ee;
  background: rgba(6, 182, 212, 0.08);
  padding: 1px 5px;
  border-radius: 4px;
}

/* 애니메이션 */
.slide-up-enter-active {
  transition: all 0.35s cubic-bezier(0.16, 1, 0.3, 1);
}

.slide-up-enter-from {
  opacity: 0;
  transform: translateY(12px);
}
</style>
