import request from '@/utils/request'

// 查询repairOrder列表
export function listRepairOrder(query) {
  return request({
    url: '/system/repairOrder/list',
    method: 'get',
    params: query
  })
}

// 查询repairOrder详细
export function getRepairOrder(rId) {
  return request({
    url: '/system/repairOrder/' + rId,
    method: 'get'
  })
}

// 新增repairOrder
export function addRepairOrder(data) {
  return request({
    url: '/system/repairOrder',
    method: 'post',
    data: data
  })
}

// 修改repairOrder
export function updateRepairOrder(data) {
  return request({
    url: '/system/repairOrder',
    method: 'put',
    data: data
  })
}

// 删除repairOrder
export function delRepairOrder(rId) {
  return request({
    url: '/system/repairOrder/' + rId,
    method: 'delete'
  })
}
