package com.segware.servidortcp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mensagem-h2");
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	 
}
