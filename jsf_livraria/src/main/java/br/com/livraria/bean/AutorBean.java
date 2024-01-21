package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.model.Autor;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

@Data
@Named
@ViewScoped
public class AutorBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -707625226127792836L;
	
	private Autor autor;
	private Long autorId = 0L;

	@PostConstruct
	public void init() {
		autor = new Autor();
	}
	
	public String gravar() {
		new AutorDAO().salvar(autor);
		return "livro?faces-redirect=true";
	}
	
	public void carregarAutorPeloId() {	
		autor = new AutorDAO().getById(autorId);
	}
	
	public List<Autor> getAutores() {
		return new AutorDAO().getAll();
	}
}
