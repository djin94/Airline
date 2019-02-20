$(document).ready(function () {
    getUserName();
});

function getUserName() {
    $.ajax({
        url: '/api/v1/user/userlogin',
        method: 'get',
        contentType: 'application/json',
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#userName').html("<a href='/user/account'>" + data.login + "</a>");
        },
        error: function (e) {
            alert("error");
        }
    });
}