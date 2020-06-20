package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.transaction.TransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); //HibernateJpaVendorAdapter é uma classe que implementa a interface HibernateJpaVendorAdapter
		factoryBean.setJpaVendorAdapter(vendorAdapter);	//Setar qual implementação do JPA que esta sendo utilizado 
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("");
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		factoryBean.setDataSource(dataSource);
		
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");//dialeto que sera usado na nossa aplicação (conversar com MYSQL)
		props.setProperty("hibernate.show_sql", "true");//propriedade para podermos ver o SQL gerado pelo hibernate
		props.setProperty("hibernate.hbm2ddl.auto", "update");// hbm (hibernate mapping) 2(to) ddl(daga defination lenguage)propriedade que gerencia o banco automaticamente quando altermos nosso modelo
		factoryBean.setJpaProperties(props);//adicionando as propriedades ao FactoryBean
		
		factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");//dznzd ao hibernate onde procurar por nossas models (entidade)
		
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

}
