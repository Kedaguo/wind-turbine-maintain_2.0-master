<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="任务状态" prop="taskState">
                <el-select v-model="queryParams.taskState" placeholder="请选择任务状态" clearable>
                    <el-option v-for="dict in dict.type.tl_task_state" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="发布时间" prop="taskCreateTime">
                <el-date-picker clearable v-model="queryParams.taskCreateTime" type="date" value-format="yyyy-MM-dd"
                    placeholder="请选择任务发布时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-table v-loading="loading" :data="taskList">
            <el-table-column label="序号" type="index" align="center"></el-table-column>
            <el-table-column label="发布时间" align="center" prop="taskCreateTime" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.taskCreateTime, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="发布人" align="center" prop="taskCreateBy" />
            <el-table-column label="任务状态" align="center" prop="taskState">
                <template slot-scope="scope">
                    <el-tag><dict-tag :options="dict.type.tl_task_state" :value="scope.row.taskState" /></el-tag>
                </template>
            </el-table-column>
            <el-table-column label="仿真开始时间" align="center" prop="taskStartTime" />
            <el-table-column label="仿真结束时间" align="center" prop="taskEndTime" />
            <el-table-column label="任务花费" align="center" prop="taskCost" />
            <el-table-column label="时间消耗" align="center" prop="taskTime" />
            <el-table-column label="任务成绩" align="center" prop="taskPoints" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <div>
                        <el-button class="link-type" size="mini" type="text" @click="startTask(scope.row)"
                            v-if="scope.row.taskState === 1">开始任务</el-button>
                        <el-button class="link-type" size="mini" type="text" @click="continueTask(scope.row)"
                            v-if="scope.row.taskState === 2">继续任务</el-button>
                        <el-button class="link-type" size="mini" type="text" @click="showTaskDetails(scope.row)"
                            v-if="scope.row.taskState === 3">查看详情</el-button>
                    </div>
                </template>
            </el-table-column>
        </el-table>
        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />
    </div>
</template>

<script>
import { studentListTask, taskBegin } from "@/api/system/task";

export default {
    name: "Task",
    dicts: ['tl_task_state'],
    data() {
        return {
            // 遮罩层
            loading: true,
            // 选中数组
            ids: [],
            // 非单个禁用
            single: true,
            // 非多个禁用
            multiple: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // task表格数据
            taskList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                taskPoints: null,
                taskState: null,
                taskCreateTime: null,
                taskCreateBy: null
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
            }
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询task列表 */
        getList() {
            this.loading = true;
            studentListTask(this.queryParams).then(response => {
                console.log(response)
                this.taskList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
        },
        // 表单重置
        reset() {
            this.form = {
                taskId: null,
                userId: null,
                taskCost: null,
                taskTime: null,
                taskPoints: null,
                taskState: null,
                taskStartTime: null,
                taskEndTime: null,
                taskCreateTime: null,
                taskCreateBy: null
            };
            this.resetForm("form");
        },
        /** 搜索按钮操作 */
        handleQuery() {
            this.queryParams.pageNum = 1;
            this.getList();
        },
        /** 重置按钮操作 */
        resetQuery() {
            this.resetForm("queryForm");
            this.handleQuery();
        },
        // 开始任务
        startTask(row) {
            this.$confirm('开始执行当前任务', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                //将当前开始的任务存储到session中
                sessionStorage.setItem('taskId', row.taskId);
                let invokeTarget = `ryTask.windTurbineSimulation(${row.taskId}L,${row.userId}L,1000)`
                let data = {
                    invokeTarget: invokeTarget,
                    jobId: row.jobId,
                    status: 0
                }
                taskBegin(data).then(() => {
                    this.$router.push(`task-map/index/${row.taskId}`)
                })
            }).catch(() => {    
            });
        },
        //继续任务
        continueTask(row){
            this.$router.push(`task-map/index/${row.taskId}`)
        },
        //查看任务的详细信息
        showTaskDetails(row){
            
        }
    }
};
</script>
