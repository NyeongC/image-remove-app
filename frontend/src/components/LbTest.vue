<script setup>
import { ref, computed } from 'vue'

const serverInfo = ref(null)
const loading = ref(false)
const error = ref(null)

const formattedTime = computed(() => {
  if (!serverInfo.value?.timestamp) return ''
  return new Date(serverInfo.value.timestamp).toLocaleString('ko-KR')
})

async function testLoadBalancer() {
  loading.value = true
  error.value = null
  serverInfo.value = null
  try {
    const res = await fetch('/api/lb-test')
    if (!res.ok) throw new Error(`HTTP ${res.status}`)
    serverInfo.value = await res.json()
  } catch (e) {
    error.value = e.message
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="lb-test">
    <h2>로드밸런싱 테스트</h2>
    <button @click="testLoadBalancer" :disabled="loading">
      {{ loading ? '요청 중...' : 'API 요청 보내기' }}
    </button>

    <div v-if="serverInfo" class="result">
      <div class="row">
        <span class="label">hostname</span>
        <span class="value">{{ serverInfo.hostname }}</span>
      </div>
      <div class="row">
        <span class="label">port</span>
        <span class="value">{{ serverInfo.port }}</span>
      </div>
      <div class="row">
        <span class="label">ip</span>
        <span class="value">{{ serverInfo.ip }}</span>
      </div>
      <div class="row">
        <span class="label">timestamp</span>
        <span class="value muted">{{ formattedTime }}</span>
      </div>
    </div>

    <div v-if="error" class="error">오류: {{ error }}</div>
  </div>
</template>

<style scoped>
.lb-test {
  margin-top: 40px;
  display: inline-block;
  text-align: left;
  border: 1px solid #ddd;
  border-radius: 10px;
  padding: 1.4rem 2rem;
  min-width: 360px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0,0,0,0.07);
}

.lb-test h2 {
  margin-top: 0;
  margin-bottom: 1rem;
  font-size: 1.1rem;
  color: #333;
}

.lb-test button {
  padding: 10px 22px;
  font-size: 1rem;
  cursor: pointer;
  background: #4f46e5;
  color: #fff;
  border: none;
  border-radius: 6px;
  transition: background 0.2s;
}

.lb-test button:hover:not(:disabled) {
  background: #4338ca;
}

.lb-test button:disabled {
  opacity: 0.55;
  cursor: not-allowed;
}

.result {
  margin-top: 1.2rem;
  border: 1px solid #e5e7eb;
  border-radius: 8px;
  overflow: hidden;
}

.row {
  display: flex;
  align-items: center;
  padding: 0.6rem 1rem;
  border-bottom: 1px solid #f0f0f0;
}

.row:last-child {
  border-bottom: none;
}

.label {
  width: 90px;
  font-size: 0.78rem;
  font-weight: 600;
  color: #6b7280;
  text-transform: uppercase;
  letter-spacing: 0.04em;
  flex-shrink: 0;
}

.value {
  font-size: 0.95rem;
  color: #111;
  font-family: 'Courier New', monospace;
}

.value.muted {
  color: #6b7280;
  font-family: inherit;
  font-size: 0.88rem;
}

.error {
  margin-top: 1rem;
  color: #dc2626;
  font-size: 0.9rem;
}
</style>
