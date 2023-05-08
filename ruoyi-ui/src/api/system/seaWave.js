import request from '@/utils/request'

// 查询seaWave列表
export function listSeaWave(query) {
  return request({
    url: '/system/seaWave/list',
    method: 'get',
    params: query
  })
}

// 查询seaWave详细
export function getSeaWave(sId) {
  return request({
    url: '/system/seaWave/' + sId,
    method: 'get'
  })
}

// 新增seaWave
export function addSeaWave(data) {
  return request({
    url: '/system/seaWave',
    method: 'post',
    data: data
  })
}

// 修改seaWave
export function updateSeaWave(data) {
  return request({
    url: '/system/seaWave',
    method: 'put',
    data: data
  })
}

// 删除seaWave
export function delSeaWave(sId) {
  return request({
    url: '/system/seaWave/' + sId,
    method: 'delete'
  })
}
