import request from '@/utils/request'

// 查询task列表
export function listTask(query) {
  return request({
    url: '/system/task/list',
    method: 'get',
    params: query
  })
}

// 查询task详细
export function getTask(taskId) {
  return request({
    url: '/system/task/' + taskId,
    method: 'get'
  })
}

// 新增task
export function addTask(data) {
  return request({
    url: '/system/task',
    method: 'post',
    data: data
  })
}

// 修改task
export function updateTask(data) {
  return request({
    url: '/system/task',
    method: 'put',
    data: data
  })
}

// 删除task
export function delTask(taskId) {
  return request({
    url: '/system/task/' + taskId,
    method: 'delete'
  })
}

// 学生查询task列表
export function studentListTask(query) {
  return request({
    url: '/system/taskStudent/list',
    method: 'get',
    params: query
  })
}

// 开始当前任务
export function TaskBegin(data) {
  return request({
    url: '/monitor/job/startTaskJob',
    method: 'post',
    data: data
  })
}


