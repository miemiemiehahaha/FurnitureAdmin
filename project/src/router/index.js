import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/userManage'
  },
  {
    path: '/adminLogin',
    component: () => import('../components/adminLogin/index.vue')
  },
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

router.beforeEach((to, from, next) => {
  const adminId = window.sessionStorage.getItem('adminId')
  if (adminId) {
    next()
  } else {
    if (to.path == '/adminLogin') {
      next()
    } else {
      next('/adminLogin')
    }
  }
})

export default router
