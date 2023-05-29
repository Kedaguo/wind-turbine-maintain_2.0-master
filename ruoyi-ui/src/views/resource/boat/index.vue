<template>
  <div id="main">
    <div style="width: 650px; height: 400px; float: left" id="boat"></div>
    <div style="width: 650px; height: 400px; float: right" id="operator"></div>
    <div class="app-container">
      <el-table v-loading="loading" :data="boatList">
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
    </div>
  </div>
</template>

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
        taskId: 91,
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
    this.getBoatData();
    this.getList();
  },
  mounted() {
    // 在通过mounted调用即可
  },
  methods: {
    /** 查询boat列表 */
    getList() {
      this.loading = true;
      listBoat(this.queryParams).then((response) => {
        this.boatList = response.rows;
        console.log("1111" + this.boatList);
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
