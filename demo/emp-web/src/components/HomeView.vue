<template>
  <div>
    <div class="sidebar">
      <ul>
        <li
          @click="selectTab('打卡')"
        >
          打卡
        </li>
        <li
          @click="selectTab('員工')"
        >
          員工
        </li>
         <li
          @click="selectTab('測試')"
        >
          測試
        </li>
        <li
          @click="selectTab('Jquery測試')"
        >
          Jquery測試
        </li>
      </ul>
    </div>
    <div class="content">
      <div v-if="activeTab === '打卡'"></div>
      <div v-if="activeTab === '員工'">員工</div>
      <div v-if="activeTab === '測試'">測試</div>
      <div v-if="activeTab === 'Jquery測試'">Jquery測試</div>
    </div>
  </div>
</template>


<script>
import router from "../router";
export default {
  data() {
    return {
      activeTab: {}, // 預設選擇的頁籤
    };
  },
  methods: {
    selectTab(tab) {
      if(tab === '打卡'){
        router.push("/checkIn");
      } else if(tab === '員工'){
        router.push("/emp");
      } else if(tab === 'Jquery測試'){
        router.push("/Function");
        // }else if(tab === '出勤紀錄'){
        // router.push("/CheckInRec");
      } else {
        router.push("/sunTest");
      }
    },
  },
};
</script>


