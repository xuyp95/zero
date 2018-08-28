/*
* 该文件将BootStrap-Dialog 进一步封装
* 将固定形式定义好，减少重复代码在页面的出现
* 该脚本依赖于 bootstrap-dialog，所以必须在该脚本之前添加 bootstrap-dialog 相关引用
* 该脚本将在后续业务中根据业务需要不断完善
* @author: xuyp
* @DateTime: 2018-06-24
* */

var ZeroDialog = function ($) {
};

/*
* 错误消息会话框
* @Param title: 会话框标题内容
* @param message: 会话消息内容
* */
ZeroDialog.error = function(title, message) {
        BootstrapDialog.alert({
            title: title,
            type: BootstrapDialog.TYPE_DANGER,
            message: message,
            buttonLabel: "确定"
        });
}

/*
* 成功消息会话框
* @param title: 标题内容
* @param message: 会话消息内容
* @param onshown: 会话框打开完成后执行,
* 对应bootstrap-dialog 的 onshown 方法，有一个参数：dialogRef，是会话框 dialog 对象
* */
ZeroDialog.success = function(title, message, onshown) {
    BootstrapDialog.alert({
        title: title,
        type: BootstrapDialog.TYPE_SUCCESS,
        message: message,
        buttonLabel: "确定",
        onshown: function(dialogRef) {
            if (typeof(onshown) == "function") {
                onshown(dialogRef);
            }
        }
    });
}

/**
 * 警告信息会话框
 * @param title 会话标题内容
 * @param message 会话消息内容
 */
ZeroDialog.warning = function(title, message) {
    BootstrapDialog.alert({
        title: title,
        type: BootstrapDialog.TYPE_WARNING,
        message: message,
        buttonLabel: "确定"
    })
}

/**
 * 会话框内容显示自定义页面（简易版）
 *
 * @param title 标题内容
 * @param url 请求页面内容的 url
 */
ZeroDialog.show = function(title, url) {
    BootstrapDialog.show({
        title: title,
        type: BootstrapDialog.TYPE_DEFAULT,
        size: BootstrapDialog.SIZE_WIDE,
        cssClass: "fade",
        closeable: true,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        data: {
            'pageToLoad': url,
        }
    });
}

/**
 * 会话框显示自定义页面
 * 有按钮版本，尚未使用，有使用到后续修改和扩展相关内容 add by:xuyp 2018-06-24
 * @param title 标题内容
 * @param url 请求页面内容的 url
 * @param success 点击确定后执行的方法，暂时没使用到
 */
ZeroDialog.show = function(title, url, success) {
    BootstrapDialog.show({
        title: title,
        type: BootstrapDialog.TYPE_DEFAULT,
        size: BootstrapDialog.SIZE_WIDE,
        cssClass: "fade",
        closeable: true,
        message: function (dialog) {
            var $message = $('<div></div>');
            var pageToLoad = dialog.getData('pageToLoad');
            $message.load(pageToLoad);
            return $message;
        },
        data: {
            'pageToLoad': url,
        }, //按钮先注释掉，后续会在业务中形成,如若需要会在定义出来
        buttons: [{
            label: '<i class="fa fa-close"></i> 取消',
            action: function (dialog) {
                dialog.close();
            }
        }, {
            label: '<i class="fa fa-check"></i> 确定',
            cssClass: 'btn btn-primary',
            action: function (dialog) {
                success(dialog);
            }
        }]
    });
}

/**
 * 会话确认框
 * @param title 标题内容
 * @param message 确认内容
 * @param okLabel 确按钮的文本
 * @param cancelLabel 取消按钮的文本
 * @param okCallback 确认的执行方法
 * @param cancelCallback 取消后的执行方法
 */
ZeroDialog.confirm = function(title, message, okLabel, cancelLabel, okCallback, cancelCallback) {
    BootstrapDialog.confirm({
        title: title,
        type: BootstrapDialog.TYPE_WARNING,
        message: message,
        btnCancelLabel: cancelLabel,
        btnOKLabel: okLabel, // <-- Default value is 'OK',
        btnOKClass: 'btn-warning',
        callback: function(result) {
            if (result) {
                if (typeof (okCallback) == "function") {
                    okCallback();// 点击【确定】执行方法
                }
            } else {
                if (typeof (cancelCallback)) {
                    cancelCallback();// 点击【取消】执行的方法
                }
            }
        }
    })
}