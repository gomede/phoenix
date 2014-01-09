package br.com.phoenix.lib.core.email.impl;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.phoenix.lib.core.email.AbstractTemplateMethod;

/**
 * @author Everton Gomede <a>everton@sicoobpr.com.br</a>
 * <p>
 * Caso tenha a necessidade de enviar de um servidor especifico
 * override no metodo initialize() com os parametros disponiveis
 *
 * <p>String emailOrigem
 * <br>String nomeEmailOrigem
 * <br>String passEmailOrigem
 * <br>String smtp
 * <br>String mailSmtpHost
 * <br>String mailSmtpAuth
 * <br>String (ver as constantes disponiveis)
 *
 * <p>
 * Caso tenha a necessidade de setar o mime type
 * override no metodo configureMimeType() selecione
 * os tipos disponiveis para a classe
 *
 * <p>TEXT_HTML - formato HTML
 * <br>TEXT_PLAIN - texto puro
 */
public class EmailTemplateMethod extends AbstractTemplateMethod {

	public final static String TEXT_HTML = "text/html";

	public final static String TEXT_PLAIN = "text/plain";

	protected String emailOrigem;

	protected String nomeEmailOrigem;

	protected String passEmailOrigem;

	protected String smtp;

	protected String mailSmtpHost;
	
	protected String mailSmtpPort;

	protected String mailSmtpAuth;

	protected String mimeType;

	private String [] emailsDestino;

	private String subject;

	private String corpoMensagem;

	Map<String, DataSource> attachments;

	@Override
	public void initialize() {
		this.emailOrigem = "gppuel@gmail.com";
		this.nomeEmailOrigem = "GPP UEL";
		this.passEmailOrigem = "gppuel2014";
		this.smtp = "smtp.gmail.com";
		this.mailSmtpHost = "mail.smtp.host";
		this.mailSmtpPort = "mail.smtp.port";
		this.mailSmtpAuth = "mail.smtp.auth";
	}

	@Override
	public void configureMimeType() {
		this.mimeType = TEXT_PLAIN;
	}
	
	public EmailTemplateMethod setMimeType(String string){
		this.mimeType = string;
		return this;
	}

	public EmailTemplateMethod(String emailDestino, String subject, String corpoMensagem, Map<String, DataSource> attachments) {
		initialize();
		configureMimeType();
		this.emailsDestino = new String[]{emailDestino};
		this.subject = subject;
		this.corpoMensagem = corpoMensagem;
		this.attachments = attachments;
	}

	public EmailTemplateMethod(String emailDestino, String subject, String corpoMensagem) {
		initialize();
		configureMimeType();
		this.emailsDestino = new String[]{emailDestino};
		this.subject = subject;
		this.corpoMensagem = corpoMensagem;
	}

	public EmailTemplateMethod(String [] emailsDestino, String subject, String corpoMensagem) {
		initialize();
		configureMimeType();
		this.emailsDestino = emailsDestino;
		this.subject = subject;
		this.corpoMensagem = corpoMensagem;
	}

	@Override
	public final void sendAuthenticationEmail() throws Exception {

		if (logger.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder("Enviando o email para o(s) destinatario(s): ");
			for (String str : emailsDestino) sb.append(str+", ");
			logger.debug(sb.toString().subSequence(0, sb.toString().length()-2));
		}

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		
		final Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailOrigem, passEmailOrigem);
			}
		};

		Session session = Session.getInstance(props, auth);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailOrigem, nomeEmailOrigem));

		for (String email : emailsDestino) {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
		}

		corpoMensagem = Charset.forName("ISO-8859-1").decode(ByteBuffer.wrap(corpoMensagem.getBytes())).toString();

		message.setSubject(subject);
		message.setSentDate(new Date());

		if (attachments != null) {
			MimeBodyPart messagePart = new MimeBodyPart();
			messagePart.setText(corpoMensagem);

			MimeBodyPart attachmentPart = new MimeBodyPart();
			Set<String> filenames = attachments.keySet();
			for (String filename : filenames) {
	            attachmentPart.setDataHandler(new DataHandler(attachments.get(filename)));
	            attachmentPart.setFileName(filename);
			}

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(attachmentPart);

            message.setContent(multipart);
        } else {
        	message.setContent(corpoMensagem, mimeType);
        }


		Transport.send(message);
	}

}