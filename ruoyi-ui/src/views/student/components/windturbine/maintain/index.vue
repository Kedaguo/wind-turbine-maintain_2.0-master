<template>
    <div>
    <div><el-button type="primary" @click="dialogVisible = true">主要按钮</el-button></div>
    <div>
        <el-dialog v-bind="$attrs" :visible.sync="dialogVisible" v-on="$listeners" @open="onOpen"
            @close="onClose" title="维修单">
            <el-row :gutter="15">
                <el-form ref="maintainForm" :model="formData" :rules="rules" size="medium" label-width="100px">
                    <el-col :span="24">
                        <el-form-item label="码头" prop="field101">
                            <el-select v-model="formData.field101" placeholder="请选择码头" clearable
                                :style="{ width: '100%' }">
                                <el-option v-for="(item, index) in field101Options" :key="index"
                                    :label="item.label" :value="item.value"
                                    :disabled="item.disabled"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="24">
                        <el-form-item label="任务" prop="field113">
                            <el-checkbox-group v-model="formData.field113" size="medium">
                                <el-checkbox v-for="(item, index) in field113Options" :key="index"
                                    :label="item.value" :disabled="item.disabled" border>{{ item.label
                                    }}</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="海上平台" prop="field108" required>
                            <el-switch v-model="formData.field108"></el-switch>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="出海人数" prop="field109">
                            <el-input-number v-model="formData.field109" placeholder="出海人数"
                                controls-position=right>
                            </el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form>
            </el-row>
            <div slot="footer">
                <el-button @click="close">取消</el-button>
                <el-button type="primary" @click="handleConfirm">确定</el-button>
            </div>
        </el-dialog>
    </div>
    <div>
        <template>
  <div>
    <el-row :gutter="15">
      <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
        <el-col :span="12">
          <el-form-item label="码头" prop="field102">
            <el-select v-model="formData.field102" placeholder="请选择出海码头" clearable :style="{width: '100%'}">
              <el-option v-for="(item, index) in field102Options" :key="index" :label="item.label"
                :value="item.value" :disabled="item.disabled"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="海上平台" prop="field101" required>
            <el-switch v-model="formData.field101"></el-switch>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="船只" prop="field109">
            <el-input-number v-model="formData.field109" placeholder="船只数量"></el-input-number>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="人员" prop="field111">
            <el-input-number v-model="formData.field111" placeholder="人员数量" controls-position=right>
            </el-input-number>
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
</template>


    </div>
</div>
</template>

<script>
export default {
    inheritAttrs: false,
    components: {},
    props: [],
    data() {
        return {
            dialogVisible: false,
            //visible:true,
            formData: {
                field101: 1,
                field113: [],
                field108: false,
                field109: 0,
                field112: null,
            },
            rules: {
                field101: [{
                    required: true,
                    message: '请选择码头',
                    trigger: 'change'
                }],
                field113: [{
                    required: true,
                    type: 'array',
                    message: '请至少选择一个field113',
                    trigger: 'change'
                }],
                field109: [{
                    required: true,
                    message: '出海人数',
                    trigger: 'blur'
                }],
                field112: [{
                    required: true,
                    message: '请选择出海时间',
                    trigger: 'change'
                }],
            },
            field101Options: [{
                "label": "松下码头",
                "value": 1
            }, {
                "label": "大练岛陆岛码头",
                "value": 2
            }],
            field113Options: [{
                "label": "维修",
                "value": 1
            }, {
                "label": "保养",
                "value": 2
            }],
        }
    },
    computed: {},
    watch: {},
    created() { },
    mounted() { },
    methods: {
        onOpen() { },
        onClose() {
            this.$refs['maintainForm'].resetFields()
        },
        close() {
            this.$emit('update:visible', false)
        },
        handleConfirm() {
            this.$refs['maintainForm'].validate(valid => {
                if (!valid) return
                this.close()
            })
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    }
}

</script>