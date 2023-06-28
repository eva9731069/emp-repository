<template>
  <!-- 套用HomeView.vue頁面 -->
  <home-layout />
  <div>test</div>
</template>

<script>
import homeLayout from "@/components/HomeView.vue";

export default {
  components: {
    homeLayout,
  },
};
</script>
