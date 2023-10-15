<template>
  <el-card v-loading="loading">
    <!--新增编辑模态框-->
    <el-dialog :title="title" :visible.sync="dialogVisible" width="30%">
      <el-form ref="articleForm"  :model="article" label-width="80px">
        <el-form-item label="物品名称">
          <el-input v-model="article.articleName"></el-input>
        </el-form-item>
        <el-form-item label="物品分类">
          <el-select v-model="article.articleType" placeholder="请选择物品分类" style="width: 100%">
            <el-option label="笔" :value="1"></el-option>
            <el-option label="笔记本" :value="2"></el-option>
            <el-option label="订书机" :value="3"></el-option>
            <el-option label="打印纸" :value="4"></el-option>
            <el-option label="订书针" :value="5"></el-option>
            <el-option label="文件夹" :value="6"></el-option>
            <el-option label="档案袋" :value="7"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="库存数量">
          <el-input type="number" v-model="article.stockNum"></el-input>
        </el-form-item>
        <el-form-item label="物品单位">
          <el-input v-model="article.stockUnit"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input type="textarea" v-model="article.articleDescribe"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSubmit">确 定</el-button>
          </span>
    </el-dialog>

    <!--查询区域-->
    <el-row>
      <el-col :span="6">
        物品名称：<el-input placeholder="请输入物品名称" style="width: 200px" v-model="queryParam.articleName"></el-input>
      </el-col>
      <el-col :span="6">
        物品分类：
        <el-select v-model="queryParam.articleType" placeholder="请选择物品分类">
          <el-option label="笔" value="1"></el-option>
          <el-option label="笔记本" value="2"></el-option>
          <el-option label="订书机" value="3"></el-option>
          <el-option label="打印纸" value="4"></el-option>
          <el-option label="订书针" value="5"></el-option>
          <el-option label="文件夹" value="6"></el-option>
          <el-option label="档案袋" value="7"></el-option>
        </el-select>
      </el-col>
      <el-col :span="6">
        <el-button @click="handleSearch" type="primary" icon="el-icon-search" size="medium">搜索</el-button>
        <el-button @click="handleReset" type="primary" icon="el-icon-refresh-right" size="medium">重置</el-button>
      </el-col>
    </el-row>

    <!--操作区域-->
    <el-row style="margin-top: 20px">
      <el-col>
        <el-button @click="handleAdd" type="success" icon="el-icon-plus" size="medium">新增</el-button>
      </el-col>
    </el-row>
    <!--表格区域-->
    <el-row style="margin-top: 20px">
      <el-col :span="24" align="center">
        <el-table border :data="tableData" style="width: 100%">
          <el-table-column prop="articleId" label="序号" width="100" align="center"></el-table-column>
          <el-table-column prop="articleName" label="物品名称" width="400" align="center"></el-table-column>
          <el-table-column prop="articleType" label="物品分类" align="center">
            <template slot-scope="scope">
              <!--物品分类（1-笔，2-笔记本，3-订书机，4-打印纸，5-订书针，6-文件夹，7-档案袋-->
              <span style="color: #4498ff;" v-if="scope.row.articleType === 1">笔</span>
              <span style="color: #4498ff;" v-if="scope.row.articleType === 2">笔记本</span>
              <span style="color: #4498ff;" v-if="scope.row.articleType === 3">订书机</span>
              <span style="color: #4498ff;" v-if="scope.row.articleType === 4">打印纸</span>
              <span style="color: #4498ff;" v-if="scope.row.articleType === 5">订书针</span>
              <span style="color: #4498ff;" v-if="scope.row.articleType === 6">文件夹</span>
              <span style="color: #4498ff;" v-if="scope.row.articleType === 7">档案袋</span>
            </template>
          </el-table-column>
          <el-table-column prop="stockNum" label="库存数量"  align="center"></el-table-column>
          <el-table-column prop="stockUnit" label="物品单位"  align="center"></el-table-column>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template slot-scope="scope">
              <el-button @click="handleEdit(scope.row)" type="primary" size="mini">编辑</el-button>
              <el-button @click="handleDelete(scope.row)" type="danger" size="mini">删除</el-button>
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
  </el-card>

</template>




<script>
  import request from '@/utils/request'
  import { MessageBox, Message } from 'element-ui'

  export default {
    name: 'article-maintenance',
    // 数据区域
    data() {
      return {
        tableData: [{
          articleId: '1',
          articleName: '得力（deli）0468 大号省力型订书机',
          articleType: 1,
          stockNum: 100,
          stockUnit: '台'
        }, {
          articleId: '2',
          articleName: '得力（deli）0468 大号省力型订书机',
          articleType: 2,
          stockNum: 100,
          stockUnit: '台'
        }],
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
        // 新增编辑相关
        title: "新增",
        dialogVisible: false,
        article: {},
      }
    },
    // 生命周期方法，进入页面执行
    created() {
      this.loadData()
    },

    // 方法区域
    methods: {
      /**
       * 列表查询
       */
      loadData() {
        this.loading = true
        // 分页参数
        this.queryParam.currentPage = this.pagination.currentPage;
        this.queryParam.pageSize = this.pagination.pageSize;
        request({
          url: "article/queryPage",
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
          // 延时0.5秒
          setTimeout(()=>{
            this.loading = false
          }, 500)
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
       * 新增
       */
      handleAdd() {
        this.title = "新增物品"
        this.dialogVisible = true
        this.article = {}
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
       * 编辑
       */
      handleEdit(record) {
        this.title = "修改物品"
        this.dialogVisible = true
        // 将record对象浅拷贝一份
        this.article = {...record}
      },
      /**
       * 新增编辑提交
       */
      handleSubmit() {
        request({
          url:"article/saveOrUpdate",
          method:"post",
          data: this.article
        }).then(res=> {
          if (res.success) {
            Message({
              message: res.message,
              type: 'success',
              duration: 2 * 1000
            })
            // 关闭模态框
            this.dialogVisible = false
            // 刷新列表，从第一页开始
            this.pagination.currentPage = 1
            this.loadData()
          }
        })
      },
      /**
       * 删除
       */
      handleDelete(record) {
        this.$confirm('是否确认删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          request({
            url:"article/deleteById",
            method:"get",
            params: {"articleId": record.articleId}
          }).then(res => {
            if (res.success) {
              Message({
                message: "删除成功",
                type: 'success',
                duration: 2 * 1000
              })
              // 刷新列表，从第一页开始
              this.pagination.currentPage = 1
              this.loadData()
            } else {
              Message({
                message: "删除失败",
                type: 'error',
                duration: 2 * 1000
              })
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
    }
  }
</script>

<style scoped>

</style>
