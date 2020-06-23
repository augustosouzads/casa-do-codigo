package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component //anotação generica, apenas diz ao spring q a classe é um componente Spring
public class FileSaver {//classe para gravar os arquivos especiais(PDF,jpg,mpeg etc..) 
	
	@Autowired
	private HttpServletRequest request;

	public String Write(String baseFolder, MultipartFile file) {
		
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);//metodo que pega o enderço real do arquivo
			String path = realPath + "/" + file.getOriginalFilename();
			file.transferTo(new File(path));//transferindo o arquivo para o destino
			
			return baseFolder + "/" + file.getOriginalFilename();
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
