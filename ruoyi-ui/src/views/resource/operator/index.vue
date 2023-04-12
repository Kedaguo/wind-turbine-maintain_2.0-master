<template>
    <div class="app-container">
        <el-table v-loading="loading" :data="operatorList">
            <el-table-column label="年薪" align="center" prop="bSpeed" />
            <el-table-column label="每小时工资" align="center" prop="bCapacity" />
            <el-table-column label="加班费" align="center" prop="bWindSpeed" />
            <el-table-column label="空闲" align="center" prop="bHourCost" />
            <el-table-column label="工作" align="center" prop="bHourCost" />
        </el-table>
    </div>
</template>
  
<script>
import { listOperator, } from "@/api/resource/operator";

export default {
    name: "Operator",
    data() {
        return {
            // 遮罩层
            loading: true, 
            boatList: [],
        };
    },
    created() {
        this.getList();
    },
    methods: {
        getList() {
            this.loading = true;
            listOperator().then(response => {
                this.OperatorList = response.rows;
                this.loading = false;
            });
        },
    }
};
</script>
  