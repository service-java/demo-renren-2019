<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="companyName">
      <el-input v-model="dataForm.companyName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="公司所在地区" prop="areaId">
      <el-input v-model="dataForm.areaId" placeholder="公司所在地区"></el-input>
    </el-form-item>
    <el-form-item label="签约人" prop="signPerson">
      <el-input v-model="dataForm.signPerson" placeholder="签约人"></el-input>
    </el-form-item>
    <el-form-item label="签约时间" prop="signDate">
      <el-input v-model="dataForm.signDate" placeholder="签约时间"></el-input>
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
          companyId: 0,
          companyName: '',
          areaId: '',
          signPerson: '',
          signDate: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          companyName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          areaId: [
            { required: true, message: '公司所在地区不能为空', trigger: 'blur' }
          ],
          signPerson: [
            { required: true, message: '签约人不能为空', trigger: 'blur' }
          ],
          signDate: [
            { required: true, message: '签约时间不能为空', trigger: 'blur' }
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
        this.dataForm.companyId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.companyId) {
            this.$http({
              url: this.$http.adornUrl(`/biz/company/info/${this.dataForm.companyId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.companyName = data.bizcompany.companyName
                this.dataForm.areaId = data.bizcompany.areaId
                this.dataForm.signPerson = data.bizcompany.signPerson
                this.dataForm.signDate = data.bizcompany.signDate
                this.dataForm.gmtCreate = data.bizcompany.gmtCreate
                this.dataForm.gmtModified = data.bizcompany.gmtModified
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
              url: this.$http.adornUrl(`/biz/company/${!this.dataForm.companyId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'companyId': this.dataForm.companyId || undefined,
                'companyName': this.dataForm.companyName,
                'areaId': this.dataForm.areaId,
                'signPerson': this.dataForm.signPerson,
                'signDate': this.dataForm.signDate,
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
