<template>
  <div class="app-layout" :class="{ 'sidebar-collapsed': sidebarCollapsed }">
    <!-- 사이드바 -->
    <aside class="sidebar">
      <div class="sidebar-top">
        <!-- 로고 -->
        <RouterLink to="/" class="sidebar-logo">
          <div class="logo-icon">✦</div>
          <Transition name="fade">
            <span v-if="!sidebarCollapsed">Erasify</span>
          </Transition>
        </RouterLink>

        <!-- 접기 버튼 -->
        <button class="collapse-btn" @click="sidebarCollapsed = !sidebarCollapsed">
          <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <path v-if="!sidebarCollapsed" d="M15 18l-6-6 6-6"/>
            <path v-else d="M9 18l6-6-6-6"/>
          </svg>
        </button>
      </div>

      <!-- 네비게이션 -->
      <nav class="sidebar-nav">
        <div class="nav-section-label" v-if="!sidebarCollapsed">메인</div>

        <RouterLink to="/app" class="nav-item" exact>
          <div class="nav-icon">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14M14 8h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z"/>
            </svg>
          </div>
          <Transition name="fade">
            <span v-if="!sidebarCollapsed">배경 제거</span>
          </Transition>
          <Transition name="fade">
            <div v-if="!sidebarCollapsed" class="nav-badge">NEW</div>
          </Transition>
        </RouterLink>

        <RouterLink to="/app/history" class="nav-item">
          <div class="nav-icon">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"/>
            </svg>
          </div>
          <Transition name="fade">
            <span v-if="!sidebarCollapsed">히스토리</span>
          </Transition>
        </RouterLink>

        <div class="nav-section-label" v-if="!sidebarCollapsed">계정</div>

        <RouterLink to="/app/settings" class="nav-item">
          <div class="nav-icon">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.65 1.65 0 00.33 1.82l.06.06a2 2 0 010 2.83 2 2 0 01-2.83 0l-.06-.06a1.65 1.65 0 00-1.82-.33 1.65 1.65 0 00-1 1.51V21a2 2 0 01-2 2 2 2 0 01-2-2v-.09A1.65 1.65 0 009 19.4a1.65 1.65 0 00-1.82.33l-.06.06a2 2 0 01-2.83 0 2 2 0 010-2.83l.06-.06A1.65 1.65 0 004.68 15a1.65 1.65 0 00-1.51-1H3a2 2 0 01-2-2 2 2 0 012-2h.09A1.65 1.65 0 004.6 9a1.65 1.65 0 00-.33-1.82l-.06-.06a2 2 0 010-2.83 2 2 0 012.83 0l.06.06A1.65 1.65 0 009 4.68a1.65 1.65 0 001-1.51V3a2 2 0 012-2 2 2 0 012 2v.09a1.65 1.65 0 001 1.51 1.65 1.65 0 001.82-.33l.06-.06a2 2 0 012.83 0 2 2 0 010 2.83l-.06.06A1.65 1.65 0 0019.4 9a1.65 1.65 0 001.51 1H21a2 2 0 012 2 2 2 0 01-2 2h-.09a1.65 1.65 0 00-1.51 1z"/>
            </svg>
          </div>
          <Transition name="fade">
            <span v-if="!sidebarCollapsed">설정</span>
          </Transition>
        </RouterLink>

        <div class="nav-section-label" v-if="!sidebarCollapsed">개발</div>

        <RouterLink to="/app/lb-test" class="nav-item">
          <div class="nav-icon">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <polyline points="22 12 18 12 15 21 9 3 6 12 2 12"/>
            </svg>
          </div>
          <Transition name="fade">
            <span v-if="!sidebarCollapsed">LB 테스트</span>
          </Transition>
        </RouterLink>
      </nav>

      <!-- 사용량 -->
      <Transition name="fade">
        <div v-if="!sidebarCollapsed" class="usage-card">
          <div class="usage-header">
            <span>이번 달 사용량</span>
            <span class="usage-count">3 / 10</span>
          </div>
          <div class="usage-bar">
            <div class="usage-fill" style="width: 30%"></div>
          </div>
          <RouterLink to="/login" class="upgrade-btn">Pro 업그레이드</RouterLink>
        </div>
      </Transition>

      <!-- 유저 프로필 -->
      <div class="sidebar-user">
        <div class="user-avatar">J</div>
        <Transition name="fade">
          <div v-if="!sidebarCollapsed" class="user-info">
            <span class="user-name">홍길동</span>
            <span class="user-plan">무료 플랜</span>
          </div>
        </Transition>
        <Transition name="fade">
          <button v-if="!sidebarCollapsed" class="user-menu-btn">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <circle cx="12" cy="12" r="1"/><circle cx="19" cy="12" r="1"/><circle cx="5" cy="12" r="1"/>
            </svg>
          </button>
        </Transition>
      </div>
    </aside>

    <!-- 메인 영역 -->
    <div class="main-area">
      <!-- 상단 헤더 -->
      <header class="app-header">
        <div class="header-left">
          <h1 class="page-title">{{ pageTitle }}</h1>
        </div>
        <div class="header-right">
          <div class="header-credits">
            <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M13 2L3 14h9l-1 8 10-12h-9l1-8z"/>
            </svg>
            <span>7 크레딧 남음</span>
          </div>
          <RouterLink to="/" class="header-btn">
            <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
              <path d="M3 9l9-7 9 7v11a2 2 0 01-2 2H5a2 2 0 01-2-2z"/><polyline points="9 22 9 12 15 12 15 22"/>
            </svg>
            홈
          </RouterLink>
        </div>
      </header>

      <!-- 페이지 컨텐츠 -->
      <main class="page-content">
        <RouterView />
      </main>
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
</template>

