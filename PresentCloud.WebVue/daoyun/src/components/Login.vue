<template>
  <div class="login-wrap">
    <el-form
      label-position="top"
      :rules="rules"
      ref="ruleForm"
      :model="formData"
      label-width="80px">
      <h2>用户登录</h2>
      <el-form-item label="账号|手机号" prop="account">
        <el-input v-model="formData.account"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="formData.password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="login-btn" type="primary" @click="login">登录</el-button>
      </el-form-item>
      <el-form-item>
        <div class="Register"><a href="#/Register">注册</a><a href="#/Forget">忘记密码</a></div>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      formData: {
        account: '',
        password: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' },
          { min: 5, max: 12, message: '长度必须是5到12个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 11, message: '长度在 6 到 11 个字符', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    async login() {
      // 表单验证
      this.$refs.ruleForm.validate(async (valid) => {
        if (!valid) {
          return;
        }
        const res = await this.$http.post('/login', this.formData);
        const data = res.data;
        // console.log(res);
        if (data.meta.status === 200) {
          localStorage.setItem('uid', data.data['id']);
          localStorage.setItem('token', data.data['token']);
          this.$message({
            type: 'success',
            message: '登录成功!'
          });
          this.$router.push({
            name: data.data['pathName'],
            params:{
              'name':data.data['name'],
              'avatar':data.data['avatar']
            }
          });
        } else {
          // 登录失败，返回失败的原因
          this.$message({
            type: 'error',
            message: data.meta.msg
          });
        }
      });
    }
  }
};
</script>

<style scoped>
  .login-wrap{
    background-image: url('../assets/images/login.jpg');
    background-repeat: no-repeat;
    background-size: 100% 100%;
  }
  .login-wrap {
    background-color: #324152;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .el-form.el-form--label-top {
    padding: 40px;
    width: 500px;
    border-radius: 5px;
    background-color: #fff;
  }
  .el-form .login-btn {
    width: 100%;
  }
  .Register{
    padding: 10px;
  }
  .Register>a{
    padding: 0 10px;
  }
</style>
