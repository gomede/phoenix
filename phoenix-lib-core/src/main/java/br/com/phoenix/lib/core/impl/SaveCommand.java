package br.com.phoenix.lib.core.impl;

import org.hibernate.Session;

import br.com.phoenix.lib.core.AbstractCommand;

public class SaveCommand extends AbstractCommand<Object> {

	private Object object;

	public SaveCommand(Object object) {
		this.object = object;
	}

	@Override
	public Object execute(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Salvando objeto do tipo : " + object.getClass().getName());
            logger.debug("Objeto com valor : " + object);
        }
		session.saveOrUpdate(object);
		return object;
	}

}