<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %><!--tag do Spring para tratar das msg de erro-->
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %><!--tag do Spring que permite ele gerenciar o controller atraves da url (colocando o prefixo "s") -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais -  Casa do Coódigo</title>
</head>
<body>			<!--na linha de baixo o mvcUrl pega a inicial maiuscula do controller PC"ProdutoController" e depois chama o metodo gravar  -->
	<form:form action="${s:mvcUrl('PC#gravar').build( )}" method="POST"
			commandName="produto"> <!--commandName é um parametro que indica que td que estamos -->
		<div>					   <!--trabalhando é referente ao Produto que esta no nosso controller  -->
			<label>Titulo</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />	
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao" rows="10" cols="20" />
			<form:errors path="descricao" />	
		</div>
		<div>
			<label>Páginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas" />	
		</div>
		<div>
			<label>Data de Lançamento</label>
			<form:input path="dataLancamento" />
			<form:errors path="dataLancamento" />	
		</div>
		<c:forEach items="${tipos }" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco }</label>
				<form:input path="precos[${status.index }].valor" />
				<form:hidden path="precos[${status.index }].tipo" value="${tipoPreco }" />
			</div> 
		</c:forEach>
		<button type="submit">Cadastrar</button>
	</form:form> <!-- a tag form:form é uma tag do Spring que permite que resumimos o codigo -->
				 <!-- sem precisar colocar "produto." antes do atributo.. Ex <form:errors path="paginas" />-->
				 	
</body>
</html>