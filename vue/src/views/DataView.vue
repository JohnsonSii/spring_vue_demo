<template>
  <div style="padding: 10px">
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-input
          v-model="search"
          class="w-50 m-2"
          placeholder="请输入关键字"
          style="width: 20%"
          clearable
      >
        <template #prefix>
          <el-icon class="el-input__icon">
            <search/>
          </el-icon>
        </template>
      </el-input>
      <el-button type="primary" style="margin-left: 5px; margin-bottom: 5px" @click="load"
      >查询
      </el-button
      >
    </div>
    <div id="sheet">
      <el-table
          :data="tableData"
          border
          stripe
          style="width: 100%"
          row-style="height: 50px"
          header-row-style="height: 50px"
      >
        <el-table-column prop="id" label="ID" sortable/>
        <el-table-column prop="title" label="标题"/>
        <el-table-column prop="content" label="内容"/>
        <el-table-column fixed="right" label="操作" width="130">
          <template #default="scope">
            <el-button
                text
                size="small"
                @click="handleEdit(scope.row)"
                style="color: #409eff"
            >编辑
            </el-button
            >
            <el-popconfirm title="确认删除吗？" @confirm="handleDelete(scope.row.id)">
              <template #reference>
                <el-button text size="small" style="color: red">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-size="pageSize"
          :page-sizes="[5, 10, 20]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
      <el-dialog v-model="dialogVisible" title="新增博客" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="标题">
            <el-input v-model="form.title" style="width: 80%"/>
          </el-form-item>
          <el-form-item label="内容">
            <el-input
                type="textarea"
                :rows="15"
                v-model="form.content"
                style="width: 80%"
            />
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="save"
            >确定</el-button
            >
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import request from '../../utils/request';

export default {
  name: "DataView",
  components: {},
  data() {
    return {
      form: {},
      dialogVisible: false,
      search: "",
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
    };
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get("/data", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
    },
    save() {
      if (this.form.id) {  // 更新
        request.put("/data", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更改成功"
            })

          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
          this.dialogVisible = false

        })
      } else {  // 新增
        request.post("/data", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load()
          this.dialogVisible = false
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id){
      console.log(id)
      request.delete("/data/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()
      })
    },
    handleSizeChange(pageSize) {  // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {  // 改变当前页码触发
      this.pageNum = pageNum
      this.load()
    },
  },
};
</script>

<style>
#sheet {
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
</style>
