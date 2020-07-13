package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@Repository //mapea o produtoDAO
@Transactional //indica ao Spring que essa classe é transacional (OBS: importar de..org.springFramework.
public class ProdutoDAO {
	
	@PersistenceContext //solicitando ao Spring para injetar o Entity manager na classe e gerencialo
	private EntityManager manager;//gerenciador das entidades....
	
	public void gravar(Produto produto){
		manager.persist(produto);		//maneger.persist passando (produto)para ser feito gerenciamento e persistencia		
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p", Produto.class).getResultList();//metodo que retorna uma lista de produtos
	}

	public Produto find(Integer id) {//metodo usando uma Planed Query(query planejada)
		return manager.createQuery("select distinct(p) from Produto p"
				+ " join fetch p.precos preco where p.id = :id",Produto.class)//Query que busca o id do produto e ja relaciona com os preços do produto com o produto pelo id via fetch join
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public BigDecimal somaPrecosPorTipo(TipoPreco tipoPreco){
	    TypedQuery<BigDecimal> query = manager.createQuery("select sum(preco.valor) from Produto p "
	    		+ "join p.precos preco where preco.tipo = :tipoPreco", BigDecimal.class);
	    query.setParameter("tipoPreco", tipoPreco);
	    return query.getSingleResult();
	}

}
