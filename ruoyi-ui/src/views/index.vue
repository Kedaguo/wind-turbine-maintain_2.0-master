<template>
  <div class="app-container home">
    <el-row>
      <el-col :span="15">
        <!-- <div><h3>当前时间：{{ time }}</h3></div> -->
        <div class="grid-content bg-purple-light">
          <baidu-map class="bm-view" ak="fE3WYGSDWGkTXsjM0SAxisTysOr4ft5u" :center="center" :zoom="zoom"
            scroll-wheel-zoom="true">
            <!-- 风机所在的范围 -->
            <bm-circle :center="circlePath.center" :radius="circlePath.radius" stroke-color="blue" :stroke-opacity="0.5"
              :stroke-weight="2"></bm-circle>
            <!-- 码头点位 -->
            <bm-marker :position="{ lng: 119.6215439, lat: 25.72599323 }" animation="BMAP_ANIMATION_BOUNCE">
              <bm-label content="福州松下码头" :labelStyle="{ color: 'red', fontSize: '24px' }"
                :offset="{ width: -35, height: 30 }" />
            </bm-marker>
            <bm-marker :position="{ lng: 119.703598, lat: 25.61707242 }">
              <bm-label content="大练岛陆岛码头" :labelStyle="{ color: 'red', fontSize: '24px' }"
                :offset="{ width: -35, height: 30 }" />
            </bm-marker>
            <!-- 风机点位 -->
            <!-- <bm-point-collection :points="points" shape="BMAP_POINT_SHAPE_STAR" color="red" size="BMAP_POINT_SIZE_NORMAL"
              @click="clickHandler"></bm-point-collection> -->
            <bm-marker v-for="point in points" :key="point.id" :title="point.id"
              :position="{ lng: point.lng, lat: point.lat }" @click="clickHandler(point)">
            </bm-marker>
          </baidu-map>
        </div>
      </el-col>
      <el-col :span="7">
        <div class="grid-content e-form">
          <!-- <el-row :gutter="6"><ve-line :data="chartData" :mark-line="markLine"></ve-line></el-row> -->
          <el-row :gutter="6">
            <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
              <el-col :span="12">
                <el-form-item label="码头" prop="wharf">
                  <el-select v-model="formData.wharf" placeholder="请选择出海码头" clearable :style="{ width: '100%' }">
                    <el-option v-for="(item, index) in field102Options" :key="index" :label="item.label"
                      :value="item.value" :disabled="item.disabled"></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="海上平台" prop="ship" required>
                  <el-switch v-model="formData.ship"></el-switch>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="船只" prop="shipNum">
                  <el-input-number v-model="formData.shipNum" placeholder="船只数量"></el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="人员" prop="number">
                  <el-input-number v-model="formData.number" placeholder="人员数量" controls-position=right>
                  </el-input-number>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-form-item label="维修顺序" prop="linePath">
                  <el-tag v-for="point in formData.linePath" :key="point.id" closable @close="handleClose(tag)">
                    {{ point.id }}
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
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row>
    </el-row>
    <el-divider />
  </div>
</template>

<script>
import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import BmCircle from 'vue-baidu-map/components/overlays/Circle.vue'
import BmMarker from 'vue-baidu-map/components/overlays/Marker.vue'
import BmLabel from 'vue-baidu-map/components/overlays/Label.vue'
import BmPointCollection from 'vue-baidu-map/components/overlays/PointCollection.vue'
import BmPolyline from 'vue-baidu-map/components/overlays/Polyline.vue'
import VeLine from 'v-charts/lib/line.common'
import { list } from "@/api/index";
import { time } from 'console'


export default {
  name: "Index",
  components: {
    BaiduMap,
    BmCircle,
    BmMarker,
    BmLabel,
    VeLine,
    BmPointCollection,
    BmPolyline,
  },
  data() {
    this.markLine = {
      data: [{ name: "平均线", type: "average" }]
    };
    return {
      time: '',
      center: {
        lng: 119.9215439, lat: 25.72599323
      },
      zoom: 12,
      circlePath: {
        center: {
          lng: 119.9215439, lat: 25.72599323
        },
        radius: 5000
      },
      points: [{ id: 1, lng: 119.9215439, lat: 25.72599323 }, { id: 2, lng: 119.9215439, lat: 25.92599323 }],
      version: "1.0.0",
      chartData: {
        columns: ["时间", "风速", "风机转速", "海浪高度"],
        rows: [
          { 时间: "1/1", 风速: 13, 风机转速: 13, 海浪高度: 32 },
          { 时间: "1/1", 风速: 13, 风机转速: 13, 海浪高度: 32 },
        ]
      },
      formData: {
        wharf: undefined,
        ship: false,
        shipNum: 0,
        number: undefined,
        linePath: [],
      },
      rules: {
        wharf: [{
          required: true,
          message: '请选择出海码头',
          trigger: 'change'
        }],
        shipNum: [{
          required: true,
          message: '船只数量',
          trigger: 'blur'
        }],
        number: [{
          required: true,
          message: '人员数量',
          trigger: 'blur'
        }],
      },
      field102Options: [{
        "label": "松下码头",
        "value": 1
      }, {
        "label": "大练岛陆岛码头",
        "value": 2
      }],
    };
  },
  mounted() {
    setInterval(() => {
      this.time = Date().toLocaleString();
    }, 1000)
  },
  methods: {
    //获取当前时间
    getTime() {

    },
    handleClose(tag) {
      this.formData.linePath.splice(this.formData.linePath.indexOf(tag), 1);
    },
    goTarget(href) {
      window.open(href, "_blank");
    },
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
      }).then(() => {
        launchOut(this.formData)
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },

    clickHandler(e) {
      this.formData.linePath.push({ id: e.id, lng: e.lng, lat: e.lat })
      //alert(`单击点的坐标为：${e.lng}, ${e.lat}`);
    },

    getList() {
      list().then(response => {
        //获取到所有添加到出海任务中的风机的经纬度坐标，
      });
    },
    getChart() {
      chart().then(response => {

      })
    }
  }
};
</script>

<style lang="scss">
.e-form {
  height: 800px;
}

.e-line {
  width: 100%;
  height: 150px;
}

.bm-view {
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 400px;
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

  font-family: "open sans",
  "Helvetica Neue",
  Helvetica,
  Arial,
  sans-serif;
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

