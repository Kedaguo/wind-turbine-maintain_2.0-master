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
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户编号" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务编号" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规划名称" prop="aName">
        <el-input
          v-model="queryParams.aName"
          placeholder="请输入规划名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="arrangementList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="规划序列" type="index" width="50"/>
      <!-- <el-table-column label="规划编号" align="center" prop="aId" /> -->
      <el-table-column label="任务编号" align="center" prop="taskId" />
      <el-table-column label="港口" align="center" prop="pportName" />
      <el-table-column label="维修人员数量" align="center" prop="oNum" />
      <el-table-column label="船舶编号" align="center" prop="bId" />
      <el-table-column label="船舶类型" align="center" prop="bType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_type" :value="scope.row.bType"/>
        </template>
      </el-table-column>
      <el-table-column label="用户学号" align="center" prop="userName" />
      <el-table-column label="规划名称" align="center" prop="aName" />
      <el-table-column label="始点" align="center" prop="startLocation" />
      <el-table-column label="终点" align="center" prop="endLocation" />
      <el-table-column label="开始仿真模拟时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束仿真模拟时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
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
import { listArrangement, getArrangement, delArrangement, addArrangement, updateArrangement } from "@/api/resource/arrangement";

export default {
  name: "Arrangement",
  dicts: ['b_type'],
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
      // arrangement表格数据
      arrangementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pId: null,
        oNum: null,
        bNum: null,
        bType: null,
        userId: null,
        taskId: null,
        aName: null,
        startLocation: null,
        endLocation: null,
        startTime: null,
        endTime: null,
        aCreateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bType: [
          { required: true, message: "船舶类型不能为空", trigger: "change" }
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  mounted(){
     // 在通过mounted调用即可
     const taskId = sessionStorage.getItem("taskId");
    if (!taskId) {
      // 不存在taskId，显示弹框
      this.showDialog = true; // 使用一个变量来控制弹框的显示与隐藏
      this.startCountdown(); // 开始计时器
      // window.addEventListener("beforeunload", (event) => this.handleBeforeUnload(event));
    }else{
      this.getList();
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
    /** 查询arrangement列表 */
    getList() {
      this.loading = true;
      listArrangement(this.queryParams).then(response => {
        this.arrangementList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        aId: null,
        pId: null,
        oNum: null,
        bId: null,
        bType: null,
        userId: null,
        taskId: null,
        aName: null,
        startLocation: null,
        endLocation: null,
        startTime: null,
        endTime: null,
        aCreateTime: null,
        pPortName:null,
        userName:null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
  }
};
</script>
