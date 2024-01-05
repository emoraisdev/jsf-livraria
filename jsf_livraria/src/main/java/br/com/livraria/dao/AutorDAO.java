package br.com.livraria.dao;

import java.util.List;

import br.com.livraria.model.Autor;

public class AutorDAO {

	public void salvar(Autor autor) {
		
		BancoAux.salvar(autor);
	}
	
	public List<Autor> getAll() {
		return BancoAux.getAll(Autor.class, "Autor");
	}
	
	public Autor getById(Long id) {
		return BancoAux.find(Autor.class, id);
	}
}
