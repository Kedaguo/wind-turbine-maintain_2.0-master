<template>
  <div class="app-container">




    <el-table v-loading="loading" :data="maintainList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="保养序列" type="index" width="50"/>
      <!-- <el-table-column label="保养编号" align="center" prop="mId" /> -->
      <el-table-column label="每年保养频率" align="center" prop="mFrequencyPerYear" />
      <el-table-column label="保养成本" align="center" prop="mConsumableCost" />
      <el-table-column label="保养准备时间" align="center" prop="mPrepareDuration" />
      <el-table-column label="保养时间" align="center" prop="mMaintainDuration" />
      <el-table-column label="保养人员" align="center" prop="mPeopleNumber" />
      <el-table-column label="保养类型" align="center" prop="mFaultType" />
      <el-table-column label="保养设备" align="center" prop="mFaultDevice" />
      <el-table-column label="未知属性" align="center" prop="mFaultfre" />
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
import { listMaintain, getMaintain} from "@/api/resource/maintain";

export default {
  name: "Maintain",
  dicts: ['maintain_state'],
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
        mState: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
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


  }
};
</script>
