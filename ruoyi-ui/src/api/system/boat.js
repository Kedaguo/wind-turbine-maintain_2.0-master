import request from '@/utils/request'

// 查询boat列表
export function listBoat(query) {
  return request({
    url: '/system/boat/list',
    method: 'get',
    params: query
  })
}

// 查询boat详细
export function getBoat(bId) {
  return request({
    url: '/system/boat/' + bId,
    method: 'get'
  })
}

// 新增boat
export function addBoat(data) {
  return request({
    url: '/system/boat',
    method: 'post',
    data: data
  })
}

// 修改boat
export function updateBoat(data) {
  return request({
    url: '/system/boat',
    method: 'put',
    data: data
  })
}

// 删除boat
export function delBoat(bId) {
  return request({
    url: '/system/boat/' + bId,
    method: 'delete'
  })
}
