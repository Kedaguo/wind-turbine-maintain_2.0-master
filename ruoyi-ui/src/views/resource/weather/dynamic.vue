<template>
    <div style="width: auto;height: 400px ;" id="dynamic"></div>
</template>

<script>
import { } from '@/api/resource/boat';
//通过this.$echarts来使用
  export default {
    name: "Temperature",
    data(){
      return{
        // loading:true,
        queryParams:{
          taskId:91
        },
        params:{
          windSpeed:1,
          windSpeedTurbine:2,
          waveHight:3

        },
        data:[],
        now : new Date(2007,1,1),
        oneDay : 24 * 3600 * 1000,
        value :  Math.random() * 1000

      }
    },
    created(){

    },
    mounted(){
    	// 在通过mounted调用即可
    this.echartsInitWaveHeight()
    this.timer = setInterval(()=>{
      for (var i = 0; i < 5; i++) {
        this.data.shift();
        this.data.push(this.randomData());
      }
      this.$echarts.init(document.getElementById('dynamic')).setOption({
        series: [
          {
            data: this.data
          }
        ]
      });
    },1000)
	  },
    methods: {
      randomData() {
        this.now = new Date(+this.now + this.oneDay);
        this.value = this.value + Math.random() * 21 - 10;
        console.log("111111111111111111");
        return {
          name: this.now.toString(),
          value: [
            [this.now.getFullYear(), this.now.getMonth() + 1, this.now.getDate()].join('/'),
            Math.round(this.value)
          ]
        };
      },
      echartsInitWaveHeight() {
      for (var i = 0; i < 1000; i++) {
        this.data.push(this.randomData());
      }
	    	//因为初始化echarts 的时候，需要指定的容器 id='main'
			this.$echarts.init(document.getElementById('dynamic')).setOption({
        title: {
            text: 'Dynamic Data & Time Axis'
        },
        tooltip: {
          trigger: 'axis',
          formatter: function (params) {
            params = params[0];
            var date = new Date(params.name);
            return (
              date.getDate() +
              '/' +
              (date.getMonth() + 1) +
              '/' +
              date.getFullYear() +
              ' : ' +
              params.value[1]
            );
          },
          axisPointer: {
            animation: true
          }
        },
        xAxis: {
          type: 'time',
          splitLine: {
            show: false
          }
        },
        yAxis:[ {
          type: 'value',
          name:'windSpeed',
          boundaryGap: [0, '100%'],
          splitLine: {
            show: false
          },
          axisLabel: {
          formatter: '{value} km/h'
         }
        },
        {
          type: 'value',
          name:'waveHeight',
          boundaryGap: [0, '100%'],
          splitLine: {
            show: false
          },
          axisLabel: {
          formatter: '{value} m'
         }
        }],
        series: [
          {
            name: 'windSpeed',
            type: 'line',
            yAxisIndex: 0, // 指定使用第一个Y轴
            showSymbol: false,
            data: this.data
          },
          {
            name: 'waveHeight',
            type: 'line',
            yAxisIndex: 1, // 指定使用第二个Y轴
            showSymbol: false,
            data: this.data
          }
        ]

      })},


      beforeDestroy() {
        // 在组件销毁时销毁图表实例
        echarts.dispose(document.getElementById('dynamic'));
        clearInterval(this.timer);
      }
}
  }


</script>
