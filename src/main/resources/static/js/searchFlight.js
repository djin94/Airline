$(document).ready(function () {
    loadFlights();
});
function loadFlights() {
    $.ajax({
        url: '/searchflight',
        method: 'post',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (jQuery.isEmptyObject(data)) {
                printNotFoundMessage();
            } else {
                printFlights(data);
            }
        },
        error: function (e) {
            alert("error");
        }
    });
}

function printNotFoundMessage() {
    var notFoundString = "<h3>К сожалению, мы не нашли подходящих для вас рейсов. Попробуйте изменить параметры поиска.</h3>";
    $('#flights').html(notFoundString);
}

function printFlights(data) {
    var table = "<table class='table'>";
    table += "<tr><th>Номер рейса</th><th>Дата вылета</th><th>Время вылета</th><th>Аэропорт отправления</th>" +
        "<th>Аэропорт прибытия</th><th>Самолет</th><th>Купить билет</th></tr>";
    for (var i = 0; i != data.length; ++i) {
        table += "<tr id='tr" + i + "'>" +
            "<td>" + data[i].number + "</td>" + "<td>" + data[i].dateString + "</td>" + "<td>" + data[i].timeString + "</td>" +
            "<td>" + data[i].departureAirport + "</td>" + "<td>" + data[i].arrivalAirport + "</td>" + "<td>" + data[i].planeName +
            "</td>" + "<td><button onclick=\"buyTicket('" + data[i].number + "', '" + data[i].dateString + "', '" + data[i].timeString + "')\" class='btn btn-primary' id = '" + i + "'>" + "Купить</button></td>" +

            "</tr>";
    }
    table += "</table>";
    $('#flights').html(table);
}

function buyTicket(number, dateString, timeString) {
    window.location.replace("/buyticket?number=" + number + "&dateString=" + dateString + "&timeString=" + timeString);
}
