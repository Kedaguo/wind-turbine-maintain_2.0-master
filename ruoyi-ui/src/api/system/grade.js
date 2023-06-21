import request from '@/utils/request'

//教师查询所有学生任务的明细
export function teacherListTask(query) {
    return request({
        url: '/system/taskStudent/taskTeacherList',
        method: 'get',
        params: query
    })
}
// 查询学生的出海日志
export function studentLog(query) {
    return request({
        url: '/system/task/list',
        method: 'get',
        params: query
    })
}
// 查询学生的出海日志
export function gradeScoring(query) {
    return request({
        url: '/system/taskStudent',
        method: 'put',
        data: query
    })
}
