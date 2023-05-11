//共享数据
const task = {
    state : {
        taskId: null
    },
    //获取共享变量的方法
    getters : {
        getTaskId(state) {
            return state.taskId
        }
    },
    //方法用于修改共享存储中的数据
    mutations : {
        changeTaskId(state, taskId) {
            state.taskId = taskId
        }
    },
    actions : {
        asyncUpdateTaskId({ commit }, taskId) {
            commit('changeTaskId', taskId)
        }
    },

}
export default task
