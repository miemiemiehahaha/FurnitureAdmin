<template>
  <div>测试页面（删）

      <div>
          1、orderManage订单页面点击详情弹出子订单信息！<br>
          2、goodsManage产品页面数据渲染不出来！<br>
          3、goodsManage产品页面因行内容过多想做成“展开行”的形式<br>
          <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="ID">
            <span>{{ props.row.id }}</span>
          </el-form-item>
          <el-form-item label="品牌名">
            <span>{{ props.row.goodsName }}</span>
          </el-form-item>
           <el-form-item label="性质">
            <span>{{ props.row.color }}</span>
          </el-form-item>
           <el-form-item label="尺寸">
            <span>{{ props.row.size }}</span>
          </el-form-item>
          <el-form-item label="产品分类">
            <span>{{ props.row.categoryID }}</span>
          </el-form-item>
          <el-form-item label="价格">
            <span>{{ props.row.inPrice }}</span>
          </el-form-item>
          <el-form-item label="库存">
            <span>{{ props.row.quantity }}</span>
          </el-form-item>
          <el-form-item label="图片">
            <span>{{ props.row.picInfo }}</span>
          </el-form-item>
          <el-form-item label="产品描述">
            <span>{{ props.row.detail }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
      </div>
  </div>
  
</template>

<script>
export default {

}
</script>

<style>

</style>