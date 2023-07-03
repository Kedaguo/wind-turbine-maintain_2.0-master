<template>
  <div id="main">
     <!-- 弹框 -->
     <div class="modal-wrapper"  v-if="showDialog" >
      <div class="modal-mask"  @click.stop></div>
        <div class="modal-content">
          <el-dialog  v-if="showDialog" title="提示消息" :visible.sync="showDialog">
            <p class="alert-text" style="text-align: center;font-size: 20px;">任务尚未开始，请开始任务！</p>
            <p class="alert-timer">跳转倒计时：{{ countdown }}秒</p>
          </el-dialog>
        </div>
     </div>
    <div id="title" style="text-align: center;font-size: 24px;"><h1>展示未来24小时内气象信息变化</h1></div>
    <div id="title" style="text-align: center;font-size: 12px;color:brown;"><h2>当前时间：{{ currentTime }}</h2></div>
    <div style="width: auto; height: 400px" id="weather"></div>
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
import { weatherList } from "@/api/resource/weather";
//通过this.$echarts来使用
export default {
  name: "Temperature",
  data() {
    return {
      loading: false,
      showDialog: false,
      countdown: 3, // 计时器初始值为3
      queryParams: {
        taskId: sessionStorage.getItem("taskId"),
      },
      colors: ["#5470C6", "#91CC75", "#EE6666"],
      windSpeed: [],
      windTurbineSpeed: [],
      waveHeight: [],
      xAxisData: [],
      res: [],
      currentTime:"",
      timeId: null,
    };
  },
  created() {},
  mounted() {
    // 在通过mounted调用即可
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
    startCountdown() {
      let timer = setInterval(() => {
        this.countdown--;
        if (this.countdown === 0) {
          clearInterval(timer);
          // 在这里执行路由跳转
          this.$router.push({ path: "/task" });

          // 关闭当前页面
          this.$nextTick(() => {
            this.$destroy();
          });
        }
      }, 1000); // 每秒减少1
    },
    startTimer(){
      this.timerId = setInterval(this.timerHandler, 1000);
    },
    stopTimer() {
      clearInterval(this.timerId);
      this.timerId = null;
    },
    timerHandler(){
      this.echartsInit();
    },
    echartsInit() {
      weatherList(this.queryParams).then((response) => {
        this.currentTime = response.data[0].stTime;
        this.windSpeed = response.data.map((item) =>
          Math.round(item.stWindSpeed)
        );
        this.windTurbineSpeed = response.data.map(
          (item) => item.stWindSpeedTurbine
        );
        this.waveHeight = response.data.map((item) =>
          Math.round(item.stWaveHeight)
        );
        this.xAxisData = response.data.map((item) => item.stTime);
        console.log("res" + this.xAxisData);
        //因为初始化echarts 的时候，需要指定的容器 id='main'
        this.$echarts.init(document.getElementById("weather")).setOption({
          color: this.colors,
          tooltip: {
            trigger: "axis",
            axisPointer: {
              type: "cross",
            },
          },
          grid: {
            right: "20%",
          },
          toolbox: {
            feature: {
              dataView: { show: true, readOnly: false },
              restore: { show: true },
              saveAsImage: { show: true },
            },
          },
          legend: {
            data: ["Evaporation", "Precipitation", "Temperature"],
          },
          xAxis: [
            {
              type: "category",
              axisTick: {
                alignWithLabel: true,
              },
              // prettier-ignore
              data: this.xAxisData,
            },
          ],
          yAxis: [
            {
              type: "value",
              name: "windSpeed",
              position: "right",
              alignTicks: true,
              axisLine: {
                show: true,
                lineStyle: {
                  color: this.colors[0],
                },
              },
              axisLabel: {
                formatter: "{value} km/h",
              },
            },
            {
              type: "value",
              name: "waveHeight",
              position: "right",
              alignTicks: true,
              offset: 80,
              axisLine: {
                show: true,
                lineStyle: {
                  color: this.colors[1],
                },
              },
              axisLabel: {
                formatter: "{value} m",
              },
            },
            {
              type: "value",
              name: "windTurbineSpeed",
              position: "left",
              alignTicks: true,
              axisLine: {
                show: true,
                lineStyle: {
                  color: this.colors[2],
                },
              },
              axisLabel: {
                formatter: "{value} r/s",
              },
            },
          ],
          series: [
            {
              name: "windSpeed",
              type: "bar",
              yAxisIndex: 0, // 指定使用第一个Y轴
              data: this.windSpeed,
            },
            {
              name: "waveHeight",
              type: "bar",
              yAxisIndex: 1,
              data: this.waveHeight,
            },
            {
              name: "windTurbineSpeed",
              type: "line",
              yAxisIndex: 2,
              data: this.windTurbineSpeed,
            },
          ],
        });
      });
    },

    beforeDestroy() {
      // 在组件销毁时销毁图表实例
      echarts.dispose(document.getElementById("main"));
    },
  },
};
</script>
