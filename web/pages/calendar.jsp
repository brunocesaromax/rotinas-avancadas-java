<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Calendário</title>

    <link href='../script/fullcalendar.min.css' rel='stylesheet'/>
    <link href='../script/fullcalendar.print.min.css' rel='stylesheet' media='print'/>
    <script src='../script/moment.min.js'></script>
    <script src='../script/jquery.min.js'></script>
    <script src='../script/fullcalendar.min.js'></script>

    <style>

        body {
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }

    </style>

</head>
<body>

<div id='calendar'></div>


</body>

<script>

    $(document).ready(function () {

        $.get("buscarCalendarioDatas", function (response) { // ajax get Servlet

            var datas = JSON.parse(response);

            /*datas = {
                title: 'All Day Event',
                start: '2019-01-01'
            };*/


            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,basicWeek,basicDay'
                },
                defaultDate: '2019-01-12',
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: datas

            });

        });


    });


</script>
</html>
