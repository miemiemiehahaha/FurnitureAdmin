<template>
  <div class="status">
    <div class="wrapper">
      <img v-if="!loginStatus" @click="login" src="@/assets/img/admin.png" alt="" />
      <img v-if="loginStatus" @click="quit" src="@/assets/img/quit.png" alt="" />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    isLogin: {
      type: Boolean,
      default: false
    },
    id: {
      type: Number,
      default: 0
    }
  },
  data () {
    return {
      loginStatus: Boolean(window.sessionStorage.getItem('adminId'))
    }
  },
  computed: {
    adminId () {
      return window.sessionStorage.getItem('adminId') || this.id
    }
  },
  watch: {
    isLogin (value) {
      this.loginStatus = value
    }
  },
  methods: {
    login () {
      this.$router.push('/adminLogin')
    },
    quit() {
      this.$confirm('', '是否退出登录？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      })
        .then(() => {
          this.$axios({
            method: 'POST',
            url: '/FurnitureAdm/loginExit',
            data: this.$qs.stringify({ id: this.adminId })
          }).then(({ data }) => {
            const { code } = data
            if (code === 200) {
              this.$message({
                message: '退出成功!',
                type: 'success'
              })
              window.sessionStorage.removeItem('adminId')
              this.$emit('logout')
            } else {
              this.$message({
                message: '退出失败!请重试',
                type: 'error'
              })
            }
          })
        })
        .catch(() => {})
    }
  }
}
</script>

<style>
.status {
  position: absolute;
  top: 15px;
  right: 15px;
}
.status .wrapper {
  position: relative;
  width: 30px;
  height: 30px;
}
.status .wrapper img {
  cursor: pointer;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
}
</style>
