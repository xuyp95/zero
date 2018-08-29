$(function() {
    var roleTable = new RoleTable();
    roleTable.Init();
})

var RoleTable = function() {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#authRoleTable').bootstrapTable({
            url: CTX + 'role/queryPage',         //请求后台的URL（*）
            method: 'POST',                      //请求方式（*）
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            // toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            showColumns: false,                  //是否显示所有的列
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
            height: $(window).height() - $(".nav-tabs").height() -$(".main-footer").height()-$("#role-toolbar").height()-$("#rolePanel").height() - $(".navbar-static-top").height() - 80,   //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns: [{
                checkbox: true,
                width: "10%"
            }, {
                field: 'name',
                title: '角色名称',
                align: 'center',
                valign: 'middle',
                width: "20%"
            }, {
                field: 'description',
                title: '角色描述',
                align: 'center',
                valign: 'middle',
                width: "20%"
            }, {
                field: 'updateTime',
                title: '更新时间',
                align: 'center',
                valign: 'middle',
                width: "30%"
            },{
                field: 'opt',
                title: '操作',
                align: 'center',
                formatter: function(value, row) {
                    var str = "";
                    str += "<a href='#' onclick='editRole(\"" + row.id + "\")' title='编辑'>编辑</a>";
                    str += " | <a href='#' onclick='delRole(\"" + row.id + "\")' title='删除'>删除</a>";
                    return str;
                }
            }]
        });
    }
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var vo = $("#searchForm").serialize();
        var temp = {   // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            pageSize: params.limit,   //页面大小
            pageNo: params.offset//,  //页码
            // vo: vo
        };
        return temp;
    };
    return oTableInit;
};

/**
 * 编辑用户
 * @param id
 */
function editRole(id) {
    // 如果不是从行那边直接操作编辑，则判断是否有选中行进行操作
    if (id == null || id == "" || typeof(id) == "undefined") {
        var selectRows = $('#authRoleTable').bootstrapTable("getSelections");
        if (selectRows.length == 1) {
            id = selectRows[0].id;
        } else {
            BootstrapDialog.alert({
                title: "提示",
                type: BootstrapDialog.TYPE_WARNING,
                buttonLabel: "确定",
                message: "请选择一个对象进行操作"
            });
            return;
        }
    }
    addRole(id);
}


var roleDialog = null;
function addRole(id) {
    var url = CTX + "role/edit";
    var title = "新增";
    if (id) {
        title = "编辑";
        url += "?id="+id;
    }
    roleDialog = BootstrapDialog.show({
        title: title + "用户",
        type : BootstrapDialog.TYPE_DEFAULT,
        size : BootstrapDialog.SIZE_WIDE,
        message: function(dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData("pageToLoad");
            $message.load(pageToLoad);

            return $message;
        },
        data: {
            "pageToLoad": url
        }
    })
}

function refreshRole() {
    $('#authRoleTable').bootstrapTable("refresh", {pageSize:10,pageNo:1});
}

// 删除用户
function delRole(id) {
    var ids = id;
    // 判断是否从【操作栏】点击的删除，优先级高于checkbox的选择
    if (ids == null && typeof(ids) == "undefined" || ids == "") {
        // 获取选中行，并取得id
        var selectRows = $('#authRoleTable').bootstrapTable("getSelections");
        if (selectRows.length > 0) {
            var tempIds = "";
            for(var i = 0; i < selectRows.length; i++) {
                tempIds += selectRows[i].id;
            }
            ids = tempIds;
        }
    }
    // 如果【操作栏】与左侧checkbox都没有选择的时候点击删除，则进行提示
    if (ids == null && typeof(ids) == "undefined" || ids == "") {
        ZeroDialog.warning("删除提示","请选择删除对象by:Zeroialog")
        return ;
    }
    BootstrapDialog.confirm({
        title: "删除提示",
        type: BootstrapDialog.TYPE_WARNING,
        message: "确认执行删除操作",
        btnCancelLabel: '取消',
        btnOKLabel: '删除', // <-- Default value is 'OK',
        btnOKClass: 'btn-warning',
        callback: function(result) {
            if (result) {
                $.ajax({
                    url: CTX + "role/delete",
                    type: "POST",
                    data:{ids: ids},
                    asyn: false,
                    dataType:"json",// 指定服务器返回的数据类型
                    beforeSend: function(XHR) {
                        // 参数 XMLHttpRequest 对象
                        console.log("发送请求之前调用");
                    },
                    complete: function(XHR, TS) {
                        // 参数： XMLHttpRequest 对象和一个描述请求类型的字符串。
                        console.log("请求完成后调用的函数，请求成功或者失败都调用");
                    },
                    success: function(data) {
                        if (data.code == "200") {
                            ZeroDialog.success("删除提示", "删除成功", function(dialogRef) {
                                setTimeout(function() {
                                    refreshRole();// 刷新列表
                                    dialogRef.close();// 关闭提示框
                                }, 1000)
                            });
                        } else {
                            ZeroDialog.warning("删除提示", "删除失败");
                        }
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        var message = XMLHttpRequest.responseJSON.message;
                        ZeroDialog.warning("提示", message);
                    }
                })
            }
        }
    })
}