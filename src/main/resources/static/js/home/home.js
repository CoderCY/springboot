$(function(){

    $.showLoading();

    var url = "/" + serial_id + "/home";
    $.get(url, function (result) {
        $.hideLoading();
        new Vue({
            el: '#tabhome',
            data: {
                navs: result
            }
        });
        $(".weui-tabbar__item").find(".icon").css("color",nav_icon_color);
        $(".weui-tabbar__label").css("color",nav_icon_color);
        $(".weui-tab__bd-item:first-child").addClass("weui-tab__bd-item--active");
        $(".weui-tabbar__item:first-child").addClass("weui-bar__item--on");
        var h1 = $(".weui-tabbar").height();
        var h2 = $(".weui-tab__bd").height();
        $(".weui-tab__frame").css("height", (h2-h1-2) + "px");

        $(".weui-bar__item--on").find(".icon").css("color",click_nav_icon_color);
        $(".weui-bar__item--on .weui-tabbar__label").css("color",click_nav_icon_color);
    }, "json");
});

$(document).on('click','.weui-tabbar__item',function(doc){
    $(this).find(".icon, .weui-tabbar__label").css("color",click_nav_icon_color);
    $(this).siblings().find(".icon, .weui-tabbar__label").css("color",nav_icon_color);
    // $(this).find("").css("color",click_nav_icon_color);
    // $(this).siblings().find("").css("color",nav_icon_color);
});