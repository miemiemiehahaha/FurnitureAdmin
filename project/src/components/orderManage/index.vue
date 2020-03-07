<template>
  <div class="orderManage">
    <div class="searchWrapper">
      <el-input
        class="order_search"
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索：enter"
        @keyup.enter.native="fetchOrderList"
      />
      <el-button size="mini" @click="reset" type="success">重置</el-button>
    </div>
    <el-table :data="orderList" style="width: 100%" :highlight-current-row="true">
      <el-table-column label="用户名" prop="userName" />
      <el-table-column label="订单号" prop="orderCode" />
      <el-table-column label="商品数目" prop="totalNum" />
      <el-table-column label="订单总价" prop="totalMoney" />
      <el-table-column label="创建日期" prop="orderDate" />
      <el-table-column align="right">
        <el-popover placement="right" width="400" trigger="click">
          <el-table :data="orderdetailList">
            <el-table-column width="150" property="goodsName" label="产品名" />
            <el-table-column width="100" property="num" label="数量" />
            <el-table-column width="300" property="price" label="单价" />
          </el-table>
          <el-button slot="reference">详情</el-button>
        </el-popover>
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
      orderList: []
    };
  },
  created() {
    this.fetchOrderList();
  },
  methods: {
    reset() {
      this.search = "";
      this.fetchOrderList();
    },
    handleCurrentChange() {
      this.fetchOrderList();
    },

    fetchOrderList() {
      this.$axios({
        method: "POST",
        url: "/FurnitureAdm/getOrderList",
        data: this.$qs.stringify({
          page: this.page.pageNum,
          pageSize: this.page.pageSize,
          query: this.search
        })
      }).then(({ data }) => {
        const { code, result } = data;
        if (code === 200) {
          this.orderList = result.list;
          this.total = result.total;
        }
      });
    }
  }
};
</script>

<style>
.addNew {
  margin-bottom: 15px;
}
.order_search {
  width: 25%;
}
.searchWrapper {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
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