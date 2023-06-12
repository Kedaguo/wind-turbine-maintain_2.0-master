import request from '@/utils/request'

// 查询repairOrder列表
export function listRepairOrder(query) {
  return request({
    url: '/system/repairOrder/listByStudent',
    method: 'get',
    params: query
  })
}

export function noStartStateList(query) {
  return request({
    url: '/system/repairOrder/noStartNum',
    method: 'get',
    params: query
  })
}

export function runningStateList(query) {
  return request({
    url: '/system/repairOrder/runningNum',
    method: 'get',
    params: query
  })
}

export function endStateList(query) {
  return request({
    url: '/system/repairOrder/endNum',
    method: 'get',
    params:query
  })
}
export function rFaultNum(query) {
  return request({
    url: '/system/repairOrder/rFaultNum',
    method: 'get',
    params: query
  })
}

export function rMaintainNum(query) {
  return request({
    url: '/system/repairOrder/rMaintainNum',
    method: 'get',
    params: query
  })
}

export function rFaultAndMaintainNum(query) {
  return request({
    url: '/system/repairOrder/rFaultAndMaintainNum',
    method: 'get',
    params:query
  })
}
