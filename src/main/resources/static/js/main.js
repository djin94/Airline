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
    var list = "<div class='list-group'>";
    for (var i = 0; i != data.length; i++) {
        list += "<button type='button' class='list-group-item list-group-item-action' onclick='writeNameAirportToInput(";
        list += data[i].name+")'>"+data[i].name;
        list += "</button>";
    }
    list += "</div>";
    $('#airports').html(list);
}

function writeNameAirportToInput(name) {
    $('#nameAirport').val(name);
}

function loadFlights() {
    $.ajax('', {
        method: 'get',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            var table = "<table class='table'>";
            table += "<tr><th>Номер рейса</th><th>Дата вылета</th><th>Время вылета</th><th>Аэропорт отправления</th>" +
                "<th>Аэропорт прибытия</th><th>Самолет</th><th>Купить билет</th></tr>";
            for (var i = 0; i != data.length; ++i) {
                table += "<tr>" + "<td>" + data[i].number + "</td>" + "<td>" + data[i].dateString + "</td>" + "<td>" + data[i].timeString + "</td>" +
                    "<td>" + data[i].departureAirport + "</td>" + "<td>" + data[i].arrivalAirport + "</td>" + "<td>" + data[i].planeName +
                    "</td>" + "</tr>"
            }
            table += "</table>";
            $('#flights').html(table);
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