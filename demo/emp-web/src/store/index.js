
import Vuex from 'vuex';

const store = new Vuex.Store({
  state: {
    isLoading: false,
    clickedTimes: 0,
    empId: '',
    empName: '',
    auth:'',
    absence:'',
    leaveEarly:'',
    holidayType:'',
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
    setAuth(state, newValue) {
      state.auth = newValue;
    },
    setAbsence(state, newValue) {
      state.absence = newValue;
    },
    setLeaveEarly(state, newValue) {
      state.leaveEarly = newValue;
    },
    setHolidayType(state, newValue) {
      state.holidayType = newValue;
    },
  },
  getters: {
    getAddLayoutVisible(state) {
      return state.isAddLayoutVisible;
    },
  },

})
export default store;