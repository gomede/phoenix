package br.com.phoenix.lib.core.security;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CriptografiaTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCriptografar() {
		Criptografia criptografia = new Criptografia();
		String criptografar = criptografia.criptografar("teste");
		String criptografar2 = criptografia.criptografar("teste");
		assertEquals(criptografar, criptografar2);
		assertNotEquals("teste", criptografar);
	}

}
