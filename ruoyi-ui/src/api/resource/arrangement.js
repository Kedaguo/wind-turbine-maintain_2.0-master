import request from '@/utils/request'

// 查询arrangement列表
export function listArrangement(query) {
  return request({
    url: '/system/arrangement/listByStudent',
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


