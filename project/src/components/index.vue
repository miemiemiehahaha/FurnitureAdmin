<template>
  <div class="main">
    <el-container>
      <el-header class="topHeader">
        宅家家居后台管理系统
      </el-header>
      <el-container>
        <el-aside class="asideNav" width="200px">
          <Aside />
        </el-aside>
        <el-container style="margin: 40px 0 0 200px">
          <el-main>
            <div class="content-wrap">
              <router-view />
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
export default {
  components: {
    Aside
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
