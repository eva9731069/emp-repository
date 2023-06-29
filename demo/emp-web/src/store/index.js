
import Vuex from 'vuex';

const store = new Vuex.Store({
  state: {
    isLoading: false,
    clickedTimes: 0,
    empId: '',
    isAddLayoutVisible: false
  },
  mutations: {
    Loaded(state) {
      state.isLoading = !state.isLoading
    },
    setEmpId(state, empId) {
      state.empId = empId;
    },
    updateAddLayoutVisible(state, newValue) {
      state.isAddLayoutVisible = newValue;
    },
  },
  getters: {
    getAddLayoutVisible(state) {
      return state.isAddLayoutVisible;
    },
  },

})
export default store;