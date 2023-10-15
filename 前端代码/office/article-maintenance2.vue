<template>
  <el-card v-loading="loading">
    <!--&lt;!&ndash;查询区域&ndash;&gt;-->
    <!--<el-row>-->
      <!--<el-col :span="6">-->
        <!--物品名称：<el-input placeholder="请输入物品名称" style="width: 200px" v-model="queryParam.articleName"></el-input>-->
      <!--</el-col>-->
      <!--<el-col :span="6">-->
        <!--物品分类：-->
        <!--<el-select v-model="queryParam.articleType" placeholder="请选择物品分类">-->
          <!--<el-option label="笔" value="1"></el-option>-->
          <!--<el-option label="笔记本" value="2"></el-option>-->
          <!--<el-option label="订书机" value="3"></el-option>-->
          <!--<el-option label="打印纸" value="4"></el-option>-->
          <!--<el-option label="订书针" value="5"></el-option>-->
          <!--<el-option label="文件夹" value="6"></el-option>-->
          <!--<el-option label="档案袋" value="7"></el-option>-->
        <!--</el-select>-->
      <!--</el-col>-->
      <!--<el-col :span="6">-->
        <!--<el-button @click="handleSearch" type="primary" icon="el-icon-search" size="medium">搜索</el-button>-->
        <!--<el-button @click="handleReset" type="primary" icon="el-icon-refresh-right" size="medium">重置</el-button>-->
      <!--</el-col>-->
    <!--</el-row>-->

    <!--&lt;!&ndash;操作区域&ndash;&gt;-->
    <!--<el-row style="margin-top: 20px">-->
      <!--<el-col>-->
        <!--<el-button @click="handleAdd" type="success" icon="el-icon-plus" size="medium">新增</el-button>-->
      <!--</el-col>-->
    <!--</el-row>-->
    <!---->
    <!---->


    <!--表格区域-->
    <el-row style="margin-top: 20px">
      <el-col :span="24" align="center">
        <el-table border :data="tableData" style="width: 100%">
          <el-table-column prop="userId" label="序号" width="100" align="center"></el-table-column>
          <el-table-column prop="loginName" label="登录名" width="400" align="center"></el-table-column>
          <el-table-column prop="password" label="密码" align="center"></el-table-column>
        </el-table>
      </el-col>
    </el-row>

    <!--&lt;!&ndash;分页&ndash;&gt;-->
    <!--<el-row style="margin-top: 15px">-->
      <!--<el-col :span="24" align="center">-->
        <!--<el-pagination background layout="prev, pager, next"-->
                       <!--:total="2" :page-size="1" :current-page="1" hide-on-single-page>-->
        <!--</el-pagination>-->
      <!--</el-col>-->
    <!--</el-row>-->
  </el-card>

</template>




<script>
  import request from '@/utils/request'
  import { MessageBox, Message } from 'element-ui'
  export default {
    name: 'user-maintenance',
    // 数据区域
    data() {
      return {
        tableData: [],
        loading: false,
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
        request({
          url: "user/queryList",
          method: "get",
        }).then(res => {
          if (res.success) {
            this.tableData = res.result;
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

    }
  }
</script>

<style scoped>

</style>
