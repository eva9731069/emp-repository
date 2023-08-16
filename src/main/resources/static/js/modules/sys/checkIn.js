$(function () {
	$("#jqGrid").jqGrid({
		datatype: "local",
		colModel: [
			{ label: 'id', name: 'id', index: 'id', width: 50, hidden: true, key: true },
			{ label: '員工名稱', name: 'name', index: 'name', width: 80 },
			{ label: '上班打卡時間', name: 'orderNum', index: 'order_Num', width: 80 },
			{
				label: '下班打卡時間', name: 'status', width: 80, formatter: function (value, options, row) {
					return value === 0 ?
						'<span class="label label-danger">上班</span>' :
						'<span class="label label-success">下班</span>';
				}
			},
			// { label: '建立時間', name: 'createTime', index: 'create_time', width: 80 }
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
			keyword: null
		},
		showList: true,
		title: null,
		checkIn: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function () {
			console.log('1');
			vm.showList = false;
			vm.title = "員工打卡功能";
			vm.checkIn = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
			vm.title = "修改";

			vm.getInfo(id)
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
					console.log("data=>" + JSON.stringify(vm.checkIn));
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
			console.log('confirmCheckOut');
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
					console.log("data=>" + JSON.stringify(vm.checkIn));
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

		// },
		// del: function (event) {
		// 	var ids = getSelectedRows();
		// 	if(ids == null){
		// 		return ;
		// 	}

		// 	confirm('確定刪除此筆資料？', function(){
		// 		$.ajax({
		// 			type: "POST",
		// 		    url: baseURL + "/sys/dept/delete",
		//             contentType: "application/json",
		// 		    data: JSON.stringify(ids),
		// 		    success: function(r){
		// 				if(r.code == 0){
		// 					alert('操作成功', function(index){
		// 						$("#jqGrid").trigger("reloadGrid");
		// 					});
		// 				}else{
		// 					alert(r.msg);
		// 				}
		// 			}
		// 		});
		// 	});
		// },
		// getInfo: function(id){
		// 	$.get(baseURL + "/sys/dept/info/"+id, function(r){
		//         vm.checkIn = r.checkIn;
		//     });
		// },
		//  getDept: function(){
		//         //加載部門樹
		//         $.get(baseURL + "/sys/dept/select", function(r){
		//             ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
		//             var node = ztree.getNodeByParam("id", vm.dept.parentId);
		//             ztree.selectNode(node);
		//             vm.dept.parentName = node.name;
		//         })
		//     },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
				datatype: "json", // 設置 datatype 為 json，表示從後端獲取數據
				url: baseURL + '/sys/dept/list', // 更新 URL，改為您的後端 URL
				postData: { 'keyword': vm.q.keyword },
				page: page
			}).trigger("reloadGrid");
		}
	}
});