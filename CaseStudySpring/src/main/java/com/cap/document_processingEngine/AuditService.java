package com.cap.document_processingEngine;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class AuditService {
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("Audit configuration");
	}
	
	@PreDestroy
	public void predestroy() {
		System.out.println("predestroy has being called");
	}
}
