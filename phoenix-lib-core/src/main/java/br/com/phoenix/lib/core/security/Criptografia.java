package br.com.phoenix.lib.core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Criptografia {
	/**
	 *
	 * @param senha
	 * @return senha criptografada
	 */
	public final String criptografar(String senha) {
		try {
			final MessageDigest message = MessageDigest.getInstance("MD5");
			final byte[] digest = message.digest(senha.getBytes());

			final StringBuilder hexString = new StringBuilder();

			for (byte b : digest) {
				 hexString.append(String.format("%02X", 0xFF & b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
