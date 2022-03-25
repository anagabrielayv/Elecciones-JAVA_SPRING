package com.elecciones.service;

import com.elecciones.bean.EmailBean;

public interface IEmailService {
	boolean enviarEmailVoto(EmailBean emailBean);
}
