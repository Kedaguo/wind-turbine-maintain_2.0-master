<template>
  <div class="main">
    <!-- 弹框 -->
    <div class="modal-wrapper"   v-if="showDialog"  @click.stop>
      <div class="modal-mask"  v-if="showDialog"  @click.stop></div>
        <div class="modal-content" >
          <el-dialog  v-if="showDialog" title="提示消息" :visible.sync="showDialog">
            <p class="alert-text" style="text-align: center;color:#ed0505;font-size: 25px;">任务尚未开始，请开始任务！</p>
            <p class="alert-timer">跳转倒计时：{{ countdown }}秒</p>
          </el-dialog>
        </div>
    </div>

    <div style="width: 650px; height: 400px; float: left" id="rState"></div>
    <div style="width: 650px; height: 400px; float: right ;" id="rType"></div>
    <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="风机编号" prop="tId">
        <el-input
          v-model="queryParams.tId"
          placeholder="请输入风机编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="派单状态" prop="rState">
        <el-select v-model="queryParams.rState" placeholder="请选择派单状态" clearable>
          <el-option
            v-for="dict in dict.type.repair_order_state"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label=" 发生时间" prop="rCreateTime">
        <el-date-picker clearable
          v-model="queryParams.rCreateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择 发生时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="repairOrderList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="维修单编号" align="center" prop="rId" />
      <el-table-column label="任务编号" align="center" prop="taskId" />
      <el-table-column label="风机编号" align="center" prop="tId" />
      <el-table-column label="派单状态" align="center" prop="rState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.repair_order_state" :value="scope.row.rState"/>
        </template>
      </el-table-column>
      <el-table-column label=" 发生时间" align="center" prop="rCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rCreateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
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
  .alert-text{
    text-align: center;
  }
  .alert-timer{
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
import { listRepairOrder, noStartStateList,runningStateList,endStateList, rFaultAndMaintainNum, rMaintainNum, rFaultNum } from "@/api/resource/repairOrder";

export default {
  name: "RepairOrder",
  dicts: ['repair_order_state'],
  data() {
    return {
      // 遮罩层
      loading: true,
      showDialog: false,
      countdown: 3, // 计时器初始值为3
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
      // repairOrder表格数据
      repairOrderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tId: null,
        taskId: sessionStorage.getItem("taskId"),
        userId: null,
        rState: null,
        rCreateTime: null
      },
      noStartState: 0,
      runningState: 0,
      endState: 0,
      faultType: 0,
      maintainType: 0,
      doubleType: 0,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
  },
  mounted(){
    // 在通过mounted调用即可
    const taskId = sessionStorage.getItem("taskId");
    if (!taskId) {
      // 不存在taskId，显示弹框
      this.showDialog = true; // 使用一个变量来控制弹框的显示与隐藏
      this.startCountdown(); // 开始计时器
      // window.addEventListener("beforeunload", (event) => this.handleBeforeUnload(event));
    }else{
      this.startTimer();
    }

  },
  methods: {
    startTimer(){
      this.timerId = setInterval(this.timerHandler, 10000);
    },
    stopTimer() {
      clearInterval(this.timerId);
      this.timerId = null;
    },
    timerHandler(){
      this.getList();
      this.getRepairOrderData();
    },
    startCountdown() {
      let timer = setInterval(() => {
        this.countdown--;
        if (this.countdown === 0) {
          clearInterval(timer);
          // 在这里执行路由跳转
          console.log("123123")
          // this.$router.push('/your-route');
          this.$router.push({ path: "/task" });

          // 关闭当前页面
          this.$nextTick(() => {
            this.$destroy();
          });
        }
      }, 1000); // 每秒减少1
    },
    /** 查询repairOrder列表 */
    getList() {
      this.loading = false;
      listRepairOrder(this.queryParams).then(response => {
        this.repairOrderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getRepairOrderData() {
      Promise.all([
      noStartStateList(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'noStartState',response.data)
      }),
      runningStateList(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'runningState',response.data)
      }),
      endStateList(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'endState',response.data)
      }),
      rFaultNum(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'faultType',response.data)
      }),
      rMaintainNum(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'maintainType',response.data)
      }),
      rFaultAndMaintainNum(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'doubleType',response.data)
      }),
      ]).then(response =>{
        this.rStateEchartsInit()
        this.rTypeEchartsInit()
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        rId: null,
        tId: null,
        taskId: null,
        userId: null,
        rState: null,
        rCreateTime: null
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
      this.ids = selection.map(item => item.rId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    //初始化echarts
    rStateEchartsInit() {
      //因为初始化echarts 的时候，需要指定的容器
      this.$echarts.init(document.getElementById("rState")).setOption({
        title: {
          text: "维修单状态数据分布",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              {
                value: this.noStartState,
                name: "未派单",
                itemStyle: { color: "" },
              },
              { value: this.runningState, name: " 派单中" },
              { value: this.endState, name: "结束" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
    },
    rTypeEchartsInit() {
      //因为初始化echarts 的时候，需要指定的容器
      this.$echarts.init(document.getElementById("rType")).setOption({
        title: {
          text: "维修单类型数据分布",
          left: "center",
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: [
              {
                value: this.faultType,
                name: "故障",
                itemStyle: { color: "" },
              },
              { value: this.maintainType, name: " 保养" },
              { value: this.doubleType, name: "故障和保养" },
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
    },
    beforeDestroy() {
      // 在组件销毁时销毁图表实例
      echarts.dispose(document.getElementById("operator"));
    },
  }
};
</script>
