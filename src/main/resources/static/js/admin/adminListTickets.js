$(document).ready(function () {
    loadTickets();
});

function loadTickets() {
    $.ajax({
        url: '/admin/listtickets',
        method: 'post',
        cache: false,
        timeout: 600000,
        success: function (data) {
            printTickets(data);
        },
        error: function (e) {
            alert("error");
        }
    });
}

function printTickets(data) {
    var table = "<table class='table'>";
    table += "<tr><th>Фамилия</th><th>Имя</th><th>Отчество</th><th>Место</th>";
    for (var i = 0; i != data.length; ++i) {
        table += "<tr id='tr" + i + "'>" +
            "<td>" + data[i].userDTO.lastName + "</td>" +
            "<td>" + data[i].userDTO.firstName + "</td>" +
            "<td>" + data[i].userDTO.patronym + "</td>" +
            "<td>" + data[i].sit + "</td>" +
            "</tr>";
    }
    table += "</table>";
    $('#tickets').html(table);
}