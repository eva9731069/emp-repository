<template>

<div class="container">
    <div class="row">
      <div class="col-sm-4">
        <home-layout />
      </div>
      <div class="col-sm-8">
       <div>
    <label>
      <input type="radio" value="off" v-model="workStatus" />
      下班
    </label>
    <label>
      <input type="radio" value="on" v-model="workStatus" />
      上班
    </label>
    <button @click="clockIn">打卡</button>

    <div v-if="showSuccessModal" class="modal">
      <div class="modal-content">
        <h2>打卡成功</h2>
        <button @click="closeModal">關閉</button>
      </div>
    </div>
  </div>
      </div>
    </div>
  </div>

</template>

<script>
import homeLayout from "@/components/HomeView.vue";
import store from "../store";
import axios from "axios";
import router from "../router";


export default {
  components: {
    homeLayout,
  },
  data() {
    return {
      workStatus: "off", // 預設為下班狀態
      showSuccessModal: false, // 控制打卡成功視窗的顯示
    };
  },
  methods: {
    setWorkStatus(status) {
      this.workStatus = status;
    },
    clockIn() {
    
    console.log("GET_USERNAME=>"+store.state.empId);
      // 模擬打卡操作，這裡使用setTimeout函數模擬異步操作
      axios
        .post("/user/checkIn", {
          // emp_account: this.emp_account,
          // emp_password: this.emp_password,
          emp_no: store.state.empId,
          ch_name: store.state.empName
        })
        .then((response) => {
          this.sessionData = response.data;

          //登入成功後跳轉頁面
          if (response.status === 200) {
            alert("打卡成功");
            router.push("/checkIn");
            console.warn("signup", this.emp_account, this.emp_password);
          } else {
            alert("打卡失敗");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    closeModal() {
      this.showSuccessModal = false;
    },
  },
  // watch: {
  //   workStatus(newStatus) {
  //     // 監聽workStatus的變化，當選擇「上班」或「下班」時，自動顯示打卡成功視窗
  //     if (newStatus === "on" || newStatus === "off") {
  //       this.clockIn();
  //     }
  //   },
  // },
};
</script>

<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 20px;
  text-align: center;
}

button {
  margin-top: 10px;
}
</style>
