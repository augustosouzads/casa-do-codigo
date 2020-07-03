package br.com.casadocodigo.loja.conf;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

}
