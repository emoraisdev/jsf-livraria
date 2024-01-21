package br.com.livraria.dao;

import br.com.livraria.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO {
	
	public boolean existe(Usuario usuario) {
		
		EntityManager manager = BancoAux.createManager();
		
		TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u"
				+ " where u.email = :email and u.senha = :senha", Usuario.class)
				.setParameter("email", usuario.getEmail())
				.setParameter("senha", usuario.getSenha());
		
		try {
		query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		} finally {
			manager.close();
		}
		
		return true;
	}

}
