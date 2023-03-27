import request from '@/utils/request'
// 查询在线风机列表
export function list(query) {
  return request({
    url: '/monitor/repair/list',
    method: 'get',
    params: query
  })
}
// 删除故障风机监控
export function handleDelete(id) {
    return request({
      url: '/monitor/repair/' + id,
      method: 'post'
    })
  }
  