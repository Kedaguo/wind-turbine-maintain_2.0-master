<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="故障频率" prop="fFrequencyPerYear">-->
<!--        <el-input-->
<!--          v-model="queryParams.fFrequencyPerYear"-->
<!--          placeholder="请输入故障频率"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="维修成本" prop="fConsumableCost">-->
<!--        <el-input-->
<!--          v-model="queryParams.fConsumableCost"-->
<!--          placeholder="请输入维修成本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="船只类型" prop="fBoatModel">
        <el-input
          v-model="queryParams.fBoatModel"
          placeholder="请输入船只类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="准备时间/天" prop="fPrepareDuration">-->
<!--        <el-input-->
<!--          v-model="queryParams.fPrepareDuration"-->
<!--          placeholder="请输入准备时间/天"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="维修时间" prop="fMaintainDuration">-->
<!--        <el-input-->
<!--          v-model="queryParams.fMaintainDuration"-->
<!--          placeholder="请输入维修时间"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="维修人员数量" prop="fPeopleNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.fPeopleNumber"-->
<!--          placeholder="请输入维修人员数量"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
<!--      <el-form-item label="故障" prop="fFaultfre">-->
<!--        <el-input-->
<!--          v-model="queryParams.fFaultfre"-->
<!--          placeholder="请输入故障"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:fault:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:fault:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:fault:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:fault:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="faultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="故障编号" align="center" prop="fId" />
      <el-table-column label="故障频率" align="center" prop="fFrequencyPerYear" />
      <el-table-column label="维修成本" align="center" prop="fConsumableCost" />
      <el-table-column label="船只类型" align="center" prop="fBoatModel" />
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
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:fault:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:fault:remove']"
          >删除</el-button>
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

    <!-- 添加或修改故障信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="故障频率" prop="fFrequencyPerYear">
          <el-input v-model="form.fFrequencyPerYear" placeholder="请输入故障频率" />
        </el-form-item>
        <el-form-item label="维修成本" prop="fConsumableCost">
          <el-input v-model="form.fConsumableCost" placeholder="请输入维修成本" />
        </el-form-item>
        <el-form-item label="船只类型" prop="fBoatModel">
          <el-input v-model="form.fBoatModel" placeholder="请输入船只类型" />
        </el-form-item>
        <el-form-item label="准备时间/天" prop="fPrepareDuration">
          <el-input v-model="form.fPrepareDuration" placeholder="请输入准备时间/天" />
        </el-form-item>
        <el-form-item label="维修时间" prop="fMaintainDuration">
          <el-input v-model="form.fMaintainDuration" placeholder="请输入维修时间" />
        </el-form-item>
        <el-form-item label="维修人员数量" prop="fPeopleNumber">
          <el-input v-model="form.fPeopleNumber" placeholder="请输入维修人员数量" />
        </el-form-item>
        <el-form-item label="故障类型" prop="fFaultType">
          <el-select v-model="form.fFaultType" placeholder="请选择故障类型">
            <el-option
              v-for="dict in dict.type.fault_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="故障设备" prop="fFaultDevice">
          <el-input v-model="form.fFaultDevice" placeholder="请输入故障设备" />
        </el-form-item>
        <el-form-item label="故障" prop="fFaultfre">
          <el-input v-model="form.fFaultfre" placeholder="请输入故障" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFault, getFault, delFault, addFault, updateFault } from "@/api/system/fault";

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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加故障信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fId = row.fId || this.ids
      getFault(fId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改故障信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fId != null) {
            updateFault(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFault(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fIds = row.fId || this.ids;
      this.$modal.confirm('是否确认删除故障信息编号为"' + fIds + '"的数据项？').then(function() {
        return delFault(fIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/fault/export', {
        ...this.queryParams
      }, `fault_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
