<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <function-layout />
      </div>
      <div class="col-sm-8">
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
          <editJquery-layout v-if="selectedItem" />
        </div>
      </div>
    </div>

    <table v-if="daysList.length > 0">
      <tr>
        <!-- 顯示結果 項目/023-07-01/023-07-02 -->
        <th v-for="item in daysList" :key="item.index">{{ item }}</th>
        <th>{{ item }}</th>
      </tr>
      <!-- 顯示結果 審查/▉/⬤ -->
      <td v-for="item in todoList" :key="item.index">{{ item }}</td>
    </table>

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
          <td id="emp_no">{{ item.emp_no }}</td>
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
  <!-- 套用HomeView.vue頁面 -->

  <!-- <ul>
    <li v-for="item in itemsTest" :key="item.emp_no">{{ item.emp_no }}</li>
  </ul> -->
</template>

<script>
import addLayout from "@/components/AddView.vue";
import editJqueryLayout from "@/components/EditJqueryView.vue";
import store from "../store";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { mapState } from "vuex";
import functionLayout from "./FunctionView.vue";

export default {
  components: {
    addLayout,
    editJqueryLayout,
    functionLayout,
  },
  data() {
    return {
      emp_account: "",
      emp_password: "",
      items: null,
      empList: [],
      daysList: [],
      todoList: [],
      itemsList: [],
      statusList: [],
      isShow: true,
      itemsTest: [],
    };
  },
  mounted() {
    //  this.setStatusList();
    this.setdayList();
    this.setTodoList();
    this.setItemsList();
  },
  computed: {
    ...mapState({
      isAddLayoutVisible: (state) => state.isAddLayoutVisible,
      selectedItem: (state) => state.selectedItem,
    }),
  },
  methods: {
    // setStatusList() {
    //   this.itemsList.push("報告製作");
    //   this.itemsList.push("審查");
    //   //  this.daysList.push('023-07-03');
    // },
    setItemsList() {
      this.itemsList.push("報告製作");
      this.itemsList.push("審查");
      //  this.daysList.push('023-07-03');
    },
    setdayList() {
    this.daysList.push("項目");
      this.daysList.push("023-07-01");
      this.daysList.push("023-07-02");
      //  this.daysList.push('023-07-03');
    },
    setTodoList() {
      this.todoList.push("審查");
      this.todoList.push("▉");
      this.todoList.push("⬤");
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
    queryAllItem() {
      // eslint-disable-next-line no-undef
      $.ajax({
        url: "/user/queryAll", // 指定服务器端API的URL
        method: "POST",
        success: (response) => {
          this.itemsTest = response; // 将从服务器获取的数据赋值给items数组
          console.log("this.itemsTest=>" + this.itemsTest);
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

</style>
