package br.com.phoenix.lib.core.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractTemplateMethod {

	protected final Log logger = LogFactory.getLog(getClass());
	
	public abstract void initialize();
	
	public abstract void configureMimeType();
	
	public abstract void sendAuthenticationEmail() throws Exception;

	
}