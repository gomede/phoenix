package br.com.phoenix.lib.core;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

@Service
public class CommandInvoker extends HibernateDaoSupport {

	public <T> T invokeInReadOnly(final AbstractCommand<T> command) {
		return (T) getHibernateTemplate().execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				return command.execute(session);
			}
		});

	}
	
	public <T> T invokeInTransaction(final AbstractCommand<T> command) {
		return (T) getHibernateTemplate().execute(new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException, SQLException {
				return command.execute(session);
			}
		});

	}
}