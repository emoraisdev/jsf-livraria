package br.com.livraria.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class BancoAux {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("bancoPU");
	private static EntityManager manager = factory.createEntityManager();

	public static <T> void salvar(T object) {
		manager.getTransaction().begin();
		manager.persist(object);
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getAll(Class<T> entityClass, String tabela) {

		String jpql = new StringBuilder("SELECT e FROM ").append(tabela).append(" e").toString();

		Query query = manager.createQuery(jpql);

		// Execute a consulta e obtenha a lista de resultados
		return query.getResultList();
	}
	
	public static <T> T find(Class<T> entityClass, Long id) {
		return manager.find(entityClass, id);
	}
}
