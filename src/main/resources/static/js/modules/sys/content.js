$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + '/sys/content/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', key: true, hidden: true },
			{ label: '標題', name: 'title', width: 120 },
            { label: '分類', name: 'categorys', width: 100 },
            { label: '專題', name: 'features', width: 100 },
            { label: '標籤', name: 'tags', width: 100 },
			{ label: '創建時間', name: 'createTime',index: 'create_time', width: 90 }
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

    initTinymce();

    $("#contentTags").tagEditor();
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
    },
    check:{
        enable:true,
        chkboxType: { "Y": "", "N": "" },
        nocheckInherit:true
    }
};
var categoryZtree;
var featureZtree;

var vm = new Vue({
	el:'#rapp',
	data:{
        q:{
            keyword: null
        },
		showList: true,
		title: null,
		content: {
            thumbnail: null,
            orderNum: null,
            tagNames:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.content = {
                thumbnail:null,
                orderNum:0,
                tagNames:null
			};
            setTinymceContent('');
            //獲取分類、專題樹
            this.getCategoryTree();
            this.getFeatureTree();

            //清空tagEditor數據
            $(".tag-editor li:not(:first)").remove();
		},
		update: function () {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id);

            //清空tagEditor數據
            $(".tag-editor li:not(:first)").remove();
		},
		saveOrUpdate: function () {
		    //獲取編輯器内容
            vm.content.text=getTinymceContent();

            //獲取標籤
            vm.content.tagNames=$("#contentTags").tagEditor('getTags')[0].tags;

            //獲取選擇的分類、專題
            var taxonomyIdList = new Array();
            var categoryNodes = categoryZtree.getCheckedNodes(true);
            for(var i=0; i<categoryNodes.length; i++) {
                taxonomyIdList.push(categoryNodes[i].id);
            }
            var featureNodes = featureZtree.getCheckedNodes(true);
            for(var i=0; i<featureNodes.length; i++) {
                taxonomyIdList.push(featureNodes[i].id);
            }
            vm.content.taxonomyIdList = taxonomyIdList;

			var url = vm.content.id == null ? "/sys/content/save" : "/sys/content/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.content),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function () {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('確定要刪除選中的記錄？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "/sys/content/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
                                vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "/sys/content/info/"+id, function(r){
                vm.content = r.content;

                setTinymceContent(r.content.text);

                //勾選内容所擁有的分類、專題
                var taxonomyIds = vm.content.taxonomyIdList;
                vm.getCategoryTree(vm.getCategoryTreeCallback, taxonomyIds);
                vm.getFeatureTree(vm.getFeatureTreeCallback, taxonomyIds);

                //設置標籤
                var contentTags=vm.content.tagNames;
                if(contentTags){
                    for(var i=0; i<contentTags.length; i++) {
                        $("#contentTags").tagEditor('addTag', contentTags[i]);
                    }
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
		},
        openAttachmentLayer: function () {
            openAttachmentLayer(vm.selectAttachment, 'image');
        },
        selectAttachment: function (data) {
			vm.content.thumbnail=data;
        },
        removeAttachment: function () {
            vm.content.thumbnail=null;
        },
		getCategoryTree: function (callback, taxonomyIds) {
            //加載分類樹
            $.get(baseURL + "/sys/taxonomy/selectByType", {type:0}, function(r){
                categoryZtree = $.fn.zTree.init($("#categoryTree"), setting, r);
                //展開所有節點
                categoryZtree.expandAll(true);

                if(callback){
                    callback(taxonomyIds);
                }
            });
        },
        getCategoryTreeCallback: function (taxonomyIds) {
            if(taxonomyIds){
                for(var i=0; i<taxonomyIds.length; i++) {
                    var categoryNode = categoryZtree.getNodeByParam("id", taxonomyIds[i]);
                    if(categoryNode){
                        categoryZtree.checkNode(categoryNode, true, false);
                    }
                }
            }
        },
        getFeatureTree: function (callback, taxonomyIds) {
            //加載分類樹
            $.get(baseURL + "/sys/taxonomy/selectByType", {type:1}, function(r){
                featureZtree = $.fn.zTree.init($("#featureTree"), setting, r);
                //展開所有節點
                featureZtree.expandAll(true);

                if(callback){
                    callback(taxonomyIds);
                }
            });
        },
        getFeatureTreeCallback: function (taxonomyIds) {
            if(taxonomyIds){
                for(var i=0; i<taxonomyIds.length; i++) {
                    var featureNode = featureZtree.getNodeByParam("id", taxonomyIds[i]);
                    if (featureNode){
                        featureZtree.checkNode(featureNode, true, false);
                    }
                }
            }
        }
	}
});