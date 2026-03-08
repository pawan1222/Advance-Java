package com.cap.document_processingEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DocumentEngine {
	DocumentProcessor documentProcessor;
	
	@Autowired
	public DocumentEngine(@Qualifier("xmlDocumentProcessor")XmlDocumentProcessor xmlDocumentProcessor, DocumentProcessor documentProcessor) {
		this.documentProcessor = documentProcessor;
	}
	
	@Autowired
	StorageService storageService;
	
	AuditService auditService;
	
	@Autowired
	public void setAuditService(AuditService auditService) {
		this.auditService=auditService;
	}
}
