$(function () {
	$("#jqGrid").jqGrid({
		datatype: "local",
		colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, hidden: true, key: true },
			{ label: '員工名稱', name: 'chName', index: 'chName', width: 80 },
			{ label: '上班打卡時間', name: 'checkInTime', index: 'order_Num', width: 80 },
			{ label: '下班打卡時間', name: 'checkOutTime', index: 'order_Num', width: 80 },
			{ label: '曠職日期', name: 'missWorkDate', index: 'order_Num', width: 80 },
			{
				label: '曠職狀態', name: 'missWorkStatus', width: 80, formatter: function (value, options, row) {
					return value === 'Y' ?
						'<span class="label label-danger">曠職</span>' :
						'<span class="label label-success">未曠職</span>';
				}
			},
		],
		viewrecords: true,
		height: 385,
		rowNum: 10,
		rowList: [10, 30, 50],
		rownumbers: true,
		rownumWidth: 25,
		autowidth: true,
		multiselect: true,
		pager: "#jqGridPager",
		jsonReader: {
			root: "page.list",
			page: "page.currPage",
			total: "page.totalPage",
			records: "page.totalCount"
		},
		prmNames: {
			page: "page",
			rows: "limit",
			order: "order"
		},
		gridComplete: function () {
			//隱藏grid底部滾動條
			$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x": "hidden" });
		}
	});
});

var vm = new Vue({
	el: '#rapp',
	data: {
		q: {
			userName: null,
			startDate: null,
			endDate: null
		},
		showList: true,
		title: null,
		checkIn: {}
	},
	methods: {
		query: function (event) {
			var checkResult = vm.checkQueryParam(event.target.id);

			if (checkResult === false) {
				return;
			}
			
			if (localStorage.getItem("role") === 'emp') {
				vm.q.userName = localStorage.getItem("userName");
			}
			
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				datatype: "json", // 設置 datatype 為 json，表示從後端獲取數據
				url: baseURL + '/sys/checkIn/list', // 更新 URL，改為您的後端 URL
				postData: {
					'userName': vm.q.userName,
					'startDate': vm.q.startDate,
					'endDate': vm.q.endDate,
				},
				page: page
			}).trigger("reloadGrid");
		},
		add: function () {
			vm.showList = false;
			vm.title = "員工打卡功能";
			vm.checkIn = {};
		},
		saveOrUpdate: function (event) {
			if (vm.checkIn.status === undefined) {
				alert('請選擇上班或下班');
				return;
			}
			var url = "/sys/checkIn/save";

			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(vm.checkIn),
				success: function (r) {
					if (r.code === 0) {
						if (r.msg === "尚未超過9小時") {
							confirm('上班時間尚未超過9小時，確定要打卡嗎？', function () {
								vm.confirmCheckOut();
							});
						} else {
							alert(r.msg, function (index) {
								// vm.reload();
							});
						}
					} else {
						alert(r.msg);
					}
				}
			});
		},
		confirmCheckOut: function (event) {
			const data = {
				status: "1",
				isCheckOutConfirm: "true"
			};
			if (vm.checkIn.status === undefined) {
				alert('請選擇上班或下班');
				return;
			}
			var url = "/sys/checkIn/save";

			$.ajax({
				type: "POST",
				url: baseURL + url,
				contentType: "application/json",
				data: JSON.stringify(data),
				success: function (r) {
					if (r.code === 0) {
						alert(r.msg, function (index) {
							// vm.reload();
						});
					} else {
						alert(r.msg);
					}
				}
			});

		},
		reload: function (event) {
			//傳入參數檢查
			var checkResult = vm.checkQueryParam(event.target.id);
			
			if (checkResult === false) {
				return;
			}

			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				datatype: "json", // 設置 datatype 為 json，表示從後端獲取數據
				url: baseURL + '/sys/checkIn/list', // 更新 URL，改為您的後端 URL
				postData: {
					'userName': vm.q.userName,
					'startDate': vm.q.startDate,
					'endDate': vm.q.endDate,
				},
				page: page
			}).trigger("reloadGrid");
		},
		checkQueryParam: function (triggeredButtonId) {
			
			if (localStorage.getItem("role") === 'emp') {
				vm.q.userName = localStorage.getItem("userName");
			}

			if (triggeredButtonId !== 'back') {
				if (vm.q.userName === null || vm.q.userName.trim() === '') {
					alert('員工帳號不可為空');
					return false;
				}

				if (vm.q.startDate === null || vm.q.startDate.trim() === '') {
					alert('日期起日不可為空');
					return false;
				}

				if (vm.q.endDate === null || vm.q.endDate.trim() === '') {
					alert('日期迄日不可為空');
					return false;
				}
			}


		},
	}
});