package com.reservation.msreservation.Service.Interface;

import com.reservation.msreservation.Entity.Reservation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface InterfaceReservation {
    Optional<Reservation>fetchByReservation(Integer id);
    Optional<List<Reservation>> fetchAllReservation();

    Optional<List<Reservation>>fetchAnnulee();
    Optional<List<Reservation>>fetchPast();
    ResponseEntity<?> addReservation(Reservation reservation);
    ResponseEntity<?> annuleeReservation(Integer id);
    public Optional<List<Reservation>> getAnnuleeReservation(Integer id);
    ResponseEntity<?> getDate();
    Optional<List<Reservation>> recherche(String recherche);


}
