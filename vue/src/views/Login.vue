<template>
  <div
    style="
      width: 100%;
      height: 100vh;
      background-color: #337ecc;
      overflow: hidden;
    "
  >
    <div
      style="
        width: 340px;
        margin: 250px auto;
        border: 1px solid white;
        padding: 50px 30px 70px;
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
      "
    >
      <div
        style="
          color: black;
          font-size: 30px;
          text-align: center;
          padding: 30px 0;
        "
      >
        欢迎登录
      </div>
      <hr style="border: 1px solid black" />
      <br />
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="用户名">
            <template #prefix>
              <el-icon class="el-input__icon"><user /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" show-password placeholder="密码">
            <template #prefix>
              <el-icon class="el-input__icon"><lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <div style="width: 100%; display: flex; flex-direction: row">
            <el-button style="flex: 1" type="primary" @click="login"
              >登录</el-button
            >
            <el-button style="flex: 1" type="primary" @click="register"
              >前往注册</el-button
            >
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";
export default {
  name: "Login",
  data() {
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    login() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          request.post("user/login", this.form).then((res) => {
            if (res.code === "0") {
              this.$message({
                type: "success",
                message: "登录成功",
              });
              this.$router.push("/home"); // 页面跳转
            } else {
              this.$message({
                type: "error",
                message: res.msg,
              });
            }
          });
        }
      });
    },
    register() {
      this.$router.push("/register");
    },
  },
};
</script>

<style scoped>
</style>