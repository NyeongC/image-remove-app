<template>
  <div class="remove-view">
    <!-- 업로드 없을 때: 업로드 영역 -->
    <div v-if="!uploadedImage" class="upload-section">
      <div
        class="drop-zone"
        :class="{ dragging: isDragging }"
        @dragover.prevent="isDragging = true"
        @dragleave="isDragging = false"
        @drop.prevent="handleDrop"
        @click="triggerFileInput"
      >
        <div class="drop-zone-inner">
          <div class="drop-icon">
            <svg width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
              <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4"/>
              <polyline points="17 8 12 3 7 8"/>
              <line x1="12" y1="3" x2="12" y2="15"/>
            </svg>
          </div>
          <h3>이미지를 드래그하거나 클릭해서 업로드</h3>
          <p>JPG, PNG, WEBP 지원 · 최대 25MB</p>
          <button type="button" class="btn-primary upload-btn">
            파일 선택
          </button>
        </div>

        <!-- 드래그 오버레이 -->
        <div v-if="isDragging" class="drag-overlay">
          <div class="drag-overlay-inner">
            <svg width="48" height="48" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4"/>
              <polyline points="17 8 12 3 7 8"/>
              <line x1="12" y1="3" x2="12" y2="15"/>
            </svg>
            <p>놓아서 업로드</p>
          </div>
        </div>
      </div>

      <input
        ref="fileInput"
        type="file"
        accept="image/*"
        class="hidden-input"
        @change="handleFileChange"
      />

      <!-- 예시 이미지들 -->
      <div class="examples">
        <p class="examples-label">예시로 사용해보기</p>
        <div class="examples-grid">
          <div
            v-for="example in exampleImages"
            :key="example.id"
            class="example-item"
            @click="useExample(example)"
          >
            <div class="example-img" :style="{ background: example.gradient }">
              <div class="example-person"></div>
            </div>
            <span>{{ example.label }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 처리 중 -->
    <div v-else-if="isProcessing" class="processing-section">
      <div class="processing-card">
        <div class="processing-animation">
          <div class="processing-ring ring-1"></div>
          <div class="processing-ring ring-2"></div>
          <div class="processing-ring ring-3"></div>
          <div class="processing-center">
            <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z" fill="rgba(124,58,237,0.3)"/>
            </svg>
          </div>
        </div>
        <h3>AI가 배경을 제거하는 중...</h3>
        <p>보통 1초 이내에 완료됩니다</p>
        <div class="progress-bar">
          <div class="progress-fill" :style="{ width: progress + '%' }"></div>
        </div>
        <span class="progress-text">{{ progress }}%</span>
      </div>
    </div>

    <!-- 에러 화면 -->
    <div v-else-if="uploadError" class="error-section">
      <div class="error-card">
        <div class="error-icon">
          <svg width="32" height="32" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <circle cx="12" cy="12" r="10"/>
            <line x1="12" y1="8" x2="12" y2="12"/>
            <line x1="12" y1="16" x2="12.01" y2="16"/>
          </svg>
        </div>
        <h3>처리 실패</h3>
        <p>{{ uploadError }}</p>
        <button class="btn-primary" @click="reset">다시 시도</button>
      </div>
    </div>

    <!-- 결과 화면 -->
    <div v-else class="result-section">
      <!-- 상단 툴바 -->
      <div class="result-toolbar">
        <div class="toolbar-left">
          <button class="btn-ghost" @click="reset">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M19 12H5M12 19l-7-7 7-7"/>
            </svg>
            새 이미지
          </button>
          <div class="view-toggle">
            <button
              class="toggle-btn"
              :class="{ active: viewMode === 'split' }"
              @click="viewMode = 'split'"
            >전/후 비교</button>
            <button
              class="toggle-btn"
              :class="{ active: viewMode === 'result' }"
              @click="viewMode = 'result'"
            >결과만</button>
            <button
              class="toggle-btn"
              :class="{ active: viewMode === 'original' }"
              @click="viewMode = 'original'"
            >원본</button>
          </div>
        </div>
        <div class="toolbar-right">
          <button class="btn-secondary" @click="downloadResult">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
            </svg>
            PNG 다운로드
          </button>
        </div>
      </div>

      <!-- 이미지 뷰어 -->
      <div class="image-viewer" :class="viewMode">
        <!-- 전/후 비교 뷰 -->
        <div v-if="viewMode === 'split'" class="split-view">
          <div class="image-panel">
            <div class="panel-label">원본</div>
            <div class="image-container original-img">
              <img :src="originalImageUrl" class="result-image" alt="원본" />
            </div>
          </div>
          <div class="split-divider">
            <div class="divider-line"></div>
            <div class="divider-dot"></div>
          </div>
          <div class="image-panel">
            <div class="panel-label">결과</div>
            <div class="image-container transparent-bg">
              <img :src="resultImageUrl" class="result-image" alt="배경 제거 결과" />
            </div>
          </div>
        </div>

        <!-- 결과만 뷰 -->
        <div v-else-if="viewMode === 'result'" class="single-view">
          <div class="image-container transparent-bg large">
            <img :src="resultImageUrl" class="result-image large" alt="배경 제거 결과" />
          </div>
        </div>

        <!-- 원본만 뷰 -->
        <div v-else class="single-view">
          <div class="image-container large">
            <img :src="originalImageUrl" class="result-image large" alt="원본" />
          </div>
        </div>
      </div>

      <!-- 이미지 정보 -->
      <div class="image-info-bar">
        <div class="info-item">
          <span class="info-label">원본</span>
          <span class="info-value">{{ imageInfo.name }} · {{ imageInfo.originalSize }}</span>
        </div>
        <div class="info-divider"></div>
        <div class="info-item">
          <span class="info-label">결과</span>
          <span class="info-value">PNG · 투명 배경</span>
        </div>
        <div class="info-divider"></div>
        <div class="info-item">
          <span class="info-label">처리 시간</span>
          <span class="info-value success">{{ imageInfo.elapsed }}초</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const fileInput = ref(null)
const isDragging = ref(false)
const uploadedImage = ref(null)
const isProcessing = ref(false)
const uploadError = ref(null)
const progress = ref(0)
const viewMode = ref('split')
const originalImageUrl = ref(null)
const resultImageUrl = ref(null)
const imageInfo = ref({ name: '', originalSize: '', elapsed: '' })

const exampleImages = [
  { id: 1, label: '인물 사진', gradient: 'linear-gradient(135deg, #667eea, #764ba2)' },
  { id: 2, label: '제품 사진', gradient: 'linear-gradient(135deg, #f093fb, #f5576c)' },
  { id: 3, label: '동물 사진', gradient: 'linear-gradient(135deg, #4facfe, #00f2fe)' },
  { id: 4, label: '자동차', gradient: 'linear-gradient(135deg, #43e97b, #38f9d7)' },
]

function triggerFileInput() {
  fileInput.value?.click()
}

function handleDrop(e) {
  isDragging.value = false
  const file = e.dataTransfer.files[0]
  if (file) processFile(file)
}

function handleFileChange(e) {
  const file = e.target.files[0]
  if (file) processFile(file)
}

function useExample(example) {
  uploadedImage.value = example
}

async function processFile(file) {
  uploadedImage.value = { label: file.name }
  uploadError.value = null
  isProcessing.value = true
  progress.value = 0

  if (originalImageUrl.value) URL.revokeObjectURL(originalImageUrl.value)
  if (resultImageUrl.value) URL.revokeObjectURL(resultImageUrl.value)
  originalImageUrl.value = URL.createObjectURL(file)

  const progressInterval = setInterval(() => {
    if (progress.value < 85) progress.value += Math.random() * 8
  }, 200)

  const startTime = Date.now()
  try {
    const formData = new FormData()
    formData.append('image', file)

    const response = await fetch('/api/images/upload', {
      method: 'POST',
      body: formData,
    })

    if (!response.ok) {
      const text = await response.text()
      throw new Error(text || `서버 오류 (${response.status})`)
    }

    const blob = await response.blob()
    resultImageUrl.value = URL.createObjectURL(blob)

    const elapsed = ((Date.now() - startTime) / 1000).toFixed(2)
    imageInfo.value = {
      name: file.name,
      originalSize: formatSize(file.size),
      elapsed,
    }

    progress.value = 100
    setTimeout(() => { isProcessing.value = false }, 300)
  } catch (err) {
    isProcessing.value = false
    uploadError.value = err.message || '알 수 없는 오류가 발생했습니다.'
  } finally {
    clearInterval(progressInterval)
  }
}

function formatSize(bytes) {
  if (bytes >= 1024 * 1024) return (bytes / (1024 * 1024)).toFixed(1) + 'MB'
  return (bytes / 1024).toFixed(0) + 'KB'
}

function downloadResult() {
  if (!resultImageUrl.value) return
  const a = document.createElement('a')
  a.href = resultImageUrl.value
  const baseName = imageInfo.value.name.replace(/\.[^.]+$/, '')
  a.download = `${baseName}_no_bg.png`
  a.click()
}

function reset() {
  if (originalImageUrl.value) URL.revokeObjectURL(originalImageUrl.value)
  if (resultImageUrl.value) URL.revokeObjectURL(resultImageUrl.value)
  uploadedImage.value = null
  isProcessing.value = false
  uploadError.value = null
  progress.value = 0
  viewMode.value = 'split'
  originalImageUrl.value = null
  resultImageUrl.value = null
  if (fileInput.value) fileInput.value.value = ''
}
</script>

<style scoped>
.remove-view {
  max-width: 1200px;
}

/* 업로드 섹션 */
.upload-section {
  display: flex;
  flex-direction: column;
  gap: 40px;
}

.drop-zone {
  position: relative;
  border: 2px dashed var(--border);
  border-radius: var(--radius-xl);
  padding: 80px 40px;
  text-align: center;
  cursor: pointer;
  transition: all 0.3s;
  background: var(--bg-card);
  overflow: hidden;
}

.drop-zone:hover {
  border-color: rgba(124, 58, 237, 0.5);
  background: rgba(124, 58, 237, 0.04);
}

.drop-zone.dragging {
  border-color: var(--purple);
  background: rgba(124, 58, 237, 0.08);
}

.drop-zone-inner {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.drop-icon {
  width: 80px;
  height: 80px;
  background: var(--gradient-bg);
  border: 1px solid rgba(124, 58, 237, 0.2);
  border-radius: var(--radius-xl);
  display: flex;
  align-items: center;
  justify-content: center;
  color: #a78bfa;
  margin-bottom: 8px;
}

.drop-zone-inner h3 {
  font-size: 20px;
  font-weight: 600;
  color: var(--text-primary);
}

.drop-zone-inner p {
  font-size: 14px;
  color: var(--text-muted);
  margin-bottom: 8px;
}

.upload-btn {
  margin-top: 4px;
}

.drag-overlay {
  position: absolute;
  inset: 0;
  background: rgba(124, 58, 237, 0.15);
  backdrop-filter: blur(4px);
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: var(--radius-xl);
}

.drag-overlay-inner {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
  color: #c4b5fd;
}

.drag-overlay-inner p {
  font-size: 20px;
  font-weight: 600;
}

.hidden-input {
  display: none;
}

/* 예시 이미지 */
.examples {
  text-align: center;
}

.examples-label {
  font-size: 13px;
  color: var(--text-muted);
  margin-bottom: 16px;
  font-weight: 500;
}

.examples-grid {
  display: flex;
  gap: 12px;
  justify-content: center;
}

.example-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  transition: transform 0.2s;
}

.example-item:hover {
  transform: translateY(-2px);
}

.example-img {
  width: 80px;
  height: 80px;
  border-radius: var(--radius-md);
  border: 1px solid var(--border);
  position: relative;
  overflow: hidden;
  transition: border-color 0.2s;
}

.example-item:hover .example-img {
  border-color: rgba(124, 58, 237, 0.5);
}

.example-person {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 50%;
  height: 70%;
  background: rgba(255,255,255,0.3);
  border-radius: 40% 40% 0 0;
}

.example-item span {
  font-size: 12px;
  color: var(--text-secondary);
  font-weight: 500;
}

/* 처리 중 */
.processing-section {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
}

.processing-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  max-width: 320px;
  text-align: center;
}

