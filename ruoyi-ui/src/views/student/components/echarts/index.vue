<template>
    <section class="mainbox">
        <div class="column">
            <div class="panel bar" id="main">
                <div class="chart"></div>
                <div class="panel-footer"></div>
            </div>
            <div class="panel line">
                <h2>折线图</h2>
                <div class="chart"></div>
                <div class="panel-footer"></div>
            </div>
            <div class="panel pie">
                <h2>饼形图</h2>
                <div class="chart"></div>
                <div class="panel-footer"></div>
            </div>
        </div>
        <div class="column">
            <div class="no">
                <div class="no-hd">
                    <ul>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
                <div class="no-bd">
                    <ul>
                        <li></li>
                        <li></li>
                    </ul>
                </div>
            </div>
            <div class="map">
                <div class="chart" id="container"></div>
            </div>
        </div>
        <div class="column">
            <div class="panel bar1">
                <h2>柱状图</h2>
                <div class="chart"></div>
                <div class="panel-footer"></div>
            </div>
            <div class="panel line1">
                <h2>折线图</h2>
                <div class="chart"></div>
                <div class="panel-footer"></div>
            </div>
            <div class="panel pie1">
                <h2>饼形图-地区分布</h2>
                <div class="chart"></div>
                <div class="panel-footer"></div>
            </div>
        </div>
    </section>
</template>

<script>
import * as echarts from 'echarts';

export default {
    name: "Index",
    data() {
        const generateData = _ => {
            const data = [];
            for (let i = 1; i <= 15; i++) {
                data.push({
                    key: i,
                    label: `备选项 ${i}`,
                    disabled: i % 4 === 0
                });
            }
            return data;
        };
        return {
            data: generateData(),
            value: [1, 4],
            activeIndex: '1',
            activeIndex2: '1'
        };
    },
    mounted() {
        //DOM初始化完成进行地图初始化
        this.initMap();
        this.myEcharts();
    },
    methods: {
        // 顶部导航栏选择下拉菜单
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        //初始化地图
        initMap() {
            AMapLoader.load({
                key: "4bf258de1b52f7cef26738f9d2323523",             // 申请好的Web端开发者Key，首次调用 load 时必填
                version: "2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
                plugins: [''],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
            }).then((AMap) => {
                this.map = new AMap.Map("container", {  //设置地图容器id
                    viewMode: "3D",    //是否为3D地图模式
                    zoom: 5,           //初始化地图级别
                    center: [119.6215439, 25.72599323], //初始化地图中心点位置
                });
            }).catch(e => {
                console.log(e);
            })
        },
        //初始化图表
        myEcharts() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                legend: {
                    data: ['销量']
                },
                xAxis: {
                    data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    }
};
</script>

<style>
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}


li {
    list-style: none;
}

.mainbox {
    font-family: Arial, Helvetica, sans-serif;
    margin: 0;
    padding: 0;
    /*  背景图定位 / 背景图尺寸  cover 完全铺满容器  contain 完整显示在容器内 */
    background: url(../../assets/bg.jpg) no-repeat;
    background-size: cover;
    /* 行高是字体1.15倍 */
    line-height: 1.15;
    min-width: 1024px;
    max-width: 100%;
    padding: 10px 10px 0;
    display: flex;
    height: 100%;
}

.mainbox .column {
    flex: 3;
}

.mainbox .column:nth-child(2) {
    flex: 5;
    margin: 0 10px 15px;
    overflow: hidden;
}

.panel {
    position: relative;
    height: 310px;
    border: 1px solid rgba(25, 186, 139, 0.17);
    /* 背景图片无法找到 */
    background: rgba(255, 255, 255, 0.04) url(../../../../assets/images/line.png);
    padding: 15px 40px;
    margin-bottom: 15px;
}

.panel::before {
    position: absolute;
    top: 0;
    left: 0;
    content: "";
    width: 10px;
    height: 10px;
    border-top: 2px solid #02a6b5;
    border-left: 2px solid #02a6b5;
}

.panel::after {
    position: absolute;
    top: 0;
    right: 0;
    content: "";
    width: 10px;
    height: 10px;
    border-top: 2px solid #02a6b5;
    border-right: 2px solid #02a6b5;
}

.panel .panel-footer {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
}

.panel .panel-footer::before {
    position: absolute;
    bottom: 0;
    left: 0;
    content: "";
    width: 10px;
    height: 10px;
    border-bottom: 2px solid #02a6b5;
    border-left: 2px solid #02a6b5;
}

.panel .panel-footer::after {
    position: absolute;
    bottom: 0;
    right: 0;
    content: "";
    width: 10px;
    height: 10px;
    border-bottom: 2px solid #02a6b5;
    border-right: 2px solid #02a6b5;
}

.panel h2 {
    height: 48px;
    line-height: 48px;
    text-align: center;
    color: #fff;
    font-size: 15px;
    font-weight: 400;
}

.panel h2 a {
    margin: 0 15px;
    color: #fff;
    text-decoration: underline;
}

.panel .chart {
    height: 3rem;
}

.no {
    background: rgba(101, 132, 226, 0.1);
    padding: 15px;
}

.no .no-hd {
    position: relative;
    border: 1px solid rgba(25, 186, 139, 0.17);
}

.no .no-hd::before {
    content: "";
    position: absolute;
    width: 30px;
    height: 10px;
    border-top: 2px solid #02a6b5;
    border-left: 2px solid #02a6b5;
    top: 0;
    left: 0;
}

.no .no-hd::after {
    content: "";
    position: absolute;
    width: 30px;
    height: 10px;
    border-bottom: 2px solid #02a6b5;
    border-right: 2px solid #02a6b5;
    right: 0;
    bottom: 0;
}

.no .no-hd ul {
    display: flex;
}

.no .no-hd ul li {
    position: relative;
    flex: 1;
    text-align: center;
    height: 80px;
    line-height: 80px;
    font-size: 70px;
    color: #ffeb7b;
    padding: 4px 0;
    font-family: electronicFont;
    font-weight: bold;
}

.no .no-hd ul li:first-child::after {
    content: "";
    position: absolute;
    height: 50%;
    width: 1px;
    background: rgba(255, 255, 255, 0.2);
    right: 0;
    top: 25%;
}

.no .no-bd ul {
    display: flex;
}

.no .no-bd ul li {
    flex: 1;
    height: 40px;
    line-height: 40px;
    text-align: center;
    font-size: 18px;
    color: rgba(255, 255, 255, 0.7);
    padding-top: 10px;
}

.map {
    position: relative;
    height: 810px;
}

.map .chart {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 5;
    height: 810px;
    width: 100%;
}

@keyframes rotate {
    from {
        transform: translate(-50%, -50%) rotate(0deg);
    }

    to {
        transform: translate(-50%, -50%) rotate(360deg);
    }
}

@keyframes rotate1 {
    from {
        transform: translate(-50%, -50%) rotate(0deg);
    }

    to {
        transform: translate(-50%, -50%) rotate(-360deg);
    }
}

@media screen and (max-width: 1024px) {
    html {
        font-size: 42px !important;
    }
}

@media screen and (min-width: 1920) {
    html {
        font-size: 80px !important;
    }
}
</style>  