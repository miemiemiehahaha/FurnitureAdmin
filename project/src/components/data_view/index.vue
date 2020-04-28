<template>
  <div id="myChart" style="width：600px;height:650px"></div>
</template>


<script>
import Echarts from "echarts";
export default {
  data() {
    return {
      options: {
        title: [
          // #1  折线图名称
          {
            text: "每天的成交量统计图",
            x: "0%",
            y: "0%",
            textStyle: {
              fontSize: 15
            }
          },
          // #2  饼图名称
          {
            text: "各类别销售数据统计图",
            x: "55%",
            y: "0%",
            textStyle: {
              fontSize: 15
            }
          },
          // #3  柱状图名称
          {
            text: "销售量前十的品牌",
            x: "0%",
            y: "50%",
            textStyle: {
              fontSize: 15
            }
          }
        ],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          }
        },
        grid: [
          {
            left: "5%",
            right: "50%",
            top: "10%",
            bottom: "50%",
            containLabel: true
          },
          {
            // gridindex: 1,
            // left: "55%",
            // right: "0%",
            // top: "10%",
            // bottom: "50%"
          },
          {
            gridindex: 2,
            left: "5%",
            right: "10%",
            top: "62%",
            bottom: "0%",
            containLabel: true
          }
        ],
        xAxis: [
          {
            gridIndex: 0,
            type: "category",
            name: "日期",
            boundaryGap: false,
            data: []
          },
          {
            // 不需要数据
          },
          {
            gridIndex: 2,
            name: "\n\n 产品名",
            type: "category",
            boundaryGap: true,
            data: []
          }
        ],
        yAxis: [
          {
            gridIndex: 0,
            type: "value",
            name: "成交量"
          },
          {
            // 不需要数据
          },
          {
            gridIndex: 2,
            type: "value",
            name: "销售量"
          }
        ],
        series: [
          {
            xAxisIndex: 0,
            yAxisIndex: 0,
            name: "成交量",
            type: "line",
            smooth: true,
            label: {
              normal: {
                show: true,
                position: "right"
              }
            }
          },
          {
            name: "销售量",
            type: "pie"
          },
          {
            xAxisIndex: 2,
            yAxisIndex: 2,
            name: "销售量",
            type: "bar",
            itemStyle: {
              normal: {
                color: "#4ea397"
              }
            }
          }
        ]
      }
    };
  },
  mounted() {
    this.drawBarChart(); //调用drawBarChart()
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
      this.fetchData(myChart);
      // this.fetchMock(myChart)
    },

    fetchData(myChart) {
      this.$axios
        .post("/FurnitureAdm/SaleView")
        .then(res => {
          // console.log(res);
          //折线图数据
          let goodsName = res.data.map(item => {
            return item.goodsName;
          });
          let num = res.data.map(item => {
            return item.num;
          });

          //饼图数据
          let piedata = [];
          res.data.forEach(item => {
            if (item.categoryName && item.newnum) {
              piedata.push({ name: item.categoryName, value: item.newnum });
              return piedata;
            }
          });
          // console.log(piedata);

          //条形图数据
          let orderDate = [];
          res.data.forEach(item => {
            if (item.orderDate) {
              orderDate.push(item.orderDate);
              return item.orderDate;
            }
          });
          // console.log(orderDate);
          let totalNum = [];
          res.data.forEach(item => {
            if (item.totalNum) {
              totalNum.push(item.totalNum);
              return item.totalNum;
            }
          });
          // console.log(totalNum);
          setTimeout(() => {
            // 获取到数据后隐藏加载动画
            myChart.hideLoading();
            // console.log(res.data);
            myChart.setOption({
              //将异步请求获取到的数据进行装载
              xAxis: [
                {
                  data: orderDate
                },
                {
                  // data: piedata
                },
                {
                  data: goodsName.slice(0, 10),
                  axisLabel: {
                    interval: 0,
                    rotate: 30
                  }
                }
              ],
              series: [
                {
                  data: totalNum
                },
                {
                  tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                  },
                  center: ["78%", "30%"],
                  radius: "45%",
                  roseType: "area",
                  data: piedata
                },
                {
                  data: num.slice(0, 10)
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
