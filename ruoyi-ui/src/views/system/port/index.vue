<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="码头名称" prop="pPortname">
        <el-input
          v-model="queryParams.pPortname"
          placeholder="请输入码头名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="经度" prop="pLongitude">-->
<!--        <el-input-->
<!--          v-model="queryParams.pLongitude"-->
<!--          placeholder="请输入经度"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="纬度" prop="pLatitude">-->
<!--        <el-input-->
<!--          v-model="queryParams.pLatitude"-->
<!--          placeholder="请输入纬度"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="每天成本" prop="pCostperday">-->
<!--        <el-input-->
<!--          v-model="queryParams.pCostperday"-->
<!--          placeholder="请输入每天成本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="成本" prop="pFixdcost">-->
<!--        <el-input-->
<!--          v-model="queryParams.pFixdcost"-->
<!--          placeholder="请输入成本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="每艘船成本" prop="pCostPerVessalCall">-->
<!--        <el-input-->
<!--          v-model="queryParams.pCostPerVessalCall"-->
<!--          placeholder="请输入每艘船成本"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="天气限制" prop="pWeatherLimit">
        <el-input
          v-model="queryParams.pWeatherLimit"
          placeholder="请输入天气限制"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="港口深度" prop="pDepthPort">
        <el-input
          v-model="queryParams.pDepthPort"
          placeholder="请输入港口深度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="海上交通控制" prop="pTrafficControl">
        <el-input
          v-model="queryParams.pTrafficControl"
          placeholder="请输入交通控制船只"
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
          v-hasPermi="['system:port:add']"
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
          v-hasPermi="['system:port:edit']"
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
          v-hasPermi="['system:port:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:port:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="portList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="港口编号" align="center" prop="pId" />
      <el-table-column label="码头名称" align="center" prop="pPortname" />
      <el-table-column label="经度" align="center" prop="pLongitude" />
      <el-table-column label="纬度" align="center" prop="pLatitude" />
      <el-table-column label="每天成本" align="center" prop="pCostperday" />
      <el-table-column label="成本" align="center" prop="pFixdcost" />
      <el-table-column label="每艘船成本" align="center" prop="pCostPerVessalCall" />
      <el-table-column label="天气限制" align="center" prop="pWeatherLimit" />
      <el-table-column label="港口深度" align="center" prop="pDepthPort" />
      <el-table-column label="海上交通控制" align="center" prop="pTrafficControl" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:port:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:port:remove']"
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

    <!-- 添加或修改港口信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="码头名称" prop="pPortname">
          <el-input v-model="form.pPortname" placeholder="请输入码头名称" />
        </el-form-item>
        <el-form-item label="经度" prop="pLongitude">
          <el-input v-model="form.pLongitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="pLatitude">
          <el-input v-model="form.pLatitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="每天成本" prop="pCostperday">
          <el-input v-model="form.pCostperday" placeholder="请输入每天成本" />
        </el-form-item>
        <el-form-item label="成本" prop="pFixdcost">
          <el-input v-model="form.pFixdcost" placeholder="请输入成本" />
        </el-form-item>
        <el-form-item label="每艘船成本" prop="pCostPerVessalCall">
          <el-input v-model="form.pCostPerVessalCall" placeholder="请输入$每艘船成本" />
        </el-form-item>
        <el-form-item label="天气限制" prop="pWeatherLimit">
          <el-input v-model="form.pWeatherLimit" placeholder="请输入天气限制" />
        </el-form-item>
        <el-form-item label="港口深度" prop="pDepthPort">
          <el-input v-model="form.pDepthPort" placeholder="请输入港口深度" />
        </el-form-item>
        <el-form-item label="船只交通控制" prop="pTrafficControl">
          <el-input v-model="form.pTrafficControl" placeholder="请输入船只交通控制" />
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
import { listPort, getPort, delPort, addPort, updatePort } from "@/api/system/port";

export default {
  name: "Port",
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
      // 港口信息表格数据
      portList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pPortname: null,
        pLongitude: null,
        pLatitude: null,
        pCostperday: null,
        pFixdcost: null,
        pCostPerVessalCall: null,
        pWeatherLimit: null,
        pDepthPort: null,
        pTrafficControl: null
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
    /** 查询港口信息列表 */
    getList() {
      this.loading = true;
      listPort(this.queryParams).then(response => {
        this.portList = response.rows;
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
        pId: null,
        pPortname: null,
        pLongitude: null,
        pLatitude: null,
        pCostperday: null,
        pFixdcost: null,
        pCostPerVessalCall: null,
        pWeatherLimit: null,
        pDepthPort: null,
        pTrafficControl: null
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
      this.ids = selection.map(item => item.pId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加港口信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pId = row.pId || this.ids
      getPort(pId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改港口信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pId != null) {
            updatePort(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPort(this.form).then(response => {
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
      const pIds = row.pId || this.ids;
      this.$modal.confirm('是否确认删除港口信息编号为"' + pIds + '"的数据项？').then(function() {
        return delPort(pIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/port/export', {
        ...this.queryParams
      }, `port_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
