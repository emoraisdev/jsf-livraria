package br.com.livraria.dao;

import java.util.List;

import br.com.livraria.model.Livro;

public class LivroDAO {

	public void salvar(Livro livro) {
		
		BancoAux.salvar(livro);
	}
	
	public List<Livro> getAll() {
		return BancoAux.getAll(Livro.class, "Livro");
	}
	
	public void remover(Livro livro) {
		BancoAux.remover(livro);
	}
	
}
