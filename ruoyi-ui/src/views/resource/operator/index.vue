<template>
  <div id="main">
    <!-- 弹框 -->
    <div class="modal-wrapper"  v-if="showDialog">
      <div class="modal-mask"  @click.stop></div>
        <div class="modal-content">
          <el-dialog  v-if="showDialog" title="提示消息" :visible.sync="showDialog">
            <p class="alert-text" style="text-align: center;color:#ed0505;font-size: 25px;">任务尚未开始，请开始任务！</p>
            <p class="alert-timer">跳转倒计时：{{ countdown }}秒</p>
          </el-dialog>
        </div>
     </div>
    <div style="width: auto; height: 400px" id="operator"></div>
    <div class="app-container">
      <el-table v-loading="loading" :data="operatorList">
        <el-table-column label="员工序列" type="index" width="50"/>
        <!-- <el-table-column label="员工编号" align="center" prop="oId" /> -->
        <el-table-column label="年薪" align="center" prop="oannualsalar" />
        <el-table-column label="每小时工资" align="center" prop="ocommonhourlywage" />
        <el-table-column label="加班费" align="center" prop="oovertimehourlywage" />
        <el-table-column label="工作时长" align="center" prop="oWorkTime" />
        <el-table-column label="维修状态" align="center" prop="oState" />
      </el-table>
      <pagination v-show="total > 0" :total="total" :page.sync="query.pageNum" :limit.sync="query.pageSize"
            @pagination="getList" />
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
      operatorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      standbytONumber: 0,
      workONumber: 0,
      waitONumber: 0,
      queryParams: {
        taskId: sessionStorage.getItem("taskId"),
      },
      query: {
        pageNum: 1,
        pageSize: 10,
        taskId: sessionStorage.getItem("taskId"),
        oId: null,
        oState: null,
        oWorkTime: null,
        oannualsalar: null,
        ocommonhourlywage: null,
        oovertimehourlywage: null,
        otype: null,
        userId: null


      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
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
      this.getList();
      this.getOperatorData();
    }
  },
  methods: {
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
    getList() {
      this.loading = true;
      listOperator(this.query).then(response => {
        this.operatorList = response.rows;
        console.log("!!!!"+this.operatorList)
        this.total = response.total;
        this.loading = false;
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
