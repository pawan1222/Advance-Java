package Payment_service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		PaymentService paymentService=context.getBean(PaymentService.class);
		
		paymentService.processPayment(200);
		
		System.out.println(paymentService.getClass());
		
		context.close();
	}
}
