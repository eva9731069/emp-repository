<template>
<div class="from modal-body">
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
</template>


<script>
import store from "../store";
import { mapState } from 'vuex';
import axios from "axios";

export default {
  components: {},
  data() {
    return {
    
    };
  },
  computed: {
  ...mapState({
     selectedItem: state => state.selectedItem,
  })
},
  mounted() {
    // eslint-disable-next-line no-undef

  },
  methods: {
    close() {
      // store.commit("updateAddLayoutVisible", false);
      store.commit("updateSelectedItem", null);
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

      store.commit("updateSelectedItem", null);
    },
  },
};

</script>

