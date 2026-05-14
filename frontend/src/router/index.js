import { createRouter, createWebHistory } from 'vue-router'
import LandingView from '../views/LandingView.vue'

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
      ],
    },
  ],
})

export default router
