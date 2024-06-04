package com.payement;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.Tested;
import mockit.MockUp;
import org.junit.Test;

public class ReservationServiceTest {
    @Tested
    private ReservationService reservationService;

    @Mocked
    private ReservationRepository reservationRepository;

    @Test
    public void testCreateReservation() {
        new Expectations() {{
            reservationRepository.save((Reservation) any);
        }};

        Reservation reservation = reservationService.createReservation(1, "Dinner Reservation");
        assertNotNull(reservation);
        assertEquals("Dinner Reservation", reservation.getDetails());

        new Verifications() {{
            reservationRepository.save((Reservation) any);
            minTimes = 1;
            maxTimes = 1;
        }};
    }

    @Test
    public void testCancelReservation() {
        new Expectations() {{
            reservationRepository.findById(1);
            result = new Reservation(1, "Dinner Reservation");

            reservationRepository.save((Reservation) any);
        }};

        boolean result = reservationService.cancelReservation(1);
        assertTrue(result);

        new Verifications() {{
            reservationRepository.save((Reservation) any);
            minTimes = 1;
            maxTimes = 1;
        }};
    }

    @Test
    public void testCancelReservationWithMockUp() {
        new MockUp<ReservationRepository>() {
            @mockit.Mock
            public Reservation findById(int reservationId) {
                return new Reservation(1, "Mocked Reservation");
            }

            @mockit.Mock
            public void save(Reservation reservation) {
                // Mocked save method
            }
        };

        boolean result = reservationService.cancelReservation(1);
        assertTrue(result);

        new Verifications() {{
            reservationRepository.save((Reservation) any);
            min

