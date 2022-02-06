<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link href='<c:url value="/css/design.css" />' rel="stylesheet" type="text/css" />
		<link href='<c:url value="/css/bootstrap.min.css" />' rel="stylesheet" type="text/css" />
		<script type="text/javascript" src='<c:url value="/js/script.js" />'></script>
	</head>
	<body>
		<div id="entete">Gestion des utilisateurs</div>
		<div id="menu">
			<ul>
				<li><a href="<c:url value="/" />">Accueil</a></li>
				<c:choose>
					<c:when test="${ !empty sessionScope.utilisateur }">
							<li><a href="<c:url value="/users/list" />">Lister</a></li>
							<li><a href="<c:url value="/users/add" />">Ajouter</a></li>
							<li><a href="<c:url value="/logout" />">Deconnexion</a></li>					
					</c:when>
					<c:otherwise>
							<li><a href="<c:url value="/login" />">Connexion</a></li>					
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