<script setup>
import { ref, computed } from 'vue'
import { RouterLink, RouterView, useRoute } from 'vue-router'

const sidebarCollapsed = ref(false)
const route = useRoute()

const pageTitle = computed(() => {
  const titles = {
    'app-remove': '배경 제거',
    'app-history': '히스토리',
    'app-settings': '설정',
    'app-lb-test': 'LB 테스트',
  }
  return titles[route.name] ?? '배경 제거'
})
</script>

<style scoped>
.app-layout {
  display: grid;
  grid-template-columns: var(--sidebar-width) 1fr;
  min-height: 100vh;
  transition: grid-template-columns 0.3s cubic-bezier(0.4, 0, 0.2, 1);
}

.app-layout.sidebar-collapsed {
  grid-template-columns: 72px 1fr;
}

/* 사이드바 */
.sidebar {
  background: var(--bg-surface);
  border-right: 1px solid var(--border);
  display: flex;
  flex-direction: column;
  position: sticky;
  top: 0;
  height: 100vh;
  overflow: hidden;
  padding: 16px 12px;
  gap: 8px;
}

.sidebar-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 4px 4px 12px;
  border-bottom: 1px solid var(--border);
  margin-bottom: 4px;
  min-height: 56px;
}

.sidebar-logo {
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 18px;
  font-weight: 700;
  color: var(--text-primary);
  text-decoration: none;
  white-space: nowrap;
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
  flex-shrink: 0;
}

.collapse-btn {
  width: 28px;
  height: 28px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-sm);
  color: var(--text-muted);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  flex-shrink: 0;
  transition: all 0.2s;
}

.collapse-btn:hover {
  color: var(--text-primary);
  border-color: var(--border-hover);
}

/* 네비게이션 */
.sidebar-nav {
  display: flex;
  flex-direction: column;
  gap: 2px;
  flex: 1;
}

.nav-section-label {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  color: var(--text-muted);
  padding: 8px 8px 4px;
  margin-top: 8px;
  white-space: nowrap;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 8px;
  border-radius: var(--radius-md);
  color: var(--text-secondary);
  font-size: 14px;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.2s;
  white-space: nowrap;
  position: relative;
}

.nav-item:hover {
  background: var(--bg-card);
  color: var(--text-primary);
}

