package com.user.poc.payement;

public class PaymentService {
    private PaymentRepository paymentRepository;
    private NotificationService notificationService;

    public PaymentService(PaymentRepository paymentRepository, NotificationService notificationService) {
        this.paymentRepository = paymentRepository;
        this.notificationService = notificationService;
    }

    public boolean processPayment(int userId, double amount) {
        Payment payment = new Payment(userId, amount);
        paymentRepository.save(payment);
        notificationService.notifyUser(userId, "Payment processed for amount: " + amount);
        return true;
    }
}
