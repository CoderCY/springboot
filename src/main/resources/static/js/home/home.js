$.showLoading();
$(function(){

    var tab = $("#tabhome");
    $.post("/12/home", function (data) {
        if(data.length>0){
            $.each(data, function (i, ele) {

            })
        } else {
            $("#tabhome").text("");
        }
    }, "json");
})