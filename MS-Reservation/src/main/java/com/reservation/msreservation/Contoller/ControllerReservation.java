package com.reservation.msreservation.Contoller;

import com.reservation.msreservation.Entity.Reservation;
import com.reservation.msreservation.Service.Interface.InterfaceReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ControllerReservation implements InterfaceReservation {
    private final InterfaceReservation interfaceReservation;
    @GetMapping("/Reservation/{id}")
    @Override
    public Optional<Reservation> fetchByReservation(@PathVariable Integer id) {
        return interfaceReservation.fetchByReservation(id);
    }

    @Override
    @GetMapping("/Reservation")
    public Optional<List<Reservation>> fetchAllReservation() {
        return interfaceReservation.fetchAllReservation();
    }

    @Override
    @GetMapping("/Reservation/Annulee/List")
    public Optional<List<Reservation>> fetchAnnulee() {
        return interfaceReservation.fetchAnnulee();
    }

    @Override
    @GetMapping("/Reservation/Past/List")
    public Optional<List<Reservation>> fetchPast() {
        return interfaceReservation.fetchPast();
    }

    @Override
    @PostMapping("/Reservation")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        return interfaceReservation.addReservation(reservation);
    }

    @Override
    @DeleteMapping("/Reservation/Annulee")
    public ResponseEntity<?> annuleeReservation(@RequestParam Integer id) {
        return interfaceReservation.annuleeReservation(id);
    }

    @Override
    public Optional<List<Reservation>> getAnnuleeReservation(Integer id) {
        return Optional.empty();
    }


    @Override
    @GetMapping("/date")
    public ResponseEntity<?> getDate() {
        return interfaceReservation.getDate() ;
    }

    @Override
    @GetMapping("recherche/{recherche}")
    public Optional<List<Reservation>> recherche(@PathVariable String recherche) {
        return interfaceReservation.recherche(recherche);
    }
}
