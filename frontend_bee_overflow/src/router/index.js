import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DocumentView from '@/views/DocumentView.vue'
import TagView from '@/views/TagView.vue'
import ListUserView from '@/views/ListUserView.vue'
import UserinfoView from '@/views/UserinfoView.vue'
import QuestionView from '@/views/QuestionView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    {
      path: '/questions',
      name: 'questions',
      component: QuestionView,
    },
    {
      path: '/userinfo',
      name: 'userinfo',
      component: UserinfoView,
    },
    {
      path: '/listuser',
      name: 'listuser',
      component: ListUserView,
    },
    {
      path: '/tag',
      name: 'tag',
      component: TagView,
    },
    {
      path: '/document',
      name: 'document',
      component: DocumentView,
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView,
    },
  ],
})

export default router
