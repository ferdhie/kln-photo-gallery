<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="title" value="Welcome"/>
<%@include file="header.jsp"%>

<div class="header">
    <h1>Kapanlagi Photo Gallery</h1>
    <ul class="main-menu">
        <sec:authorize access="hasRole('USER')">
        <li>Hello <b class="user"><sec:authentication property="principal.username" /></b></li>
        <li><a href="<c:url value="/logout" />">logout</a></li>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <li><a href="<c:url value="/login"/>">login</a></li>
        </sec:authorize>
    </ul>


    <c:if test="${not empty param.logout}">
        <div class="logout-message">
            <p>Logout sukses</p>
        </div>
    </c:if>
</div>

<div class="note card">

    <p>Hello applicants, please read the <a href="<c:url value="/README.md"/>">README</a> first!</p>

</div>

<div class="gallery-form card">

    <c:if test="${not empty upload_error}">
        <div class="error-message">
            <p><c:out value="${upload_error}" escapeXml="true"/></p>
        </div>
    </c:if>

    <c:if test="${not empty param.upload}">
        <div class="success-message">
            <p>Upload sukses</p>
        </div>
    </c:if>

    <form method="post" enctype="multipart/form-data" action="<c:url value="/upload"/>">
        <fieldset>
            <legend>Upload Foto</legend>
            <input type="file" id="upload_file" name="file" accept="image/*"/>
            <button type="submit">Upload</button>
        </fieldset>

        <%-- spring need this for CSRF checks --%>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

<div class="gallery-content card">

    <c:if test="${not empty files}">

        <%-- @TODO image list should be limited to 10 images per page, use ajax if possible --%>

        <c:forEach items="${files}" var="thumbnail" varStatus="loop">
            <div class="photo card-1">
                <a class="imglink" href="<c:url value="/photo/${thumbnail}"/>">
                    <img src="<c:url value="/thumb/${thumbnail}"/>" class="thumbnail" />
                </a>

                <sec:authorize access="hasRole('USER')">
                <%-- implement delete mechanism here --%>
                <a class="del" href="">DELETE</a>
                </sec:authorize>
            </div>
        </c:forEach>
    </c:if>

</div>

<div class="footer">

    <p>&copy; 2015 KLN</p>

</div>


<%@include file="footer.jsp"%>