package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.TipoPreco;
import br.com.casadocodigo.loja.validation.ProdutoValidation;

@Controller
@RequestMapping("/produtos") 
public class ProdutosController {
	
	@Autowired //injeta o produtoDAO na classe e permite a instancia do mesmo.
	private ProdutoDAO produtoDao;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {//metodo Spring que inicia o binder para fazer validação do produto validation que 
		binder.addValidators(new ProdutoValidation());//o metodo ProdutoValidation que valida os atributos do Produto(livro)
	}
	
	@RequestMapping("form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");//objeto que manda o objeto para a JSP
		modelAndView.addObject("tipos", TipoPreco.values());//TipoPreco.values é um metodo que todo enum tem que devolve um array dos valores nele
															//"tipos" é a chave que agente pega na JSP
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView gravar(@Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {//BindingRresult tras o resutaldo do initBinding que fez a validação dos atributos
																													 //É preciso anotar a paramettro com @valid e implementar o metodo @initBinding
		if(result.hasErrors()) {
			return form(produto);
		}
		
		produtoDao.gravar(produto);
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto castrado com sucesso");//adicionando um atributo (flash) que persiste até a proxima requisição para ser apresentado apos o redirecionamento
		
		return new ModelAndView("redirect:produtos");//fazendo um redireionamento via GET para evitar o "bug-do-F5" 
	}
	
	@RequestMapping(method=RequestMethod.GET)// parametro de url pq os dois metodos listar() e gravar() tem o mesmo mapeamento (/produtos)mapeado no inicio da classe.
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject("produtos", produtos);
		
		return modelAndView;
	}
	
	

}
