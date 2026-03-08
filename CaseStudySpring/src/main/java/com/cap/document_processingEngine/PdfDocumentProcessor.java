package com.cap.document_processingEngine;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PdfDocumentProcessor implements DocumentProcessor {
	@Override
	public void method() {
		System.out.println("PdfDocumentProcessor class");
	}
}
