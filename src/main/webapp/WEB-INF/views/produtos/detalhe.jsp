<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<%@include file="/WEB-INF/views/cabecalho.jsp" %>

	<article id="${produto.id }" itemscope>
		<header id="product-highlight" class="clearfix">
			<div id="product-overview" class="container">
				<img itemprop="image" width="280px" height="395px" src="http://cdn.shopify.com/s/files/1/0155/7645/products/css-eficiente-featured_large.png?v=1435245145"
					class="product-featured-image" />
				<h1 class="product-title" itemprop="name">
				  ${produto.titulo }
				</h1>
				<p class="product-author">
					<span class="product-author-link"> </span>
				</p>
				<p itemprop="description" class="book-description">${produto.descricao }</p>
			</div>
		</header>

		<section class="buy-options clearfix">
			<form:form servletRelativeAction="/carrinho/add" method="post" cssClass="container">
				<ul id="variants" class="clearfix">
					<input type="hidden" value="${produto.id }" name="produtoId"/>
					<c:forEach items="${produto.precos }" var="preco">
						<li class="buy-option" itemprop="offers" itemscope >
							<input type="radio" name="tipoPreco" class="variant-radio" id="tipo" value="${preco.tipo }" checked> 
							<label itemprop="category" itemscope class="variant-label">${preco.tipo }</label> 
							<small class="compare-at-price">R$ 39,90</small>
							<p class="variant-price" itemprop="price">${preco.valor } </p>
						</li>
					</c:forEach>	
				</ul>]
				<button type="submit" class="submit-image icon-basket-alt" 4alt="Compre agora" title="Compre agora '${produto.titulo }'!"></button>
			</form:form>
		</section>

		<div class="container">
		
			<section class="summary">
					<h3>E muito mais... <a href='/pages/sumario-java8'>veja o sumário</a>.</h3>
			</section>

			<section class="data product-detail">
				<h2 class="section-title">Dados do livro:</h2>
				<p>Número de páginas: <span itemprop="numberOfPages">${produto.paginas }</span></p>

				<p><a rel="nofollow" href="https://groups.google.com/forum#java8-casadocodigo">Participe</p></a>
				<p>Data de publicação: <span class="publishedAt">
				<fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time }"/>
				</span></p>
				<p>Encontrou um erro? <a href='/submissao-errata' target='_blank'>Submeta uma errata</p></a>
			</section>
		</div>
	</article>

<%@include file="/WEB-INF/views/rodape.jsp" %>


</body>
</html>