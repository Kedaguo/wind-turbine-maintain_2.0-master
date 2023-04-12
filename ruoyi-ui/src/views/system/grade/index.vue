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
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['system:task:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="taskList"  row-key="taskId"
            :tree-props="{ children: 'children', hasChildren: 'hasChildren' }">>
            <el-table-column label="发布时间" align="center" prop="taskTime">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.taskCreateTime, '{y}-{m}-{d}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="发布人" align="center" prop="taskCreateBy" />
            <el-table-column label="学生学号" align="center" prop="userName" />
            <el-table-column label="学生姓名" align="center" prop="nickName" />
            <el-table-column label="任务花费" align="center" prop="taskCost" />
            <el-table-column label="时间消耗" align="center" prop="taskTime" />
            <el-table-column label="出海失败" align="center" prop="taskTime" />
            <el-table-column label="任务状态" align="center" prop="taskState">
                <template slot-scope="scope">
                    <el-tag v-if="scope.row.taskState === 1"><dict-tag :options="dict.type.tl_task_state"
                            :value="scope.row.taskState" /></el-tag>
                    <el-tag v-else-if="scope.row.taskState === 2" type="success"><dict-tag
                            :options="dict.type.tl_task_state" :value="scope.row.taskState" /></el-tag>
                    <el-tag v-else type="info"><dict-tag :options="dict.type.tl_task_state"
                            :value="scope.row.taskState" /></el-tag>
                </template>
            </el-table-column>
            <el-table-column label="任务成绩" align="center" prop="taskPoints" />
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.taskState === 1" size="mini" type="text" icon="el-icon-edit"
                        @click="queryLog(scope.row)" v-hasPermi="['system:task:remove']">出海日志</el-button>
                    <el-button v-if="scope.row.taskState === 1" size="mini" type="text" icon="el-icon-edit"
                        @click="handleGrade(scope.row)" v-hasPermi="['system:task:remove']">任务评价</el-button>
                </template>
            </el-table-column>
        </el-table>
        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />
        <el-dialog :visible.sync="logDialog" title="出海日志">
            <el-table :data="gridData">
                <el-table-column property="address" label="风机编号"></el-table-column>
                <el-table-column property="date" label="码头" width="150"></el-table-column>
                <el-table-column property="name" label="海上平台" width="200"></el-table-column>
                <el-table-column property="address" label="船只"></el-table-column>
                <el-table-column property="address" label="人员"></el-table-column>
                <el-table-column property="address" label="维修顺序"></el-table-column>
            </el-table>
            <span slot="footer" class="dialog-footer">
                <el-button @click="logDialog = false">关 闭</el-button>
            </span>
        </el-dialog>
        <el-dialog title="任务评价" :visible.sync="gradeDialog" width="30%">
            <el-form ref="gradeForm" :rules="rules" :model="gradeForm" label-width="80px">
                <el-form-item label="输入学生成绩" prop="gradeForm.grade">
                    <el-input v-model="gradeForm.grade"></el-input>
                </el-form-item>

            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="logDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitForm('gradeForm')">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import { teacherListTask, studentLog } from "@/api/system/grade";

export default {
    name: "Task",
    dicts: ['tl_task_state'],
    data() {
        return {
            gradeForm: {
                grade: '',
            },
            rules: {
                number: [
                    { required: true, message: '请输入小数', trigger: 'blur' },
                    { pattern: /^(\d*\.)?\d+$/, message: '请输入正确的小数格式', trigger: 'blur' }
                ]
            },
            //航海日志的对话框
            logDialog: false,
            //打分对话框
            gradeDialog: false,
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
            //航海日志
            gridData: [],
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                taskPoints: null,
                taskState: null,
                taskCreateTime: null,
                taskCreateBy: null
            },

        };
    },
    created() {
        this.getList();
    },
    methods: {
        //查看学生出海日志
        queryLog(row) {
            this.logDialog = true;
            studentLog().then
        },
        //老师评分
        handleGrade() {

        },
        /** 查询task列表 */
        getList() {
            this.loading = true;
            teacherListTask(this.queryParams).then(response => {
                console.log(response)
                this.taskList = response.rows;
                console.log(this.taskList)
                this.total = response.total;
                this.loading = false;
            });
        },
        // 取消按钮
        cancel() {
            this.open = false;
            this.reset();
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

        // 提交学生成绩
        submitForm(form) {
            this.$refs[form].validate(valid => {
                if (valid) {
                    console.log(this.form);
                    // 在这里写提交表单的业务逻辑
                    this.dialogVisible = false; // 关闭对话框
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
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
