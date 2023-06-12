import request from '@/utils/request'

// 查询maintain列表
export function listMaintain(query) {
  return request({
    url: '/system/maintain/listByStudent',
    method: 'get',
    params: query
  })
}

// 查询maintain详细
export function getMaintain(mId) {
  return request({
    url: '/system/maintain/' + mId,
    method: 'get'
  })
}
