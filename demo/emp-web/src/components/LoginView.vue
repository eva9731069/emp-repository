<template>
  <img class="logo" src="../assets/pixel.png" />
  <h1>Sign up</h1>
  <div class="register">
    <input type="text" v-model="empAccount" placeholder="Enter Name" />
    <input
      type="password"
      v-model="empPassword"
      placeholder="Enter Password"
    />
    <button v-on:click="signUp">Sign Up</button>
  </div>
</template>

<script>
import axios from "axios";
import router from "../router";
import store from "../store";

export default {
  name: "SignUp",
  data() {
    return {
      empAccount: "",
      empPassword: "",
    };
  },
  methods: {
    async signUp() {
       axios
        .post("/user/login", {
          empAccount: this.empAccount,
          empPassword: this.empPassword,
        })
        .then((response) => {
          this.sessionData = response.data;

          // alert(response.data.emp_no);//取得後端的值

          //登入成功後跳轉頁面
          if (this.sessionData != "") {
            router.push("/home");
            console.warn("signup", this.emp_account, this.emp_password);
            // store.commit(`auth/${SET_USERNAME}`, '55555');

            store.commit("setEmpId", response.data.emp_no);
            store.commit("setEmpName", response.data.ch_name);
            console.log("GET_USERNAME=>" + store.state.empId);
          } else {
            alert("登入失敗");
          }
        })
        .catch((error) => {
          console.error(error);
        });

      // eslint-disable-next-line no-unused-vars
      // let result = await axios.post("/user/login", {
      //   emp_account: this.emp_account,
      //   emp_password: this.emp_password,
      // });

      // alert(result.status); //200
      // alert("sucess");
      // alert(this.httpsession.geta);
      // alert(JSON.stringify(result.data));
      // router.push("/home");
      // console.warn("signup", this.emp_account, this.emp_password);

      // //登入成功後跳轉頁面
      // if (sessionStorage) {
      //   router.push("/home");
      //   console.warn("signup", this.emp_account, this.emp_password);
      // } else {
      //   alert('登入失敗');
      // }
    },
  },
};
</script>

<style>
.logo {
  width: 200px;
}

.register input {
  width: 600px;
}
</style>