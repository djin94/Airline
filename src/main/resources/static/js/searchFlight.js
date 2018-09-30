function loadFlights() {
    $.ajax( {
        url: '/searchflight',
        method: 'post',
        cache: false,
        timeout: 600000,
        success: function (data) {
            var table = "<table class='table'>";
            table += "<tr><th>Номер рейса</th><th>Дата вылета</th><th>Время вылета</th><th>Аэропорт отправления</th>" +
                "<th>Аэропорт прибытия</th><th>Самолет</th><th>Купить билет</th></tr>";
            for (var i = 0; i != data.length; ++i) {
                table += "<tr>" + "<td>" + data[i].number + "</td>" + "<td>" + data[i].dateString + "</td>" + "<td>" + data[i].timeString + "</td>" +
                    "<td>" + data[i].departureAirport + "</td>" + "<td>" + data[i].arrivalAirport + "</td>" + "<td>" + data[i].planeName +
                    "</td>" + "</tr>";
            }
            table += "</table>";
            $('#flights').html(table);
        },
        error:function (e) {
            alert("error");
        }
    });
}