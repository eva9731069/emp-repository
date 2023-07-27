<template>
  <!-- 套用HomeView.vue頁面 -->
  <div class="container">
    <div class="row">
      <div class="col-sm-4">
        <function-layout />
      </div>
      <div class="col-sm-4">
        test123
        <!-- <div><el-button @click="sendDataToServer"></div> -->
        <!-- <button @click="sendDataToServer" class="btn btn-dark">發送訊息</button> -->
        <div
          style="
            margin: 150px auto;
            width: 500px;
            box-shadow: 0 0 20px #33333350;
            border-radius: 10px;
            padding: 20px;
          "
        >
          <h2>天晴聊天室</h2>

          <div style="margin-top: 20px; text-align: left">
            <input type="text" v-model="msg" />
            <button @click="websocketsend(msg)">发送</button>
            <div v-if="bmsg">
              <ul>
                <li v-for="(item, i) in bmsg" :key="i">
                  <strong :class="{ services: item.user === '服务器' }">
                    {{item.user}}
                  </strong>
                  回复时间：{{ item.time }}
                  <div>
                    {{ item.content }}
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-4">
        <div class="sidebar"></div>
      </div>
    </div>
  </div>
</template>

<script>
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.bundle";
import functionLayout from "./FunctionView.vue";

export default {
  name: "WebSocket",
  components: {
    functionLayout,
  },
  data() {
    return {
      websock: null,
      msg: null,
      bmsg: [],
    };
  },
  mounted() {
    this.initWebSocket();
  },
  // eslint-disable-next-line vue/no-deprecated-destroyed-lifecycle
  destroyed() {
    this.websock.close(); // 离开路由断开websocket连接
  },
  methods: {
    initWebSocket() {
      // 初始化websocket
      const wsuri = "ws://localhost:8080/webSocketServer?userId=parker";
      this.websock = new WebSocket(wsuri);
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen() {
      //连接建立之后执行send方法发送数据
      let actions = { test: "12345" };
      this.websocketsend(JSON.stringify(actions));
    },
    websocketonerror() {
      //连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e) {
      //数据接收
      const redata = JSON.stringify(e.data);
      this.arrOpear(redata, "services");
    },
    websocketsend(Data) {
      //数据发送
      this.websock.send(Data);
      this.arrOpear(Data, "localhost");
    },
    websocketclose(e) {
      //关闭
      console.log("断开连接", e);
    },
    arrOpear(msg, type) {
      let _b = {
        content: msg,
        user: type === "services" ? "服务器" : "我",
        time: new Date().toLocaleTimeString(),
      };
      this.bmsg.push(_b);
    },
  },
};
</script>
