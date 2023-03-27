<template>
    <div class="app-container">
        <el-table v-loading="loading" :data="list.slice((pageNum - 1) * pageSize, pageNum * pageSize)" style="width: 100%;">
            <el-table-column label="序号" type="index" align="center">
                <template slot-scope="scope">
                    <span>{{ (pageNum - 1) * pageSize + scope.$index + 1 }}</span>
                </template>
            </el-table-column>
            <el-table-column label="风机编号" align="center" prop="tokenId" :show-overflow-tooltip="true" />
            <el-table-column label="风机功率" align="center" prop="userName" :show-overflow-tooltip="true" />
            <el-table-column label="风机品牌" align="center" prop="deptName" />
            <el-table-column label="运行状态" align="center" prop="ipaddr" :show-overflow-tooltip="true" />
            <!-- <el-table-column label="登录地点" align="center" prop="loginLocation" :show-overflow-tooltip="true" />
        <el-table-column label="浏览器" align="center" prop="browser" />
        <el-table-column label="操作系统" align="center" prop="os" /> -->
            <el-table-column label="登录时间" align="center" prop="loginTime" width="180">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.loginTime) }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleForceLogout(scope.row)"
                        v-hasPermi="['monitor:online:forceLogout']">强退</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" />
    </div>
</template>
  
<script>
import { list, forceLogout } from "@/api/student/online";

export default {
    name: "Online",
    data() {
        return {
            // 遮罩层
            loading: true,
            // 总条数
            total: 0,
            // 表格数据
            list: [],
            pageNum: 1,
            pageSize: 10,         
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询在线风机列表 */
        getList() {
            this.loading = true;
            list(4).then(response => {
                this.list = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        },
        /** 强退按钮操作 */
        handleForceLogout(row) {
            this.$modal.confirm('是否确认强退名称为"' + row.userName + '"的用户？').then(function () {
                return forceLogout(row.tokenId);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("强退成功");
            }).catch(() => { });
        }
    }
};
</script>
  
  