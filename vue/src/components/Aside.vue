<template>
  <div style="box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);">
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
            style="width: 220px; min-height: calc(100vh - 50px);"
            default-active="user"
            class="el-menu-vertical-demo"
        >
          <el-sub-menu index="1">
            <template #title>
              系统管理
            </template>
            <el-menu-item index="user" :route="{path: 'home'}" @click="tohome">用户管理</el-menu-item>
          </el-sub-menu>
          <el-menu-item index="data" :route="{path: 'data'}" @click="todata">数据管理</el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Aside",
  methods: {
    tohome() {
      this.$router.push("/home")
    },
    todata() {
      this.$router.push("/data")
    }
  }
};
</script>

<style>
</style>