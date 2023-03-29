<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="任务成绩" prop="taskPoints">
                <el-input v-model="queryParams.taskPoints" placeholder="请输入任务成绩" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="任务状态" prop="taskState">
                <el-select v-model="queryParams.taskState" placeholder="请选择任务状态" clearable>
                    <el-option v-for="dict in dict.type.tl_task_state" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item label="任务发布时间" prop="taskCreateTime">
                <el-date-picker clearable v-model="queryParams.taskCreateTime" type="date" value-format="yyyy-MM-dd"
                    placeholder="请选择任务发布时间">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="发布人" prop="taskCreateBy">
                <el-input v-model="queryParams.taskCreateBy" placeholder="请输入发布人" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['system:task:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['system:task:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
                    v-hasPermi="['system:task:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['system:task:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="任务编号" align="center" prop="taskId" />
            <el-table-column label="发布时间" align="center" prop="taskCreateTime" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.taskCreateTime, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="发布人" align="center" prop="taskCreateBy" />
            <el-table-column label="任务花费" align="center" prop="taskCost" />
            <el-table-column label="时间消耗" align="center" prop="taskTime" />
            <el-table-column label="任务成绩" align="center" prop="taskPoints" />
            <el-table-column label="任务状态" align="center" prop="taskState">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.taskState === 1"><dict-tag :options="dict.type.tl_task_state" :value="scope.row.taskState" /></el-tag>
                    <el-tag v-if="scope.row.taskState === 2" type="success"><dict-tag :options="dict.type.tl_task_state" :value="scope.row.taskState" /></el-tag>
                    <el-tag v-else type="info"><dict-tag :options="dict.type.tl_task_state" :value="scope.row.taskState" /></el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['system:task:edit']">修改</el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['system:task:remove']">删除</el-button>
                    <el-button v-if="scope.row.taskState === 1" size="mini" type="text" icon="el-icon-edit" @click="handleTask(scope.row)"
                        v-hasPermi="['system:task:remove']">开始任务</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />
    </div>
</template>

<script>
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/system/task";

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
        //开始执行任务
        handleTask(){
            this.$modal.confirm('开始当前任务？')
        },
        /** 查询task列表 */
        getList() {
            this.loading = true;
            listTask(this.queryParams).then(response => {
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
        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map(item => item.taskId)
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
       
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const taskId = row.taskId || this.ids
            getTask(taskId).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改task";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.taskId != null) {
                        updateTask(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addTask(this.form).then(response => {
                            this.$modal.msgSuccess("新增成功");
                            this.open = false;
                            this.getList();
                        });
                    }
                }
            });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            const taskIds = row.taskId || this.ids;
            this.$modal.confirm('是否确认删除task编号为"' + taskIds + '"的数据项？').then(function () {
                return delTask(taskIds);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('system/task/export', {
                ...this.queryParams
            }, `task_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
  