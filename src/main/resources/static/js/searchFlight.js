function loadFlights() {
    $.ajax( {
        url: '/searchflight',
        method: 'post',
        cache: false,
        timeout: 600000,
        success: function (data) {
            if (jQuery.isEmptyObject(data)){
                printNotFoundMessage();
            }else {
                printFlights(data);
            }
        },
        error:function (e) {
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
        table += "<tr id='tr"+i+"'>" +
            "<td>" + data[i].number + "</td>" + "<td>" + data[i].dateString + "</td>" + "<td>" + data[i].timeString + "</td>" +
            "<td>" + data[i].departureAirport + "</td>" + "<td>" + data[i].arrivalAirport + "</td>" + "<td>" + data[i].planeName +
            "</td>" + "<td><button onclick='buyTicket(this)' class='btn btn-primary' id = '"+i+"'>"+"Купить</button></td>"+
            "</tr>";
    }
    table += "</table>";
    $('#flights').html(table);
}

function printFlightsDiv(data) {
    var table = "<div class='table'>";
    table+="<form method='get' action='buyticket'>";
    table+=""
    for (var i = 0; i != data.length; ++i) {
        table+="";
    }
}

function getTrByButtonClick(btn) {
    return document.getElementById("tr" + btn.id);
}
function buyTicket(btn) {
    var tr = getTrByButtonClick(btn);
    // $.ajax( {
    //     url: '/buyticket',
    //     method: 'get',
    //     contentType: 'application/json',
    //     dataType: 'json',
    //     data: JSON.stringify({
    //         number: tr[0].innerHTML,
    //         dateString:tr[1].innerHTML,
    //         timeString:tr[2].innerHTML
    //     }),
    //     cache: false,
    //     timeout: 600000,
    //     error:function (e) {
    //         alert("error");
    //     }
    // });
    window.location.replace("/buyticket?number="+tr[0].innerHTML+"&dateString="+tr[1].innerHTML+"&timeString="+tr[2].innerHTML);
}

function getJSONFromTr(tr) {
    return JSON.stringify({
        number: tr[0].innerHTML,
        dateString:tr[1].innerHTML,
        timeString:tr[2].innerHTML
    })
}