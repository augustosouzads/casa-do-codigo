package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping("/")
	@Cacheable(value="produtosHome")//anotação para deixar o metodo salvo em cache para n ser necessario acessar toda hr o Banco de dados!(performance) 
	public ModelAndView index() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}

}
