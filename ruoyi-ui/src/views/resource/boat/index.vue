<template>
    <div class="app-container">
        <el-table v-loading="loading" :data="boatList">
            <el-table-column label="船舶类型" align="center" prop="bType">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.b_type" :value="scope.row.bType" />
                </template>
            </el-table-column>
            <el-table-column label="船舶速度" align="center" prop="bSpeed" />
            <el-table-column label="船舶容量" align="center" prop="bCapacity" />
            <el-table-column label="风速" align="center" prop="bWindSpeed" />
            <el-table-column label="海浪" align="center" prop="bWaveHeight" />
            <el-table-column label="每年成本" align="center" prop="bAnnualCost" />
            <el-table-column label="每小时油耗" align="center" prop="bHourCost" />
            <el-table-column label="空闲" align="center" prop="bHourCost" />
            <el-table-column label="工作" align="center" prop="bHourCost" />
        </el-table>
    </div>
</template>
  
<script>
import { listBoat} from "@/api/resource/boat";

export default {
    name: "Boat",
    dicts: ['b_type'],
    data() {
        return {
            loading: true,          
            boatList: [],  
        };
    },
    created() {
        //this.getList();
    },
    methods: {
        /** 查询boat列表 */
        getList() {
            this.loading = true;
            listBoat().then(response => {
                this.boatList = response.rows;
                this.loading = false;
            });
        },
    }
};
</script>
  