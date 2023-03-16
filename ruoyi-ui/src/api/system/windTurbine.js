import request from '@/utils/request'

// 查询风机配置信息列表
export function listWindTurbine(query) {
  return request({
    url: '/system/windTurbine/list',
    method: 'get',
    params: query
  })
}

// 查询风机配置信息详细
export function getWindTurbine(tId) {
  return request({
    url: '/system/windTurbine/' + tId,
    method: 'get'
  })
}

// 新增风机配置信息
export function addWindTurbine(data) {
  return request({
    url: '/system/windTurbine',
    method: 'post',
    data: data
  })
}

// 修改风机配置信息
export function updateWindTurbine(data) {
  return request({
    url: '/system/windTurbine',
    method: 'put',
    data: data
  })
}

// 删除风机配置信息
export function delWindTurbine(tId) {
  return request({
    url: '/system/windTurbine/' + tId,
    method: 'delete'
  })
}
