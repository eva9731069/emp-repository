<template>
  <div class="from modal-body">
    <div class="modal" id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <input
              type="text"
              id="emp_no"
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
              id="ch_name"
              v-model="selectedItem.ch_name"
              placeholder="員工姓名"
            /><br /><br />
          </div>
          <div class="modal-footer">
            <button
              id="EditItem"
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
import { mapState } from "vuex";

export default {
  components: {},
  data() {
    return {
      isShow: false,
      isAddLayoutVisible: true,
    };
  },
  computed: {
    ...mapState({
      selectedItem: (state) => state.selectedItem,
    }),
  },
  mounted() {
    // eslint-disable-next-line no-undef
     console.log("emp_password=>" + $("#emp_password").val());
    // eslint-disable-next-line no-undef
    $("#EditItem").click(function () {
      // eslint-disable-next-line no-undef
      $.ajax({
        url: "/user/editJquery",
        method: "POST",
        data: {
          // eslint-disable-next-line no-undef
          emp_no: $("#emp_no").val(),
          // eslint-disable-next-line no-undef
          emp_password: $("#emp_password").val(),
          // eslint-disable-next-line no-undef
          ch_name: $("#ch_name").val(),
        },
        // eslint-disable-next-line no-unused-vars
        success: function (response) {
          store.commit("updateSelectedItem", null);
          alert("修改成功");
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
      store.commit("updateSelectedItem", null);
    },
  },
};
</script>

