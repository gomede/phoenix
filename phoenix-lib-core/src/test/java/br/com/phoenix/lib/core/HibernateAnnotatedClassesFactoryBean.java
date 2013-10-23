package br.com.phoenix.lib.core;

import org.springframework.beans.factory.FactoryBean;

public class HibernateAnnotatedClassesFactoryBean implements FactoryBean {

	@Override
	public Object getObject() {
		Class[] classes = new Class[] { 
		
				EntityMock.class,

		};

		return classes;
	}

	@Override
	public Class getObjectType() {
		return Class[].class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
