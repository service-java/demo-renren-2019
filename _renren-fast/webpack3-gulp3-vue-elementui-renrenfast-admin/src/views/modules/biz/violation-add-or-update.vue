<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="处理办法" prop="treatMethod">
      <el-input v-model="dataForm.treatMethod" placeholder="处理办法"></el-input>
    </el-form-item>
    <el-form-item label="违约情况描述" prop="description">
      <el-input v-model="dataForm.description" placeholder="违约情况描述"></el-input>
    </el-form-item>
    <el-form-item label="违约日期" prop="violationDate">
      <el-input v-model="dataForm.violationDate" placeholder="违约日期"></el-input>
    </el-form-item>
    <el-form-item label="是否显示到网站(0: 否; 1: 是 )" prop="isShow">
      <el-input v-model="dataForm.isShow" placeholder="是否显示到网站(0: 否; 1: 是 )"></el-input>
    </el-form-item>
    <el-form-item label="是否删除(0: 否；1: 是)" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否删除(0: 否；1: 是)"></el-input>
    </el-form-item>
    <el-form-item label="" prop="gmtCreate">
      <el-input v-model="dataForm.gmtCreate" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="gmtModified">
      <el-input v-model="dataForm.gmtModified" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          violationId: 0,
          treatMethod: '',
          description: '',
          violationDate: '',
          isShow: '',
          isDelete: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          treatMethod: [
            { required: true, message: '处理办法不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '违约情况描述不能为空', trigger: 'blur' }
          ],
          violationDate: [
            { required: true, message: '违约日期不能为空', trigger: 'blur' }
          ],
          isShow: [
            { required: true, message: '是否显示到网站(0: 否; 1: 是 )不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除(0: 否；1: 是)不能为空', trigger: 'blur' }
          ],
          gmtCreate: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          gmtModified: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.violationId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.violationId) {
            this.$http({
              url: this.$http.adornUrl(`/biz/violation/info/${this.dataForm.violationId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.treatMethod = data.bizviolation.treatMethod
                this.dataForm.description = data.bizviolation.description
                this.dataForm.violationDate = data.bizviolation.violationDate
                this.dataForm.isShow = data.bizviolation.isShow
                this.dataForm.isDelete = data.bizviolation.isDelete
                this.dataForm.gmtCreate = data.bizviolation.gmtCreate
                this.dataForm.gmtModified = data.bizviolation.gmtModified
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/biz/violation/${!this.dataForm.violationId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'violationId': this.dataForm.violationId || undefined,
                'treatMethod': this.dataForm.treatMethod,
                'description': this.dataForm.description,
                'violationDate': this.dataForm.violationDate,
                'isShow': this.dataForm.isShow,
                'isDelete': this.dataForm.isDelete,
                'gmtCreate': this.dataForm.gmtCreate,
                'gmtModified': this.dataForm.gmtModified
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
