
import Vuex from 'vuex';

const store = new Vuex.Store({
  state: {
    isLoading: false,
    clickedTimes: 0,
    empId: '',
    isAddLayoutVisible: false,
    selectedItems: [],
    selectedItemEmpNo: null
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
    updateSelectedItems(state, items) {
      state.selectedItems = items;
    },
    setSelectedItemEmpNo(state, empNo) {
      state.selectedItemEmpNo = empNo;
    }
  },
  getters: {
    getAddLayoutVisible(state) {
      return state.isAddLayoutVisible;
    },
  },

})
export default store;