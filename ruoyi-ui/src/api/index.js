import request from '@/utils/request'

// 查询故障风机监控列表
export function list() {
  return request({
    url: '/index/list',
    method: 'get',
  })
}

export function launchOut(data) {
  return request({
    url: '/index/launch',
    method: 'post',
    data: data
  })
}

export function chart() {
  return request({
    url: '/index/chart',
    method: 'get',
  })
}