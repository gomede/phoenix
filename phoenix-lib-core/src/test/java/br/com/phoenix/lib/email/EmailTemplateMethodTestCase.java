package br.com.phoenix.lib.email;

import junit.framework.TestCase;

import org.junit.Test;

import br.com.phoenix.lib.core.email.impl.EmailTemplateMethod;

public class EmailTemplateMethodTestCase extends TestCase {

	@Test
	public void testSendEmail() {
		
		final String [] emails = {"evertongomede@gmail.com", "rafaelthiago@gmail.com"};
		
		final EmailTemplateMethod method = new EmailTemplateMethod(
				emails, "GPP UEL",
				"Teste de envio de e-mail. Favor desconsiderar.");
		
		try {
			method.sendAuthenticationEmail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
