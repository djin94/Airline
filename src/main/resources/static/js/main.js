$(document).ready(function () {
    $('#date').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });
});

function searchDepartureAirport() {
    $.ajax({
        url: '/api/v1/airports',
        method: 'post',
        contentType: "application/json",
        data: $('#nameDepartureAirport').val(),
        dataType: 'json',
        success: function (data) {
            loadAirports(data, 'departureAirports', 'nameDepartureAirport');
        }
    });
}

function searchArrivalAirport() {
    $.ajax({
        url: '/api/v1/airports',
        method: 'post',
        contentType: "application/json",
        data: $('#nameArrivalAirport').val(),
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