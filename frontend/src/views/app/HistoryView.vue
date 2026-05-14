<template>
  <div class="history-view">
    <!-- 상단 필터/검색 -->
    <div class="history-header">
      <div class="search-bar">
        <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <circle cx="11" cy="11" r="8"/><path d="m21 21-4.35-4.35"/>
        </svg>
        <input type="text" v-model="searchQuery" placeholder="파일명으로 검색..." />
      </div>

      <div class="filter-row">
        <div class="filter-tabs">
          <button
            v-for="filter in filters"
            :key="filter.value"
            class="filter-tab"
            :class="{ active: activeFilter === filter.value }"
            @click="activeFilter = filter.value"
          >
            {{ filter.label }}
          </button>
        </div>

        <div class="view-mode-toggle">
          <button class="mode-btn" :class="{ active: gridMode === 'grid' }" @click="gridMode = 'grid'">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/>
              <rect x="14" y="14" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/>
            </svg>
          </button>
          <button class="mode-btn" :class="{ active: gridMode === 'list' }" @click="gridMode = 'list'">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <line x1="8" y1="6" x2="21" y2="6"/><line x1="8" y1="12" x2="21" y2="12"/><line x1="8" y1="18" x2="21" y2="18"/>
              <line x1="3" y1="6" x2="3.01" y2="6"/><line x1="3" y1="12" x2="3.01" y2="12"/><line x1="3" y1="18" x2="3.01" y2="18"/>
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- 통계 -->
    <div class="stats-row">
      <div class="stat-card">
        <div class="stat-icon purple">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14"/>
          </svg>
        </div>
        <div>
          <strong>24</strong>
          <span>전체 이미지</span>
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-icon cyan">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z"/>
          </svg>
        </div>
        <div>
          <strong>3</strong>
          <span>이번 달 처리</span>
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-icon green">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
          </svg>
        </div>
        <div>
          <strong>18</strong>
          <span>다운로드</span>
        </div>
      </div>
      <div class="stat-card">
        <div class="stat-icon pink">
          <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
          </svg>
        </div>
        <div>
          <strong>0.4초</strong>
          <span>평균 처리시간</span>
        </div>
      </div>
    </div>

    <!-- 히스토리 그리드 -->
    <div v-if="gridMode === 'grid'" class="history-grid">
      <div
        v-for="item in filteredItems"
        :key="item.id"
        class="history-card"
        @click="selectedItem = item"
      >
        <div class="card-images">
          <div class="card-image before" :style="{ background: item.gradient }">
            <div class="img-person"></div>
            <div class="img-bg"></div>
          </div>
          <div class="card-arrow">
            <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3">
              <path d="M5 12h14M12 5l7 7-7 7"/>
            </svg>
          </div>
          <div class="card-image after transparent-bg">
            <div class="img-person" :style="{ background: item.personGradient }"></div>
          </div>
        </div>
        <div class="card-info">
          <div class="card-name">{{ item.name }}</div>
          <div class="card-meta">
            <span class="card-date">{{ item.date }}</span>
            <span class="card-size">{{ item.size }}</span>
          </div>
        </div>
        <div class="card-actions">
          <button class="card-btn" title="다운로드">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
            </svg>
          </button>
          <button class="card-btn delete" title="삭제">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="3 6 5 6 21 6"/><path d="M19 6l-1 14a2 2 0 01-2 2H8a2 2 0 01-2-2L5 6m3 0V4a1 1 0 011-1h4a1 1 0 011 1v2"/>
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- 히스토리 리스트 -->
    <div v-else class="history-list">
      <div
        v-for="item in filteredItems"
        :key="item.id"
        class="list-item"
      >
        <div class="list-thumbnails">
          <div class="thumb before" :style="{ background: item.gradient }">
            <div class="thumb-person"></div>
          </div>
          <svg width="12" height="12" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" class="list-arrow">
            <path d="M5 12h14M12 5l7 7-7 7"/>
          </svg>
          <div class="thumb after transparent-bg">
            <div class="thumb-person" :style="{ background: item.personGradient }"></div>
          </div>
        </div>
        <div class="list-info">
          <span class="list-name">{{ item.name }}</span>
          <span class="list-size">{{ item.size }}</span>
        </div>
        <div class="list-date">{{ item.date }}</div>
        <div class="list-status">
          <span class="status-badge">완료</span>
        </div>
        <div class="list-actions">
          <button class="card-btn">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M21 15v4a2 2 0 01-2 2H5a2 2 0 01-2-2v-4M17 8l-5-5-5 5M12 3v12"/>
            </svg>
          </button>
          <button class="card-btn delete">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="3 6 5 6 21 6"/><path d="M19 6l-1 14a2 2 0 01-2 2H8a2 2 0 01-2-2L5 6m3 0V4a1 1 0 011-1h4a1 1 0 011 1v2"/>
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- 빈 상태 -->
    <div v-if="filteredItems.length === 0" class="empty-state">
      <div class="empty-icon">
        <svg width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
          <path d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14M14 8h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
        </svg>
      </div>
      <h3>이미지가 없습니다</h3>
      <p>배경 제거 후 여기에서 확인할 수 있습니다</p>
      <RouterLink to="/app" class="btn-primary">배경 제거 시작</RouterLink>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { RouterLink } from 'vue-router'

