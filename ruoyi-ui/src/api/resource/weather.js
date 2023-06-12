import request from '@/utils/request'

// 查询boat列表
export function weatherList(query) {
  return request({
    url: '/system/seaWave/weatherList',
    method: 'get',
    params: query
  })
}


