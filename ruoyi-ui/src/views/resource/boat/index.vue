<template>
  <div id="main">
    <!-- 弹框 -->
    <div class="modal-wrapper"  v-if="showDialog">
      <div class="modal-mask"  @click.stop></div>
        <div class="modal-content">
          <el-dialog  v-if="showDialog" title="提示消息" :visible.sync="showDialog">
            <p class="alert-text" style="text-align: center;font-size: 25px;">任务尚未开始，请开始任务！</p>
            <p class="alert-timer">跳转倒计时：{{ countdown }}秒</p>
          </el-dialog>
        </div>
     </div>

    <div style="width: 650px; height: 400px; float: left" id="boat"></div>
    <div style="width: 650px; height: 400px; float: right" id="operator"></div>
    <div class="app-container">
      <el-table v-loading="loading" :data="boatList">
        <el-table-column label="序列" type="index" width="50"></el-table-column>
        <el-table-column label="船舶类型" align="center" prop="bt··ype">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.b_type" :value="scope.row.bType" />
          </template>
        </el-table-column>
        <el-table-column label="船舶速度" align="center" prop="bspeed" />
        <el-table-column label="船舶容量" align="center" prop="bcapacity" />
        <el-table-column label="风速" align="center" prop="bwindSpeed" />
        <el-table-column label="海浪" align="center" prop="bwaveHeight" />
        <el-table-column label="每年成本" align="center" prop="bannualCost" />
        <el-table-column label="每小时油耗" align="center" prop="bhourCost" />
        <el-table-column label="空闲" align="center" prop="bhourCost" />
        <el-table-column label="工作" align="center" prop="bhourCost" />
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
import {
  listBoat,
  getBoatNum,
  getWaitBoatNumber,
  getStandbyBoatNumber,
  getWorkBoatNumber,
} from "@/api/resource/boat";

export default {
  name: "Boat",
  dicts: ["b_type"],
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
      // boat表格数据
      boatList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: sessionStorage.getItem("taskId"),
        bModel: null,
        bSpeed: null,
        bCapacity: null,
        bType: null,
        bLongitude: null,
        bLatitude: null,
      },
      //表单参数
      form: {},
      //表单校验
      rules: {
        bType: [
          {
            required: true,
            message: "船舶类型不能为空",
            trigger: "change",
          },
        ],
      },
      boatNum: 0,
      standbyBoatNumber: 0,
      workBoatNumber: 0,
      waitBoatNumber: 0,
      queryEchartsParams: {
        taskId: sessionStorage.getItem("taskId"),
      },
    };
  },
  created() {

  },
  mounted() {
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
      this.timerId = setInterval(this.timerHandler, 1000);
    },
    stopTimer() {
      clearInterval(this.timerId);
      this.timerId = null;
    },
    timerHandler(){
      this.getBoatData();
      this.getList();
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
    /** 查询boat列表 */
    getList() {
      this.loading = false;
      listBoat(this.queryParams).then((response) => {
        this.boatList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getBoatData() {
      Promise.all([
        getBoatNum(this.queryEchartsParams).then((response) => {
          console.log("" + response);
          this.boatNum = response.data;
        }),
        getWaitBoatNumber(this.queryEchartsParams).then((response) => {
          console.log(response);
          this.waitBoatNumber = response.data;
        }),
        getStandbyBoatNumber(this.queryEchartsParams).then((response) => {
          console.log(response);
          this.standbyBoatNumber = response.data;
        }),
        getWorkBoatNumber(this.queryEchartsParams).then((response) => {
          console.log(response);
          this.workBoatNumber = response.data;
        }),
      ]).then((response) => {
        this.echartsInit1();
        this.echartsInit2();
      });
    },
    //初始化echarts
    echartsInit1() {
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("boat")).setOption({
        title: {
          text: "港口船舶状态",
          subtext: "Exhibit Data",
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
                value: this.standbyBoatNumber,
                name: "待命",
                itemStyle: { color: "" },
              },
              { value: this.waitBoatNumber, name: " 等待条件允许作业" },
              { value: this.workBoatNumber, name: "出海作业" },
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
    echartsInit2() {
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("operator")).setOption({
        title: {
          text: "船舶出海作业",
          subtext: "Exhibit Data",
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
              { value: 1048, name: "待命", itemStyle: { color: "" } },
              { value: 735, name: " 等待条件允许出海" },
              { value: 580, name: "风机之间穿梭，把工人接上" },
              { value: 480, name: "风机之间穿梭，把工人放下" },
              { value: 380, name: "等待工人施工完成" },
              { value: 280, name: "去风机/返回抛锚点" },
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
      echarts.dispose(document.getElementById("boat"));
      echarts.dispose(document.getElementById("operator"));
    },
  },
};
</script>
