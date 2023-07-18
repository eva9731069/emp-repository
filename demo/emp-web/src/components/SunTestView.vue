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
        <div class="sidebar">
          <ul>
            <li v-for="item in list" @click="selectTab(item)" :key="item.empNo">
              {{ item }}
            </li>
          </ul>
        </div>
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
    homeLayout,
  },
  data() {
    return {
      // isAddLayoutVisible: false,
      list: ["要", "点击", "的", "列表项"],
      selectedTab: "",
    };
  },
  mounted() {
    this.getFunctionList();
  },
  methods: {
    selectTab(tab) {
      this.selectedTab = tab;
      // 执行你的其他操作...
    },
    getFunctionList(tab) {
      this.selectedTab = tab;
      // 执行你的其他操作...
    },
  },
};
</script>
