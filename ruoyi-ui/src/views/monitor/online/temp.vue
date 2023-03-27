<template>
    <div class="app-container">
      <el-table
        v-loading="loading"
        :data="list.slice((pageNum-1)*pageSize,pageNum*pageSize)"
        style="width: 100%;"
      >
        <el-table-column label="风机编号" align="center" prop="tokenId" :show-overflow-tooltip="true" />
        <el-table-column label="经度" align="center" prop="deptName" />
        <el-table-column label="维度" align="center" prop="deptName" />
        <el-table-column label="功率" align="center" prop="userName" :show-overflow-tooltip="true" />
        <el-table-column label="价格" align="center" prop="deptName" />
        <el-table-column label="寿命" align="center" prop="deptName" />
        <el-table-column label="费用" align="center" prop="deptName" />
        <el-table-column label="品牌" align="center" prop="deptName" />
        <el-table-column label="运行状态" align="center" prop="ipaddr" :show-overflow-tooltip="true" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleForceLogout(scope.row)"
              v-hasPermi="['monitor:online:forceLogout']"
            >强退</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <pagination v-show="total>0" :total="total" :page.sync="pageNum" :limit.sync="pageSize" />
    </div>
  </template>
  
  <script>
  import { list, forceLogout } from "@/api/monitor/online";
  
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
      /** 查询登录日志列表 */
      getList() {
        this.loading = true;
        list(this.queryParams).then(response => {
          this.list = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
  
      /** 强退按钮操作 */
      handleForceLogout(row) {
        this.$modal.confirm('是否确认强退名称为"' + row.userName + '"的用户？').then(function() {
          return forceLogout(row.tokenId);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("强退成功");
        }).catch(() => {});
      }
    }
  };
  </script>
  
  