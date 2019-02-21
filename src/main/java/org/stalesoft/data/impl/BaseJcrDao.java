package org.stalesoft.data.impl;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.stalesoft.exception.DaoException;

public class BaseJcrDao {
	
	public static final Long LOGIN_ERROR = "LOGIN_ERROR".hashCode() + 0L;
	public static final Long REPOSITORY_ERROR = "REPOSITORY_ERROR".hashCode() + 0L;
	
	private static Logger log = LoggerFactory.getLogger(BaseJcrDao.class);
	
	@Autowired
	private Repository repository;
	
	protected Session getSession()  {

		Session session = null;
		
		try {
			session = repository.login(new SimpleCredentials("admin", "superSecret!".toCharArray()));
			
		} catch (LoginException e) {
			log.error(e.getMessage(), e);
			throw new DaoException(LOGIN_ERROR, e.getMessage());
			
		} catch (RepositoryException e) {
			log.error(e.getMessage(), e);
			throw new DaoException(REPOSITORY_ERROR, e.getMessage());
		}
		
		return session;
	}
	

}
