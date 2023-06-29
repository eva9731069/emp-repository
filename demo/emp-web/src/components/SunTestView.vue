<template>
  <!-- 套用HomeView.vue頁面 -->
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <home-layout />
      </div>
      <div class="col-sm-4">
       <div>test</div>
      </div>
      <div class="col-sm-4">
        <!-- 右邊欄位 -->
       <div>test</div>
      </div>
    </div>
  </div>
</template>

<script>
import homeLayout from "@/components/HomeView.vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";


export default {
  components: {
    homeLayout
  },
  data() {
    return {
      // isAddLayoutVisible: false,
    };
  },

};
</script>
