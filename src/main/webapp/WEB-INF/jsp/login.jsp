<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="taglibs.jsp"%>
<c:set var="title" value="Login"/>

<%@include file="header.jsp"%>

<h1>LOGIN</h1>

<div class="login-page card">

<form action="<c:url value="/login"/>" method="POST">

<fieldset>
    <legend>Login</legend>

    <c:if test="${not empty param.error}">
        <p>Invalid username and password.</p>
    </c:if>
    <c:if test="${not empty param.logout}">
        <p>Logout sukses.</p>
    </c:if>

    <div>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </div>

    <div>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </div>

    <button type="submit">Login</button>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</fieldset>

</form>

</div>

<%@include file="footer.jsp"%>