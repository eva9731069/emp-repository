$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/sys/user/list',
        datatype: "json",
        colModel: [			
			{ label: '使用者編號', name: 'id', index: 'id', key: true, hidden: true },
			{ label: '使用者名稱', name: 'username', width: 75 },
            { label: '暱稱', name: 'nickname', width: 75 },
			{ label: 'email', name: 'email', width: 90 },
			{ label: '手機號碼', name: 'mobile', width: 100 },
			{ label: '狀態', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 2 ?
					'<span class="label label-danger">停用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '建立時間', name: 'createTime', index: "create_time", width: 80}
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

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var ztree;

var vm = new Vue({
	el:'#rapp',
	data:{
		q:{
            keyword: null
		},
		showList: true,
		title:null,
		roleList:{},
		user:{
			status:1,
            deptId:null,
            deptName:null,
			roleIdList:[]
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roleList = {};
			vm.user = {deptName:null, deptId:null, status:1, roleIdList:[]};
			
			//獲取角色信息
			this.getRoleList();

            vm.getDept();
		},
        getDept: function(){
            //加載部門樹
            $.get(baseURL + "/sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("id", vm.user.deptId);
                if(node != null){
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            });
        },
		update: function () {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getUser(userId);
			//獲取角色信息
			this.getRoleList();
		},
		del: function () {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('確定刪除此資料？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "/sys/user/delete",
                    contentType: "application/json",
				    data: JSON.stringify(userIds),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		saveOrUpdate: function () {
			var url = vm.user.id == null ? "/sys/user/save" : "/sys/user/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.user),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功2', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		getUser: function(userId){
			$.get(baseURL + "/sys/user/info/"+userId, function(r){
				vm.user = r.user;
				vm.user.password = null;

                vm.getDept();
			});
		},
		getRoleList: function(){
			$.get(baseURL + "/sys/role/select", function(r){
				vm.roleList = r.list;
			});
		},
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "選取部門",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['確定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //選擇上級部門
                    vm.user.deptId = node[0].id;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
            });
        },
		reload: function () {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'keyword': vm.q.keyword},
                page:page
            }).trigger("reloadGrid");
		}
	}
});