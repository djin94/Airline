$(document).ready(function () {
    getUserName();
    getCurrentUserData();
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

function editPassenger() {
    $.ajax({
        url: '/api/v1/user/passenger',
        method: 'put',
        contentType: "application/json",
        data: JSON.stringify({
            lastName: $('#lastName').val(),
            firstName: $('#firstName').val(),
            patronym: $('#patronym').val(),
            passportNumber: $('#passportNumber').val()
        }),
        dataType: 'json',
        complete: function (data) {
            $('#passengerEditStatus').html("<h3>Пасспортные данные изменены</h3>");
        }
    });
}

function getCurrentUserData() {
    $.ajax({
        url: '/api/v1/user/userlogin',
        method: 'get',
        contentType: 'application/json',
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            printUserData(data);
        },
        error: function (e) {
            alert("error");
        }
    });
}

function printUserData(data) {
    $('#userName').html("<a href='/user/account'>" + data.login + "</a>");
    document.getElementById('lastName').value = data.lastName;
    document.getElementById('firstName').value = data.firstName;
    document.getElementById('patronym').value = data.patronym;
    document.getElementById('passportNumber').value = data.passportNumber;
}