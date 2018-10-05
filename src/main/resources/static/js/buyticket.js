$(document).ready(function () {
    loadFlightPrice();
});
function loadFlightPrice() {
    $.ajax({
        url: '/buyticket/flightprices',
        method: 'get',
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            printFlightPrices(data);
        },
        error: function (e) {
            alert("error");
        }
    });
}

function printFlightPrices(data) {
    var select = "<label for='flightPrice'>Категория</label>";
    select += "<select name='flightPrice' id='flightPrice' class='form-control'>";
    for (var i = 0; i != data.length; ++i) {
        select += "<option>" + data[i].level + " - " + data[i].price + " EUR</option>";
    }
    select += "</select>";
    $('#flightPriceSelect').html(select);
}

function buyTicket(number, dateString, timeString) {
    window.location.replace("/buyticket?number=" + number + "&dateString=" + dateString + "&timeString=" + timeString);
}

function createTicket() {
    $.ajax({
        url: '/buyticket',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({
            passengerDTO: {
                lastName: $('#lastName').val(),
                firstName: $('#firstName').val(),
                patronym: $('#patronym').val(),
                passportNumber: $('#passportNumber').val()
            },
            place: $('#place').val(),
            flightPrice: $('#flightPrice').val()
        }),
        dataType: 'json',
        complete: function (data) {
            $('#ticketCreateStatus').html("<h3>Билет оформлен</h3>");
        }
    });
}