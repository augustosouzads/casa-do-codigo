package br.com.casadocodigo.loja.controllers;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)//anotação do JUnit que diz que quem vai rodar os testes do JUnit com os objetos do Spring é a classe SpringJUnit4ClassRunner
@WebAppConfiguration
@ContextConfiguration(classes= {JPAConfiguration.class,AppWebConfiguration.class,DataSourceConfigurationTest.class,SecurityConfiguration.class})//anotation que diz onde estao os arquivos de configuração
@ActiveProfiles("test")
public class ProdutosControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;// O Objeto MockMvc fará as requisições para os controllers da aplicação.
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before//A @Before é quem faz com que o método seja chamado antes que qualquer teste seja executado.
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
	}
	
	@Test
	public void deveRetornarParaHomeComOsLivros() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/"))
	            .andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
	            .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void somenteAdminDeveAcessarProdutosForm() throws Exception{
	    mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
	    .with(SecurityMockMvcRequestPostProcessors
                .user("user@casadocodigo.com.br").password("123456")
                .roles("USUARIO")))// processo de POST antes de executar o GET da página, passando neste Post Processor os dados de autenticação do usuário
            .andExpect(MockMvcResultMatchers.status().is(403));;
	}

}
