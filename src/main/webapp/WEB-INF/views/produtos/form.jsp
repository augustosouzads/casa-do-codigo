<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livro de Java, Android, iPhone, Ruby, PHP e muito mais -  Casa do Coódigo</title>
</head>
<body>
	<form action="/casadocodigo/produtos" method="POST">
		<div>
			<label>Titulo</label>
			<input type="text" name="titulo">
		</div>
		<div>
			<label>Descrição</label>
			<textarea rows="10" cols="20" name="descricao"></textarea>
		</div>
		<div>
			<label>Páginas</label>
			<input type="text" name="paginas">
		</div>
		<button type="submit">Cadastrar</button>
	</form>

</body>
</html>