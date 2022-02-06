	
	<c:import url="/WEB-INF/inc/header.jsp" />
	<div id="corps">
	<c:if test="${ !empty message }">
			<div id="statusMessageBox" class="alert alert-danger">
				${ message }
				<span id="control" onclick="hideMessageBox()" title="Fermer">x</span>
				
			</div>
		</c:if>
	<h1 id="titre-principal">Ajout d'un utilisateur</h1>
	<form  method="post">
		<div class="formItem">
			<label>Nom</label>
			<input type="text" name="nom">
		</div>
		<div class="formItem">
			<label>Prenom</label>
			<input type="text" name="prenom">
		</div>
		<div class="formItem">
			<label>Login</label>
			<input type="text" name="login">
		</div>
		<div class="formItem">
			<label>Password</label>
			<input type="password" name="password">
		</div>
		<div class="formItem">
			<label>Confirm Password</label>
			<input type="password" name="password_bis">
		</div>
		<div class="formItem">
			<input type="submit" value="Ajouter">
		</div>
	</form>
	</div>
<c:import url="/WEB-INF/inc/footer.jsp" />
	