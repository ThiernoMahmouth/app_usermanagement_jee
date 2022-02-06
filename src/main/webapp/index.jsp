<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:import url="/WEB-INF/inc/header.jsp" />	
	
	<div id="corps">
		<c:if test="${ !empty status }">
			<div id="statusMessageBox" class="alert alert-${ status ? 'success':'danger' }">
				${ message }
				<span id="control" onclick="hideMessageBox()" title="Fermer">x</span>
			</div>
		</c:if>
		<h1 id="titre-principal">Liste des utilistateurs</h1>
		<c:choose>
				<c:when test = "${ empty utilisateurs }">
					<p>La liste des utilisteurs est vide! </p>
				</c:when>
				<c:otherwise>
					<table>
					 	<tr>
							<th>ID</th>
							<th>Nom</th>
							<th>Prenom</th>
							<th>Login</th>
							<th colspan="2">Action</th>
						</tr>
						<c:forEach items="${ utilisateurs }" var="utilisateur">
								<tr>
									<td><c:out value="${ utilisateur.id }"/></td>
									<td><c:out value="${ utilisateur.nom }"/></td>
									<td><c:out value="${ utilisateur.prenom }"/></td>
									<td><c:out value="${ utilisateur.login }"/></td>
								
									<td><a href='<c:url value="/users/update?id=${ utilisateur.id }"/>'>Modifier</a></td>
									<td><a href='<c:url value="/users/delete?id=${ utilisateur.id }"/>'>Supprimer</a></td>
								</tr>
						</c:forEach>
					</table>
			</c:otherwise>
		</c:choose>
	</div>
	<c:import url="/WEB-INF/inc/footer.jsp" />
	