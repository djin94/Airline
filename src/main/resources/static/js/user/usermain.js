$(document).ready(function () {
    $('#date').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });
    getUserName();
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

function searchDepartureAirport() {
    $.ajax({
        url: '/searchAirport',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({name: $('#nameDepartureAirport').val()}),
        dataType: 'json',
        success: function (data) {
            loadAirports(data, 'departureAirports', 'nameDepartureAirport');
        }
    });
}

function searchArrivalAirport() {
    $.ajax({
        url: '/searchAirport',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({name: $('#nameArrivalAirport').val()}),
        dataType: 'json',
        success: function (data) {
            loadAirports(data, 'arrivalAirports', 'nameArrivalAirport');
        }
    });
}

function loadAirports(data, typeAirport, idInput) {
    document.getElementById(typeAirport).hidden = false;
    var list = "<div id = 'listAirport'>";
    for (var i = 0; i != data.length; i++) {
        list += "<button type='button' class='list-group-item list-group-item-action' onclick='writeNameAirportToInput(";
        list += "value,\"" + typeAirport + "\", \"" + idInput + "\")' value = '" + data[i].name + "'>" + data[i].name;
        list += "</button>";
    }
    list += "</div>";
    $('#' + typeAirport).html(list);
}

function writeNameAirportToInput(name, typeAirport, idInput) {
    document.getElementById(idInput).value = name;
    var airport = document.getElementById(typeAirport);
    airport.getElementsByTagName('*')[0].hidden = true;
}

function getFlights() {
    $.ajax({
        url: '/user/flights',
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

}