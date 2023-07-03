<template>
  <div class="app-container home">
    <el-page-header @back="goBack" content="详情页面"> </el-page-header>
    <el-row>
      <el-col :span="15">
        <!-- <div><h3>当前时间：{{ time }}</h3></div> -->
        <div class="grid-content bg-purple-light">
          <baidu-map
            class="bm-view"
            ak="mkey"
            :map-options="mapOptions"
            :center="center"
            :zoom="zoom"
            :scroll-wheel-zoom="true"
            @ready="initializeMap"
          >
            <!-- 风机所在的范围 -->
            <bm-circle
              :center="circlePath.center"
              :radius="circlePath.radius"
              stroke-color="blue"
              :stroke-opacity="0.5"
              :stroke-weight="2"
            ></bm-circle>
            <!-- 码头点位 -->
            <bm-marker
              v-for="(point, index) in harbourList"
              :key="index"
              :position="{ lng: point.pLongitude, lat: point.pLatitude }"
              animation="BMAP_ANIMATION_BOUNCE"
            >
              <bm-label
                :content="point.pPortname"
                :labelStyle="{ color: 'red', fontSize: '24px' }"
                :offset="{ width: -35, height: 30 }"
              />
            </bm-marker>
            <!-- 正常的风机点位 -->
            <bm-marker
              v-for="point in onlineList"
              :key="point.id"
              :title="'风机编号：' + point.tId"
              :position="{ lng: point.tlongitude, lat: point.tlatitude }"
              :icon="onlineIcon"
              @click="clickHandler(point)"
            >
            </bm-marker>
            <!-- 故障的风机点位 -->
            <bm-marker
              v-for="point in offlineList"
              :key="point.id"
              :title="'风机编号：' + point.tId.toString()"
              :position="{ lng: point.tlongitude, lat: point.tlatitude }"
              :icon="offlineIcon"
              @click="clickHandler(point)"
            >
            </bm-marker>
            <!-- 保养风机的点位 -->
            <bm-marker
              v-for="point in maintainList"
              :key="point.id"
              :title="'风机编号：' + point.tId.toString()"
              :position="{ lng: point.tlongitude, lat: point.tlatitude }"
              :icon="maintainIcon"
              @click="clickHandler(point)"
            >
            </bm-marker>
            <!-- 保养停机的点位 -->
            <bm-marker
              v-for="point in maintain2List"
              :key="point.id"
              :title="'风机编号：' + point.tId.toString()"
              :position="{ lng: point.tlongitude, lat: point.tlatitude }"
              :icon="maintain2Icon"
              @click="clickHandler(point)"
            >
            </bm-marker>
            <!-- 叠加状态的点位 -->
            <bm-marker
              v-for="point in doubleStateList"
              :key="point.id"
              :title="'风机编号：' + point.tId.toString()"
              :position="{ lng: point.tlongitude, lat: point.tlatitude }"
              :icon="doubleStateIcon"
              @click="clickHandler(point)"
            >
            </bm-marker>
            <!-- 正在维修中的风机点位 -->
            <bm-marker
              v-for="point in onRepairList"
              :key="point.id"
              :title="'风机编号：' + point.tId.toString()"
              :position="{ lng: point.tlongitude, lat: point.tlatitude }"
              :icon="onRepairIcon"
              @click="clickHandler(point)"
            >
            </bm-marker>
            <!-- 维修的路线 -->
            <!-- <bml-curve-line :points="linePath"></bml-curve-line> -->
            <!-- 要实现的是多艘船同时维修的功能 -->
            <bml-lush
              ref="lushu"
              v-for="ship in shipList"
              :key="ship.id"
              @stop="reset"
              :path="ship.path"
              :icon="icon"
              :play="ship.play"
              :speed="500"
              :rotation="false"
            >
            </bml-lush>
          </baidu-map>
        </div>
      </el-col>
      <!-- <el-col :span="9">
                <div class="grid-content e-line">
                    <ve-line :data="chartData" :mark-line="markLine"></ve-line>
                </div>
            </el-col> -->
      <!-- <el-col :span="9">
        <el-form-item label="任务状态" prop="status">
          <el-select
            v-model="queryParams.status"
            placeholder="请选择任务状态"
            clearable
          >
            <el-option
              v-for="dict in dict.type.sys_job_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
      </el-col> -->
      <el-col :span="9">
        <el-form
          ref="elForm"
          :model="formData"
          :rules="rules"
          size="medium"
          label-width="100px"
        >
          <el-col :span="12">
            <el-form-item label="码头" prop="port">
              <el-select
                v-model="formData.port"
                placeholder="请选择出海码头"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in harbourList"
                  :key="index"
                  :label="item.pPortname"
                  :value="item.pId"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="海上平台" prop="motherShip" required>
              <el-switch v-model="formData.motherShip"></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="船只" prop="ship">
              <el-select
                v-model="formData.ship"
                placeholder="请选择出海船只"
                clearable
                :style="{ width: '100%' }"
              >
                <el-option
                  v-for="(item, index) in shipList"
                  :key="index"
                  :label="item.bmodel"
                  :value="item.bId"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="维修工人" prop="number">
              <el-input-number
                v-model="formData.number"
                placeholder="维修工人数量"
                controls-position="right"
              >
              </el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="维修顺序" prop="linePath">
              <el-tag
                v-for="point in formData.linePath"
                :key="point.tId"
                closable
                ref="tag"
                @close="handleClose(point)"
              >
                {{ point.tId }}
              </el-tag>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item size="large">
              <el-button type="primary" @click="submitForm">提交</el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-col>
        </el-form>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="15">
        <p>这里有一段其他的数字</p>
      </el-col>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
