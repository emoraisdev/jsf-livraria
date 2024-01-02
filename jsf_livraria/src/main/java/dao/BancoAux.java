package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BancoAux {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("bancoPU");
	private static EntityManager manager = factory.createEntityManager();
	
	public static <T> void salvar(T object) {		
		manager.getTransaction().begin();
		manager.persist(object);
		manager.getTransaction().commit();
	}
}
