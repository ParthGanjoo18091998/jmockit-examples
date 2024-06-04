package com.payement;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.Tested;
import mockit.MockUp;
import org.junit.Test;

public class PaymentServiceTest {
    @Tested
    private PaymentService paymentService;

    @Mocked
    private PaymentRepository paymentRepository;

    @Mocked
    private NotificationService notificationService;

    @Test
    public void testProcessPayment() {
        new Expectations() {{
            paymentRepository.save((Payment) any);
            notificationService.notifyUser(anyInt, anyString);
        }};

        boolean result = paymentService.processPayment(1, 100.0);
        assertTrue(result);

        new Verifications() {{
            paymentRepository.save((Payment) any);
            minTimes = 1;
            maxTimes = 1;

            notificationService.notifyUser(anyInt, anyString);
            minTimes = 1;
            maxTimes = 1;
        }};
    }

    @Test
    public void testProcessPaymentWithMockUp() {
        new MockUp<PaymentRepository>() {
            @mockit.Mock
            public void save(Payment payment) {
                // Mocked save method
            }
        };

        new MockUp<NotificationService>() {
            @mockit.Mock
            public void notifyUser(int userId, String message) {
                // Mocked notify method
            }
        };

        boolean result = paymentService.processPayment(1, 200.0);
        assertTrue(result);

        new Verifications() {{
            notificationService.notifyUser(1, "Payment processed for amount: 200.0");
            minTimes = 1;
            maxTimes = 1;
        }};
    }
}

