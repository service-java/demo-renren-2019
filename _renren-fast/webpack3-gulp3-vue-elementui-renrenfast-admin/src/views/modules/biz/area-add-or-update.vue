<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="地区名称" prop="name">
      <el-input v-model="dataForm.name" placeholder="地区名称"></el-input>
    </el-form-item>
    <el-form-item label="父级地区id" prop="parentId">
      <el-input v-model="dataForm.parentId" placeholder="父级地区id"></el-input>
    </el-form-item>
    <el-form-item label="通用编码" prop="code">
      <el-input v-model="dataForm.code" placeholder="通用编码"></el-input>
    </el-form-item>
    <el-form-item label="自定义编码" prop="customCode">
      <el-input v-model="dataForm.customCode" placeholder="自定义编码"></el-input>
    </el-form-item>
    <el-form-item label="是否删除(0 否; 1: 是)" prop="isDelete">
      <el-input v-model="dataForm.isDelete" placeholder="是否删除(0 否; 1: 是)"></el-input>
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
          areaId: 0,
          name: '',
          parentId: '',
          code: '',
          customCode: '',
          isDelete: '',
          gmtCreate: '',
          gmtModified: ''
        },
        dataRule: {
          name: [
            { required: true, message: '地区名称不能为空', trigger: 'blur' }
          ],
          parentId: [
            { required: true, message: '父级地区id不能为空', trigger: 'blur' }
          ],
          code: [
            { required: true, message: '通用编码不能为空', trigger: 'blur' }
          ],
          customCode: [
            { required: true, message: '自定义编码不能为空', trigger: 'blur' }
          ],
          isDelete: [
            { required: true, message: '是否删除(0 否; 1: 是)不能为空', trigger: 'blur' }
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
        this.dataForm.areaId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.areaId) {
            this.$http({
              url: this.$http.adornUrl(`/biz/area/info/${this.dataForm.areaId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.bizarea.name
                this.dataForm.parentId = data.bizarea.parentId
                this.dataForm.code = data.bizarea.code
                this.dataForm.customCode = data.bizarea.customCode
                this.dataForm.isDelete = data.bizarea.isDelete
                this.dataForm.gmtCreate = data.bizarea.gmtCreate
                this.dataForm.gmtModified = data.bizarea.gmtModified
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
              url: this.$http.adornUrl(`/biz/area/${!this.dataForm.areaId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'areaId': this.dataForm.areaId || undefined,
                'name': this.dataForm.name,
                'parentId': this.dataForm.parentId,
                'code': this.dataForm.code,
                'customCode': this.dataForm.customCode,
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
