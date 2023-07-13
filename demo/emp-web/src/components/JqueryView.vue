<template>
  <!-- 套用HomeView.vue頁面 -->
  <home-layout />
  <div class="col-9">
    <div>
      <input
        id="emp_account"
        type="text"
        v-model="emp_account"
        placeholder="員工帳號"
      />
      <input
        id="emp_password"
        type="text"
        v-model="emp_password"
        placeholder="員工密碼"
      />
      <button v-on:click="queryOne">單一查詢</button>

      <button v-on:click="queryAllItem">查詢全部</button>
      <button @click="addViewOpen">新增</button>

      <!-- 新增功能 -->
      <add-layout v-if="isAddLayoutVisible" />

      <!-- 修改功能 -->
      <!-- <edit-layout v-if="selectedItem" /> -->
      <!-- 修改功能 -->
      <editJquery-layout v-if="selectedItem"/>
    </div>
  </div>
  <div>
    <br />
    <br />
    <br />
  </div>

  <div class="col-12">
    <!-- <ul>
    <li v-for="item in itemsTest" :key="item.emp_no">{{ item.emp_no }}</li>
  </ul> -->
    <table class="empTable table-sm table-bordered" v-if="itemsTest.length > 0">
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
        <tr v-for="item in itemsTest" :key="item.emp_no">
          <td  id="emp_no">{{ item.emp_no }}</td>
          <td>{{ item.emp_account }}</td>
          <td>{{ item.emp_password }}</td>
          <td>{{ item.ch_name }}</td>
          <td>
            <button @click="editItem(item)">修改</button>
          </td>
          <td><button @click="deleteJquery(emp_no)">刪除</button></td>
        </tr>

      </tbody>
    </table>
  </div>
</template>

<script>
import homeLayout from "@/components/HomeView.vue";
import addLayout from "@/components/AddView.vue";
import editJqueryLayout from "@/components/EditJqueryView.vue";
import store from "../store";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { mapState } from "vuex";

export default {
  components: {
    homeLayout,
    addLayout,
    editJqueryLayout,
  },
  data() {
    return {
      emp_account: "",
      emp_password: "",
      items: null,
      empList: [],
      isShow: true,
      itemsTest: [],
    };
  },
  mounted() {
    // const self = this; // 缓存Vue组件的this
    // eslint-disable-next-line no-undef
    // $("#queryOne").click(function () {
    //   // eslint-disable-next-line no-undef
    //   $.ajax({
    //     url: "/user/jqueryQuery",
    //     method: "POST",
    //     // contentType: "application/json",
    //     data: {
    //       // eslint-disable-next-line no-undef
    //       emp_account: $("#emp_account").val(),
    //       // eslint-disable-next-line no-undef
    //       emp_password: $("#emp_password").val(),
    //     },
    //     success: (response) => {
    //       // console.log('itemsTest->'+response.empList);
    //        console.log('itemsTest->'+response);
    //       this.itemsTest = response;

    //     },
    //     // success: (response) => {
    //     //   console.log(response);
    //     //   self.itemsTest = response;
    //     // },
    //     error: (error) => {
    //       console.log(error);
    //     },
    //   });
    // });
  },
  computed: {
    ...mapState({
      isAddLayoutVisible: (state) => state.isAddLayoutVisible,
      selectedItem: (state) => state.selectedItem,
    }),
  },
  methods: {
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
    queryAllItem() {
      // eslint-disable-next-line no-undef
      $.ajax({
        url: "/user/queryAll", // 指定服务器端API的URL
        method: "POST",
        success: (response) => {
          this.itemsTest = response; // 将从服务器获取的数据赋值给items数组
          console.log('this.itemsTest=>'+this.itemsTest);
        },
        error: (error) => {
          console.log(error);
        },
      });
    },
    queryOne() {
      // eslint-disable-next-line no-undef
      $.ajax({
        url: "/user/jqueryQuery",
        method: "POST",
        data: {
          // eslint-disable-next-line no-undef
          emp_account: $("#emp_account").val(),
          // eslint-disable-next-line no-undef
          emp_password: $("#emp_password").val(),
        },
        success: (response) => {
          this.itemsTest = response;
        },
        error: (error) => {
          console.log(error);
        },
      });
    },
    deleteJquery(emp_no) {
       // eslint-disable-next-line no-undef
       $.ajax({
        url: "/user/delete",
        method: "POST",
        data: {
          // eslint-disable-next-line no-undef
          emp_no: emp_no,
        },
        success: (response) => {
           alert("刪除成功");
           console.log(response.status);
        },
        error: (error) => {
          console.log(error);
        },
      });
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
