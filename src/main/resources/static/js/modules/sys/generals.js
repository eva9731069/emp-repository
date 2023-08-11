$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/sys/generals/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'ID', index: 'id', width: 50, key: true },
			{ label: '', name: '名稱', index: 'name', width: 80 },
			{ label: '', name: '圖片', index: 'image', width: 80 },
			{ label: '', name: 'PC端圖片', index: 'pc_image', width: 80 },
			{ label: '', name: '影片連結', index: 'video', width: 80 },
			{ label: '', name: '預覽圖片', index: 'preview_image', width: 80 },
			{ label: '', name: '預覽連結', index: 'preview_video', width: 80 },
			{ label: '', name: '創建時間', index: 'create_time', width: 80 },
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隱藏grid底部滾動條
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rapp',
	data:{
		showList: true,
		title: null,
		generals: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.generals = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
			var url = vm.generals.id == null ? "/sys/generals/save" : "/sys/generals/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.generals),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('確定要刪除選中的記錄？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "/sys/generals/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "/sys/generals/info/"+id, function(r){
                vm.generals = r.generals;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});