<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/home_page_style.css">
    <link rel="stylesheet" type="text/css" href="../../resources/css/bootstrap.min.css">
    <script type="text/javascript" src="../../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="../../resources/js/respond.min.js"></script>
    <link href='https://fonts.googleapis.com/css?family=Lobster|Pacifico|Kaushan+Script' rel='stylesheet' type='text/css'>
    <title>Second Page</title>
</head>
<body>
<div class="container-fluid">

    <div class="row">
        <%--<div class="col-xs-2 col-md-2 icon">Currency Exchange</div>--%>
        <div class="col-xs-12 col-md-12 header">
            <spring:form action="/exit" method="post" class = "button">
                <div>${loginUser.email}</div>
                <input class = "button" type="submit" value="Exit">
            </spring:form>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-4 left-side">
            <div class="row rows">
                <div class="date">${date}</div>
            </div>
            <div class="row rows">
                <div class="api">${api}</div>
            </div>
            <div class="row rows">
                <div class="api">${api1}</div>
            </div>
            <div class="row rows">
                <div class="api">${api2}</div>
            </div>
            <div class="row rows">
                <div class="api">${api3}</div>
            </div>
            <div class="row rows">
                <div class="api">${api4}</div>
            </div>
        </div>
        <div class="col-xs-8 content">
        </div>
    </div>
</div>
</body>
</html>