import BaiduMap from "vue-baidu-map/components/map/Map.vue";
import BmCircle from "vue-baidu-map/components/overlays/Circle.vue";
import BmMarker from "vue-baidu-map/components/overlays/Marker.vue";
import BmLabel from "vue-baidu-map/components/overlays/Label.vue";
import BmPointCollection from "vue-baidu-map/components/overlays/PointCollection.vue";
import BmPolyline from "vue-baidu-map/components/overlays/Polyline.vue";
import { BmlCurveLine } from "vue-baidu-map";
import { BmlLushu } from "vue-baidu-map";
import VeLine from "v-charts/lib/line.common";
import { time } from "console";
import {
  listOnlineTurbine,
  listOfflineTurbine,
  listMaintainTurbine,
  listPort,
  listAllTurbine,
  listShip,
} from "@/api/map/index";

export default {
  name: "TaskStart",
  components: {
    BaiduMap,
    BmCircle,
    BmMarker,
    BmLabel,
    VeLine,
    BmPointCollection,
    BmPolyline,
    BmlCurveLine,
    "bml-lush": BmlLushu,
  },
  dicts:['sys_job_status'],
  data() {
    this.markLine = {
      data: [{ name: "平均线", type: "average" }],
    };
    return {
      //当前正在执行的是那个任务
      taskId: null,
      //海上路书
      // play: true,
      // path: [
      //     // { tId: 5, lng: 119.9215439, lat: 25.72599323 },
      //     // { tId: 5, lng: 119.9207479, lat: 25.71059923 },
      //     // { tId: 5, lng: 119.9235689, lat: 25.70135523 },
      // ],
      icon: {
        url: require("../../assets/turbine/ship.png"),
        size: { width: 50, height: 50 },
        // opts: { anchor: { width: 27, height: 13 } }
      },
      //定时任务的id
      onlineIntervalId: null,
      offlineIntervalId: null,
      maintainIntervalId: null,
      timeIntervalId: null,
      //自定义的点
      onlineIcon: {
        url: require("../../assets/turbine/online.png"),
        imageSize: { width: 50, height: 50 },
        size: { width: 50, height: 50 },
      },
      //故障风机的图标
      offlineIcon: {
        url: require("../../assets/turbine/offline.png"),
        imageSize: { width: 50, height: 50 },
        size: { width: 50, height: 50 },
      },
      //保养风机的图标
      maintainIcon: {
        url: require("../../assets/turbine/maintain.png"),
        imageSize: { width: 50, height: 50 },
        size: { width: 50, height: 50 },
      },
      //保养风机的图标
      doubleStateIcon: {
        url: require("../../assets/turbine/doubleState.png"),
        imageSize: { width: 50, height: 50 },
        size: { width: 50, height: 50 },
      },
      //正在维修的风机图标
      onRepairIcon: {
        url: require("../../assets/turbine/onRepair.png"),
        imageSize: { width: 50, height: 50 },
        size: { width: 50, height: 50 },
      },
      //地图的设置项
      mapOptions: {
        enableMapClick: false, // 去除地图左下角的版权水印
      },
      //百度地图连接秘钥
      mkey: "fE3WYGSDWGkTXsjM0SAxisTysOr4ft5u",

      value: "",
      //时间
      time: "",
      // 地图中心点的位置
      center: {
        lng: 119.9215439,
        lat: 25.72599323,
      },
      // 地图的缩放级别
      zoom: 12,
      // 地图圆的覆盖物
      circlePath: {
        center: {
          lng: 119.9215439,
          lat: 25.72599323,
        },
        radius: 5000,
      },
      //所有的风机
      turbineList: [],
      //正常的风机列表
      onlineList: [],
      //故障风机
      offlineList: [],
      //保养风机
      maintainList: [],
      //保养停机
      maintain2List: [],
      //两种状态的叠加态
      doubleStateList: [],
      // 维修中
      onRepairList: [],
      //港口列表
      harbourList: [],
      //船只列表
      shipList: [],
      chartData: {
        columns: ["时间", "风速", "风机转速", "海浪高度"],
        rows: [
          { 时间: "1/1", 风速: 13, 风机转速: 13, 海浪高度: 32 },
          { 时间: "1/1", 风速: 13, 风机转速: 13, 海浪高度: 32 },
        ],
      },
      //提交的表单
      formData: {
        port: undefined,
        motherShip: false,
        ship: undefined,
        number: undefined,
        linePath: [],
      },
      // 检验表单是否合格
      rules: {
        port: [
          {
            required: true,
            message: "请选择出海码头",
            trigger: "change",
          },
        ],
        ship: [
          {
            required: true,
            message: "船只数量",
            trigger: "change",
          },
        ],
        number: [
          {
            required: true,
            message: "人员数量",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    // this.$refs.lushu.init()
  },
  created() {
    //初始化正在执行的任务编号
    this.taskId = this.$route.params && this.$route.params.taskId;
    this.timeIntervalId = setInterval(() => {
      this.time = Date().toLocaleString();
    }, 1000);
    this.turbineIntervalId = setInterval(() => {
      this.getTurbine();
    }, 2000);
    this.getHarbour();
    this.getShipList();
  },
  beforeDestroy() {
    clearInterval(this.timeIntervalId);
    clearInterval(this.turbineIntervalId);
  },
  destroyed() {
    //this.$refs.lushu.destroy()
  },

  methods: {
    handleRouteLoaded(route) {
      console.log("Route loaded:", route);
      // 在路线加载完成后的回调函数中，可以对路线进行操作
      // 例如获取路线长度、耗时等信息，或者在地图上绘制路线
    },
    // 控制路书暂停
    reset() {
      this.play = false;
    },
    //查询船只
    getShipList() {
      listShip(this.taskId).then((res) => {
        this.shipList = res.data;
        console.log(JSON.stringify(res.data));
      });
    },
    //查询可用出海港口
    getHarbour() {
      // let taskId = this.$route.params && this.$route.params.taskId;
      listPort(this.taskId).then((res) => {
        this.harbourList = res.data;
      });
    },
    //查询所有风机根据风机状态添加到不同的风机列表中
    getTurbine() {
      // let taskId = this.$route.params && this.$route.params.taskId;
      listAllTurbine(this.taskId).then((response) => {
        this.turbineList = response.data;
        console.log(response.data);
        this.turbineList.forEach((item) => {
          if (item.fState === 4 || item.mState === 4) {
            this.onRepairList.push(item);
          } else if (item.fState === 1 && item.mState === 2) {
            this.doubleStateList.push(item);
          } else if (item.fState === 2 && item.mState === 3) {
            this.onlineList.push(item);
          } else if (item.fState === 1) {
            this.offlineList.push(item);
          } else if (item.mState === 2) {
            this.maintainList.push(item);
          }
        });
        // console.log("所有的风机" + this.turbineList)
      });
    },
    //返回主页面
    goBack() {
      this.$router.push("/task");
    },
    //进行地图初始化
    initializeMap() {
      this.center.lng = 119.9215439;
      this.center.lat = 25.72599323;
      this.zoom = 12;
    },
    //获取当前时间
    getTime() {},
    //关闭风机标签
    handleClose(tag) {
      this.formData.linePath.splice(this.formData.linePath.indexOf(tag), 1);
    },
    goTarget(href) {
      window.open(href, "_blank");
    },
    //将选择的风机添加到路书中
    setPath() {
      this.path = [];
      const portObj = this.harbourList.find(
        (harbour) => harbour.pId === this.formData.port
      );
      //检验合格
      this.path.push({ lng: portObj.pLongitude, lat: portObj.pLatitude }); // 将portObj添加到path列表中
      this.formData.linePath.forEach((item) => {
        this.path.push({ lng: item.lng, lat: item.lat }); // 将linePath中的元素逐个添加到path列表中
      });
      this.path.push({ lng: portObj.pLongitude, lat: portObj.pLatitude }); // 将portObj添加到path列表中
      this.play = true;
      // console.log(this.path);
    },
    submitForm() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        else {
          // 将表单提交给后端
          this.setPath(this.formData.ship);
        }
      });
    },
    resetForm() {
      this.$refs["elForm"].resetFields();
    },
    // 只能维修处于保养或者故障状态的风机
    clickHandler(e) {
      //  console.log(e)
      if (e.fState === 2 && e.mState === 3) {
        this.$message({
          message: "风机正常工作",
          type: "warning",
        });
      } else if (e.fState === 4 && e.mState === 4) {
        this.$message({
          message: "正在维修中",
          type: "warning",
        });
      } else {
        let mark = 0;
        this.formData.linePath.forEach((item) => {
          if (item.tId === e.tId) {
            mark = 1;
          }
        });
        if (mark === 0) {
          this.formData.linePath.push({
            tId: e.tId,
            lng: e.tlongitude,
            lat: e.tlatitude,
          });
        } else if (mark === 1) {
          this.$message({
            message: "已经添加过了",
            type: "warning",
          });
        }
      }
    },

    getChart() {
      chart().then((response) => {});
    },
  },
};
</script>

<style lang="scss">
.e-line {
  width: 100%;
  height: 150px;
}

.bm-view {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 500px;
}

.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }

  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }

  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>
