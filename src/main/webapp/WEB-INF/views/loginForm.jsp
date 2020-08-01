<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %><!--tag do Spring para tratar das msg de erro-->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %><!--tag do Spring que permite ele gerenciar o controller atraves da url (colocando o prefixo "s") -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>

<c:url value="/resources/css" var="cssPath"/>
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath }/bootstrap-theme.min.css">

<style type="text/css">
	body {
		padding-bottom: 60px;
	}
</style>
</head>
<body>	


		
  <div class="container">
  
    <h1>Login da Casa do Código</h1>
  
	<form:form servletRelativeAction="/login" method="POST"	>
		<div class="form-group">					   
			<label>E-mail</label>
			<input name="username" type="text" class="form-control" />
		</div>
		<div class="form-group">					   
			<label>Senha</label>
			<input type="password" name="password" class="form-control" />
		</div>
		<button type="submit" class="btn btn-primary">Logar</button>
	</form:form> <!-- a tag form:form é uma tag do Spring que permite que resumimos o codigo -->
	</div>			 <!-- sem precisar colocar "produto." antes do atributo.. Ex <form:errors path="paginas" />-->
				 	
</body>
</html>