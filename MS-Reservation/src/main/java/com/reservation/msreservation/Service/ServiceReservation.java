package com.reservation.msreservation.Service;

import com.reservation.msreservation.Entity.Reservation;
import com.reservation.msreservation.Repo.RepoReservation;
import com.reservation.msreservation.Service.Interface.InterfaceReservation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ServiceReservation implements InterfaceReservation {
    private final RepoReservation repoReservation;

    @Override
    public Optional<Reservation> fetchByReservation(Integer id) {
        return repoReservation.fetchById(GetEmail(),id);
    }
    String GetEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public Optional<List<Reservation>> fetchAllReservation() {


        return repoReservation.fetchAllReservation(GetEmail(),new Date());
    }

    @Override
    public Optional<List<Reservation>> fetchAnnulee() {

        return repoReservation.findAllByAnnulee(GetEmail());
    }

    @Override
    public Optional<List<Reservation>> fetchPast() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<?> addReservation(Reservation reservation) {

        List<Reservation> existingReservations = repoReservation.findOverlappingReservations(reservation.getIdBien(), reservation.getDateDebut(), reservation.getDateFin(),reservation.getTypeBien());
        if (!existingReservations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The property is already reserved for the specified date range");
        }

        // Save the reservation to the database
        try {
            repoReservation.save(reservation);
            return ResponseEntity.ok("Reservation added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add reservation: " + e.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> annuleeReservation(Integer id) {
        if (repoReservation.AnullerReservation(GetEmail(),id).isPresent()){
            Reservation reservation= repoReservation.AnullerReservation(GetEmail(),id).get();
            reservation.setAnnulee(true);
            repoReservation.save(reservation);
            return  ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }

    @Override
    public Optional<List<Reservation>> getAnnuleeReservation(Integer id) {
        return repoReservation.getAnnuleeReservation(id,GetEmail());

    }

    @Override
    public ResponseEntity<?> getDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSSS");
        String formattedDate = now.format(formatter);
        return new ResponseEntity<>(formattedDate, HttpStatus.OK);
    }

    @Override
    public Optional<List<Reservation>> recherche(String recherche) {
        return repoReservation.recherche(GetEmail(),recherche);
    }
}
