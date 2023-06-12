import request from '@/utils/request'

// 查询故障信息列表
export function listFault(query) {
  return request({
    url: '/system/fault/listByStudent',
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


