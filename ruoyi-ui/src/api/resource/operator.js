import request from '@/utils/request'

// 查询boat列表
export function listOperator() {
  return request({
    url: '/system/taskOperator/list',
    method: 'get',
  })
}
