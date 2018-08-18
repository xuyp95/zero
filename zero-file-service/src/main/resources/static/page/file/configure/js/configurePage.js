// 参数配置页面脚本


// 更改文件上传方式
function changeWay(way) {
    if (way == "local") {
        alert("本地上传方式");
    } else if ("smb" == way) {
        alert("SMB共享文件上传方式");
    }
}

// 保存参数
function save() {
    var data = $("#configForm").serialize();
    $.ajax({
        url: CTX + "configure/save",
        type: "POST",
        data: data,
        dataType: "JSON",
        success: function (data) {
            if (data.code == "success") {
                ZeroDialog.success("提示", "保存成功", function(dialogRef){window.setTimeout(function() {dialogRef.close();}, 800)});
            } else {
                ZeroDialog.warning("提示", data.msg);
            }
        },
        error: function () {
            ZeroDialog.error("提示", "保存失败");
        }
    });
}