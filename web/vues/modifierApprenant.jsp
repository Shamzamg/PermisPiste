<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="container">
    <h1> Modification d'un adhérent </h1>
    <form method="post" action="ServletControleurApprenant?action=modifierApprenantAction">
    <div class="col-md-12 well well-md">
        <h1>Modifier un adhérent</h1>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <input type="hidden" name="idApprenant" value="${Apprenant.idApprenant}" id="id" class="form-control" min="0">
            <label class="col-md-3 control-label">Nom de l'Apprenant : </label>
            <div class="col-md-3">
                <input type="text" name="txtnom" value="${Apprenant.nomApprenant}" id="nom" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Prénom de l'Apprenant : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtprenom" value="${Apprenant.prenomApprenant}" id="prenom" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Ville de l'Apprenant : </label>
            <div class="col-md-3">
                <input type="text" name="txtville" value="${Apprenant.villeApprenant}" id="ville" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Modifier
            </button>

            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                                window.location = '../index.jsp';
                            }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
    </form>
</div>
</body>
<%@include file="footer.jsp"%>
</html>