.processing-animation {
  position: relative;
  width: 100px;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.processing-ring {
  position: absolute;
  border-radius: 50%;
  border: 2px solid transparent;
  border-top-color: var(--purple);
  animation: spin 1s linear infinite;
}

.ring-1 {
  width: 100px;
  height: 100px;
  animation-duration: 1.2s;
}

.ring-2 {
  width: 76px;
  height: 76px;
  border-top-color: var(--indigo);
  animation-direction: reverse;
  animation-duration: 0.9s;
}

.ring-3 {
  width: 52px;
  height: 52px;
  border-top-color: var(--cyan);
  animation-duration: 0.7s;
}

@keyframes spin {
  to { transform: rotate(360deg); }
}

.processing-center {
  position: relative;
  z-index: 1;
}

.processing-card h3 {
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
}

.processing-card p {
  font-size: 14px;
  color: var(--text-secondary);
}

.progress-bar {
  width: 200px;
  height: 4px;
  background: var(--border);
  border-radius: 2px;
  overflow: hidden;
}

.progress-fill {
  height: 100%;
  background: var(--gradient);
  border-radius: 2px;
  transition: width 0.1s ease;
}

.progress-text {
  font-size: 13px;
  color: var(--text-muted);
  font-weight: 600;
}

/* 결과 섹션 */
.result-section {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.result-toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  flex-wrap: wrap;
}

.toolbar-left,
.toolbar-right {
  display: flex;
  align-items: center;
  gap: 12px;
}

.view-toggle {
  display: flex;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  padding: 3px;
  gap: 2px;
}

.toggle-btn {
  padding: 7px 14px;
  font-size: 13px;
  font-weight: 500;
  color: var(--text-muted);
  background: transparent;
  border: none;
  border-radius: var(--radius-sm);
  cursor: pointer;
  transition: all 0.2s;
}

.toggle-btn.active {
  background: var(--bg-surface);
  color: var(--text-primary);
  box-shadow: var(--shadow-sm);
}

/* 이미지 뷰어 */
.image-viewer {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-xl);
  overflow: hidden;
  min-height: 480px;
  display: flex;
  align-items: stretch;
}

