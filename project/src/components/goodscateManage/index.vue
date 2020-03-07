<template>
  <div class="goodscateManage">
    <el-button class="addNew" icon="el-icon-plus" @click="addNew"
      >新增产品类别</el-button
    >
    <el-table
      :data="categoryList"
      style="width: 100%"
      :highlight-current-row="true"
    >
      <el-table-column label="ID" prop="id" />
      <el-table-column label="类别名" prop="categoryName" />
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <div class="searchWrapper">
            <el-input
              v-model="search"
              size="mini"
              placeholder="输入关键字搜索：enter"
              @keyup.enter.native="fetchcategoryList"
            />
            <el-button size="mini" @click="reset" type="success">重置</el-button>
          </div>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id, scope.row.categoryName)"
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
        <span class="title">{{ isAdd ? '添加类别' : '编辑类别' }}</span>
        <el-form class="formDetail" :model="cateInfoDetail">
          <el-form-item label="ID:" :label-width="'80px'"  v-if="!isAdd" >
            <el-input
              v-model="cateInfoDetail.id"
              :disabled="!isAdd"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="类别名:" :label-width="'80px'">
            <el-input
              v-model="cateInfoDetail.categoryName"
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
        pageSize: 6
      },
      categoryList: [],
      drawerShow: false,
      cateInfoDetail: {},
      isAdd: null
    }
  },
  created() {
    this.fetchcategoryList()
  },
  methods: {
    handleDelete(id, categoryname) {
      this.$confirm(`此操作将会永久删除类别(${categoryname}), 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      })
        .then(() => {
          this.deletecate(id)
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
      this.cateInfoDetail = userInfo
      this.drawerShow = true
    },
    reset() {
      this.search = ''
      this.fetchcategoryList()
    },
    handleCurrentChange() {
      this.fetchcategoryList()
    },
    addNew() {
      this.cateInfoDetail = {
        id: '',
        categoryName: ''
      }
      this.isAdd = true
      this.drawerShow = true
    },
    addOrUpdate() {
      const url = this.isAdd
        ? '/FurnitureAdm/addCategory'
        : '/FurnitureAdm/categoryUpdate'
      this.$axios({
        url,
        method: 'POST',
        data: this.$qs.stringify({
          id: this.cateInfoDetail.id || '',
          categoryName: this.cateInfoDetail.categoryName
        })
      }).then(({ data }) => {
        if (data.code === 200) {
          this.fetchcategoryList()
          this.$message({
            type: 'success',
            message: this.isAdd ? '添加成功!' : '修改成功!'
          })
        } else {
          let message = this.isAdd ? '添加失败!' : '修改失败!'
          if (data.result.isExist) {
            message = '类别已存在，添加失败!'
          }
          this.$message({
            type: 'error',
            message
          })
        }
        this.drawerShow = false
      })
    },
    deletecate(id) {
      this.$axios({
        method: 'POST',
        url: '/FurnitureAdm/deleteCategory',
        data: this.$qs.stringify({
          id
        })
      }).then(({ data }) => {
        const { code } = data
        if (code === 200) {
          this.fetchcategoryList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      })
    },
    fetchcategoryList() {
      this.$axios({
        method: 'POST',
        url: '/FurnitureAdm/getcategoryList',
        data: this.$qs.stringify({
          page: this.page.pageNum,
          pageSize: this.page.pageSize,
          query: this.search
        })
      }).then(({ data }) => {
        const { code, result } = data
        if (code === 200) {
          this.categoryList = result.list
          this.total = result.total
        }
      })
    }
  }
}
</script>

<style>
.addNew {
  margin-bottom: 15px;
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