import request from '@/utils/request'

// 查询arrangement列表
export function listArrangement(query) {
  return request({
    url: '/system/arrangement/list',
    method: 'get',
    params: query
  })
}

// 查询arrangement详细
export function getArrangement(aId) {
  return request({
    url: '/system/arrangement/' + aId,
    method: 'get'
  })
}

// 新增arrangement
export function addArrangement(data) {
  return request({
    url: '/system/arrangement',
    method: 'post',
    data: data
  })
}

// 修改arrangement
export function updateArrangement(data) {
  return request({
    url: '/system/arrangement',
    method: 'put',
    data: data
  })
}

// 删除arrangement
export function delArrangement(aId) {
  return request({
    url: '/system/arrangement/' + aId,
    method: 'delete'
  })
}
