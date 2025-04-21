import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import DocumentView from '@/views/DocumentView.vue'
import TagView from '@/views/TagView.vue'
import ListUserView from '@/views/ListUserView.vue'
import UserinfoView from '@/views/UserinfoView.vue'
import QuestionView from '@/views/QuestionView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import QuestionInputView from '@/views/QuestionInputView.vue'
import UploadImageView from '@/views/UploadImageView.vue'
import CheckQuestionView from '@/views/AdminViews/CheckQuestionView.vue'
import DocumentAdminView from '@/views/AdminViews/DocumentAdminView.vue'
import StatisticsTableView from '@/views/AdminViews/StatisticsTableView.vue'
import UserBlackListView from '@/views/AdminViews/UserBlackListView.vue'
import QuestionBlackListView from '@/views/AdminViews/QuestionBlackListView.vue'
import QuestionDetailView from '@/views/QuestionDetailView.vue'
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
    {
      path: '/question/add',
      name: 'add',
      component: QuestionInputView,
    },
    {
      path: '/question/edit/:id',
      name: 'edit',
      component: QuestionInputView,
    },
    {
      path: '/upload',
      name: 'upload',
      component: UploadImageView,
    },
    {
      path: '/blacklist/users',
      name: 'userblacklist',
      component: UserBlackListView,
    },
    {
      path: '/blacklist/questions',
      name: 'questionblacklist',
      component: QuestionBlackListView,
    },
    {
      path: '/checkquestion',
      name: 'checkquestion',
      component: CheckQuestionView,
    },
    {
      path: '/documentadmin',
      name: 'documentadmin',
      component: DocumentAdminView,
    },
    {
      path: '/statisticstable',
      name: 'statisticstable',
      component: StatisticsTableView,
    },
    {
      path: '/question/detail/:id',
      name: 'questionDetail',
      component: QuestionDetailView,
    },
  ],
})

export default router
