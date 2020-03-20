<template>
  <div class="login" v-loading="loading">
    <div class="header">
      <div class="logo">
        <img src="../../assets/logo.png" alt="" />
      </div>
      <p class="title">管理员登录</p>
    </div>
    <div class="content">
      <el-input v-model="username" placeholder="用户名" @keyup.enter.native="loginWait"></el-input>
      <el-input show-password v-model="password" placeholder="密码" @keyup.enter.native="loginWait"></el-input>
      <el-button type="primary" size="medium" @click="loginWait"
        >登 陆</el-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      username: '',
      password: ''
    }
  },
  methods: {
    message(title, type) {
      this.$notify({
        title,
        type
      })
    },
    loginWait() {
      if (!this.username) {
        this.message('管理员用户名不能为空', 'error')
        return
      }
      if (!this.password) {
        this.message('管理员密码不能为空', 'error')
        return
      }
      this.loading = true
      setTimeout(() => {
        this.login()
      }, 1000)
    },
    login() {
      this.$axios({
        method: 'POST',
        data: this.$qs.stringify({
          userName: this.username,
          pwd: this.password
        }),
        url: '/FurnitureAdm/adminLogin'
      }).then(({ data }) => {
        const { code, result } = data
        if (code === 200) {
          this.$emit('login', result.id)
          this.message('登陆成功', 'success')
          window.sessionStorage.setItem('adminId', result.id)
          this.$router.push('/userManage')
        } else {
          this.message('登录失败，用户名/密码错误', 'error')
        }
        this.loading = false
      })
    }
  }
}
</script>

<style>
.login {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, 0);
  width: 350px;
  border: 1px solid #ccc;
  padding: 25px;
}
.login .header .logo {
  width: 200px;
  margin: 0 auto;
}
.login .header .logo img {
  width: 100%;
}
.login .header .title {
  text-align: center;
  width: 100%;
  height: 30px;
  padding: 5px;
  font-size: 18px;
  font-weight: 500;
  color: #000;
}
.login .content .el-input {
  margin: 10px auto 15px;
}
.login .content .el-button {
  width: 100%;
}
.btn {
    color: #FFF;
    background-color: #7a8698;
    border-color: #7a8698;
}
.btn:hover {
    color: #FFF;
    background-color: #808080;
    border-color: #7a8698;
}
</style>
