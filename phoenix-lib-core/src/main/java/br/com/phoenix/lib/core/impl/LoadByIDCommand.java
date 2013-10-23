package br.com.phoenix.lib.core.impl;

import java.io.Serializable;

import org.hibernate.Session;

import br.com.phoenix.lib.core.AbstractCommand;

public class LoadByIDCommand extends AbstractCommand<Object> {

	private Serializable ID;
	
	private Class klazz;

	public LoadByIDCommand(Class klazz, Serializable ID) {
		this.ID = ID;
		this.klazz = klazz;
	}

	@Override
	public Object execute(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Carregando o objeto do tipo : " + klazz.getName());
            logger.debug("Objeto com valor : " + ID);
        }
		
		return session.load(klazz, ID);
	}

}