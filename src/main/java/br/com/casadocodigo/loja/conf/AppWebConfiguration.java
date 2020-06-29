package br.com.casadocodigo.loja.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.CarrinhoCompras;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class,ProdutoDAO.class,FileSaver.class,CarrinhoCompras.class})
@EnableCaching//anotação para habilitar o Caching(cache)do Spring. -> requer o método public CacheManager
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		resolver.setExposedContextBeanNames("carrinhoCompras");//metodo para expor uma bean do Spring(carrinhoCompras)para a JSP e assim poder ser ivsta pelo JSTL
		
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
	
	@Bean
	public MultipartResolver multipartResolver() {//metodo que faz a parte de receber arquivos de multiplos formatos..(pdf.jpg etc..)
		return new StandardServletMultipartResolver();
		
	}
	
	@Bean
	public RestTemplate restTemplate() {//método que habilita a interação do sistema com o protocolo http enviar dados formatados para Json 
		return new RestTemplate();
	}
	
	@Bean
	public CacheManager cacheManager() {//metodo que prove o caching (gerente do caching)
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder()//construindo o cache
				.maximumSize(100)							//configurando o maximo de elementos que guardara o cache	
				.expireAfterAccess(5, TimeUnit.MINUTES);	//configurando o tempo de expiração do cache	
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(builder);//setando o builer no gerente do caching
		
		return manager;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {//metodo para o Spring liberar o acesso do TomCat a pasta resources dentro da pasta Webapp 
	    configurer.enable();
	}
	
	

}
