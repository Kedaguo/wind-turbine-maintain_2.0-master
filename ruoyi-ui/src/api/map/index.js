import request from '@/utils/request'

//查询所有的风机
export function listAllTurbine(data) {
    return request({
        url: '/system/taskTurbine/list',
        method: 'post',
        data: {
            taskId: data
        }
    })
}

//正常风机
export function listOnlineTurbine(data) {
    return request({
        url: '/system/taskTurbine/normalTurbineList',
        method: 'post',
        data: {
            taskId: data
        }
    })
}

//故障风机
export function listOfflineTurbine(data) {
    return request({
        url: '/system/taskTurbine/fStateTurbineList',
        method: 'post',
        data: {
            taskId: data
        }
    })
}

//保养风机
export function listMaintainTurbine(data) {
    return request({
        url: '/system/taskTurbine/mStateTurbineList',
        method: 'post',
        data: {
            taskId: data
        }
    })
}

//获取仿真时间
export function getSimulationTime() {
    return request({
        url: '/system/taskStudent/getSimulationTime',
        method: 'get',
    })
}

// 查询港口信息列表
export function listPort(data) {
    return request({
        url: '/system/taskPort/listByUser?taskId='+data,
        method: 'get',
    })
}
