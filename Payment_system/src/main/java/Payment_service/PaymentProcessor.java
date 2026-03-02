package Payment_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {
	

	PaymentService paymentservice;
	

	@Autowired
	TransactionLogger transactionlogger;
	
	public PaymentProcessor( @Qualifier("upiPayment") PaymentService paymentservice) {
		// TODO Auto-generated constructor stub
		this.paymentservice=paymentservice;
	}
}
