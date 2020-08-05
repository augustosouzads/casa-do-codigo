package br.com.casadocodigo.loja.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("prod")
public class JPAProductionConfiguration {
	
	@Autowired
	private Environment environment;//[interface do Spring] que já é carregada na inicialização da aplicação e injeta o Spring o ambiente para nós o ambiente que estamos trabalhando"
		
	@Bean
	public Properties aditionalProperties() {
		Properties props = new Properties();//configurando propriedades do hibernate
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");//dialeto que sera usado na nossa aplicação (conversar com PostgreSQL)
		props.setProperty("hibernate.show_sql", "true");//propriedade para podermos ver o SQL gerado pelo hibernate
		props.setProperty("hibernate.hbm2ddl.auto", "update");// hbm (hibernate mapping) 2(to) ddl(daga defination lenguage)propriedade que gerencia o banco automaticamente quando altermos nosso modelo
		return props;
}
	
	@Bean
	public DataSource dataSource() throws URISyntaxException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();//objeto que cuida da base de dados de configuração para gerenciar as conexoes
		dataSource.setDriverClassName("org.postgresql.Driver");//informando a classe que sera utilizada para fazer a conexao.
		// usuario:senha@host:port/path
		URI dbUrl = new URI(environment.getProperty("DATABASE_URL"));
		
		dataSource.setUrl("jdbc:postgresql://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath());//passando url de conexao
		dataSource.setUsername(dbUrl.getUserInfo().split(":")[0]);// passando usuario usando o split para dividir a string em 2 posições atraves do sinal ":"
		dataSource.setPassword(dbUrl.getUserInfo().split(":")[1]); //passando a senha atraves da posiçao [1]
		
		return dataSource;
	}
}
