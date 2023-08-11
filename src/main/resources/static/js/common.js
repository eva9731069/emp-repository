//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

//工具集合Tools
window.T = {};

// 獲取請求參數
// 使用示例
// location.href = http://localhost/index.html?id=123
// T.p('id') --> 123;
var url = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
};
T.p = url;

//請求前綴
var baseURL = getRootPath();
// var baseURL = "http://192.168.162.1:8889/vgk/dev/admin";

function getRootPath() {
    var pathName = parent.location.pathname;
    var projectName = pathName.substring(0, pathName.substr(1).lastIndexOf('/') + 1);
    return projectName;
}

//上傳文件的映射地址
var uploadFileResource = baseURL + '/uploadFile';

//登入token
var token = localStorage.getItem("X-Token");
if (token == 'null') {
    parent.location.href = baseURL + '/login.html';
}

//jquery全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false,
    headers: {
        "X-Token": token
    },
    xhrFields: {
        withCredentials: true
    },
    complete: function (xhr) {
        //無效的token，則跳轉到登入页面
        if (xhr.responseJSON.code == 401) {
            parent.location.href = baseURL + '/login.html';
        }
    }
});

//jqgrid全局配置
$.extend($.jgrid.defaults, {
    ajaxGridOptions: {
        headers: {
            "X-Token": token
        }
    }
});

//權限判斷
function hasPermission(permission) {
    if (window.parent.permissions.indexOf(permission) > -1) {
        return true;
    } else {
        return false;
    }
}

//重寫alert
window.alert = function (msg, callback) {
    parent.layer.alert(msg, function (index) {
        parent.layer.close(index);
        if (typeof(callback) === "function") {
            callback("ok");
        }
    });
}

//重寫confirm式樣框
window.confirm = function (msg, callback) {
    parent.layer.confirm(msg, {btn: ['確定', '取消']},
        function () {//確定事件
            if (typeof(callback) === "function") {
                callback("ok");
            }
        });
}

//選擇一條記錄
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("請選取一筆資料");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只可以選取一筆資料");
        return;
    }

    return selectedIDs[0];
}

//選擇多條記錄
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("請選取一筆資料");
        return;
    }

    return grid.getGridParam("selarrrow");
}

//判斷是否為空
function isBlank(value) {
    return !value || !/\S/.test(value);
}

function openLayer(width, height, title, div) {
    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: title,
        shadeClose: false,
        area: [width, height],
        content: jQuery("#" + div)
    });
}


//選擇附件layer
function openAttachmentLayer(callback, mime_type) {
    var attachment_jqGrid = $("#attachment_jqGrid");

    layer.open({
        type: 1,
        skin: 'layui-layer-molv',
        title: '選取檔案',
        shadeClose: false,
        area: ['80%', '80%'],
        content: jQuery("#attachmentLayer"),
        btn: ['確定', '取消'],
        btn1: function (index) {
            var rowKey = attachment_jqGrid.getGridParam("selrow");
            if (!rowKey) {
                alert("請選擇一條記錄");
                return;
            }

            var rowData = attachment_jqGrid.jqGrid("getRowData", rowKey);
            var path = rowData.path;
            callback(uploadFileResource + path);

            layer.close(index);
        }
    });

    attachment_jqGrid.jqGrid({
        url: baseURL + '/sys/attachment/list',
        postData: {mime_type: mime_type},
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '標題', name: 'title', width: 100},
            {label: '地址', name: 'path', width: 100, hidden: true},
            {
                label: '縮小圖', name: 'img', index: 'path', width: 100, formatter: function (value, options, row) {
                var mime = row.mimeType;
                var path = row.path;
                if (mime.indexOf('image') >= 0) {
                    return '<img style="width: 100px;height: 100px;" src="' + uploadFileResource + path + '" >';
                } else if (mime.indexOf('audio') >= 0) {
                    return '<img style="width: 100px;height: 100px;" src="' + baseURL + '/image/audio.jpg" >';
                } else if (mime.indexOf('video') >= 0) {
                    return '<img style="width: 100px;height: 100px;" src="' + baseURL + '/image/video.jpg" >';
                } else if (mime.indexOf('application') >= 0) {
                    return '<img style="width: 100px;height: 100px;" src="' + baseURL + '/image/file.jpg" >';
                }
            }
            },
            {label: '創建時間', name: 'createTime', index: 'create_time', width: 90}
        ],
        viewrecords: true,
        height: 450,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: false,
        pager: "#attachment_jqGridPager",
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
            attachment_jqGrid.closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
}

//創建attachment-layer组件
var attachmentLayerTemplate = Vue.extend({
    template: [
        '<div id="attachmentLayer" style="display: none;">',
        '<div class="grid-btn" style="margin-top: 12px;">\n' +
        '    <div class="form-group col-sm-2">\n' +
        '    <input type="text" class="form-control" v-model="title" @keyup.enter="searchAttachment" placeholder="標題">\n' +
        '    </div>\n' +
        '    <a class="btn btn-default" @click="searchAttachment"><i class="fa fa-search">&nbsp;查询</i></a>\n' +
        '</div>',
        '<table id="attachment_jqGrid"></table>',
        '<div id="attachment_jqGridPager"></div>',
        '</div>'
    ].join(''),
    data() {
        return {
            title: ''
        }
    },
    methods: {
        searchAttachment: function () {
            $("#attachment_jqGrid").jqGrid('setGridParam', {
                postData: {'title': this.title},
                page: 1
            }).trigger("reloadGrid");
        }
    }
});

Vue.component('attachment-layer', attachmentLayerTemplate);

//文本編輯器
function initTinymce() {
    tinymce.init({
        selector: '#textarea',
        height: 365,
        language: 'zh_CN',
        menubar: false,
        automatic_uploads: true,
        paste_data_images: true,
        convert_urls: false,
        relative_urls: false,
        imagetools_toolbar: "rotateleft rotateright | flipv fliph | editimage imageoptions",
        imagetools_proxy: '',
        images_upload_url: '',
        wordcount_countregex: /[\u4e00-\u9fa5_a-zA-Z0-9]/g,
        file_picker_callback: function (callback, value, meta) {
            openAttachmentLayer(callback);
        },
        font_formats: "宋体=SimSun;新宋体=NSimSun;微软雅黑=Microsoft YaHei;华文黑体=STHeiti;楷体=KaiTi;Arial=arial,helvetica,sans-serif;Courier New=courier new,courier,monospace;AkrutiKndPadmini=Akpdmi-n",
        fontsize_formats: "12px 14px 16px 18px 20px 24px 32px",
        plugins: [
            "advlist autolink autosave link image media imagetools lists charmap print preview hr anchor pagebreak spellchecker",
            "searchreplace wordcount visualblocks visualchars code codesample fullscreen insertdatetime media nonbreaking",
            "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"],
        toolbar1: '  bold italic underline strikethrough removeformat | blockquote hr table image media codesample | anchor link   unlink | alignleft aligncenter alignright alignjustify | bullist numlist     ',
        toolbar2: '  fontselect | fontsizeselect | formatselect | outdent indent | forecolor backcolor  |  undo redo | code  fullscreen',
    });
}

//獲取文本編輯器内容
function getTinymceContent() {
    return tinymce.activeEditor.getBody().innerHTML;
}

//設置文本編輯器内容
function setTinymceContent(text) {
    tinymce.activeEditor.setContent(text);
}