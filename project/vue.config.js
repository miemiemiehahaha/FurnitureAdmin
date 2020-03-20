const path = require('path')
function resolve (dir) {
  return path.join(__dirname, dir)
}
module.exports = {
  publicPath: "",
  lintOnSave: true,
  chainWebpack: (config) => {
    config.resolve.alias
      .set('@', resolve('src'))
  },
  devServer: {
    port: 3000,
    proxy: {
      "/FurnitureAdm": {
        target: "http://127.0.0.1:8080",
        changeOrigin: true, //是否跨域
        ws: true,
        pathRewrite: {
          "^FurnitureAdm": "/FurnitureAdm"
        }
      },
    }
  }
}