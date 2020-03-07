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
      <el-button class="addNew1" icon="el-icon-plus" @click="addNew">新增产品</el-button>
    </div>
    <el-table :data="goodsList" style="width: 100%" :highlight-current-row="true">
      <el-table-column label="ID" prop="id" />
      <el-table-column label="品牌名" prop="goodsName" />
      <el-table-column label="性质" prop="color" />
      <el-table-column label="价格" prop="inPrice" />
      <el-table-column label="库存" prop="quantity" />
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id, scope.row.goodsName)"
          >删除</el-button>
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
    <el-drawer :with-header="false" :visible.sync="drawerShow" :direction="'rtl'">
      <div class="form-body">
        <span class="title">{{ isAdd ? '添加产品' : '编辑产品' }}</span>
        <el-form class="formDetail" :model="goodsInfoDetail">
          <el-form-item label="ID:" :label-width="'80px'" v-if="!isAdd">
            <el-input v-model="goodsInfoDetail.id" :disabled="!isAdd" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="品牌名:" :label-width="'80px'">
            <el-input v-model="goodsInfoDetail.goodsName" :disabled="!isAdd" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性质:" :label-width="'80px'" v-if="isAdd">
            <el-input v-model="goodsInfoDetail.color" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="库存:" :label-width="'80px'">
            <el-input v-model="goodsInfoDetail.quantity" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="尺寸:" :label-width="'80px'">
            <el-input v-model="goodsInfoDetail.size" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="价格:" :label-width="'80px'">
            <el-input v-model="goodsInfoDetail.inPrice" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="描述:" :label-width="'80px'">
            <el-input v-model="goodsInfoDetail.detail" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="图片:" :label-width="'80px'">
            <el-input v-model="goodsInfoDetail.picInfo" autocomplete="off"></el-input>
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
      search: "",
      total: 0,
      page: {
        pageNum: 1,
        pageSize: 6
      },
      goodsList: [],
      drawerShow: false,
      goodsInfoDetail: {},
      isAdd: null
    };
  },
  created() {
    this.fetchgoodsList();
  },
  methods: {
    handleDelete(id, goodsname) {
      this.$confirm(`此操作将会永久删除产品(${goodsname}), 是否继续?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(() => {
          this.deleteGoods(id);
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    handleEdit(goodsInfo) {
      this.isAdd = false;
      this.goodsInfoDetail = goodsInfo;
      this.drawerShow = true;
    },
    reset() {
      this.search = "";
      this.fetchgoodsList();
    },
    handleCurrentChange() {
      this.fetchgoodsList();
    },
    addNew() {
      this.goodsInfoDetail = {
        goodsName: "",
        color: "",
        size: "",
        quantity: "",
        inPrice: "",
        detail: "",
        picInfo: ""
      };
      this.isAdd = true;
      this.drawerShow = true;
    },
    addOrUpdate() {
      const url = this.isAdd
        ? "/FurnitureAdm/AddGoods"
        : "/FurnitureAdm/updateGoods";
      this.$axios({
        url,
        method: "POST",
        data: this.$qs.stringify({
          id: this.goodsInfoDetail.id || "",
          goodsName: this.goodsInfoDetail.goodsName,
          color: this.goodsInfoDetail.color,
          size: this.goodsInfoDetail.size,
          quantity: this.goodsInfoDetail.quantity,
          inPrice: this.goodsInfoDetail.inPrice,
          detail: this.goodsInfoDetail.detail,
          picInfo: this.goodsInfoDetail.picInfo
        })
      }).then(({ data }) => {
        if (data.code === 200) {
          this.fetchgoodsList();
          this.$message({
            type: "success",
            message: this.isAdd ? "添加成功!" : "修改成功!"
          });
        } else {
          let message = this.isAdd ? "添加失败!" : "修改失败!";
          if (data.result.isExist) {
            message = "产品已存在，添加失败!";
          }
          this.$message({
            type: "error",
            message
          });
        }
        this.drawerShow = false;
      });
    },
    deleteGoods(id) {
      this.$axios({
        method: "POST",
        url: "/FurnitureAdm/DeleteGoods",
        data: this.$qs.stringify({
          id
        })
      }).then(({ data }) => {
        const { code } = data;
        if (code === 200) {
          this.fetchgoodsList();
          this.$message({
            type: "success",
            message: "删除成功!"
          });
        }
      });
    },
    fetchgoodsList() {
      this.$axios({
        method: "POST",
        url: "/FurnitureAdm/getgoodsList",
        data: this.$qs.stringify({
          page: this.page.pageNum,
          pageSize: this.page.pageSize,
          query: this.search
        })
      }).then(({ data }) => {
        const { code, result } = data;
        if (code === 200) {
          this.goodsList = result.list;
          this.total = result.total;
        }
      });
    }
  }
};
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
.form-body .formDetail {
  flex: 1;
}
.form-body .formBtn {
  padding-bottom: 20px;
}
</style>