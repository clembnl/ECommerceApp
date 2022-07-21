const fs = require('fs')

module.exports = {
    devServer: {
      proxy: {
        '^/api': {
          target: 'http://localhost:8090',
          changeOrigin: true,
          logLevel: 'debug',
          pathRewrite: { '^/api': '' },
        },        
      },
    },
  }
