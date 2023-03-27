<template>
  <div class="app-container">
    <el-table
      v-loading="loading"
      :data="list.slice((pageNum-1)*pageSize,pageNum*pageSize)"
      style="width: 100%;" show-summary="true"
    >
      <el-table-column label="风机编号" align="center" prop="tokenId" :show-overflow-tooltip="true" />
      <el-table-column label="时间消耗" align="center" prop="deptName" />
      <el-table-column label="人员派遣" align="center" prop="deptName" />
      <el-table-column label="任务类型" align="center" prop="deptName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['monitor:online:forceLogout']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" />
  </div>
</template>

<script>
import { list, handleDelete } from "@/api/monitor/repair";

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
      // 查询参数
      queryParams: {
        ipaddr: undefined,
        userName: undefined
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
  
    getList() {
      this.loading = true;
      list(this.queryParams).then(response => {
        this.list = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    
    handleDelete(row) {
      this.$modal.confirm('取消当前任务？').then(function() {
        return handleDelete(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("取消成功");
      }).catch(() => {});
    }
  }
};
</script>

