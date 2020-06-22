package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class,ProdutoDAO.class})
public class AppWebConfiguration {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	@Bean
	public MessageSource messageSource() {//metodo que cria uma configuração que diz onde esta os arquivos de mensagem de erro  
		ReloadableResourceBundleMessageSource messageSource // e a retorna.
			= new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/message");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);

		return messageSource;
	}
	
	@Bean
	public FormattingConversionService mvcConversionService() {//FormattingConversionService classe do Spring
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();//indicando ao Spring qual qual formato que queremos trabalhar
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));//informando qual o padrao a ser registrado
		registrar.registerFormatters(conversionService);//registrando o formato dentro do conversor padrao
		
		return conversionService;
	}
	
	

}
