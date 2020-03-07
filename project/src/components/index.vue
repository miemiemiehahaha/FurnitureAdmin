<template>
  <div class="main">
    <el-container>
      <el-header class="topHeader">
        宅家家居后台管理系统
        <login-status :isLogin="isLogin" :id="adminId" @logout="logout"/>
      </el-header>
      <el-container>
        <el-aside class="asideNav" width="200px">
          <Aside />
        </el-aside>
        <el-container style="margin: 40px 0 0 200px">
          <el-main>
            <div class="content-wrap">
              <router-view @login="login"/>
            </div>
          </el-main>
          <el-footer>Footer</el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from './aside'
import LoginStatus from './adminLogin/components/LoginStatus'
export default {
  components: {
    Aside,
    LoginStatus
  },
  data () {
    return {
      isLogin: null,
      adminId: 0
    }
  },
  methods: {
    login (id) {
      this.isLogin = true
      this.adminId = id
    },
    logout () {
      this.isLogin = false
      this.adminId = 0
    }
  }
}
</script>

<style>
.content-wrap {
  padding: 20px;
  background-color: blanchedalmond;
}
.topHeader {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  background-color: powderblue;
  z-index: 996;
  height: 60px;
  line-height: 60px;
  font-size: 20px;
}
.asideNav {
  width: 200px;
  position: fixed;
  left: 0;
  top: 100px;
  z-index: 999;
}
</style>
