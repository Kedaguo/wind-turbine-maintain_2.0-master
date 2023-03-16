import request from '@/utils/request'

// 查询maintain列表
export function listMaintain(query) {
  return request({
    url: '/system/maintain/list',
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

// 新增maintain
export function addMaintain(data) {
  return request({
    url: '/system/maintain',
    method: 'post',
    data: data
  })
}

// 修改maintain
export function updateMaintain(data) {
  return request({
    url: '/system/maintain',
    method: 'put',
    data: data
  })
}

// 删除maintain
export function delMaintain(mId) {
  return request({
    url: '/system/maintain/' + mId,
    method: 'delete'
  })
}
