package br.com.livraria.bean;

import java.io.Serializable;

import br.com.livraria.dao.AutorDAO;
import br.com.livraria.model.Autor;
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
	
	private Autor autor = new Autor();

	public void gravar() {
		new AutorDAO().salvar(autor);
	}
}
