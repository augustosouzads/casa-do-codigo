package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {//adicionando SecurityConfiguration no root para que a configuracao de seguranca ja esteja pronta assim que a plicação subir
		return new Class[] {SecurityConfiguration.class,AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {/*AppWebConfiguration.class, JPAConfiguration.class*/};//adicionando as classes de configuração que serão reconhecidas pelo Spring
	}							//acima desta frase esta comentado para retornar array vazia!

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		return new Filter[] {encodingFilter, new OpenEntityManagerInViewFilter()};//OpenEnTMan... é um filtro que serve para resolver o problema de laizy(Permitindo o Spring deixar o entit manager aberto e carregar a tabela de Preços tardia"element collection"
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {//metodo de configuração do Multipart.. classe para receber arquivos especiais(PDF,jpg,mpeg ..etc)
		registration.setMultipartConfig(new MultipartConfigElement(""));//esse metodo diz que queremos que do jeito que recebemos o arquivo ele devera ser enviado ao servidor.
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {//metodo de inicialização que ao iniciarmos a aplicaçãoi diz ao Spring que o perfil que iremos utilizar é o de dev.
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);//addicionando um listener do Spring (RequestContextListener)
		servletContext.setInitParameter("spring.profiles.active", "dev");//setando parametro inicial dizendo que o profile ativo da aplicação é o dev
	}

}
