<template>
  <!-- 套用HomeView.vue頁面 -->
  <home-layout />
  <div >
    33333
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
  data() {
    return {
      emp_account: "",
      emp_password: "",
      items: null,
      empList: [],
      isShow: true,
      isUpdate: false,
      selectedItem: null,
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
