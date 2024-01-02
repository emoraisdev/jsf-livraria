package dao;

import br.com.livraria.model.Autor;

public class AutorDAO {

	public void salvar(Autor autor) {
		
		BancoAux.salvar(autor);
	}
}
