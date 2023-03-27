import request from '@/utils/request'

// 动态获取路由
export const getRouters = () => {
  return request({
    url: '/getRouters',
    method: 'get'
  })
}
