import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'bootstrap/js/dist/modal.js'
import 'bootstrap/dist/js/bootstrap.js';


const app = createApp(App)
app.use(store)
app.use(router)
app.mount('#app')


