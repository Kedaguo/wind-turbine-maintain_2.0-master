import request from '@/utils/request'

// 查询boat列表
export function listBoat() {
  return request({
    url: '/system/taskBoat/list',
    method: 'get',
    params: query
  })
}