.split-view {
  display: grid;
  grid-template-columns: 1fr auto 1fr;
  width: 100%;
}

.image-panel {
  display: flex;
  flex-direction: column;
  padding: 24px;
  gap: 12px;
}

.panel-label {
  font-size: 12px;
  font-weight: 600;
  color: var(--text-muted);
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.image-container {
  flex: 1;
  border-radius: var(--radius-lg);
  overflow: hidden;
  border: 1px solid var(--border);
}

.transparent-bg {
  background-image:
    linear-gradient(45deg, #1a1a2e 25%, transparent 25%),
    linear-gradient(-45deg, #1a1a2e 25%, transparent 25%),
    linear-gradient(45deg, transparent 75%, #1a1a2e 75%),
    linear-gradient(-45deg, transparent 75%, #1a1a2e 75%);
  background-size: 20px 20px;
  background-position: 0 0, 0 10px, 10px -10px, -10px 0;
}

.split-divider {
  width: 1px;
  background: var(--border);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: relative;
}

.divider-dot {
  width: 28px;
  height: 28px;
  background: var(--bg-surface);
  border: 2px solid var(--purple);
  border-radius: 50%;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
}

.mock-image {
  width: 100%;
  height: 100%;
  min-height: 360px;
  position: relative;
  display: flex;
  align-items: flex-end;
  justify-content: center;
  overflow: hidden;
}

.mock-image.transparent {
  background: transparent;
}

.mock-image.large {
  min-height: 440px;
}

.mock-person {
  width: 55%;
  height: 80%;
  border-radius: 40% 40% 20% 20% / 50% 50% 30% 30%;
  position: absolute;
  bottom: 0;
  opacity: 0.85;
}

.mock-bg {
  position: absolute;
  inset: 0;
  opacity: 0.4;
}

.single-view {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 24px;
}

/* 이미지 정보 바 */
.image-info-bar {
  display: flex;
  align-items: center;
  gap: 24px;
  padding: 16px 24px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  flex-wrap: wrap;
}

.info-item {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.info-label {
  font-size: 11px;
  font-weight: 600;
  color: var(--text-muted);
  text-transform: uppercase;
  letter-spacing: 0.05em;
}

.info-value {
  font-size: 13px;
  font-weight: 500;
  color: var(--text-secondary);
}

.info-value.success {
  color: #34d399;
}

.info-divider {
  width: 1px;
  height: 32px;
  background: var(--border);
}

/* 결과 이미지 */
.result-image {
  width: 100%;
  height: 100%;
  object-fit: contain;
  display: block;
}

.result-image.large {
  min-height: 440px;
  max-height: 600px;
}

/* 에러 섹션 */
.error-section {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 400px;
}

.error-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
  max-width: 320px;
  text-align: center;
  padding: 40px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-xl);
}

.error-icon {
  width: 64px;
  height: 64px;
  background: rgba(239, 68, 68, 0.1);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #f87171;
}

.error-card h3 {
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
}

.error-card p {
  font-size: 14px;
  color: var(--text-secondary);
  word-break: break-all;
}
</style>
