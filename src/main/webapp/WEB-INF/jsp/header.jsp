<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="taglibs.jsp"%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><c:if test="${not empty title}"><c:out value="${title}" escapeXml="true"/> - </c:if>Kapanlagi Photo Gallery</title>

    <%--
        if you use ajax, please use this as header key and value before sent, for jQuery

        $(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            $.ajax({
                url: 'foo/bar',
                headers: { header: token },
                ...
            });

            or setup all request using default header

            $.ajaxSetup({
                headers: { header: token }
            });
        });

     --%>
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

    <%--
        @TODO make this page beautiful and responsive, add more CSS / modif the css below
    --%>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/app.css"/>" />

</head>
<body>


