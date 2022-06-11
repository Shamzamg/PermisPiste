<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="container">
	<div class="jumbotron text-center">
		<h1>Listing des Apprenants</h1>
	</div>

	<div class="container">
		<a class="btn btn-secondary" href="../index.jsp" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
		<h2>Tableau des Apprenants</h2>
		<div class="container">
			<h3>Liste des Apprenants</h3>
			<table class="table table-hover" border="1" style="margin-bottom: 130px;">
				<tr>
					<th class="col-md-1">Numero</th>
					<th class="col-md-2">Nom</th>
					<th class="col-md-2">Prénom</th>
					<th class="col-md-3">Action</th>
				</tr>

				<c:forEach items="${mesApprenants}" var="item">
					<tr>
						<td>${item.numApprenant}</td>
						<td>${item.nomApprenant}</td>
						<td>${item.prenomApprenant}</td>
						<td>
							<a class="btn btn-info" href="ServletControleurApprenant?action=modifierApprenant&id=${item.numApprenant}" role="button"><span
								class="glyphicon glyphicon-pencil"></span> Modifier</a>
							<a class="btn btn-danger" href="ServletControleurApprenant?action=supprimerApprenant&id=${item.numApprenant}" role="button"><span
									class="glyphicon glyphicon-remove-circle"></span> Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>