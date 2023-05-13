package com.project.cleanarchitecture.application.service.interfaces;

import com.project.cleanarchitecture.domain.model.Payment;
import com.project.cleanarchitecture.domain.model.Subscription;

public interface PaymentService {
    
    public Payment createPayment(Double value);
    
    public Payment updatePayment(Payment payment, Subscription subscription);
    
    public boolean isPaymentConfirmed(Payment payment);
    
    public boolean isPaymentAmountCorrect(Double value, Payment payment);
    
}
