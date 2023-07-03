<template>
  <div id="main">
    <!-- 弹框 -->
    <div class="modal-wrapper" v-if="showDialog">
      <div class="modal-mask" @click.stop></div>
      <div class="modal-content">
        <el-dialog
          v-if="showDialog"
          title="提示消息"
          :visible.sync="showDialog"
        >
          <p
            class="alert-text"
            style="text-align: center; font-size: 25px"
          >
            任务尚未开始，请开始任务！
          </p>
          <p class="alert-timer">跳转倒计时：{{ countdown }}秒</p>
        </el-dialog>
      </div>
    </div>
    <div class="app-container">
      <el-form
        :model="queryParams"
        ref="queryForm"
        size="small"
        :inline="true"
        v-show="showSearch"
        label-width="68px"
      >
        <el-form-item label="码头名称" prop="pPortname">
          <el-input
            v-model="queryParams.pPortname"
            placeholder="请输入码头名称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button
            type="primary"
            icon="el-icon-search"
            size="mini"
            @click="handleQuery"
            >搜索</el-button
          >
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
            >重置</el-button
          >
        </el-form-item>
      </el-form>
      <el-table v-loading="loading" :data="portList">
        <el-table-column label="港口序列" type="index" width="50" />
        <!-- <el-table-column label="港口编号" align="center" prop="pId" /> -->
        <el-table-column label="码头名称" align="center" prop="pPortname" />
        <el-table-column label="经度" align="center" prop="pLongitude" />
        <el-table-column label="纬度" align="center" prop="pLatitude" />
        <el-table-column label="每天成本" align="center" prop="pCostperday" />
        <el-table-column label="成本" align="center" prop="pFixdcost" />
        <el-table-column
          label="每艘船成本"
          align="center"
          prop="pCostPerVessalCall"
        />
        <el-table-column label="天气限制" align="center" prop="pWeatherLimit" />
        <el-table-column label="港口深度" align="center" prop="pDepthPort" />
        <el-table-column
          label="海上交通控制"
          align="center"
          prop="pTrafficControl"
        />
      </el-table>

      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>
  </div>
</template>
<style>
/* .alert{
    text-align: center;
  } */
/* .alert{
    width: 200px;
    height: auto;
  } */
.alert-text {
  text-align: center;
}
.alert-timer {
  font-size: 15px;
}
.modal-wrapper {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 9999;
}

.modal-mask {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.modal-content {
  /* 弹框内容样式 */
  z-index: 2;
}
</style>
<script>
import { listPort } from "@/api/system/port";

export default {
  name: "Port",
  data() {
    return {
      // 遮罩层
      loading: true,
      showDialog: false,
      countdown: 3, // 计时器初始值为3
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
        taskId: sessionStorage.getItem("taskId"),
        pageNum: 1,
        pageSize: 10,
        pPortname: null,
      },
    };
  },
  created() {},
  mounted() {
    // 在通过mounted调用即可
    const taskId = sessionStorage.getItem("taskId");
    if (!taskId) {
      // 不存在taskId，显示弹框
      this.showDialog = true; // 使用一个变量来控制弹框的显示与隐藏
      this.startCountdown(); // 开始计时器
      // window.addEventListener("beforeunload", (event) => this.handleBeforeUnload(event));
    } else {
      this.startTimer();
    }
  },
  methods: {
    startTimer(){
      this.timerId = setInterval(this.timerHandler, 1000);
    },
    stopTimer() {
      clearInterval(this.timerId);
      this.timerId = null;
    },
    timerHandler(){
      this.getList();
    },
    startCountdown() {
      let timer = setInterval(() => {
        this.countdown--;
        if (this.countdown === 0) {
          clearInterval(timer);
          // 在这里执行路由跳转
          console.log("123123");
          // this.$router.push('/your-route');
          this.$router.push({ path: "/task" });

          // 关闭当前页面
          this.$nextTick(() => {
            this.$destroy();
          });
        }
      }, 1000); // 每秒减少1
    },
    /** 查询港口信息列表 */
    getList() {
      this.loading = false;
      listPort(this.queryParams).then((response) => {
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
  },
};
</script>
