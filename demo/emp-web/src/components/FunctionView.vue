<template>
  <div class="sidebar">
    <ul>
      <li 
        v-for="item in functionList"
        @click="selectTab(item)"
        :key="item.index"
      >
        {{ item }}
      </li>
    </ul>
  </div>
</template>


<script>

import store from "../store";
import axios from "axios";
import router from "../router";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";

export default {
  components: {
   
  },
  data() {
    return {
      functionList: [],
    };
  },
  mounted() {
    this.getLoginFunction();
  },
  methods: {
    selectTab(tab) {
      if(tab === '打卡'){
        router.push("/checkIn");
      } else if(tab === '員工'){
        router.push("/emp");
      } else if(tab === 'Jquery測試'){
        router.push("/jqueryTest");
        } else if(tab === '出勤紀錄'){
        router.push("/CheckInRec");
      }else if(tab === '薪資查詢'){
        router.push("/SalaryQuery"); 
      }else if(tab === '薪資管理'){
        router.push("/SalaryManage");
      }else {
        router.push("/sunTest");
      }
    },
    getLoginFunction() {
      axios
        .post("/user/getUserFunction", {
          empNo: store.state.empId
        })
        .then((response) => {
          this.sessionData = response.data;
          //登入成功後跳轉頁面
          if (response.status === 200) {
            this.functionList = response.data;
          } else {
            alert("取得功能清單失敗");
          }
        })
        .catch((error) => {
          alert('身分驗證失敗，請重新登入');
          router.push("/");
          console.error(error);
        });
    },
  },
};
</script>

<style>
.sidebar {
  float: left;
  width: 20%;
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
}

.sidebar li {
  cursor: pointer;
  padding: 10px;
}

</style>