import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/userManage',
    component: () => import('../components/userManage/index.vue')
  },
  {
    path: '/orderManage',
    component: () => import('../components/orderManage/index.vue')
  },
  {
    path: '/goodscateManage',
    component: () => import('../components/goodscateManage/index.vue')
  },
  {
    path: '/goodsManage',
    component: () => import('../components/goodsManage/index.vue')
  },{
    path: '/test1',
    component: () => import('../components/test1/index.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
