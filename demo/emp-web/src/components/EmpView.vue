<template>
  <!-- 套用HomeView.vue頁面 -->
  <home-layout />
  <div class="col-9">
    <div>
      <input type="text" v-model="empAccount" placeholder="員工帳號" />
      <input type="text" v-model="empPassword" placeholder="員工密碼" />
      <button @click="queryItem">單一查詢</button>
      <button @click="queryAllItem">查詢全部</button>
      <button @click="addViewOpen">新增</button>

      <!-- 新增功能 -->
      <add-layout v-if="isAddLayoutVisible" />

      <!-- 修改功能 -->
      <edit-layout v-if="selectedItem" />
    </div>
  </div>
  <div>
    <br />
    <br />
    <br />
  </div>
  <div class="col-12">
    <table class="empTable table-sm table-bordered" v-if="empList.length > 0">
      <thead>
        <tr>
          <th>員工編號</th>
          <th>員工帳號</th>
          <th>員工密碼</th>
          <th>員工姓名</th>
          <th>修改</th>
          <th>刪除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in empList" :key="item.empNo">
          <td>{{ item.empNo }}</td>
          <td>{{ item.empAccount }}</td>
          <td>{{ item.empPassword }}</td>
          <td>{{ item.chName }}</td>
          <td>
            <button @click="editItem(item)">修改</button>
          </td>
          <td><button @click="deleteItem(item.empNo)">刪除</button></td>
        </tr>
      </tbody>
    </table>
  </div>

</template>

<script>
import homeLayout from "@/components/HomeView.vue";
import addLayout from "@/components/AddView.vue";
import editLayout from "@/components/EditView.vue";
import store from "../store";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { mapState } from "vuex";

export default {
  components: {
    homeLayout,
    addLayout,
    editLayout,
  },
  data() {
    return {
      empAccount: "",
      empPassword: "",
      items: null,
      empList: [],
      isShow: true
    };
  },
  computed: {
    ...mapState({
      isAddLayoutVisible: (state) => state.isAddLayoutVisible,
      selectedItem: (state) => state.selectedItem,
    }),
  },

  methods: {
    queryItem() {
      axios
        .post("/user/query", {
          empAccount: this.empAccount,
          empPassword: this.empPassword,
        })
        .then((response) => {
          this.empList = response.data;
          //res=>vo用這個寫法
          // this.items = response.data;
          // console.log(this.items.ch_name);
          // this.empList = [];
        })
        .catch((error) => {
          console.error(error);
        });
    },
    queryAllItem() {
      axios
        .post("/user/queryAll")
        .then((response) => {
          this.empList = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    deleteItem(empNo) {
      axios
        .post("/user/delete", {
          empNo: empNo,
        })
        .then((response) => {
          alert("刪除成功");
          console.log(response.status);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    handleUpdateProperty(newValue) {
      this.isShow = newValue;
    },
    addViewOpen() {
      store.commit("updateAddLayoutVisible", true);
    },
    editItem(item) {
      this.isShow = !this.isShow;
      store.commit("updateSelectedItem", { ...item });
    },
     
  },
};
</script>

<style>
.empTable {
  margin-left: 600px;
}
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

.modal h2 {
  color: #fff;
}

.modal button {
  margin-top: 10px;
}

button {
  margin-top: 10px;
}

.testca {
  justify-content: center;
}
</style>
