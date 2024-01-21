package br.com.livraria.bean;

import java.io.Serializable;
import java.util.List;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.dao.LivroDAO;
import br.com.livraria.model.Autor;
import br.com.livraria.model.Livro;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Data;

@Named
@ViewScoped
@Data
public class LivroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6781170508428482625L;

	private Livro livro;

	private Long autorId;

	@PostConstruct
	public void init() {
		livro = new Livro();
	}

	public void gravar() {

		System.out.println("Salvar Livro: "+livro);
		if (livro.getAutores() == null || livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("O Livro deve possui ao menos um Autor."));
			return;
		}

		new LivroDAO().salvar(livro);
		init();
	}

	public void adicionarAutor() {

		if (!(livro.getAutores() != null && 
				livro.getAutores().stream().anyMatch(a -> a.getId().equals(autorId)))) {
		
			this.livro.adicionarAutor(new AutorDAO().getById(autorId));
		}
		
	}
	
	public void removerAutor(Autor autor) {
		livro.removerAutor(autor);
	}

	public List<Autor> getAutores() {
		return new AutorDAO().getAll();
	}
	
	public List<Livro> getListaLivros() {
		return new LivroDAO().getAll();
	}
	
	public void validarISBN(FacesContext fc, UIComponent component, Object value) {
		if (!value.toString().startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN: Deve Inciar com 1"));
		}
	}
	
	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public void remover(Livro livro) {
		System.out.println("Removendo Livro");
		new LivroDAO().remover(livro);
	}

}
