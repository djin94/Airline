$(document).ready(function () {
    loadFlightPrice();
    loadSits();
});

function loadFlightPrice() {
    $.ajax({
        url: '/buyticket/flightprices',
        method: 'get',
        contentType: "application/json",
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
    select += "<select name='flightPrice' id='flightPrice' class='form-control' onchange='loadSits()'>";
    for (var i = 0; i != data.length; ++i) {
        select += "<option>" + data[i].levelTicket + " - " + data[i].price + " EUR</option>";
    }
    select += "</select>";
    $('#flightPriceSelect').html(select);
}

function loadSits() {
    $.ajax({
        url: '/buyticket/sits',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({levelTicket: $('#flightPrice').val()}),
        dataType: 'json',
        success: function (data) {
            printSits(data);
        }
    });
}

function printSits(data) {
    var select = "<label for='sit'>Место</label>";
    select += "<select name='sit' id='sit' class='form-control'>";
    for (var i = 0; i != data.length; ++i) {
        select += "<option>" + data[i].place + "</option>";
    }
    select += "</select>";
    $('#sitSelect').html(select);
}

function createTicket() {
    $.ajax({
        url: '/buyticket',
        method: 'post',
        contentType: "application/json",
        data: JSON.stringify({
            userDTO: {
                lastName: $('#lastName').val(),
                firstName: $('#firstName').val(),
                patronym: $('#patronym').val(),
                passportNumber: $('#passportNumber').val()
            },
            sit: $('#sit').val()
        }),
        dataType: 'json',
        complete: function (data) {
            $('#ticketCreateStatus').html("<h3>Билет оформлен</h3>");
        }
    });
}