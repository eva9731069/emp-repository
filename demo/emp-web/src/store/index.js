
import Vuex from 'vuex';

const store = new Vuex.Store({
  state: {
    isLoading: false,
    clickedTimes: 0,
    empId: '',
  },
  mutations: {
    Loaded(state) {
      state.isLoading = !state.isLoading
    },
    setEmpId(state, empId) {
      state.empId = empId;
    },
  }

})
export default store;