.nav-item.router-link-active {
  background: rgba(124, 58, 237, 0.15);
  color: #c4b5fd;
  border: 1px solid rgba(124, 58, 237, 0.2);
}

.nav-icon {
  width: 32px;
  height: 32px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}

.nav-badge {
  margin-left: auto;
  font-size: 10px;
  font-weight: 700;
  padding: 2px 7px;
  background: var(--gradient);
  border-radius: 100px;
  color: white;
  letter-spacing: 0.05em;
}

/* 사용량 카드 */
.usage-card {
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  padding: 14px;
  margin-top: auto;
}

.usage-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.usage-header span {
  font-size: 12px;
  color: var(--text-secondary);
  font-weight: 500;
}

.usage-count {
  font-weight: 700 !important;
  color: var(--text-primary) !important;
}

.usage-bar {
  height: 4px;
  background: var(--border);
  border-radius: 2px;
  overflow: hidden;
  margin-bottom: 12px;
}

.usage-fill {
  height: 100%;
  background: var(--gradient);
  border-radius: 2px;
}

.upgrade-btn {
  display: block;
  text-align: center;
  padding: 8px;
  background: rgba(124, 58, 237, 0.15);
  border: 1px solid rgba(124, 58, 237, 0.3);
  border-radius: var(--radius-sm);
  font-size: 12px;
  font-weight: 600;
  color: #a78bfa;
  text-decoration: none;
  transition: all 0.2s;
}

.upgrade-btn:hover {
  background: rgba(124, 58, 237, 0.25);
}

/* 유저 프로필 */
.sidebar-user {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px 8px;
  border-radius: var(--radius-md);
  cursor: pointer;
  transition: all 0.2s;
  border-top: 1px solid var(--border);
  padding-top: 16px;
  margin-top: 4px;
  overflow: hidden;
}

.sidebar-user:hover {
  background: var(--bg-card);
}

.user-avatar {
  width: 32px;
  height: 32px;
  background: var(--gradient);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
  font-weight: 700;
  color: white;
  flex-shrink: 0;
}

.user-info {
  display: flex;
  flex-direction: column;
  gap: 2px;
  overflow: hidden;
  flex: 1;
}

.user-name {
  font-size: 13px;
  font-weight: 600;
  color: var(--text-primary);
  white-space: nowrap;
}

.user-plan {
  font-size: 11px;
  color: var(--text-muted);
  white-space: nowrap;
}

.user-menu-btn {
  background: transparent;
  border: none;
  color: var(--text-muted);
  display: flex;
  align-items: center;
  cursor: pointer;
  padding: 4px;
  flex-shrink: 0;
}

/* 메인 영역 */
.main-area {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  overflow-y: auto;
}

/* 헤더 */
.app-header {
  height: var(--header-height);
  border-bottom: 1px solid var(--border);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
  position: sticky;
  top: 0;
  background: var(--bg-base);
  z-index: 50;
  backdrop-filter: blur(20px);
  flex-shrink: 0;
}

.page-title {
  font-size: 18px;
  font-weight: 700;
  color: var(--text-primary);
}

.header-right {
  display: flex;
  align-items: center;
  gap: 12px;
}

.header-credits {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 6px 14px;
  background: rgba(124, 58, 237, 0.12);
  border: 1px solid rgba(124, 58, 237, 0.25);
  border-radius: 100px;
  font-size: 13px;
  font-weight: 600;
  color: #a78bfa;
}

.header-btn {
  display: flex;
  align-items: center;
  gap: 6px;
  padding: 8px 16px;
  background: var(--bg-card);
  border: 1px solid var(--border);
  border-radius: var(--radius-md);
  font-size: 13px;
  font-weight: 500;
  color: var(--text-secondary);
  text-decoration: none;
  transition: all 0.2s;
}

.header-btn:hover {
  color: var(--text-primary);
  border-color: var(--border-hover);
}

/* 페이지 컨텐츠 */
.page-content {
  flex: 1;
  padding: 32px;
}

/* 전환 애니메이션 */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.15s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
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
