<template>
  <div class="userManage">
    <el-button class="addNew" icon="el-icon-plus" @click="addNew"
      >新增用户</el-button
    >
    <el-table
      :data="userList"
      style="width: 100%"
      :highlight-current-row="true"
    >
      <el-table-column label="用户名" prop="userName" />
      <el-table-column label="收货地址" prop="addr" />
      <el-table-column label="手机号" prop="phone" />
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <div class="searchWrapper">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索：enter"
              @keyup.enter.native="fetchUserList"
            />
            <el-button size="mini" @click="reset" type="success">重置</el-button>
          </div>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id, scope.row.userName)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div class="myPagination">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        layout="total, prev, pager, next"
        :total="total"
      >
      </el-pagination>
    </div>
    <el-drawer
      :with-header="false"
      :visible.sync="drawerShow"
      :direction="'rtl'"
    >
      <div class="form-body">
        <span class="title">{{ isAdd ? '添加用户' : '编辑用户' }}</span>
        <el-form class="formDetail" :model="userInfoDetail">
          <el-form-item label="用户名:" :label-width="'80px'" >
            <el-input
              v-model="userInfoDetail.userName"
              :disabled="!isAdd"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="密码:" :label-width="'80px'" v-if="isAdd">
            <el-input
              v-model="userInfoDetail.pwd"
              autocomplete="off"
              show-password
            ></el-input>
          </el-form-item>
          <el-form-item label="收货地址:" :label-width="'80px'">
            <el-input
              v-model="userInfoDetail.addr"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="手机号:" :label-width="'80px'">
            <el-input
              v-model="userInfoDetail.phone"
              autocomplete="off"
            ></el-input>
          </el-form-item>
        </el-form>
        <div class="formBtn">
          <el-button @click="drawerShow = false">取 消</el-button>
          <el-button type="primary" @click="addOrUpdate">提交</el-button>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search: '',
      total: 0,
      page: {
        pageNum: 1,
        pageSize: 10
      },
      userList: [],
      drawerShow: false,
      userInfoDetail: {},
      isAdd: null
    }
  },
  created() {
    this.fetchUserList()
  },
  methods: {
    handleDelete(id, username) {
      this.$confirm(`此操作将会永久删除用户(${username}), 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      })
        .then(() => {
          this.deleteUser(id)
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    handleEdit(userInfo) {
      this.isAdd = false
      this.userInfoDetail = userInfo
      this.drawerShow = true
    },
    reset() {
      this.search = ''
      this.fetchUserList()
    },
    handleCurrentChange() {
      this.fetchUserList()
    },
    addNew() {
      this.userInfoDetail = {
        userName: '',
        addr: '',
        pwd: '',
        phone: ''
      }
      this.isAdd = true
      this.drawerShow = true
    },
    addOrUpdate() {
      const url = this.isAdd
        ? '/FurnitureAdm/addUser'
        : '/FurnitureAdm/userUpdate'
      this.$axios({
        url,
        method: 'POST',
        data: this.$qs.stringify({
          userId: this.userInfoDetail.id || '',
          userName: this.userInfoDetail.userName,
          pwd: this.userInfoDetail.pwd,
          addr: this.userInfoDetail.addr,
          phone: this.userInfoDetail.phone
        })
      }).then(({ data }) => {
        if (data.code === 200) {
          this.fetchUserList()
          this.$message({
            type: 'success',
            message: this.isAdd ? '添加成功!' : '修改成功!'
          })
        } else {
          let message = this.isAdd ? '添加失败!' : '修改失败!'
          if (data.result.isExist) {
            message = '用户已存在，添加失败!'
          }
          this.$message({
            type: 'error',
            message
          })
        }
        this.drawerShow = false
      })
    },
    deleteUser(id) {
      this.$axios({
        method: 'POST',
        url: '/FurnitureAdm/Deleteuser',
        data: this.$qs.stringify({
          id
        })
      }).then(({ data }) => {
        const { code } = data
        if (code === 200) {
          this.fetchUserList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      })
    },
    fetchUserList() {
      this.$axios({
        method: 'POST',
        url: '/FurnitureAdm/getuserList',
        data: this.$qs.stringify({
          page: this.page.pageNum,
          pageSize: this.page.pageSize,
          query: this.search
        })
      }).then(({ data }) => {
        const { code, result } = data
        if (code === 200) {
          this.userList = result.list
          this.total = result.total
        }
      })
    }
  }
}
</script>

<style>
.addNew {
  margin-bottom: 20px;
}
.searchWrapper {
  display: flex;
  align-items: center;
}
.searchWrapper .el-button {
  margin-left: 10px;
}
.myPagination {
  margin-top: 20px;
}
.form-body {
  height: 100%;
  display: flex;
  flex-direction: column;
}
.form-body .title {
  color: #575757;
  padding: 20px;
  text-align: left;
}
.form-body .el-input {
  width: 95% !important;
}
.form-body .formDetail {
  flex: 1;
}
.form-body .formBtn {
  padding-bottom: 20px;
}
</style>