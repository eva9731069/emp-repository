<template>
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <home-layout />
      </div>
      <div class="col-sm-8">
        <div>
          <div class="row">
          
            <div class="col">
              <div >員工帳號:</div>
              <input
                type="text"
                v-model="empAccount"
                class="form-control"
                placeholder="員工帳號"
                aria-label="First name"
              />
            </div>
            <div class="col">
              <div >員工密碼:</div>
              <input
                type="text"
                v-model="empPassword"
                class="form-control"
                placeholder="員工密碼"
                aria-label="Last name"
              />
            </div>
          </div>
          <div>
            <br />
          </div>
          <button @click="queryItem" class="btn btn-dark">單一查詢</button
          >&nbsp;&nbsp;
          <button @click="queryAllItem" class="btn btn-dark">查詢全部</button
          >&nbsp;&nbsp;
          <button @click="addViewOpen" class="btn btn-dark">新增</button>
          <div>
            <br />
          </div>

          <!-- 新增功能 -->
          <add-layout v-if="isAddLayoutVisible" />

          <!-- 修改功能 -->
          <edit-layout v-if="selectedItem" />

          <div class="row">
            <table
              class="empTable table table-bordered border-primary"
              v-if="empList.length > 0"
            >
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
                  <td class="center-align">{{ item.empNo }}</td>
                  <td class="center-align">{{ item.empAccount }}</td>
                  <td class="center-align">{{ item.empPassword }}</td>
                  <td class="center-align">{{ item.chName }}</td>
                  <td>
                    <button @click="editItem(item)" class="btn btn-dark">
                      修改
                    </button>
                  </td>
                  <td>
                    <button
                      @click="deleteItem(item.empNo)"
                      class="btn btn-dark"
                    >
                      刪除
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
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
      isShow: true,
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
</style>
