function createUser() {
    if (validate()) {
        $('#name').css('background-color', '');
        $.ajax('flight', {
            method: 'post',
            data: JSON.stringify({login: $('#name').val()}),
            complete: function (data) {
                loadFlights();
            }
        });
    } else {
        alert("Заполните поля!");
    }
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
    airport.getElementsByTagName('*')[0].hidden=true;
}

function searchFlight() {
    $.ajax({
        url: '/searchFlight',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({name: $('#nameDepartureAirport').val()}),
        dataType: 'json',
        success: function (data) {

        }
    });
}

function validate() {
    var result = true;
    if ($('#name').val() == '') {
        $('#name').css('background-color', 'red');
        result = false;
    }
    return result;
}