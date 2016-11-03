package com.winterfell.lacus.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.lang3.StringUtils;

public class MailService {
	private Properties props = new Properties();

	
	private MailService() {
		// 配置发送邮件的环境属性
		/*
		 * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
		 * mail.user / mail.from
		 * smtp.163.com 
		 * smtp.21cn.com 
		 * smtp.sina.com.cn 
		 * smtp.sohu.com 
		 * smtp.126.com
		 */
		
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.163.com");
		// 发件人的账号
		props.put("mail.user", "");
		// 访问SMTP服务时需要提供的密码
		props.put("mail.password", "");
	}

	public void send(MailDatagram datagram) throws MessagingException {
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
		message.setFrom(form);

		// 设置邮件标题
		message.setSubject(datagram.getSubject());

		// 设置收件人
		InternetAddress to = new InternetAddress(datagram.getTo());
		message.setRecipient(RecipientType.TO, to);

		// 设置抄送
		if (StringUtils.isNotBlank(datagram.getCc())) {
			InternetAddress cc = new InternetAddress(datagram.getCc());
			message.setRecipient(RecipientType.CC, cc);
		}

		// 设置密送，其他的收件人不能看到密送的邮件地址
		if (StringUtils.isNotBlank(datagram.getBcc())) {
			InternetAddress bcc = new InternetAddress(datagram.getBcc());
			message.setRecipient(RecipientType.BCC, bcc);
		}

		// 设置邮件的内容体
		message.setContent(datagram.getContent(), "text/html;charset=UTF-8");

		// 发送邮件
		Transport.send(message);
	}
}
