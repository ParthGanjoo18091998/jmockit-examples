package com.user.poc.reservation;

public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(int userId, String details) {
        Reservation reservation = new Reservation(userId, details);
        reservationRepository.save(reservation);
        return reservation;
    }

    public boolean cancelReservation(int reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation != null) {
            reservation.setCancelled(true);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
}

