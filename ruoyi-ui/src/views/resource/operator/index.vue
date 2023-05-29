<template>
  <div id="main">
    <div style="width: auto; height: 400px" id="operator"></div>
    <div class="app-container">
      <el-table v-loading="loading" :data="operatorList">
        <el-table-column label="年薪" align="center" prop="bSpeed" />
        <el-table-column label="每小时工资" align="center" prop="bCapacity" />
        <el-table-column label="加班费" align="center" prop="bWindSpeed" />
        <el-table-column label="空闲" align="center" prop="bHourCost" />
        <el-table-column label="工作" align="center" prop="bHourCost" />
      </el-table>
    </div>
  </div>
</template>

<script>
import {
  listOperator,
  getWorkONumber,
  getWaitONumber,
  getStandByONumber,
} from "@/api/resource/operator";
export default {
  name: "Operator",
  data() {
    return {
      // 遮罩层
      // loading: true,
      boatList: [],
      // boatNum: 0,
      standbytONumber: 0,
      workONumber: 0,
      waitONumber: 0,
      queryParams: {
        taskId: 91,
      },
    };
  },
  created() {
    this.getList();
    // this.getOperatorData();

s  },
  mounted() {
    this.getOperatorData()
    // this.echartsInit();
  },
  methods: {
    getList() {
      // this.loading = true;
      listOperator(this.queryParams).then(response => {
        this.OperatorList = response.rows;
        // this.loading = false;
      });
    },
    getOperatorData() {
      Promise.all([
      getWaitONumber(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'waitONumber',response.data)
      }),
      getStandByONumber(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'standbytONumber',response.data)
      }),
      getWorkONumber(this.queryParams).then(response => {
        console.log(response);
        this.$set(this,'workONumber',response.data)
      })
      ]).then(response =>{
        this.echartsInit()
      })
    },
    //初始化echarts
    echartsInit() {
      console.log("%%%%"+this.workONumber)
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("operator")).setOption({
        title: {
          text: "维修员工状态",
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
                value: this.standbytONumber,
                name: "待命",
                itemStyle: { color: "" },
              },
              { value: this.waitONumber, name: " 等待条件出海作业作业" },
              { value: this.workONumber, name: "出海作业" },
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
  },
};
</script>
