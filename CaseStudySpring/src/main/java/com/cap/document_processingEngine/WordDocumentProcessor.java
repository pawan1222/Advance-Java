package com.cap.document_processingEngine;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class WordDocumentProcessor implements DocumentProcessor{
	@Override
	public void method() {
		System.out.println("WordDocumentProcessor class");
	}
}
