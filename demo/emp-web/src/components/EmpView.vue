<template>
  <!-- 套用HomeView.vue頁面 -->
  <home-layout />
  <div class="col-9">
    <div>
      <input type="text" v-model="emp_account" placeholder="員工帳號" />
      <input type="text" v-model="emp_password" placeholder="員工密碼" />
      <button @click="queryItem">單一查詢</button>
      <button @click="queryAllItem">查詢全部</button>
      <button @click="addViewOpen">新增</button>

      <!-- 新增功能 -->
      <add-layout v-if="isAddLayoutVisible"/>

      <edit-layout v-if="selectedItem"/>

      <!-- 修改功能 -->
      <!-- <div>
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
      </div> -->
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
</template>

<script>
import homeLayout from "@/components/HomeView.vue";
import addLayout from "@/components/AddView.vue";
import store from "../store";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { mapState } from 'vuex';
import editLayout from './EditView.vue';
// import PropsChild from '@/components/EditView.vue'
// import TodoEdit from '@/components/EditView.vue';
// import editLayout from '@/components/HomeView.vue';
// import router from "../router";

export default {
  components: {
    homeLayout,
    addLayout,
    editLayout,
  },
  data() {
    return {
      emp_account: "",
      emp_password: "",
      items: null,
      empList: [],
      isShow: true,
      selectedItem: null,
    };
  },
    computed: mapState({
    isAddLayoutVisible: state => state.isAddLayoutVisible,
    selectedItemEmpNo: state => state.isAddLayoutVisible,
    ...mapState(['selectedItemEmpNo'])
  }),
  mounted() {
    // eslint-disable-next-line no-undef
    console.log("selectedItemEmpNo=>"+this.selectedItemEmpNo);
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
    handleUpdateProperty(newValue) {
      this.isShow = newValue;
    },
    addViewOpen() {
      store.commit("updateAddLayoutVisible", true);
    },
    editItem(item) {
      this.isShow = !this.isShow;
      this.selectedItem = { ...item };
    },
    close() {
      this.isShow = !this.isShow;
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
