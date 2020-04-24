<template>
  <div id="myChart" style="width：600px;height:600px"></div>
</template>



<script>
export default {
  name: "",
  data() {
    return {
    };
  },
  mounted() {
    this.drawBarChart();//调用drawBarChart()
  },
  methods: {
    drawBarChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("myChart"));
      // 绘制图表
      myChart.setOption({
        title: {
          text: "各类别销售数据统计图"
        },
        tooltip: {}, //提示框
        xAxis: {
          //横坐标的值
          data: [],
          name: "产品类别名"
        },
        yAxis: {},
        series: [
          {
            //纵坐标的值
            name: "销售量",
            type: "bar",
            data: [],
            itemStyle: {
              normal: {
                color: "rgb(145,199,174)"
              }
            }
          }
        ]
      });
      //加载动画
      myChart.showLoading();
      //axios异步获取数据
      this.$axios
        .post("/FurnitureAdm/SaleView")
        .then(res => {
          setTimeout(() => {
            // 获取到数据后隐藏加载动画
            myChart.hideLoading();
            // console.log(res.data);
            myChart.setOption({
              xAxis: [
                {
                  data: res.data.categoryname //将异步请求获取到的数据进行装载
                }
              ],
              series: [
                {
                  data: res.data.saledata
                }
              ]
            });
          }, 500); //加载动画时长为0.5秒
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script> 