package br.com.phoenix.lib.core.impl;

import org.hibernate.Session;

import br.com.phoenix.lib.core.AbstractCommand;

public class DeleteCommand extends AbstractCommand<Object> {

	private Object object;

	public DeleteCommand(Object object) {
		this.object = object;
	}

	@Override
	public Object execute(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Deletando objeto do tipo : " + object.getClass().getName());
            logger.debug("Objeto com valor : " + object);
        }
        session.delete(object);
        session.flush();
        return null;
	}

}