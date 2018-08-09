/*******************************************
 *
 * Plug-in:友好的页面加载效果
 * Author:sqinyang (sqinyang@sina.com)
 * Time:2015/04/20
 * Explanation:随着HTML5的流行，页面效果越来越炫，同时也需要加载大量的插件及素材，万恶的网速，特别对于挂在国外服务器的网站，一打开一堆素材缓缓加载，位置错乱不齐，故编写此方法，方便大家使用
 * 来源：博客园：懒得安分（博主）   https://www.cnblogs.com/landeanfen/p/5461849.html#_label2
 * 将其修改成通过 Bootstrap-Dialog 弹窗的形式显示loading by xuyp:2018-06-28
 *********************************************/

jQuery.bootstrapLoading = {
    loadingDialog : null,
    start: function (options) {
        var defaults = {
            opacity: 1,
            //loading页面透明度
            backgroundColor: "#fff",
            //loading页面背景色
            borderColor: "#bbb",
            //提示边框颜色
            borderWidth: 1,
            //提示边框宽度
            borderStyle: "solid",
            //提示边框样式
            loadingTips: "Loading, please wait...",
            //提示文本
            TipsColor: "#666",
            //提示颜色
            delayTime: 1000,
            //页面加载完成后，加载页面渐出速度
            zindex: 999,
            //loading页面层次
            sleep: 0
            //设置挂起,等于0时则无需挂起

        }
        var options = $.extend(defaults, options);

        //获取页面宽高
        // var _PageHeight = document.documentElement.clientHeight,
        //     _PageWidth = document.documentElement.clientWidth;

        //在页面未加载完毕之前显示的loading Html自定义内容
        var _LoadingHtml = '<div id="loadingPage" style="position:fixed;left:0;top:0;_position: absolute;width:100%;height:100%;background:' + options.backgroundColor + ';opacity:' + options.opacity + ';filter:alpha(opacity=' + options.opacity * 100 + ');z-index:' + options.zindex + ';">' +
            '<div id="loadingTips" style="font-size: 20px;width: auto;height: 100%;text-align: center;padding-top: 10%;background: ' + options.backgroundColor + ' /*url(/images/loading.gif) no-repeat 5px center;*/ color:' + options.TipsColor + ';background-position-x: 34%;">' +
                '<img src="/images/loading.gif" style="padding-right: 10px;"/>' +
                options.loadingTips +
            '</div>' +
            '</div>';

        //呈现loading效果
        // $("body").append(_LoadingHtml);

        loadingDialog = BootstrapDialog.alert({
            type: BootstrapDialog.TYPE_DEFAULT,
            message: _LoadingHtml
        })

        //获取loading提示框宽高
        // var _LoadingTipsH = document.getElementById("loadingTips").clientHeight,
        //     _LoadingTipsW = document.getElementById("loadingTips").clientWidth;

        //计算距离，让loading提示框保持在屏幕上下左右居中
        // var _LoadingTop = _PageHeight > _LoadingTipsH ? (_PageHeight - _LoadingTipsH) / 2 : 0,
        //     _LoadingLeft = _PageWidth > _LoadingTipsW ? (_PageWidth - _LoadingTipsW) / 2 : 0;
        //
        // $("#loadingTips").css({
        //     "left": _LoadingLeft + "px",
        //     "top": _LoadingTop + "px"
        // });

        //监听页面加载状态
        // document.onreadystatechange = PageLoaded;
        //
        // //当页面加载完成后执行
        // function PageLoaded() {
        //     if (document.readyState == "complete") {
        //         var loadingMask = $('#loadingPage');
        //
        //         setTimeout(function () {
        //                 loadingMask.animate({
        //                         "opacity": 0
        //                     },
        //                     options.delayTime,
        //                     function () {
        //                         $(this).hide();
        //
        //                     });
        //
        //             },
        //             options.sleep);
        //
        //     }
        // }
    },
    end: function () {
        // $("#loadingPage").remove();
        loadingDialog.close();
    }
}