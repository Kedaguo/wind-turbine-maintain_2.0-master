import request from '@/utils/request'

// 学生查询task列表
export function studentListTask(query) {
  return request({
    url: '/system/taskStudent/list',
    method: 'get',
    params: query
  })
}

// 开始当前任务
export function studentTaskBegin(data) {
  //  console.log("111111")
  return request({
    url: '/system/task/startTaskJob',
    method: 'post',
    data: data
  })
}

