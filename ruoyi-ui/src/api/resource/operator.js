import request from '@/utils/request'

// 查询boat列表
export function listOperator(query) {
  return request({
    url: '/system/taskOperator/listByUser',
    method: 'get',
    params:query
  })
}
export function getStandByONumber(query) {
  return request({
    url: '/system/taskOperator/getStandByONumber',
    method: 'get',
    params: query
  })
}
export function getWaitONumber(query) {
  return request({
    url: '/system/taskOperator/getWaitONumber',
    method: 'get',
    params: query
  })
}
export function getWorkONumber(query) {
  return request({
    url: '/system/taskOperator/getWorkONumber',
    method: 'get',
    params: query
  })
}

