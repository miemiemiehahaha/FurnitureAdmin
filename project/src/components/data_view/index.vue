<template>
  <div id="myChart" style="width：600px;height:600px"></div>
</template>


<script>
import Echarts from 'echarts'
export default {
  
  data() {
    return {
      mockData: [
        {
          goodsId: 1,
          num: 10
        },
        {
          goodsId: 2,
          num: 20
        },
        {
          goodsId: 3,
          num: 30
        },
        {
          goodsId: 4,
          num: 40
        }
      ],
      options: {
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
      }
    };
  },
  mounted() {
    this.drawBarChart();//调用drawBarChart()
  },
  methods: {
    drawBarChart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = Echarts.init(document.getElementById("myChart"));
      // 绘制图表
      myChart.setOption(this.options);
      //加载动画
      myChart.showLoading();
      //axios异步获取数据
      this.fetchData(myChart)
      // this.fetchMock(myChart)
    },
    // fetchMock(myChart) {
    //   setTimeout(() => {
    //     myChart.hideLoading();
    //     myChart.setOption({
    //       xAxis: [
    //         {
    //           data: [1,2,3,4]
    //         }
    //       ],
    //       series: [
    //         {
    //           data: [10,20,30,40]
    //         }
    //       ]
    //     })
    //   }, 500)
    // },
    fetchData (myChart) {
      this.$axios
        .post("/FurnitureAdm/SaleView")
        .then(res => {
          // console.log(res)
          let x = res.data.map(item => {
            return item.categoryname
          })
          let num = res.data.map(item => {
            return item.saledata
          })
          setTimeout(() => {
            // 获取到数据后隐藏加载动画
            myChart.hideLoading();
            // console.log(res.data);
            myChart.setOption({
              xAxis: [
                {
<<<<<<< Updated upstream
                  data: x //将异步请求获取到的数据进行装载
=======
                  data: res.data.goodsID //将异步请求获取到的数据进行装载
>>>>>>> Stashed changes
                }
              ],
              series: [
                {
<<<<<<< Updated upstream
                  data: num
=======
                  data: res.data.num
>>>>>>> Stashed changes
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
