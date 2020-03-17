<template>
  <div class="aside">
    <ul class="nav">
      <router-link class="item" tag="li" to="/userManage">用户管理</router-link>
      <router-link class="item" tag="li" to="/orderManage"
        >订单管理</router-link
      >
      <router-link class="item" tag="li" to="/goodscateManage"
        >产品类别管理</router-link
      >
      <router-link class="item" tag="li" to="/goodsManage"
        >产品管理</router-link
      >
    </ul>
  </div>
</template>

<script>
export default {}
</script>

<style>
.nav {
  height: 100%;
}
.nav .item {
  widows: 100%;
  height: 50px;
  text-align: center;
  line-height: 50px;
  cursor: pointer;
  margin: 20px 0;
  position: relative;
  cursor: pointer;
}
.nav .item::after {
  content: '';
  position: absolute;
  left: 0;
  top: 50px;
  bottom: -5px;
  right: 0;
  border-radius: 20px;
  transition: 0.3s;
  opacity: 0.7;
  background-color: #7a8698;
  transform-origin: left;
  transform: scaleX(0);
}
.nav .item:hover::after {
  transform: scaleX(1);
}
.nav .router-link-active {
  background-color: #e6eceb;
}
</style>
