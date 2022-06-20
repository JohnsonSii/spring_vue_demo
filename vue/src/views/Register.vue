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
        欢迎注册
        <div style="margin: 30px"></div>
        <hr style="border: 1px solid black" />
      </div>
      <el-form ref="form" :model="form" :rules="rules">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="用户名">
            <template #prefix>
              <el-icon class="el-input__icon">
                <user />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" show-password placeholder="密码">
            <template #prefix>
              <el-icon class="el-input__icon">
                <lock />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="confirm">
          <el-input v-model="form.confirm" show-password placeholder="确认密码">
            <template #prefix>
              <el-icon class="el-input__icon">
                <lock />
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item>
          <div style="width: 100%; display: flex; flex-direction: row">
            <el-button style="flex: 1" type="primary" @click="login"
              >返回登录</el-button
            >
            <el-button style="flex: 1" type="primary" @click="register"
              >注册</el-button
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
  name: "Register",
  data() {
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
        confirm: [{ required: true, message: "请确认密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    register() {
      if (this.form.password !== this.form.confirm) {
        this.$message({
          type: "error",
          message: "两次密码输入不一致",
        });
        return;
      }
      this.$refs["form"].validate((valid) => {
        if (valid) {
          request.post("user/register", this.form).then((res) => {
            if (res.code === "0") {
              this.$message({
                type: "success",
                message: "注册成功",
              });
              this.$router.push("/login"); // 页面跳转
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
    login() {
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
</style>