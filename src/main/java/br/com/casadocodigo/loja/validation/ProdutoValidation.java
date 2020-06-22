package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;//classe do Spring
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Produto;

public class ProdutoValidation implements Validator {//importar interface validator da org.SpringFrameworkValidator
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Produto.class.isAssignableFrom(clazz);//indicar que o obj produto pode ser validado pelo validator 
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");   //metodo Spring, que rejeita o atributo que estiver vazio recebe
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");//um errors que é um objeto do Spring, o atributo e o erro como parametro	
		
		Produto produto = (Produto) target;
		if(produto.getPaginas() <= 0) {
			errors.rejectValue("paginas", "field.required");//metodo da Classe Errors, recebe o atributo e o erro como parametro.
		}		
	}

}
