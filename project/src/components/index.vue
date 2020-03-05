<template>
  <div class="main">
    <el-container>
      <el-header>
        宅家家居后台管理系统
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Aside />
        </el-aside>
        <el-container>
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
</style>
