<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="码头名称" prop="pPortname">
                <el-input v-model="queryParams.pPortname" placeholder="请输入码头名称" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table v-loading="loading" :data="portList">
            <el-table-column label="港口编号" align="center" prop="pId" />
            <el-table-column label="码头名称" align="center" prop="pPortname" />
            <el-table-column label="经度" align="center" prop="pLongitude" />
            <el-table-column label="纬度" align="center" prop="pLatitude" />
            <el-table-column label="每天成本" align="center" prop="pCostperday" />
            <el-table-column label="成本" align="center" prop="pFixdcost" />
            <el-table-column label="每艘船成本" align="center" prop="pCostPerVessalCall" />
            <el-table-column label="天气限制" align="center" prop="pWeatherLimit" />
            <el-table-column label="港口深度" align="center" prop="pDepthPort" />
            <el-table-column label="海上交通控制" align="center" prop="pTrafficControl" />
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
            @pagination="getList" />
    </div>
</template>
  
<script>
import { listPort } from "@/api/system/port";

export default {
    name: "Port",
    data() {
        return {
            // 遮罩层
            loading: true,
            // 显示搜索条件
            showSearch: true,
            // 总条数
            total: 0,
            // 港口信息表格数据
            portList: [],
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                pPortname: null,
            },
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询港口信息列表 */
        getList() {
            this.loading = true;
            listPort(this.queryParams).then(response => {
                this.portList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
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
    }
};
</script>
  