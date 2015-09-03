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
		Encryptable<String> encrypt = new Criptografia<>();
		String criptografar =  encrypt.cipher("teste");
		String criptografar2 =  encrypt.cipher("teste");
		System.out.println(criptografar);
		assertEquals(criptografar, criptografar2);
		assertNotEquals("teste", criptografar);
	}

}
