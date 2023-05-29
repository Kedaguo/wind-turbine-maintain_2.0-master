import request from '@/utils/request'
import { log } from 'console'

// 查询boat列表
export function listBoat(query) {
  return request({
    url: '/system/taskBoat/list',
    method: 'get',
    params: query
  })
}
//查询船舶总数
export function getBoatNum(query) {
  return request({
    url: '/system/taskBoat/getBoatNumber',
    method: 'get',
    params: query
  })
}
//查询船舶待命数量
export function getWaitBoatNumber(query) {
  return request({
    url: '/system/taskBoat/getWaitBoatNumber',
    method: 'get',
    params: query
  })
}
//查询船舶等待条件出海作业数量
export function getStandbyBoatNumber(query) {
  return request({
    url: '/system/taskBoat/getStandbyBoatNumber',
    method: 'get',
    params: query
  })
}
//查询船舶出海作业数量
export function getWorkBoatNumber(query) {
  return request({
    url: '/system/taskBoat/getWorkBoatNumber',
    method: 'get',
    params: query
  })
}
