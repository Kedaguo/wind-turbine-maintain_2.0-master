<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="模拟时间" prop="stSimulationTime">
        <el-input
          v-model="queryParams.stSimulationTime"
          placeholder="请输入模拟时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="海浪时间" prop="stTime">
        <el-date-picker clearable
          v-model="queryParams.stTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择海浪时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="风速" prop="stWindSpeed">
        <el-input
          v-model="queryParams.stWindSpeed"
          placeholder="请输入风速"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="风机转速" prop="stWindSpeedTurbine">
        <el-input
          v-model="queryParams.stWindSpeedTurbine"
          placeholder="请输入风机转速"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="海浪高度" prop="stWaveHeight">
        <el-input
          v-model="queryParams.stWaveHeight"
          placeholder="请输入海浪高度"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:seaWave:add']"
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
          v-hasPermi="['system:seaWave:edit']"
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
          v-hasPermi="['system:seaWave:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:seaWave:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="seaWaveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="气象编号" align="center" prop="sId" />
      <el-table-column label="模拟时间" align="center" prop="stSimulationTime" />
      <el-table-column label="海浪时间" align="center" prop="stTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.stTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="风速" align="center" prop="stWindSpeed" />
      <el-table-column label="风机转速" align="center" prop="stWindSpeedTurbine" />
      <el-table-column label="海浪高度" align="center" prop="stWaveHeight" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:seaWave:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:seaWave:remove']"
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

    <!-- 添加或修改seaWave对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="模拟时间" prop="stSimulationTime">
          <el-input v-model="form.stSimulationTime" placeholder="请输入模拟时间" />
        </el-form-item>
        <el-form-item label="海浪时间" prop="stTime">
          <el-date-picker clearable
            v-model="form.stTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择海浪时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="风速" prop="stWindSpeed">
          <el-input v-model="form.stWindSpeed" placeholder="请输入风速" />
        </el-form-item>
        <el-form-item label="风机转速" prop="stWindSpeedTurbine">
          <el-input v-model="form.stWindSpeedTurbine" placeholder="请输入风机转速" />
        </el-form-item>
        <el-form-item label="海浪高度" prop="stWaveHeight">
          <el-input v-model="form.stWaveHeight" placeholder="请输入海浪高度" />
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
import { listSeaWave, getSeaWave, delSeaWave, addSeaWave, updateSeaWave } from "@/api/system/seaWave";

export default {
  name: "SeaWave",
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
      // seaWave表格数据
      seaWaveList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stSimulationTime: null,
        stTime: null,
        stWindSpeed: null,
        stWindSpeedTurbine: null,
        stWaveHeight: null
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
    /** 查询seaWave列表 */
    getList() {
      this.loading = true;
      listSeaWave(this.queryParams).then(response => {
        this.seaWaveList = response.rows;
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
        sId: null,
        stSimulationTime: null,
        stTime: null,
        stWindSpeed: null,
        stWindSpeedTurbine: null,
        stWaveHeight: null
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
      this.ids = selection.map(item => item.sId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加seaWave";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sId = row.sId || this.ids
      getSeaWave(sId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改seaWave";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sId != null) {
            updateSeaWave(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSeaWave(this.form).then(response => {
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
      const sIds = row.sId || this.ids;
      this.$modal.confirm('是否确认删除seaWave编号为"' + sIds + '"的数据项？').then(function() {
        return delSeaWave(sIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/seaWave/export', {
        ...this.queryParams
      }, `seaWave_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
