import request from '@/utils/request'
// 查询在线风机列表
export function list(query) {
  return request({
    url: '/monitor/online/list',
    method: 'get',
    params: query
  })
}
