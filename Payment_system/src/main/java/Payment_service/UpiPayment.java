package Payment_service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class UpiPayment implements PaymentService{
		TransactionLogger transactionLogger;
		public UpiPayment(TransactionLogger transactionLogger) {
			this.transactionLogger=transactionLogger;
		}

	    @Override
	    public void processPayment(double amount) {
	        System.out.println("Processing UPI Payment: " + amount);
	    }
}