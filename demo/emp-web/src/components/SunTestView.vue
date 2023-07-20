<template>
  <!-- 套用HomeView.vue頁面 -->
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
         <function-layout />
      </div>
      <div class="col-sm-4">
        <div>test</div>
      </div>
      <div class="col-sm-4">
        <div class="sidebar">

        </div>
      </div>
    </div>
  </div>
</template>

<script>


import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import functionLayout from './FunctionView.vue';

export default {
  components: {

    functionLayout,
  },
  data() {
    return {

    };
  },
  mounted() {

  },
  methods: {
   
  },
};
</script>
