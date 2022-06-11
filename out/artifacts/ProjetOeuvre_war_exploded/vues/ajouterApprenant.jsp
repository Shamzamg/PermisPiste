<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="container">
    <h1> Ajout d'un adhérent </h1>
    <form method="post" action="ServletControleurApprenant?action=insererApprenant">
    <div class="col-md-12 well well-md">
        <h1>Ajouter un adhérent</h1>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Nom de l'Apprenant : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtnom" value="" id="nom" class="form-control" min="0">
            </div>

        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Prénom de l'Apprenant : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtprenom" value="" id="prenom" class="form-control" min="0">
            </div>
        </div>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Ajouter
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