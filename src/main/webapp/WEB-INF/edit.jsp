<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<c:import url="/WEB-INF/inc/header.jsp" />
		
	<div id="corps">
	
		<h1 id="titre-principal">Modification d'un utilisateur</h1>
		<form action="" method="post">
			<div class="formItem">
				<label>Nom</label>
				<input type="text" name="nom" value='<c:out value="${ utilisateur.nom }"/>' />
			</div>
			<div class="formItem">
				<label>Prenom</label>
				<input type="text" name="prenom" value='<c:out value="${ utilisateur.prenom }"/>' >
			</div>
			<div class="formItem">
				<label>Login</label>
				<input type="text" name="login" value='<c:out value="${ utilisateur.login }"/>'>
			</div>
			<div class="formItem">
				<label>Password</label>
				<input type="password" name="password" value='<c:out value="${ utilisateur.password }"/>'>
			</div>
			<div class="formItem">
				<label>Confirm Password</label>
				<input type="password" name="password_bis" value='<c:out value="${ utilisateur.password }"/>'>
			</div>
			<div class="formItem">
				<input type="submit" value="modifier">
			</div>
		</form>
	</div>
	
	<c:import url="/WEB-INF/inc/footer.jsp" />
	