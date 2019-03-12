<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 12/03/19
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gantt View</title>

    <link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery-ui-1.8.4.css"/>
    <link rel="stylesheet" type="text/css" href="../scriptGanttView/reset.css"/>
    <link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery.ganttView.css"/>

    <script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="../scriptGanttView/date.js"></script>
    <script type="text/javascript" src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
    <script type="text/javascript" src="../scriptGanttView/jquery.ganttView.js"></script>

    <style type="text/css">
        body {
            font-family: tahoma, verdana, helvetica;
            font-size: 0.8em;
            padding: 10px;
        }
    </style>

</head>
<body>

<h1>Gantt View</h1>
<br/>
<br/>

<div id="ganttChart"></div>
<br/><br/>
<div id="eventMessage"></div>

</body>

<script type="text/javascript">

    $(document).ready(function () {

        //$.get("buscarDatasPlanejamento"), function (response) {

        //var ganttDataResposta = JSON.parse(response); //Resposta do servidor
        var ganttData = [
            {
                id: 1, name: "Feature 1", series: [
                    {name: "Planned", start: new Date(2010, 00, 01), end: new Date(2010, 00, 03)},
                    {name: "Actual", start: new Date(2010, 00, 02), end: new Date(2010, 00, 05), color: "#f0f0f0"}
                ]
            }];

        $("#ganttChart").ganttView({
            data: ganttData,
            slideWidth: 650,
            behavior: {
                onClick: function (data) {
                    var msg = "Evento de click: { inicio: " + data.start.toString("d/M/yyyy") + ", fim: " + data.end.toString("d/M/yyyy") + " }";
                    $("#eventMessage").text(msg);
                },
                onResize: function (data) {
                    var msg = "Evento de redimensionar: { inicio: " + data.start.toString("d/M/yyyy") + ", fim: " + data.end.toString("d/M/yyyy") + " }";
                    $("#eventMessage").text(msg);
                },
                onDrag: function (data) {
                    var msg = "Evento de arrastar: { inicio: " + data.start.toString("d/M/yyyy") + ", fim: " + data.end.toString("d/M/yyyy") + " }";
                    $("#eventMessage").text(msg);
                }
            }
        });
        //}
    });

</script>

</html>
