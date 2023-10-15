<template>
  <el-card v-loading="loading">
    <!--查询区域-->
    <el-row>
      <el-col :span="5">
        物品名称：<el-input placeholder="请输入物品名称" style="width: 200px" v-model="queryParam.articleName"></el-input>
      </el-col>
      <el-col :span="5">
        申请人：<el-input placeholder="请输入申请人" style="width: 200px" v-model="queryParam.userName"></el-input>
      </el-col>
      <el-col :span="5">
        <!--0-已撤回 1-待审核 2-审核通过 3-驳回-->
        申请状态：
        <el-select v-model="queryParam.applyStatus" placeholder="请选择申请状态">
          <el-option label="全部"></el-option>
          <el-option label="已撤回" value="0"></el-option>
          <el-option label="待审核" value="1"></el-option>
          <el-option label="审核通过" value="2"></el-option>
          <el-option label="驳回" value="3"></el-option>
        </el-select>
      </el-col>
      <el-col :span="5">
        <el-button @click="handleSearch" type="primary" icon="el-icon-search" size="medium">搜索</el-button>
        <el-button @click="handleReset" type="primary" icon="el-icon-refresh-right" size="medium">重置</el-button>
      </el-col>
    </el-row>

    <!--操作区域-->
    <el-row style="margin-top: 20px">
    </el-row>


    <!--表格区域-->
    <el-row style="margin-top: 20px">
      <el-col :span="24" align="center">
        <el-table border :data="tableData" style="width: 100%">
          <el-table-column prop="userName" label="申请人" width="100" align="center"></el-table-column>
          <el-table-column prop="applyTime" label="申请时间" width="200" align="center"></el-table-column>
          <el-table-column prop="applyStatus" label="申请状态" align="center">
            <template slot-scope="scope">
              <!--0-已撤回 1-待审核 2-审核通过 3-驳回-->
              <span style="color: #909090;" v-if="scope.row.applyStatus === 0">已撤回</span>
              <span style="color: orange;" v-if="scope.row.applyStatus === 1">待审核</span>
              <span style="color: green;" v-if="scope.row.applyStatus === 2">审核通过</span>
              <span style="color: red;" v-if="scope.row.applyStatus === 3">驳回</span>
            </template>
          </el-table-column>
          <el-table-column prop="articleName" label="物品名称" width="400"   align="center"></el-table-column>
          <el-table-column prop="applyNum" label="申请数量"  align="center"></el-table-column>
          <el-table-column prop="applyUnit" label="物品单位"  align="center"></el-table-column>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template slot-scope="scope">
                            <span v-if="scope.row.applyStatus === 1">
                                <el-button @click="handlePass(scope.row)" type="success" size="mini">通过</el-button>
                                <el-button @click="handleRefuse(scope.row)" type="danger" size="mini">驳回</el-button>
                            </span>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <!--分页-->
    <el-row style="margin-top: 15px">
      <el-col :span="24" align="center">
        <el-pagination background layout="prev, pager, next, sizes, total"
                       @current-change="handleCurrentChange"
                       @size-change="handleSizeChange"
                       :total="pagination.total"
                       :page-size="pagination.pageSize"
                       :current-page="pagination.currentPage"
                       :page-sizes="pagination.pageSizes">
        </el-pagination>
      </el-col>
    </el-row>



    <el-dialog title="拒绝原因"  :visible.sync="dialogVisible" width="50%">
      <el-input type="textarea" v-model="auditRemark"></el-input>

      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="refuseSubmit">确 定</el-button>
            </span>
    </el-dialog>
  </el-card>
</template>

<script>
  import request from '@/utils/request'
  import { MessageBox, Message } from 'element-ui'
  import { getUserInfo } from '@/utils/auth'

  export default {
    name: 'apply-audit',
    // 数据区域
    data() {
      return {
        tableData: [],
        // 查询参数
        queryParam: {},
        loading: false,
        // 分页参数
        pagination: {
          total: 0, // 总记录数
          pageSize: 5, // 每页显示条数
          currentPage: 1, // 当前页
          pageSizes: [5, 10, 20]
        },

        // 审核相关
        dialogVisible: false,
        auditRemark: null,
        applyId:null
      }
    },
    // 生命周期方法，进入页面执行
    created() {
      this.loadData()
    },
    methods: {
      /**
       * 分页查询
       */
      loadData() {
        this.loading = true
        // 分页参数
        this.queryParam.currentPage = this.pagination.currentPage;
        this.queryParam.pageSize = this.pagination.pageSize;
        request({
          url: "apply/queryPage",
          method: "get",
          // 传入查询参数
          params: this.queryParam
        }).then(res => {
          if (res.success) {
            this.tableData = res.result.data;
            this.pagination.total = res.result.total;
          } else {
            Message({
              message: res.message,
              type: 'error',
              duration: 2 * 1000
            })
          }
        }).finally(() => {
          setTimeout(()=>{
            this.loading = false
          },200)
        })
      },

      /**
       * 搜索
       */
      handleSearch() {
        console.log(this.queryParam)
        // 从第一页开始
        this.pagination.currentPage = 1
        this.loadData()
      },
      /**
       * 重置
       */
      handleReset() {
        // 将查询参数置空
        this.queryParam = {}
        // 从第一页开始
        this.pagination.currentPage = 1
        this.loadData()
      },

      /**
       * currentPage 改变时会触发
       *
       * @param currentPage
       */
      handleCurrentChange(currentPage) {
        this.pagination.currentPage = currentPage
        this.loadData()
      },
      /**
       * pageSize 改变时会触发
       *
       * @param pageSize
       */
      handleSizeChange(pageSize) {
        this.pagination.pageSize = pageSize
        this.loadData()
      },

      /**
       * 通过
       */
      handlePass(record) {
        this.$confirm('是否确认通过?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let article = {
            applyStatus: 2,
            applyId: record.applyId
          }
          this.applyAudit(article)
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消'
          })
        })
      },
      /**
       * 申请审核提交
       */
      applyAudit(article) {
        // 从前端缓存里获取用户信息
        let userInfo = getUserInfo()
        article.auditBy = userInfo.userName
        article.updateBy = userInfo.userName
        request({
          url:"apply/applyAudit",
          method: "post",
          data: article
        }).then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: '操作成功'
            });
            // 关闭弹窗
            this.dialogVisible = false;
            // 刷新页面
            this.pagination.currentPage = 1
            this.loadData()
          } else {
            this.$message({
              type: 'error',
              message: '操作失败'
            });
          }
        })
      },
      handleRefuse(record) {
        this.dialogVisible = true;
        this.auditRemark = null;
        this.applyId = record.applyId;
      },
      /**
       * 拒绝提交
       */
      refuseSubmit() {
        let article = {
          applyStatus: 3,
          applyId: this.applyId,
          auditRemark: this.auditRemark
        }
        this.applyAudit(article)
      }
    }
  }
</script>

<style scoped>

</style>
