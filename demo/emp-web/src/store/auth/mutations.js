import Vue from 'vue';
import Vuex from 'vuex';



const store = new Vuex.Store({
    state: {
        isLoading: false,
    },
    mutations: {
        Loaded(state) {
            state.isLoading = !state.isLoading

            // 這樣每執行一次都會設定一次
            Vue.set(state, 'clicked', false);
        }

    }
})
export default store;