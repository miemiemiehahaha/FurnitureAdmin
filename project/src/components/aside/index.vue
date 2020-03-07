<template>
  <div class="aside">
    <ul class="nav">
      <li
        class="item"
        v-for="(item, index) in nav"
        :key="index"
        @click="currentRoute(item.id)"
        :class="{ active: currentId === item.id }"
      >
        <span>{{ item.name }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentId: 0,
      nav: [
        {
          name: '用户管理',
          id: 1
        },
        {
          name: '订单管理',
          id: 2
        },
        {
          name: '产品类别管理',
          id: 3
        },
        {
          name: '产品管理',
          id:4
        },{
          name: '测试',
          id:5
        }
      ]
    }
  },
  methods: {
    currentRoute(id) {
      this.currentId = id
      if (id === 1) {
        this.$router.push('/userManage')
      } else if (id === 2) {
        this.$router.push('/orderManage')
      }else if (id === 3) {
        this.$router.push('/goodscateManage')
      }else if (id === 4) {
        this.$router.push('/goodsManage')
      }else if (id === 5) {
        this.$router.push('/test1')
      }
    }
  }
}
</script>

<style>
.nav .item {
  widows: 100%;
  height: 50px;
  text-align: center;
  line-height: 50px;
  cursor: pointer;
  margin: 5px 0;
}
.nav .active {
  background-color: #ccc;
}
.nav .item:hover {
  background-color: blanchedalmond;
}
</style>
