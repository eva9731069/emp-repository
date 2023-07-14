<template>
  <div class="from modal-body">
    <div class="modal" id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <input type="text" id="emp_no" placeholder="員工編號" /><br /><br />
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
              @click="handleClick"
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
</template>


<script>
import store from "../store";

export default {
  components: {},
  data() {
    return {
      isShow: false,
      isAddLayoutVisible: true,
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
          empNo: $("#emp_no").val(),
          // eslint-disable-next-line no-undef
          empAccount: $("#emp_account").val(),
          // eslint-disable-next-line no-undef
          empPassword: $("#emp_password").val(),
          // eslint-disable-next-line no-undef
          chName: $("#ch_name").val(),
        },
        // eslint-disable-next-line no-unused-vars
        success: function (response) {
          store.commit("updateAddLayoutVisible", false);
          alert("新增成功");
        },
        error: function (error) {
          // store.commit("updateAddLayoutVisible", false);
          console.error(error);
        },
      });
    });
  },
  methods: {
    close() {
      store.commit("updateAddLayoutVisible", false);
    },
  },
};

</script>

