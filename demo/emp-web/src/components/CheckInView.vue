<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <function-layout />
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
          <button @click="checkStatus">打卡</button>

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
import store from "../store";
import axios from "axios";
import router from "../router";
import functionLayout from './FunctionView.vue';

export default {
  components: {
    functionLayout,
  },
  data() {
    return {
      workStatus: "off", // 預設為下班狀態
      showSuccessModal: false, // 控制打卡成功視窗的顯示
    };
  },
  methods: {
    // 判斷上下班狀態
    setWorkStatus(status) {
      this.workStatus = status;
    },
    checkStatus() {
      if (this.workStatus === "on") {
        this.clockIn();
      } else if (this.workStatus === "off") {
        this.clockOut();
      }
    },

    clockIn() {
      axios
        .post("/user/checkIn", {
          empNo: store.state.empId,
          chName: store.state.empName,
        })
        .then((response) => {
          this.sessionData = response.data;

          //登入成功後跳轉頁面
          if (response.status === 200) {
            alert(this.sessionData);
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
    clockOut() {
      axios
        .post("/user/checkOut", {
          empNo: store.state.empId,
          chName: store.state.empName,
          status: false,
        })
        .then((response) => {
          this.sessionData = response.data;

          //登入成功後跳轉頁面
          if (response.status === 200) {
            if (this.sessionData === "尚未超過9小時") {
              const confirmed = confirm(this.sessionData + "確定要打卡嗎？");
              if (confirmed === true) {
                this.confirmCheckOut();
              }
            } else {
              alert(this.sessionData);
              router.push("/checkIn");
              console.warn("signup", this.emp_account, this.emp_password);
            }
          } else {
            alert("打卡失敗");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    },
    confirmCheckOut() {
      // 模擬打卡操作，這裡使用setTimeout函數模擬異步操作
      axios
        .post("/user/checkOut", {
          empNo: store.state.empId,
          chName: store.state.empName,
          status: true,
        })
        .then((response) => {
          this.sessionData = response.data;

          //登入成功後跳轉頁面
          if (response.status === 200) {
            alert(this.sessionData);
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
  },
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
