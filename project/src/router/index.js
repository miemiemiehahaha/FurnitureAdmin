import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/userManage',
    component: () => import('../components/userManage/index.vue')
  },
  {
    path: '/adminManage',
    component: () => import('../components/adminManage/index.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
