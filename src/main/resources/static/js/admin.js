$(document).ready(function () {
    $('#date').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'YYYY-MM-DD'
        }
    });
});

function searchAirport() {
    $.ajax({
        url: '/searchAirport',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({name: $('#nameAirport').val()}),
        dataType: 'json',
        success: function (data) {
            loadAirports(data);
        }
    });
}

function loadAirports(data) {
    document.getElementById('airports').hidden = false;
    var list = "<div id = 'listAirport'>";
    for (var i = 0; i != data.length; i++) {
        list += "<button type='button' class='list-group-item list-group-item-action' onclick='writeNameAirportToInput(";
        list += "value)' value = '" + data[i].name + "'>" + data[i].name+"</button>";
    }
    list += "</div>";
    $('#airports').html(list);
}

function writeNameAirportToInput(name) {
    document.getElementById('nameAirport').value = name;
    var airport = document.getElementById('airports');
    airport.getElementsByTagName('*')[0].hidden=true;
}