<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="船舶模型" prop="bModel">
        <el-input
          v-model="queryParams.bModel"
          placeholder="请输入船舶模型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船舶速度" prop="bSpeed">
        <el-input
          v-model="queryParams.bSpeed"
          placeholder="请输入船舶速度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船舶容量" prop="bCapacity">
        <el-input
          v-model="queryParams.bCapacity"
          placeholder="请输入船舶容量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="船舶类型" prop="bType">
        <el-select v-model="queryParams.bType" placeholder="请选择船舶类型" clearable>
          <el-option
            v-for="dict in dict.type.b_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="经度" prop="bLongitude">
        <el-input
          v-model="queryParams.bLongitude"
          placeholder="请输入经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纬度" prop="bLatitude">
        <el-input
          v-model="queryParams.bLatitude"
          placeholder="请输入纬度"
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
          v-hasPermi="['system:boat:add']"
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
          v-hasPermi="['system:boat:edit']"
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
          v-hasPermi="['system:boat:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:boat:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="boatList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="船舶编号" align="center" prop="bId" />
      <el-table-column label="船舶模型" align="center" prop="bModel" />
      <el-table-column label="船舶速度" align="center" prop="bSpeed" />
      <el-table-column label="船舶容量" align="center" prop="bCapacity" />
      <el-table-column label="风速" align="center" prop="bWindSpeed" />
      <el-table-column label="海浪" align="center" prop="bWaveHeight" />
      <el-table-column label="每年成本" align="center" prop="bAnnualCost" />
      <el-table-column label="每小时成本" align="center" prop="bHourCost" />
      <el-table-column label="船舶类型" align="center" prop="bType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_type" :value="scope.row.bType"/>
        </template>
      </el-table-column>
      <el-table-column label="经度" align="center" prop="bLongitude" />
      <el-table-column label="纬度" align="center" prop="bLatitude" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:boat:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:boat:remove']"
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

    <!-- 添加或修改boat对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="船舶模型" prop="bModel">
          <el-input v-model="form.bModel" placeholder="请输入船舶模型" />
        </el-form-item>
        <el-form-item label="船舶速度" prop="bSpeed">
          <el-input v-model="form.bSpeed" placeholder="请输入船舶速度" />
        </el-form-item>
        <el-form-item label="船舶容量" prop="bCapacity">
          <el-input v-model="form.bCapacity" placeholder="请输入船舶容量" />
        </el-form-item>
        <el-form-item label="风速" prop="bWindSpeed">
          <el-input v-model="form.bWindSpeed" placeholder="请输入风速" />
        </el-form-item>
        <el-form-item label="海浪" prop="bWaveHeight">
          <el-input v-model="form.bWaveHeight" placeholder="请输入海浪" />
        </el-form-item>
        <el-form-item label="每年成本" prop="bAnnualCost">
          <el-input v-model="form.bAnnualCost" placeholder="请输入每年成本" />
        </el-form-item>
        <el-form-item label="每小时成本" prop="bHourCost">
          <el-input v-model="form.bHourCost" placeholder="请输入每小时成本" />
        </el-form-item>
        <el-form-item label="船舶类型" prop="bType">
          <el-select v-model="form.bType" placeholder="请选择船舶类型">
            <el-option
              v-for="dict in dict.type.b_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经度" prop="bLongitude">
          <el-input v-model="form.bLongitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="bLatitude">
          <el-input v-model="form.bLatitude" placeholder="请输入纬度" />
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
import { listBoat, getBoat, delBoat, addBoat, updateBoat } from "@/api/system/boat";

export default {
  name: "Boat",
  dicts: ['b_type'],
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
      // boat表格数据
      boatList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bModel: null,
        bSpeed: null,
        bCapacity: null,
        bType: null,
        bLongitude: null,
        bLatitude: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bType: [
          { required: true, message: "船舶类型不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询boat列表 */
    getList() {
      this.loading = true;
      listBoat(this.queryParams).then(response => {
        this.boatList = response.rows;
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
        bId: null,
        bModel: null,
        bSpeed: null,
        bCapacity: null,
        bWindSpeed: null,
        bWaveHeight: null,
        bAnnualCost: null,
        bHourCost: null,
        bType: null,
        bLongitude: null,
        bLatitude: null
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
      this.ids = selection.map(item => item.bId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加boat";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bId = row.bId || this.ids
      getBoat(bId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改boat";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bId != null) {
            updateBoat(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBoat(this.form).then(response => {
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
      const bIds = row.bId || this.ids;
      this.$modal.confirm('是否确认删除boat编号为"' + bIds + '"的数据项？').then(function() {
        return delBoat(bIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/boat/export', {
        ...this.queryParams
      }, `boat_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
