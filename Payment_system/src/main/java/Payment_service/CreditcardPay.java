package Payment_service;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
@Lazy
public class CreditcardPay implements PaymentService{
		TransactionLogger transactionLogger;
		public CreditcardPay(TransactionLogger transactionLogger) {
	        this.transactionLogger=transactionLogger;
	    }

	    @Override
	    public void processPayment(double amount) {
	        System.out.println("Processing Credit Card Payment: " + amount);
	    }
}
