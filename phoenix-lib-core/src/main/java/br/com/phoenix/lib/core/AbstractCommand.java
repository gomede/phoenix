package br.com.phoenix.lib.core;

import org.apache.log4j.Logger;

public abstract class AbstractCommand<T> implements Command<T> {

	protected final Logger logger = Logger.getLogger(getClass());

}