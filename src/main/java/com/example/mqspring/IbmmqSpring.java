package com.example.mqspring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:content.yml")
@SpringBootApplication
@RestController
@EnableJms
public class IbmmqSpring {

	private static final String SUCCESS = "OK";
	private static final String FAILURE = "FAIL";
	static final String QUEUE = "DEV.QUEUE.1";

	@Value("${text}")
	private String text;
	private final JmsTemplate jmsTemplate;

	public IbmmqSpring(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@GetMapping("send")
	String sendMessageToQueue(){
		try {
			jmsTemplate.convertAndSend(QUEUE, text);
			return SUCCESS;
		} catch(JmsException ex){
			ex.printStackTrace();
			return FAILURE;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(IbmmqSpring.class, args);
	}

	@Bean
	public JmsListenerContainerFactory<?> ConnectionFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}

}
