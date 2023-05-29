import request from '@/utils/request'

// 查询港口信息列表
export function listPort(query) {
  return request({
    url: '/system/taskPort/list',
    method: 'get',
    params: query
  })
}

// 查询港口信息详细
export function getPort(pId) {
  return request({
    url: '/system/port/' + pId,
    method: 'get'
  })
}

// 新增港口信息
export function addPort(data) {
  return request({
    url: '/system/port',
    method: 'post',
    data: data
  })
}

// 修改港口信息
export function updatePort(data) {
  return request({
    url: '/system/port',
    method: 'put',
    data: data
  })
}

// 删除港口信息
export function delPort(pId) {
  return request({
    url: '/system/port/' + pId,
    method: 'delete'
  })
}
