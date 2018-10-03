function loadFlights() {
    $.ajax( {
        url: '/searchflight',
        method: 'post',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (jQuery.isEmptyObject(data)){
                printNotFoundMessage();
            }else {
                printFlights(data);
            }
        },
        error:function (e) {
            alert("error");
        }
    });
}
