<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保养准备时间" prop="mPrepareDuration">
        <el-input
          v-model="queryParams.mPrepareDuration"
          placeholder="请输入保养准备时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保养时间" prop="mMaintainDuration">
        <el-input
          v-model="queryParams.mMaintainDuration"
          placeholder="请输入保养时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保养设备" prop="mFaultDevice">
        <el-input
          v-model="queryParams.mFaultDevice"
          placeholder="请输入保养设备"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下一次保养时间" prop="mFaultfre">
        <el-input
          v-model="queryParams.mFaultfre"
          placeholder="请输入下一次保养时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="保养单生成时间" prop="mCreateTime">
        <el-date-picker clearable
          v-model="queryParams.mCreateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择保养单生成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="保养状态" prop="mState">
        <el-select v-model="queryParams.mState" placeholder="请选择保养状态" clearable>
          <el-option
            v-for="dict in dict.type.maintain_state"
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
          v-hasPermi="['system:maintain:add']"
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
          v-hasPermi="['system:maintain:edit']"
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
          v-hasPermi="['system:maintain:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:maintain:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maintainList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="保养编号" align="center" prop="mId" />
      <el-table-column label="每年保养频率" align="center" prop="mFrequencyPerYear" />
      <el-table-column label="保养成本" align="center" prop="mConsumableCost" />
      <el-table-column label="船只类型" align="center" prop="mBoatModel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.b_type" :value="scope.row.mBoatModel"/>
        </template>
      </el-table-column>
      <el-table-column label="保养准备时间" align="center" prop="mPrepareDuration" />
      <el-table-column label="保养时间" align="center" prop="mMaintainDuration" />
      <el-table-column label="保养人员" align="center" prop="mPeopleNumber" />
      <el-table-column label="保养类型" align="center" prop="mFaultType" />
      <el-table-column label="保养设备" align="center" prop="mFaultDevice" />
      <el-table-column label="下一次保养时间" align="center" prop="mFaultfre" />
      <el-table-column label="保养单生成时间" align="center" prop="mCreateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.mCreateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="保养状态" align="center" prop="mState">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.maintain_state" :value="scope.row.mState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:maintain:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:maintain:remove']"
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

    <!-- 添加或修改maintain对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="每年保养频率" prop="mFrequencyPerYear">
          <el-input v-model="form.mFrequencyPerYear" placeholder="请输入每年保养频率" />
        </el-form-item>
        <el-form-item label="保养成本" prop="mConsumableCost">
          <el-input v-model="form.mConsumableCost" placeholder="请输入保养成本" />
        </el-form-item>
        <el-form-item label="船只类型" prop="mBoatModel">
          <el-select v-model="form.mBoatModel" placeholder="请选择船只类型">
            <el-option
              v-for="dict in dict.type.b_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="保养准备时间" prop="mPrepareDuration">
          <el-input v-model="form.mPrepareDuration" placeholder="请输入保养准备时间" />
        </el-form-item>
        <el-form-item label="保养时间" prop="mMaintainDuration">
          <el-input v-model="form.mMaintainDuration" placeholder="请输入保养时间" />
        </el-form-item>
        <el-form-item label="保养人员" prop="mPeopleNumber">
          <el-input v-model="form.mPeopleNumber" placeholder="请输入保养人员" />
        </el-form-item>
        <el-form-item label="保养设备" prop="mFaultDevice">
          <el-input v-model="form.mFaultDevice" placeholder="请输入保养设备" />
        </el-form-item>
        <el-form-item label="下一次保养时间" prop="mFaultfre">
          <el-input v-model="form.mFaultfre" placeholder="请输入下一次保养时间" />
        </el-form-item>
        <el-form-item label="保养单生成时间" prop="mCreateTime">
          <el-date-picker clearable
            v-model="form.mCreateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择保养单生成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="保养状态" prop="mState">
          <el-select v-model="form.mState" placeholder="请选择保养状态">
            <el-option
              v-for="dict in dict.type.maintain_state"
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
import { listMaintain, getMaintain, delMaintain, addMaintain, updateMaintain } from "@/api/system/maintain";

export default {
  name: "Maintain",
  dicts: ['b_type', 'maintain_state'],
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
      // maintain表格数据
      maintainList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mPrepareDuration: null,
        mMaintainDuration: null,
        mFaultDevice: null,
        mFaultfre: null,
        mCreateTime: null,
        mState: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        mCreateTime: [
          { required: true, message: "保养单生成时间不能为空", trigger: "blur" }
        ],
        mState: [
          { required: true, message: "保养状态不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询maintain列表 */
    getList() {
      this.loading = true;
      listMaintain(this.queryParams).then(response => {
        this.maintainList = response.rows;
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
        mId: null,
        mFrequencyPerYear: null,
        mConsumableCost: null,
        mBoatModel: null,
        mPrepareDuration: null,
        mMaintainDuration: null,
        mPeopleNumber: null,
        mFaultType: null,
        mFaultDevice: null,
        mFaultfre: null,
        mCreateTime: null,
        mState: null
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
      this.ids = selection.map(item => item.mId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加maintain";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mId = row.mId || this.ids
      getMaintain(mId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改maintain";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mId != null) {
            updateMaintain(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaintain(this.form).then(response => {
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
      const mIds = row.mId || this.ids;
      this.$modal.confirm('是否确认删除maintain编号为"' + mIds + '"的数据项？').then(function() {
        return delMaintain(mIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/maintain/export', {
        ...this.queryParams
      }, `maintain_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
