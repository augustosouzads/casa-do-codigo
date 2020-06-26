package br.com.casadocodigo.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component // anotação generica que disponibiliza a classe como um Bean do Spring

@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)//Anotação que comfigura a classe para ser instanciada um objeto para cada usuário(servidor)
public class CarrinhoCompras implements Serializable { //Ex: evitar que um usuario em um outro lugar qualquer compre um item e este seja adicionado ao seu "carrinho".					 
													   //implements Serializable = salva o objeto em arquivo e toda vez que o usuario volta a sessão o ele retorna o objeto salvo.  
	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();//key set tras apenas uma coleção contendo somente as chaves(carrinhoItem), sem a Integer
	}
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	public Integer getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {//metodo para retornar o valor total de itens conforme sua quantidade.
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
		produto.setId(produtoId);
		itens.remove(new CarrinhoItem(produto, tipoPreco));
	}

}
