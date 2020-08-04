package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //habilitando o Spring para fazer as transações entre aplicaçãoo e o banco..
public class JPAConfiguration { //classe onde vamos implementar os metodos EntityManagerFactory responsavel pela criação do EntityManager que ira gerenciar nossas entidades.
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties aditionalProperties) {
		LocalContainerEntityManagerFactoryBean factoryBean =
				new LocalContainerEntityManagerFactoryBean();//objeto onde colocamos as configurações do EntityManager para o Spring poder crialo
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");//dznzd ao hibernate onde procurar por nossas models (entidade)
		factoryBean.setDataSource(dataSource);

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); //HibernateJpaVendorAdapter é uma classe que implementa a interface JpaVendorAdapter
		factoryBean.setJpaVendorAdapter(vendorAdapter);	//Setar qual implementação do JPA que esta sendo utilizado 
		factoryBean.setJpaProperties(aditionalProperties);//adicionando as propriedades ao FactoryBean
		
		return factoryBean;
	}

	@Bean
	@Profile("dev")
	public Properties aditionalProperties() {
		Properties props = new Properties();//configurando propriedades do hibernate
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");//dialeto que sera usado na nossa aplicação (conversar com MYSQL)
		props.setProperty("hibernate.show_sql", "true");//propriedade para podermos ver o SQL gerado pelo hibernate
		props.setProperty("hibernate.hbm2ddl.auto", "update");// hbm (hibernate mapping) 2(to) ddl(daga defination lenguage)propriedade que gerencia o banco automaticamente quando altermos nosso modelo
		return props;
}
	
	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();//objeto que cuida da base de dados de configuração para gerenciar as conexoes
		dataSource.setUsername("root");// passando usuario
		dataSource.setPassword(""); //passando a senha
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");//passando url de conexao
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");//informando a classe que sera utilizada para fazer a conexao.
		
		return dataSource;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {//metodo que cria o gerenciador de transações e cria a asociação com o entityManagerFactory
		return new JpaTransactionManager(emf);
	}

}
