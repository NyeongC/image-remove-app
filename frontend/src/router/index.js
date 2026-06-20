import { createRouter, createWebHistory } from 'vue-router'
import LandingView from '../views/LandingView.vue'

const ACCESS_TOKEN_KEY = 'accessToken'
const REFRESH_TOKEN_KEY = 'refreshToken'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landing',
      component: LandingView,
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/LoginView.vue'),
    },
    {
      path: '/app',
      component: () => import('../views/app/AppLayout.vue'),
      meta: { requiresAuth: true },
      children: [
        {
          path: '',
          name: 'app-remove',
          component: () => import('../views/app/RemoveView.vue'),
        },
        {
          path: 'history',
          name: 'app-history',
          component: () => import('../views/app/HistoryView.vue'),
        },
        {
          path: 'settings',
          name: 'app-settings',
          component: () => import('../views/app/SettingsView.vue'),
        },
        {
          path: 'lb-test',
          name: 'app-lb-test',
          component: () => import('../views/app/LbTestView.vue'),
        },
        {
          path: 'request-context-test',
          name: 'app-rc-test',
          component: () => import('../views/app/RequestContextTestView.vue'),
        },
      ],
    },
  ],
})

function isTokenValid(token) {
  try {
    const base64 = token.split('.')[1].replace(/-/g, '+').replace(/_/g, '/')
    const payload = JSON.parse(atob(base64))
    return payload.exp * 1000 > Date.now()
  } catch {
    return false
  }
}

router.beforeEach((to, from, next) => {
  const accessToken = to.query.token
  const refreshToken = to.query.refreshToken

  if (accessToken && refreshToken) {
    localStorage.setItem(ACCESS_TOKEN_KEY, accessToken)
    localStorage.setItem(REFRESH_TOKEN_KEY, refreshToken)

    next({
      path: to.path,
      query: {},
      replace: true,
    })

    return
  }

  if (to.meta.requiresAuth) {
    const savedToken = localStorage.getItem(ACCESS_TOKEN_KEY)

    if (!savedToken || !isTokenValid(savedToken)) {
      localStorage.removeItem(ACCESS_TOKEN_KEY)
      localStorage.removeItem(REFRESH_TOKEN_KEY)
      next('/login')
      return
    }
  }

  next()
})

export default router