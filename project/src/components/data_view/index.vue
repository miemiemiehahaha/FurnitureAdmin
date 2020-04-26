<template>
  <div id="myChart" style="width：600px;height:650px">
  </div>
</template>


<script>
import Echarts from "echarts";
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
        // title: {
        //   text: "销售量前十的产品统计图"
        // },
        // tooltip: {}, //提示框
        // xAxis: {
        //   //横坐标的值
        //   data: [],
        //   name: "产品类别名"
        // },
        // yAxis: {},
        // series: [
        //   {
        //     //纵坐标的值
        //     name: "销售量",
        //     type: "bar",
        //     data: [],
        //     itemStyle: {
        //       normal: {
        //         color: "rgb(145,199,174)"
        //       }
        //     }
        //   }
        // ]

        title: [
          // #1  折现图名称
          {
            text: "每天的成交量统计图",
            x: "0%",
            y: "0%",
            textStyle: {
              fontSize: 15
            }
          },
          // #2  柱状图名称
          {
            text: "销售量前十的产品",
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
            right: "10%",
            top: "10%",
            bottom: "55%",
            containLabel: true
          },
          {
            gridindex: 1,
            left: "5%",
            right: "10%",
            top: "50%",
            bottom: "10%",
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
            gridIndex: 1,
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
            //axisLabel: {
            //formatter: '{value} %'
            //},
          },
          {
            gridIndex: 1,
            type: "value",
            name: "销售量"
            //axisLabel: {
            //    formatter: '{value} ℃'
            //},
          }
        ],
        series: [
          {
            xAxisIndex: 0,
            yAxisIndex: 0,
            name: "日期",
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
            xAxisIndex: 1,
            yAxisIndex: 1,
            name: "产品名",
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
    fetchData(myChart) {
      this.$axios
        .post("/FurnitureAdm/SaleView")
        .then(res => {
          //console.log(res)
          let goodsName = res.data.map(item => {
            return item.goodsName;
          });
          let num = res.data.map(item => {
            return item.num;
          });
          setTimeout(() => {
            // 获取到数据后隐藏加载动画
            myChart.hideLoading();
            // console.log(res.data);
            myChart.setOption({
              // xAxis: [
              //   {
              //     data: goodsName.slice(0,10), //将异步请求获取到的数据进行装载
              //     axisLabel: {
              //         interval: 0,
              //         rotate: 30
              //   },
              //   }],
              grid: [
                {
                  left: "5%",
                  right: "10%",
                  top: "10%",
                  bottom: "50%",
                  containLabel: true
                },
                {
                  gridindex: 1,
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
                  gridIndex: 1,
                  name: "\n\n 产品名",
                  type: "category",
                  boundaryGap: true,
                  data: goodsName.slice(0, 10),
                  axisLabel: {
                    interval: 0,
                    rotate: 30
                  }
                }
              ],
              yAxis: [
                {
                  gridIndex: 0,
                  type: "value",
                  name: "成交量"
                  //axisLabel: {
                  //formatter: '{value} %'
                  //},
                },
                {
                  gridIndex: 1,
                  type: "value",
                  name: "销售量"
                  //axisLabel: {
                  //    formatter: '{value} ℃'
                  //},
                }
              ],
              series: [
                {
                  xAxisIndex: 0,
                  yAxisIndex: 0,
                  date:[],
                  name: "日期",
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
                  xAxisIndex: 1,
                  yAxisIndex: 1,
                  data:num.slice(0, 10),
                  name: "产品名",
                  type: "bar",
                  itemStyle: {
                    normal: {
                      color: "#4ea397"
                    }
                  }
                }
              ]

              // series: [
              //   {
              //     data: num.slice(0, 10)
              //   }
              // ]
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
