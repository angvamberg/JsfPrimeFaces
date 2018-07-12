package br.com.ang.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HibernateUtil {
	private static final EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("skrrninja");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
