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

function loadFlights() {
    $.ajax('flight', {
        method: 'get',
        contentType: "application/json",
        dataType: 'json',
        success: function (data) {
            var table = "<table class='table'>";
            table += "<tr><th>Номер рейса</th><th>Дата вылета</th><th>Время вылета</th><th>Аэропорт отправления</th>" +
                "<th>Аэропорт прибытия</th><th>Самолет</th><th>Купить билет</th></tr>";
            var size = data.length;
            for (var i = 0; i != size; ++i) {
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
};