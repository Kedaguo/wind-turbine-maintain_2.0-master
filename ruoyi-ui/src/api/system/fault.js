import request from '@/utils/request'

// 查询故障信息列表
export function listFault(query) {
  return request({
    url: '/system/fault/list',
    method: 'get',
    params: query
  })
}

// 查询故障信息详细
export function getFault(fId) {
  return request({
    url: '/system/fault/' + fId,
    method: 'get'
  })
}

// 新增故障信息
export function addFault(data) {
  return request({
    url: '/system/fault',
    method: 'post',
    data: data
  })
}

// 修改故障信息
export function updateFault(data) {
  return request({
    url: '/system/fault',
    method: 'put',
    data: data
  })
}

// 删除故障信息
export function delFault(fId) {
  return request({
    url: '/system/fault/' + fId,
    method: 'delete'
  })
}
