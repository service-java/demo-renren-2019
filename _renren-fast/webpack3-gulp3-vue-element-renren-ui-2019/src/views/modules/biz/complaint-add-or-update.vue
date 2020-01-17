<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="reporterName">
      <el-input v-model="dataForm.reporterName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="投诉人工作单位" prop="reporterCompany">
      <el-input v-model="dataForm.reporterCompany" placeholder="投诉人工作单位"></el-input>
    </el-form-item>
    <el-form-item label="投诉人手机号" prop="mobile">
      <el-input v-model="dataForm.mobile" placeholder="投诉人手机号"></el-input>
    </el-form-item>
    <el-form-item label="投诉内容" prop="content">
      <el-input v-model="dataForm.content" placeholder="投诉内容"></el-input>
    </el-form-item>
    <el-form-item label="附件地址" prop="attachmentUrl">
      <el-input v-model="dataForm.attachmentUrl" placeholder="附件地址"></el-input>
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
          complaintId: 0,
          reporterName: '',
          reporterCompany: '',
          mobile: '',
          content: '',
          attachmentUrl: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          reporterName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          reporterCompany: [
            { required: true, message: '投诉人工作单位不能为空', trigger: 'blur' }
          ],
          mobile: [
            { required: true, message: '投诉人手机号不能为空', trigger: 'blur' }
          ],
          content: [
            { required: true, message: '投诉内容不能为空', trigger: 'blur' }
          ],
          attachmentUrl: [
            { required: true, message: '附件地址不能为空', trigger: 'blur' }
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
        this.dataForm.complaintId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.complaintId) {
            this.$http({
              url: this.$http.adornUrl(`/biz/complaint/info/${this.dataForm.complaintId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.reporterName = data.bizcomplaint.reporterName
                this.dataForm.reporterCompany = data.bizcomplaint.reporterCompany
                this.dataForm.mobile = data.bizcomplaint.mobile
                this.dataForm.content = data.bizcomplaint.content
                this.dataForm.attachmentUrl = data.bizcomplaint.attachmentUrl
                this.dataForm.gmtCreate = data.bizcomplaint.gmtCreate
                this.dataForm.gmtModified = data.bizcomplaint.gmtModified
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
              url: this.$http.adornUrl(`/biz/complaint/${!this.dataForm.complaintId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'complaintId': this.dataForm.complaintId || undefined,
                'reporterName': this.dataForm.reporterName,
                'reporterCompany': this.dataForm.reporterCompany,
                'mobile': this.dataForm.mobile,
                'content': this.dataForm.content,
                'attachmentUrl': this.dataForm.attachmentUrl,
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
