import request from '@/utils/request'

// 查询operator列表
export function listOperator(query) {
  return request({
    url: '/system/operator/list',
    method: 'get',
    params: query
  })
}

// 查询operator详细
export function getOperator(oId) {
  return request({
    url: '/system/operator/' + oId,
    method: 'get'
  })
}

// 新增operator
export function addOperator(data) {
  return request({
    url: '/system/operator',
    method: 'post',
    data: data
  })
}

// 修改operator
export function updateOperator(data) {
  return request({
    url: '/system/operator',
    method: 'put',
    data: data
  })
}

// 删除operator
export function delOperator(oId) {
  return request({
    url: '/system/operator/' + oId,
    method: 'delete'
  })
}
