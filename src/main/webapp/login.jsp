<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<c:import url="/WEB-INF/inc/header.jsp" />
	<div id="corps">
		<h1 id="titre-principal">Connexion</h1>
		<form  method="post">
			<c:if test="${ !empty erreur }">
				<span class="erreur">${ erreur }</span>
			</c:if>
			<div class="formItem">
				<label>Login</label>
				<input type="text" name="login">
			</div>
			<div class="formItem">
				<label>Password</label>
				<input type="password" name="password">
			</div>		
			<div class="formItem">
				<input type="submit" value="Se Connecter">
			</div>
	</form>
	</div>
<c:import url="/WEB-INF/inc/footer.jsp"></c:import>