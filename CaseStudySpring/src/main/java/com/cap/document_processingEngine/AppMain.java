package com.cap.document_processingEngine;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(Config.class);
		
		DocumentProcessor documentProcessor=context.getBean(DocumentProcessor.class);
		documentProcessor.method();
		
		context.close();
	}
}
