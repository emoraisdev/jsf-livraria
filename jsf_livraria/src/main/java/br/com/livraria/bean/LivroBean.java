package br.com.livraria.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import br.com.livraria.model.Autor;
import br.com.livraria.model.Livro;
import dao.AutorDAO;
import dao.LivroDAO;
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

	private Livro livro = new Livro();

	public void gerarMassa() {
		Autor joaquim = new Autor(null, "Joaquim");
		Autor mario = new Autor(null, "Mario");
		
		new AutorDAO().salvar(joaquim);
		new AutorDAO().salvar(mario);
		new AutorDAO().salvar(new Autor(null, "Zonilda"));

		new LivroDAO().salvar(
				new Livro(null, "Livro 1", "321", 20.5, LocalDateTime.now(), Arrays.asList(joaquim)));
	
		new LivroDAO().salvar(
				new Livro(null, "Livro 2", "321", 20.5, LocalDateTime.now(), Arrays.asList(joaquim, mario)));
		new LivroDAO().salvar(
				new Livro(null, "Livro 1", "321", 20.5, LocalDateTime.now(), Arrays.asList(mario)));
	}

	public void gravar() {
		new LivroDAO().salvar(livro);
	}

}
