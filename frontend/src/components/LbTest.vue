<script setup>
import { ref } from 'vue'

const serverInfo = ref(null)
const loading = ref(false)
const error = ref(null)

async function testLoadBalancer() {
  loading.value = true
  error.value = null
  serverInfo.value = null
  try {
    const res = await fetch('/api/lb-test')
    if (!res.ok) throw new Error(`HTTP ${res.status}`)
    const data = await res.json()
    serverInfo.value = data
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
      <pre>{{ JSON.stringify(serverInfo, null, 2) }}</pre>
    </div>
    <div v-if="error" class="error">오류: {{ error }}</div>
  </div>
</template>

<style scoped>
.lb-test {
  margin-top: 40px;
  display: inline-block;
  text-align: left;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 1.2rem 1.8rem;
  min-width: 320px;
}

.lb-test h2 {
  margin-top: 0;
  font-size: 1.1rem;
}

.lb-test button {
  margin-top: 0.5rem;
  padding: 10px 20px;
  font-size: 1rem;
  cursor: pointer;
}

.lb-test button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.result {
  margin-top: 1rem;
  background: #f4f4f4;
  padding: 0.8rem;
  border-radius: 6px;
}

.result pre {
  margin: 0;
  font-size: 0.9rem;
}

.error {
  margin-top: 1rem;
  color: #e53e3e;
}
</style>
