package dao;

import br.com.livraria.model.Livro;

public class LivroDAO {

	public void salvar(Livro livro) {
		
		BancoAux.salvar(livro);
	}
}
