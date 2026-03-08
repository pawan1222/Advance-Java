package com.cap.document_processingEngine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class XmlDocumentProcessor implements DocumentProcessor{
	@Override
	public void method() {
		System.out.println("xmlDocumentProcessor class");
	}
}
