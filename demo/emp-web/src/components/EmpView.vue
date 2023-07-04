<template>
  <!-- 套用HomeView.vue頁面 -->
  <home-layout />
  <div class="col-9">
    <div>
      <input type="text" v-model="emp_account" placeholder="員工帳號" />
      <input type="text" v-model="emp_password" placeholder="員工密碼" />
      <button @click="queryItem">單一查詢</button>
      <button @click="queryAllItem">查詢全部</button>
      <!-- <button @click="open">Account</button>
      <add-layout /> -->
      <button @click="add">新增</button>

      <!-- 新增功能 -->
      <div class="from modal-body" v-show="!isShow">
        <div class="modal" id="myModal">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-body">
                <input
                  type="text"
                  id="emp_no"
                  placeholder="員工編號"
                /><br /><br />
                <input
                  type="text"
                  id="emp_account"
                  placeholder="員工帳號"
                /><br /><br />
                <input
                  type="text"
                  id="emp_password"
                  placeholder="員工密碼"
                /><br /><br />
                <input
                  type="text"
                  id="ch_name"
                  placeholder="員工姓名"
                /><br /><br />
              </div>
              <div class="modal-footer">
                <button
                  id="addItem"
                  @click="close"
                  type="button"
                  class="btn btn-danger"
                  data-bs-dismiss="modal"
                >
                  確認
                </button>
                <button
                  @click="close"
                  type="button"
                  class="btn btn-danger"
                  data-bs-dismiss="modal"
                >
                  Close
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 修改功能 -->
      <div>
        <div v-if="selectedItem">
          <div class="from modal-body" v-show="!isShow">
            <div class="modal" id="myModal">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-body">
                    <input
                      type="text"
                      v-model="selectedItem.emp_no"
                      disabled
                      placeholder="員工編號"
                    /><br /><br />
                    <input
                      type="text"
                      v-model="selectedItem.emp_account"
                      disabled
                      placeholder="員工帳號"
                    /><br /><br />
                    <input
                      type="text"
                      v-model="selectedItem.emp_password"
                      placeholder="員工密碼"
                    /><br /><br />
                    <input
                      type="text"
                      v-model="selectedItem.ch_name"
                      placeholder="員工姓名"
                    /><br /><br />
                  </div>
                  <div class="modal-footer">
                    <button
                      id="saveItem"
                      @click="saveItem(selectedItem)"
                      type="button"
                      class="btn btn-danger"
                      data-bs-dismiss="modal"
                    >
                      確認
                    </button>
                    <button
                      @click="close"
                      type="button"
                      class="btn btn-danger"
                      data-bs-dismiss="modal"
                    >
                      Close
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
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
        <tr v-for="item in empList" :key="item.emp_no">
          <td>{{ item.emp_no }}</td>
          <td>{{ item.emp_account }}</td>
          <td>{{ item.emp_password }}</td>
          <td>{{ item.ch_name }}</td>
          <td>
            <button @click="editItem(item)">修改</button>
          </td>
          <td><button @click="deleteItem(item.emp_no)">刪除</button></td>
        </tr>
      </tbody>
    </table>
  </div>

<div id="app" >
  <button v-on:click="fetchData">获取数据</button>
  <ul>
    <li v-for="item in itemsTest" :key="item.emp_no">{{ item.emp_no }}</li>
  </ul>
</div>

</template>

<script>
import homeLayout from "@/components/HomeView.vue";
// import store from "../store";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
// import PropsChild from '@/components/EditView.vue'
// import TodoEdit from '@/components/EditView.vue';
// import editLayout from '@/components/HomeView.vue';
// import router from "../router";

export default {
  components: {
    homeLayout,
    // PropsChild
  },
  el: '#app',
  data() {
    return {
      emp_account: "",
      emp_password: "",
      items: null,
      empList: [],
      isShow: true,
      isUpdate: false,
      selectedItem: null,
      itemsTest: []
      // contentData:'我是父组件的数据********'
    };
  },
  mounted() {
    // eslint-disable-next-line no-undef
    $("#addItem").click(function () {
      // eslint-disable-next-line no-undef
      $.ajax({
        url: "/user/add",
        method: "POST",
        data: {
          // eslint-disable-next-line no-undef
          emp_no: $("#emp_no").val(),
          // eslint-disable-next-line no-undef
          emp_account: $("#emp_account").val(),
          // eslint-disable-next-line no-undef
          emp_password: $("#emp_password").val(),
          // eslint-disable-next-line no-undef
          ch_name: $("#ch_name").val(),
        },
        // eslint-disable-next-line no-unused-vars
        success: function (response) {
          alert("新增成功");
        },
        error: function (error) {
          console.error(error);
        },
      });
    });
  },
  methods: {
    queryItem() {
      axios
        .post("/user/query", {
          emp_account: this.emp_account,
          emp_password: this.emp_password,
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
    
    deleteItem(emp_no) {
      axios
        .post("/user/delete", {
          emp_no: emp_no,
        })
        .then((response) => {
          alert("刪除成功");
          console.log(response.status);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    close() {
      this.isShow = !this.isShow;
    },
    add() {
      this.isShow = !this.isShow;
    },
    editItem(item) {
      this.isShow = !this.isShow;
      this.selectedItem = { ...item };
    },
    saveItem(selectedItem) {
      axios
        .post("/user/edit", {
          emp_no: selectedItem.emp_no,
          emp_password: selectedItem.emp_password,
          ch_name: selectedItem.ch_name,
        })
        .then((response) => {
          alert("修改成功");
          console.log(response.status);
        })
        .catch((error) => {
          console.error(error);
        });

      this.isShow = !this.isShow;
    },
    fetchData() {
      // eslint-disable-next-line no-undef
      $.ajax({
        url: '/user/queryAll',  // 指定服务器端API的URL
        method: 'POST',
        success: (response) => {
          this.itemsTest = response;  // 将从服务器获取的数据赋值给items数组
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
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
