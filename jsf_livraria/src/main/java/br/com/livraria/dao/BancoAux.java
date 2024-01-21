package br.com.livraria.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import lombok.Data;

public class BancoAux {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("bancoPU");
	public static EntityManager manager = factory.createEntityManager();

	public static EntityManager createManager() {
		return factory.createEntityManager();
	}

	public static void closeManager() {
		manager.close();
	}

	public static <T> void salvar(T object) {
		
		manager.getTransaction().begin();
		if (manager.contains(object)) {
			manager.persist(object);
		} else {
			manager.merge(object);
		}
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getAll(Class<T> entityClass, String tabela) {

		EntityManager manager = createManager();
		String jpql = new StringBuilder("SELECT e FROM ").append(entityClass.getSimpleName()).append(" e").toString();

		manager.getTransaction().begin();
		Query query = manager.createQuery(jpql);

		List<T> result = query.getResultList();
		manager.getTransaction().commit();

		manager.close();
		// Execute a consulta e obtenha a lista de resultados
		return result;
	}

	public static <T> T find(Class<T> entityClass, Long id) {
		T entity = manager.find(entityClass, id);
		return entity;
	}

	public static <T> void remover(T entity) {
		manager.getTransaction().begin();
		manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
		manager.getTransaction().commit();
	}
}
