import request from '@/utils/request'

// 查询故障风机监控列表
export function listOffline(query) {
  return request({
    url: '/monitor/offline/list',
    method: 'get',
    params: query
  })
}

// 删除故障风机监控
export function delOffline(id) {
  return request({
    url: '/monitor/offline/',
    method: 'post',
    params: id
  })
}
