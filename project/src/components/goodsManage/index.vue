<template>
  <div class="goodsManage">
    <div class="searchWrapper">
      <el-input
        class="order_search"
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索：enter"
        @keyup.enter.native="fetchgoodsList"
      />
      <el-button size="mini" @click="reset" type="success">重置</el-button>
      <el-button class="addNew1" icon="el-icon-plus" @click="addNew"
        >新增产品</el-button
      >
    </div>
    <el-table
      :data="goodsList"
      style="width: 100%"
      :highlight-current-row="true"
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="商品ID：">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="商品名称：">
              <span>{{ props.row.goodsName }}</span>
            </el-form-item>
            <el-form-item label="商品单价：">
              <span>￥{{ props.row.inPrice }}</span>
            </el-form-item>
            <el-form-item label="商品图片：">
              <img :src="props.row.picInfo" alt="" />
            </el-form-item>
            <el-form-item label="商品库存：">
              <span>{{ props.row.quantity }}</span>
            </el-form-item>
            <el-form-item label="商品规格：">
              <span>{{ props.row.size }}</span>
            </el-form-item>
            <el-form-item label="商品性质：">
              <span>{{ props.row.color }}</span>
            </el-form-item>
            <el-form-item label="商品描述：">
              <span>{{ props.row.detail }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="ID" prop="id" width="60px" />
      <el-table-column label="品牌名" prop="goodsName" />
      <el-table-column label="性质" prop="color" />
      <el-table-column label="价格(￥)" prop="inPrice" width="100px" />
      <el-table-column label="库存" prop="quantity" width="100px" />
      <el-table-column align="right" width="200px">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id, scope.row.goodsName)"
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
      ></el-pagination>
    </div>
    <el-drawer
      :with-header="false"
      :visible.sync="drawerShow"
      :direction="'rtl'"
    >
      <div class="form-body">
        <span class="title">{{ isAdd ? '添加产品' : '编辑产品' }}</span>
        <el-form class="formDetail" :model="goodsInfoDetail">
          <el-form-item label="ID:" :label-width="'80px'" v-if="!isAdd">
            <el-input
              v-model="goodsInfoDetail.id"
              :disabled="!isAdd"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="品牌名:" :label-width="'80px'">
            <el-input
              v-model="goodsInfoDetail.goodsName"
              :disabled="!isAdd"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="性质:" :label-width="'80px'" v-if="isAdd">
            <el-input
              v-model="goodsInfoDetail.color"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="类别:" :label-width="'80px'">
            <el-select
              v-model="goodsInfoDetail.categoryID"
              placeholder="请选择类别"
            >
              <el-option
                v-for="item in categoryList"
                :key="item.id"
                :label="item.categoryName"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="库存:" :label-width="'80px'">
            <el-input
              v-model="goodsInfoDetail.quantity"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="尺寸:" :label-width="'80px'">
            <el-input
              v-model="goodsInfoDetail.size"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="价格:" :label-width="'80px'">
            <el-input
              v-model="goodsInfoDetail.inPrice"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="描述:" :label-width="'80px'">
            <el-input
              type="textarea"
              v-model="goodsInfoDetail.detail"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="图片:" :label-width="'80px'">
            <el-input
              v-model="goodsInfoDetail.picInfo"
              autocomplete="off"
            ></el-input>
          </el-form-item>
          <el-form-item label="备注:" :label-width="'80px'">
            <el-input
              type="textarea"
              v-model="goodsInfoDetail.remark"
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
      goodsList: [],
      drawerShow: false,
      goodsInfoDetail: {},
      isAdd: null,
      categoryList: []
    }
  },
  created() {
    this.fetchgoodsList()
    this.fetchCategoryList()
  },
  methods: {
    handleDelete(id, goodsname) {
      this.$confirm(`此操作将会永久删除产品(${goodsname}), 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      })
        .then(() => {
          this.deleteGoods(id)
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    handleEdit(goodsInfo) {
      this.isAdd = false
      this.goodsInfoDetail = goodsInfo
      this.drawerShow = true
    },
    reset() {
      this.search = ''
      this.fetchgoodsList()
    },
    handleCurrentChange() {
      this.fetchgoodsList()
    },
    addNew() {
      this.goodsInfoDetail = {
        goodsName: '',
        color: '',
        size: '',
        quantity: '',
        inPrice: '',
        detail: '',
        picInfo: ''
      }
      this.isAdd = true
      this.drawerShow = true
    },
    addOrUpdate() {
      const url = this.isAdd
        ? '/FurnitureAdm/AddGoods'
        : '/FurnitureAdm/updateGoods'
      this.$axios({
        url,
        method: 'POST',
        data: this.$qs.stringify({
          goodsid: this.goodsInfoDetail.id || '',
          goodsName: this.goodsInfoDetail.goodsName,
          color: this.goodsInfoDetail.color,
          goodsize: this.goodsInfoDetail.size,
          quantity: this.goodsInfoDetail.quantity,
          categoryID: this.goodsInfoDetail.categoryID,
          inPrice: this.goodsInfoDetail.inPrice,
          detail: this.goodsInfoDetail.detail,
          picInfo: this.goodsInfoDetail.picInfo,
          remark: this.goodsInfoDetail.remark
        })
      }).then(({ data }) => {
        if (data.code === 200) {
          this.fetchgoodsList()
          this.$message({
            type: 'success',
            message: this.isAdd ? '添加成功!' : '修改成功!'
          })
        } else {
          let message = this.isAdd ? '添加失败!' : '修改失败!'
          if (data.result.isExist) {
            message = '产品已存在，添加失败!'
          }
          this.$message({
            type: 'error',
            message
          })
        }
        this.drawerShow = false
      })
    },
    deleteGoods(id) {
      this.$axios({
        method: 'POST',
        url: '/FurnitureAdm/DeleteGoods',
        data: this.$qs.stringify({
          id
        })
      }).then(({ data }) => {
        const { code } = data
        if (code === 200) {
          this.fetchgoodsList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
        }
      })
    },
    fetchCategoryList() {
      this.$axios({
        method: 'post',
        url: '/FurnitureAdm/getcategoryList',
        data: this.$qs.stringify({
          query: '',
          page: 1,
          pageSize: 99
        })
      }).then(({ data }) => {
        const {code, result} = data
        if (code === 200) {
          this.categoryList = result.list.map(item => {
            item.id = `${item.id}`
            return item
          })
        }
      })
    },
    fetchgoodsList() {
      this.$axios({
        method: 'POST',
        url: '/FurnitureAdm/getgoodsList',
        data: this.$qs.stringify({
          page: this.page.pageNum,
          pageSize: this.page.pageSize,
          query: this.search
        })
      }).then(({ data }) => {
        const { code, result } = data
        if (code === 200) {
          this.goodsList = result.goodsList
          this.total = result.total
        }
      })
    }
  }
}
</script>

<style>
.addNew1 {
  margin-bottom: 0px;
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
.form-body .el-textarea {
  width: 95% !important;
}
.form-body .el-select{
  width: 95% !important;
}
.form-body .formDetail {
  flex: 1;
}
.form-body .formBtn {
  padding-bottom: 20px;
}
.table-expand {
  display: flex;
  flex-direction: column;
}
.table-expand .el-form-item {
  margin: 0;
  padding: 4px;
  border-bottom: 1px solid #ccc;
}
.table-expand .el-form-item .el-form-item__label {
  color: #000;
}
.el-drawer__body {
  overflow-y: scroll;
}
</style>
