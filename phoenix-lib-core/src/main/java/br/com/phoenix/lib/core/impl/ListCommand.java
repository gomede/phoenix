package br.com.phoenix.lib.core.impl;

import java.util.List;

import org.hibernate.Session;

import br.com.phoenix.lib.core.AbstractCommand;

@SuppressWarnings("rawtypes")
public class ListCommand extends AbstractCommand<Object> {

	private Class klazz;
	
	public ListCommand(Class klazz) {
		this.klazz = klazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> execute(Session session) {
        if (logger.isDebugEnabled()) {
            logger.debug("Listando objeto do tipo : " + klazz.getName());
        }
        return session
        		.createCriteria(klazz)
        		.list(); 
	}

}