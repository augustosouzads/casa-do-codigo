package br.com.casadocodigo.loja.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.builders.ProdutoBuilder;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)//anotação do JUnit que diz que quem vai rodar os testes do JUnit com os objetos do Spring é a classe SpringJUnit4ClassRunner
@ContextConfiguration(classes= {JPAConfiguration.class,ProdutoDAO.class,DataSourceConfigurationTest.class})//anotation que diz onde estao os arquivos de configuração
@ActiveProfiles("test")//ativando um perfil de teste para rodar ; e utilizar o banco de dados de teste.
public class ProdutoDAOTest {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Test
	@Transactional
	public void deveSomarTodosOsPrecosPorTipoLivro() {

    List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN).more(3).buildAll();
    List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN).more(3).buildAll();

    livrosImpressos.stream().forEach(produtoDao::gravar);
    livrosEbook.stream().forEach(produtoDao::gravar);
    
    BigDecimal valor = produtoDao.somaPrecosPorTipo(TipoPreco.EBOOK);
    Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
}

}