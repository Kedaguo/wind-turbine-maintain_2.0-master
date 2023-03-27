<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="价格" prop="tPrice">
        <el-input
          v-model="queryParams.tPrice"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="功率" prop="tRatePower">
        <el-input
          v-model="queryParams.tRatePower"
          placeholder="请输入功率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="高度" prop="tHeight">
        <el-input
          v-model="queryParams.tHeight"
          placeholder="请输入高度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="寿命" prop="tLifetime">
        <el-input
          v-model="queryParams.tLifetime"
          placeholder="请输入寿命"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成本" prop="tCost">
        <el-input
          v-model="queryParams.tCost"
          placeholder="请输入成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="品牌" prop="tBrand">
        <el-input
          v-model="queryParams.tBrand"
          placeholder="请输入品牌"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="故障状态" prop="tFaultState">
        <el-select v-model="queryParams.tFaultState" placeholder="请选择故障状态" clearable>
          <el-option
            v-for="dict in dict.type.lt_turbine_fault"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="保养状态" prop="tMaintainState">
        <el-select v-model="queryParams.tMaintainState" placeholder="请选择保养状态" clearable>
          <el-option
            v-for="dict in dict.type.lt_turbine_maintain"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['system:windTurbine:add']"
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
          v-hasPermi="['system:windTurbine:edit']"
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
          v-hasPermi="['system:windTurbine:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:windTurbine:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="windTurbineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="tId" />
      <el-table-column label="功率" align="center" prop="tRatePower" />
      <el-table-column label="高度" align="center" prop="tHeight" />
      <el-table-column label="经度" align="center" prop="tLongitude" />
      <el-table-column label="纬度" align="center" prop="tLatitude" />
      <el-table-column label="品牌" align="center" prop="tBrand" />
      <el-table-column label="故障信息" align="center" prop="tFaultState">
        <template slot-scope="scope">
          <el-tag :type="scope.row.tFaultState === 3 ? 'primary' : 'danger'"><dict-tag :options="dict.type.lt_turbine_fault" :value="scope.row.tFaultState"/></el-tag>
        </template>
      </el-table-column>
      <el-table-column label="保养信息" align="center" prop="tMaintainState">
        <template slot-scope="scope">
          <el-tag :type="scope.row.tMaintainState === 3 ? 'primary' : 'warning'"><dict-tag :options="dict.type.lt_turbine_maintain" :value="scope.row.tMaintainState"/></el-tag>       
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope"> 
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:windTurbine:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:windTurbine:remove']"
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

    <!-- 添加或修改windTurbine对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模型" prop="tModel">
          <el-input v-model="form.tModel" placeholder="请输入模型" />
        </el-form-item>
        <el-form-item label="价格" prop="tPrice">
          <el-input v-model="form.tPrice" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="功率" prop="tRatePower">
          <el-input v-model="form.tRatePower" placeholder="请输入功率" />
        </el-form-item>
        <el-form-item label="功率速度曲线" prop="tPowerVelocityCurve">
          <el-input v-model="form.tPowerVelocityCurve" placeholder="请输入功率速度曲线" />
        </el-form-item>
        <el-form-item label="高度" prop="tHeight">
          <el-input v-model="form.tHeight" placeholder="请输入高度" />
        </el-form-item>
        <el-form-item label="寿命" prop="tLifetime">
          <el-input v-model="form.tLifetime" placeholder="请输入寿命" />
        </el-form-item>
        <el-form-item label="成本" prop="tCost">
          <el-input v-model="form.tCost" placeholder="请输入成本" />
        </el-form-item>
        <el-form-item label="经度" prop="tLongitude">
          <el-input v-model="form.tLongitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="tLatitude">
          <el-input v-model="form.tLatitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="品牌" prop="tBrand">
          <el-input v-model="form.tBrand" placeholder="请输入品牌" />
        </el-form-item>
        <el-form-item label="故障状态" prop="tFaultState">
          <el-select v-model="form.tFaultState" placeholder="请选择故障状态">
            <el-option
              v-for="dict in dict.type.lt_turbine_fault"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="保养状态" prop="tMaintainState">
          <el-select v-model="form.tMaintainState" placeholder="请选择保养状态">
            <el-option
              v-for="dict in dict.type.lt_turbine_maintain"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listWindTurbine, getWindTurbine, delWindTurbine, addWindTurbine, updateWindTurbine } from "@/api/system/windTurbine";

export default {
  name: "WindTurbine",
  dicts: ['lt_turbine_maintain', 'lt_turbine_fault'],
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
      // windTurbine表格数据
      windTurbineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tPrice: null,
        tRatePower: null,
        tHeight: null,
        tLifetime: null,
        tCost: null,
        tBrand: null,
        tFaultState: null,
        tMaintainState: null,
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
    /** 查询windTurbine列表 */
    getList() {
      this.loading = true;
      listWindTurbine(this.queryParams).then(response => {
        this.windTurbineList = response.rows;
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
        tId: null,
        tModel: null,
        tPrice: null,
        tRatePower: null,
        tPowerVelocityCurve: null,
        tHeight: null,
        tLifetime: null,
        tCost: null,
        tLongitude: null,
        tLatitude: null,
        tBrand: null,
        tFaultState: null,
        tMaintainState: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        falutId: null
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
      this.ids = selection.map(item => item.tId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加windTurbine";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tId = row.tId || this.ids
      getWindTurbine(tId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改windTurbine";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tId != null) {
            updateWindTurbine(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWindTurbine(this.form).then(response => {
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
      const tIds = row.tId || this.ids;
      this.$modal.confirm('是否确认删除windTurbine编号为"' + tIds + '"的数据项？').then(function() {
        return delWindTurbine(tIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/windTurbine/export', {
        ...this.queryParams
      }, `windTurbine_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
