package br.com.casadocodigo.loja.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)//@Id -> anotação que avisa ao spring que o atributo é de identificaçao
	private int id;										 //@GeneratedValue() -> anotação com parametro(strategy...) que transfere a responsabilidade de criação do id para banco de dados.		
	
	private String titulo;
	private String descricao;
	private int paginas;
	
	@ElementCollection //Mapeia o atributo como uma entidade fraca relacionada ao produto
	private List<preco> precos;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getPaginas() {
		return paginas;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<preco> getPrecos() {
		return precos;
	}
	public void setPrecos(List<preco> precos) {
		this.precos = precos;
	}
	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas + "]";
	}
	
	

}
