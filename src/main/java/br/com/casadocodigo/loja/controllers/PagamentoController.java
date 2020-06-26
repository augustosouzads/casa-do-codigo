package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;

@RequestMapping("/pagamento")
@Controller
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes model) {//RedirectAtributes.. só para passar a msg como atributo no scopo de flash ao fazer o redirect
		
		System.out.println(carrinho.getTotal());
		model.addFlashAttribute("sucesso", "Pagamento Realizado com Sucesso");
		
		 return new ModelAndView("redirect:/produtos");
	}

}
