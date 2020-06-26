package br.com.casadocodigo.loja.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component // anotação generica que disponibiliza a classe como um Bean do Spring

@Scope(value=WebApplicationContext.SCOPE_SESSION)//Anotação que comfigura a classe para ser instanciada um objeto para cada usuário(servidor)
public class CarrinhoCompras {					 //Ex: evitar que um usuario em um outro lugar qualquer compre um item e este seja adicionado ao seu "carrinho".
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	private int getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		return itens.values().stream().reduce(0, (proximo, acumulador) -> proximo + acumulador);
	}

}
