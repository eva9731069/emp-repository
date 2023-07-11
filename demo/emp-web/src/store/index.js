
import Vuex from 'vuex';

const store = new Vuex.Store({
  state: {
    isLoading: false,
    clickedTimes: 0,
    empId: '',
    empName: '',
    isAddLayoutVisible: false,
    selectedItem: null
  },
  mutations: {
    Loaded(state) {
      state.isLoading = !state.isLoading
    },
    setEmpId(state, empId) {
      state.empId = empId;
    },
    setEmpName(state, empName) {
      state.empName = empName;
    },
    updateAddLayoutVisible(state, newValue) {
      state.isAddLayoutVisible = newValue;
    },
    updateSelectedItem(state, newValue) {
      state.selectedItem = newValue;
    },
  },
  getters: {
    getAddLayoutVisible(state) {
      return state.isAddLayoutVisible;
    },
  },

})
export default store;