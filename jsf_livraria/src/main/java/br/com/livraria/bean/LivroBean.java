package br.com.livraria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
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
	
	private List<Livro> listaLivros;

	@PostConstruct
	public void init() {
		livro = new Livro();
		listaLivros = new LivroDAO().getAll();
	}

	public void gerarMassa() {
		Autor joaquim = new Autor(null, "Joaquim");
		Autor mario = new Autor(null, "Mario");

		new AutorDAO().salvar(joaquim);
		new AutorDAO().salvar(mario);
		new AutorDAO().salvar(new Autor(null, "Zonilda"));

		new LivroDAO().salvar(new Livro(null, "Livro 1", "321", 20.5, new Date(), Arrays.asList(joaquim)));

		new LivroDAO()
				.salvar(new Livro(null, "Livro 2", "321", 20.5, new Date(), Arrays.asList(joaquim, mario)));
		new LivroDAO().salvar(new Livro(null, "Livro 1", "321", 140.5, new Date(), Arrays.asList(mario)));
	}

	public void gravar() {

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

	public List<Autor> getAutores() {
		return new AutorDAO().getAll();
	}
	
	public void validarISBN(FacesContext fc, UIComponent component, Object value) {
		if (!value.toString().startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN: Deve Inciar com 1"));
		}
	}

}