const searchQuery = ref('')
const activeFilter = ref('all')
const gridMode = ref('grid')
const selectedItem = ref(null)

const filters = [
  { label: '전체', value: 'all' },
  { label: '이번 주', value: 'week' },
  { label: '이번 달', value: 'month' },
]

const historyItems = [
  {
    id: 1, name: 'profile_photo.jpg', date: '2026-05-14', size: '4.2MB',
    gradient: 'linear-gradient(135deg, #667eea, #764ba2)',
    personGradient: 'linear-gradient(180deg, #fbbf24, #f59e0b)',
    category: 'week',
  },
  {
    id: 2, name: 'product_shoot.png', date: '2026-05-13', size: '2.8MB',
    gradient: 'linear-gradient(135deg, #f093fb, #f5576c)',
    personGradient: 'linear-gradient(180deg, #60a5fa, #3b82f6)',
    category: 'week',
  },
  {
    id: 3, name: 'dog_photo.jpg', date: '2026-05-12', size: '3.1MB',
    gradient: 'linear-gradient(135deg, #4facfe, #00f2fe)',
    personGradient: 'linear-gradient(180deg, #a78bfa, #7c3aed)',
    category: 'week',
  },
  {
    id: 4, name: 'car_image.jpg', date: '2026-05-10', size: '5.7MB',
    gradient: 'linear-gradient(135deg, #43e97b, #38f9d7)',
    personGradient: 'linear-gradient(180deg, #fb7185, #f43f5e)',
    category: 'month',
  },
  {
    id: 5, name: 'team_photo.png', date: '2026-05-08', size: '6.2MB',
    gradient: 'linear-gradient(135deg, #fa709a, #fee140)',
    personGradient: 'linear-gradient(180deg, #34d399, #10b981)',
    category: 'month',
  },
  {
    id: 6, name: 'logo_design.png', date: '2026-05-05', size: '1.4MB',
    gradient: 'linear-gradient(135deg, #a18cd1, #fbc2eb)',
    personGradient: 'linear-gradient(180deg, #fbbf24, #f59e0b)',
    category: 'month',
  },
]

const filteredItems = computed(() => {
  let items = historyItems
  if (activeFilter.value !== 'all') {
    items = items.filter(i => i.category === activeFilter.value)
  }
  if (searchQuery.value) {
    items = items.filter(i => i.name.toLowerCase().includes(searchQuery.value.toLowerCase()))
  }
  return items
})
</script>

<style scoped>
.history-view {
  display: flex;
  flex-direction: column;
  gap: 24px;
  max-width: 1200px;
}

/* 헤더 */
.history-header {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.search-bar {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 12px 16px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  max-width: 400px;
  transition: border-color 0.2s;
}

.search-bar:focus-within {
  border-color: var(--border-focus);
  box-shadow: 0 0 0 3px rgba(124, 58, 237, 0.1);
}

.search-bar svg {
  color: var(--text-muted);
  flex-shrink: 0;
}

.search-bar input {
  background: transparent;
  border: none;
  color: var(--text-primary);
  font-size: 14px;
  width: 100%;
}

.search-bar input::placeholder {
  color: var(--text-muted);
}

.filter-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
}

.filter-tabs {
  display: flex;
  gap: 4px;
}

.filter-tab {
  padding: 7px 16px;
  font-size: 13px;
  font-weight: 500;
  color: var(--text-muted);
  background: transparent;
  border: 1px solid transparent;
  border-radius: var(--radius-md);
  cursor: pointer;
  transition: all 0.2s;
}

.filter-tab:hover {
  color: var(--text-secondary);
  background: var(--bg-card);
}

.filter-tab.active {
  color: #a78bfa;
  background: rgba(124, 58, 237, 0.12);
  border-color: rgba(124, 58, 237, 0.25);
}

.view-mode-toggle {
  display: flex;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  padding: 3px;
  gap: 2px;
}

.mode-btn {
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: transparent;
  border: none;
  border-radius: var(--radius-sm);
  color: var(--text-muted);
  cursor: pointer;
  transition: all 0.2s;
}

.mode-btn.active {
  background: var(--bg-surface);
  color: var(--text-primary);
}

/* 통계 */
.stats-row {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
}

.stat-card {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 16px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
}

