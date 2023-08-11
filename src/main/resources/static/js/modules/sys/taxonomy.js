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
        showList: true,
        title: null,
        type:null,
        taxonomy:{
            parentName:null,
            parentId:0,
            orderNum:0,
            type:null
        }
    },
    methods: {
        getTaxonomy: function(){
            //加載樹
            $.get(baseURL + "/sys/taxonomy/select", {type: vm.type}, function(r){
                ztree = $.fn.zTree.init($("#taxonomyTree"), setting, r.taxonomyList);
                var node = ztree.getNodeByParam("id", vm.taxonomy.parentId);
                ztree.selectNode(node);

                vm.taxonomy.parentName = node.name;
            })
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.taxonomy = {parentName:null,parentId:0,orderNum:0,type:vm.type};

            if(vm.type!=2){
                vm.getTaxonomy();
            }
        },
        update: function () {
            var taxonomyId = getTaxonomyId();
            if(taxonomyId == null){
                return ;
            }

            $.get(baseURL + "/sys/taxonomy/info/"+taxonomyId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.taxonomy = r.taxonomy;

                if(vm.type!=2){
                    vm.getTaxonomy();
                }
            });
        },
        del: function () {
            var taxonomyId = getTaxonomyId();
            if(taxonomyId == null){
                return ;
            }

            confirm('確定要刪除選中的記錄？', function(){
                $.ajax({
                    type: "POST",
                    url: baseURL + "/sys/taxonomy/delete",
                    data: "taxonomyId=" + taxonomyId,
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
            });
        },
        saveOrUpdate: function () {
            var url = vm.taxonomy.id == null ? "/sys/taxonomy/save" : "/sys/taxonomy/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.taxonomy),
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
        taxonomyTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "選擇",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#taxonomyLayer"),
                btn: ['確定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //選擇上級
                    vm.taxonomy.parentId = node[0].id;
                    vm.taxonomy.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            Taxonomy.table.refresh();
        }
    }
});

var Taxonomy = {
    id: "taxonomyTable",
    table: null,
    layerIndex: -1
};

Taxonomy.initColumn = function () {
    if(vm.type==0){
        var columns = [
            {field: 'selectItem', radio: true},
            /*{title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},*/
            {title: '名稱', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
            {title: '上級分類', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
            {title: '別名', field: 'slug', align: 'center', valign: 'middle', sortable: true, width: '160px'},
            {title: '圖標', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
                return item.icon == null ? '' : '<i class="'+item.icon+' fa-lg"></i>';
            }},
            {title: '排序號', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
        return columns;
    }else if(vm.type==1){
        var columns = [
            {field: 'selectItem', radio: true},
            /*{title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},*/
            {title: '名稱', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
            {title: '上級專題', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
            {title: '別名', field: 'slug', align: 'center', valign: 'middle', sortable: true, width: '160px'},
            {title: '圖標', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
                return item.icon == null ? '' : '<i class="'+item.icon+' fa-lg"></i>';
            }},
            {title: '排序號', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
        return columns;
    }else if(vm.type==2){
        var columns = [
            {field: 'selectItem', radio: true},
            /*{title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle', width: '80px'},*/
            {title: '名稱', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
            {title: '別名', field: 'slug', align: 'center', valign: 'middle', sortable: true, width: '160px'},
            {title: '圖標', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
                return item.icon == null ? '' : '<i class="'+item.icon+' fa-lg"></i>';
            }},
            {title: '排序號', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'}]
        return columns;
    }
};

function getTaxonomyId () {
    var selected = $('#taxonomyTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert("請選擇一條記錄");
        return;
    } else {
        return selected[0].id;
    }
}

$(function () {
    vm.type=location.href.split("?type=")[1];
    var colunms = Taxonomy.initColumn();
    var table = new TreeTable(Taxonomy.id, baseURL + "/sys/taxonomy/list?type="+vm.type, colunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("parentId");
    table.setExpandAll(false);
    table.init();
    Taxonomy.table = table;
});
