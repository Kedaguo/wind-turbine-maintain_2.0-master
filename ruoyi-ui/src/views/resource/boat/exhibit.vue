<template>
  <div id="main">
    <div  style="width: 500px;height: 400px ; float: left; " id="boat">
    </div>
    <div  style="width: 500px;height: 400px ; float: left;" id="operator">
    </div>
  </div>

</template>

<script>
import { getBoatNum , getWaitBoatNumber , getStandbyBoatNumber , getWorkBoatNumber} from '@/api/resource/boat';
//通过this.$echarts来使用
  export default {
    name: "boat",
    data(){
      return{
        loading:true,
        boatNum:0,
        standbyBoatNumber:0,
        workBoatNumber:0,
        waitBoatNumber:0,
        queryParams:{
          taskId:91
        }
      }
    },
    created(){
      this.getBoatData()
    },
    mounted(){
    	// 在通过mounted调用即可
		this.echartsInit1()
    this.echartsInit2()
	},
    methods: {

      getBoatData(){
        getBoatNum(this.queryParams).then(response =>{
          console.log(response)
          this.boatNum = response.data
        });
        getWaitBoatNumber(this.queryParams).then(response =>{
          console.log(response)
          this.waitBoatNumber = response.data
        });
        getStandbyBoatNumber(this.queryParams).then(response =>{
          console.log(response)
          this.standbyBoatNumber = response.data
        });
        getWorkBoatNumber(this.queryParams).then(response =>{
          console.log(response)
          this.workBoatNumber = response.data
        })


       },
	    //初始化echarts
	    echartsInit1() {
	    	//因为初始化echarts 的时候，需要指定的容器 id='main'
			this.$echarts.init(document.getElementById('boat')).setOption({
			    title:{
            text: '港口船舶状态',
            subtext: 'Exhibit Data',
            left: 'center'
          },
          tooltip:{
            trigger:'item'
          },
          // legend: {
          //   orient: 'vertical',
          //   left: 'left'
          // },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: '50%',
              data: [
                { value: this.standbyBoatNumber, name: '待命',itemStyle:{color:""}},
                { value: this.waitBoatNumber, name: ' 等待条件允许作业' },
                { value: this.workBoatNumber, name: '出海作业' },
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
			})

		},
       echartsInit2() {
	    	//因为初始化echarts 的时候，需要指定的容器 id='main'
			this.$echarts.init(document.getElementById('operator')).setOption({
			    title:{
            text: '船舶出海作业',
            subtext: 'Exhibit Data',
            left: 'center'
          },
          tooltip:{
            trigger:'item'
          },
          // legend: {
          //   orient: 'vertical',
          //   left: 'left'
          // },
          series: [
            {
              name: 'Access From',
              type: 'pie',
              radius: '50%',
              data: [
                { value: 1048, name: '待命',itemStyle:{color:""}},
                { value: 735, name: ' 等待条件允许出海' },
                { value: 580, name: '风机之间穿梭，把工人接上' },
                { value: 480, name: '风机之间穿梭，把工人放下' },
                { value: 380, name: '等待工人施工完成' },
                { value: 280, name: '去风机/返回抛锚点' },
              ],
              emphasis: {
                itemStyle: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
			})

		},
    beforeDestroy() {
    // 在组件销毁时销毁图表实例
    echarts.dispose(document.getElementById('boat'));
    echarts.dispose(document.getElementById('operator'));
  }
    }
  }
</script>
