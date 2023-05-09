package com.project.cleanarchitecture.application.service.interfaces;

import com.project.cleanarchitecture.domain.model.Payment;

public interface PaymentService {
    
    public Payment createPayment(Double value);
    
    public boolean isPaymentConfirmed(Payment payment);
    
    public boolean isPaymentAmountCorrect(Double value, Payment payment);
    
}
