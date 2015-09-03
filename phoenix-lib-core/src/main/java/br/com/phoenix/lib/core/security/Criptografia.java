package br.com.phoenix.lib.core.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Criptografia<T> implements Encryptable<T> {

	@Override
	public T cipher(String text) {
		try {
			final MessageDigest message = MessageDigest.getInstance("MD5");
			final byte[] digest = message.digest(text.getBytes());

			final StringBuilder hexString = new StringBuilder();

			for (byte b : digest) {
				 hexString.append(String.format("%02X", 0xFF & b));
			}
			return (T) hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}
