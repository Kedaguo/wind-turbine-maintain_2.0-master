<template>
    <div class="app-container">
        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" @click="handleDelete"
                    v-hasPermi="['monitor/offline:offline:remove']">保养</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="offlineList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="风机编号" align="center" prop="id" />
            <el-table-column label="保养信息" align="center" prop="faultInformation" />
            <el-table-column label="保养时间" align="center" prop="faultCode" />
            <el-table-column label="保养人数" align="center" prop="faultCode" />
            <el-table-column label="保养价格" align="center" prop="faultCode" />
            <el-table-column label="状态" align="center" prop="faultInformation" />
        </el-table>
        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />
    </div>
</template>
  
<script>
import { listOffline, delOffline,} from "@/api/monitor/maintain";

export default {
    name: "Maintain",
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
            // 故障风机监控表格数据
            offlineList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                faultCode: null,
                faultInformation: null
            },
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            this.loading = true;
            listOffline(this.queryParams).then(response => {
                this.offlineList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },

        // 多选框选中数据
        handleSelectionChange(selection) {
            this.ids = selection.map(item => item.id)
            this.single = selection.length !== 1
            this.multiple = !selection.length
        },
        /** 删除按钮操作 */
        handleDelete(row) {
            const ids = row.id || this.ids;
            this.$modal.confirm('添加到出海任务中？').then(function () {
                return delOffline(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("添加成功");
            }).catch(() => { });
        },
    }
};
</script>
  