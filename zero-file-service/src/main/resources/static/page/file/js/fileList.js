$(function () {
    // 初始化列表
    var table = new FileTable();
    table.Init();
})


var FileTable = function() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#fileInfoTable').bootstrapTable({
            url: CTX + 'file/getAllByPage',         //请求后台的URL（*）
            method: 'POST',                      //请求方式（*）
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 30,                       //每页的记录行数（*）
            pageList: [30, 50, 100, 200],        //可供选择的每页的行数（*）
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
//            height: $(window).height() - $(".main-header").height() -$(".main-footer").height()-$(".nav-tabs").height()-$(".panel-heading").height() - 70,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true
            }, {
                field: 'originalFileName',
                title: '文件名',
                align: 'center',
                valign: 'center'
            }, {
                field: 'path',
                title: '保存路径',
                align: 'center',
                valign: 'center'
            }, {
                field: 'creator',
                title: '上传者',
                align: 'center',
                valign: 'center'
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                valign: 'center'
            },{
                field: 'opt',
                title: '操作',
                align: 'center',
                valign: 'center',
                formatter: function(value, row) {
                    return "";
                }
            }]
        });
    }
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.limit,   //页面大小
            pageNo: params.offset  //页码
        };
        return temp;
    };
    return oTableInit;
};