.stat-icon {
  width: 40px;
  height: 40px;
  border-radius: var(--radius-md);
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.stat-icon.purple {
  background: rgba(124, 58, 237, 0.15);
  color: #a78bfa;
}

.stat-icon.cyan {
  background: rgba(6, 182, 212, 0.15);
  color: #22d3ee;
}

.stat-icon.green {
  background: rgba(52, 211, 153, 0.15);
  color: #34d399;
}

.stat-icon.pink {
  background: rgba(236, 72, 153, 0.15);
  color: #f472b6;
}

.stat-card > div:last-child {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.stat-card strong {
  font-size: 20px;
  font-weight: 700;
  color: var(--text-primary);
  display: block;
}

.stat-card span {
  font-size: 12px;
  color: var(--text-muted);
}

/* 그리드 */
.history-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 16px;
}

.history-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  overflow: hidden;
  cursor: pointer;
  transition: all 0.25s;
  position: relative;
}

.history-card:hover {
  border-color: rgba(124, 58, 237, 0.4);
  transform: translateY(-2px);
  box-shadow: var(--shadow-md);
}

.history-card:hover .card-actions {
  opacity: 1;
}

.card-images {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 12px;
  height: 160px;
}

.card-image {
  flex: 1;
  height: 100%;
  border-radius: var(--radius-md);
  position: relative;
  overflow: hidden;
}

.card-arrow {
  color: var(--text-muted);
  flex-shrink: 0;
}

.img-person {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 55%;
  height: 80%;
  background: rgba(255,255,255,0.4);
  border-radius: 40% 40% 0 0;
}

.img-bg {
  position: absolute;
  inset: 0;
  background: rgba(0,0,0,0.2);
}

.card-info {
  padding: 0 12px 12px;
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.card-name {
  font-size: 13px;
  font-weight: 600;
  color: var(--text-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.card-meta {
  display: flex;
  gap: 8px;
}

.card-date,
.card-size {
  font-size: 11px;
  color: var(--text-muted);
}

.card-actions {
  position: absolute;
  top: 8px;
  right: 8px;
  display: flex;
  gap: 4px;
  opacity: 0;
  transition: opacity 0.2s;
}

.card-btn {
  width: 28px;
  height: 28px;
  background: var(--bg-surface);
  border: 1px solid var(--border);
  border-radius: var(--radius-sm);
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--text-secondary);
  cursor: pointer;
  transition: all 0.2s;
}

.card-btn:hover {
  background: var(--bg-card-hover);
  color: var(--text-primary);
}

.card-btn.delete:hover {
  background: rgba(239, 68, 68, 0.15);
  border-color: rgba(239, 68, 68, 0.3);
  color: #f87171;
}

/* 리스트 뷰 */
.history-list {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.list-item {
  display: grid;
  grid-template-columns: auto 1fr auto auto auto;
  align-items: center;
  gap: 16px;
  padding: 12px 16px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-lg);
  transition: all 0.2s;
}

.list-item:hover {
  border-color: rgba(124, 58, 237, 0.3);
  background: var(--bg-card-hover);
}

.list-thumbnails {
  display: flex;
  align-items: center;
  gap: 8px;
}

.thumb {
  width: 48px;
  height: 48px;
  border-radius: var(--radius-sm);
  position: relative;
  overflow: hidden;
}

.thumb-person {
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 55%;
  height: 80%;
  background: rgba(255,255,255,0.4);
  border-radius: 40% 40% 0 0;
}

.list-arrow {
  color: var(--text-muted);
}

.list-info {
  display: flex;
  flex-direction: column;
  gap: 4px;
  min-width: 0;
}

.list-name {
  font-size: 14px;
  font-weight: 500;
  color: var(--text-primary);
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.list-size {
  font-size: 12px;
  color: var(--text-muted);
}

.list-date {
  font-size: 13px;
  color: var(--text-muted);
  white-space: nowrap;
}

.status-badge {
  display: inline-block;
  padding: 3px 10px;
  background: rgba(52, 211, 153, 0.15);
  border: 1px solid rgba(52, 211, 153, 0.25);
  border-radius: 100px;
  font-size: 12px;
  font-weight: 600;
  color: #34d399;
}

.list-actions {
  display: flex;
  gap: 6px;
}

/* 공통 */
.transparent-bg {
  background-image:
    linear-gradient(45deg, #1a1a2e 25%, transparent 25%),
    linear-gradient(-45deg, #1a1a2e 25%, transparent 25%),
    linear-gradient(45deg, transparent 75%, #1a1a2e 75%),
    linear-gradient(-45deg, transparent 75%, #1a1a2e 75%);
  background-size: 16px 16px;
  background-position: 0 0, 0 8px, 8px -8px, -8px 0;
}

/* 빈 상태 */
.empty-state {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 80px 40px;
  gap: 16px;
  text-align: center;
}

.empty-icon {
  width: 72px;
  height: 72px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-xl);
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--text-muted);
  margin-bottom: 8px;
}

.empty-state h3 {
  font-size: 18px;
  font-weight: 600;
  color: var(--text-primary);
}

.empty-state p {
  font-size: 14px;
  color: var(--text-secondary);
  margin-bottom: 8px;
}
</style>
