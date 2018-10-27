$(document).ready(function () {
    getUserName();
    getHistory();
});

function getUserName() {
    $.ajax({
        url: '/user/userlogin',
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

function getHistory() {
    $.ajax({
        url: '/user/history/currenthistory',
        method: 'get',
        contentType: 'application/json',
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            printFlights(data);
        },
        error: function (e) {
            alert("error");
        }
    });
}

function printFlights(data) {
    var table = "<table class='table'>";
    table += "<tr><th>Номер рейса</th><th>Дата вылета</th><th>Время вылета</th><th>Аэропорт отправления</th>" +
        "<th>Аэропорт прибытия</th><th>Самолет</th><th>Место</th></tr>";
    for (var i = 0; i != data.length; ++i) {
        table += "<tr id='tr" + i + "'>" +
            "<td>" + data[i].flightDTO.number + "</td>" + "<td>" + data[i].flightDTO.dateString + "</td>" +
            "<td>" + data[i].flightDTO.timeString + "</td>" + "<td>" + data[i].flightDTO.departureAirport + "</td>" +
            "<td>" + data[i].flightDTO.arrivalAirport + "</td>" + "<td>" + data[i].flightDTO.planeName + "</td>" +
            "<td>" + data[i].sit + "</td>" + "<td></td>" +
            "</tr>";
    }
    table += "</table>";
    $('#flights').html(table);
}