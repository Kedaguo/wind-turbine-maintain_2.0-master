<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="故障类型" prop="fFaultType">
        <el-select v-model="queryParams.fFaultType" placeholder="请选择故障类型" clearable>
          <el-option
            v-for="dict in dict.type.fault_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="故障设备" prop="fFaultDevice">
        <el-input
          v-model="queryParams.fFaultDevice"
          placeholder="请输入故障设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="faultList" @selection-change="handleSelectionChange">
      <el-table-column label="故障序列" type="index" width="50"/>
      <!-- <el-table-column label="故障编号" align="center" prop="fId" />8 -->
      <el-table-column label="故障频率" align="center" prop="fFrequencyPerYear" />
      <el-table-column label="维修成本" align="center" prop="fConsumableCost" />
      <el-table-column label="准备时间/天" align="center" prop="fPrepareDuration" />
      <el-table-column label="维修时间" align="center" prop="fMaintainDuration" />
      <el-table-column label="维修人员数量" align="center" prop="fPeopleNumber" />
      <el-table-column label="故障类型" align="center" prop="fFaultType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.fault_type" :value="scope.row.fFaultType"/>
        </template>
      </el-table-column>
      <el-table-column label="故障设备" align="center" prop="fFaultDevice" />
      <el-table-column label="故障" align="center" prop="fFaultfre" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listFault, getFault } from "@/api/resource/fault";

export default {
  name: "Fault",
  dicts: ['fault_type'],
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
      // 故障信息表格数据
      faultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fFrequencyPerYear: null,
        fConsumableCost: null,
        fBoatModel: null,
        fPrepareDuration: null,
        fMaintainDuration: null,
        fPeopleNumber: null,
        fFaultType: null,
        fFaultDevice: null,
        fFaultfre: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询故障信息列表 */
    getList() {
      this.loading = true;
      listFault(this.queryParams).then(response => {
        this.faultList = response.rows;
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
        fId: null,
        fFrequencyPerYear: null,
        fConsumableCost: null,
        fBoatModel: null,
        fPrepareDuration: null,
        fMaintainDuration: null,
        fPeopleNumber: null,
        fFaultType: null,
        fFaultDevice: null,
        fFaultfre: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.fId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
  }
};
</script>
