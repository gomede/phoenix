package br.com.phoenix.lib.core.security;

public interface Encryptable<T> {

	T cipher(String text);

}