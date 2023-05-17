package com.reservation.msreservation.Repo;

import com.reservation.msreservation.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Date;


@Repository
public interface RepoReservation extends JpaRepository<Reservation, Integer> {
   @Query("SELECT r FROM " +
           "Reservation r " +
           "WHERE r.email=?1 and " +
           "(r.dateFin > ?2 OR " +
           "r.dateDebut > ?2)" +
           "and r.annulee=false")

    Optional<List<Reservation>> fetchAllReservation(String email,Date currentDate);
    @Query("select r" +
            " from Reservation r " +
            "where " +
            "r.annulee=true and r.email=:email")
    Optional<List<Reservation>> findAllByAnnulee(@Param("email") String email);

    @Query("SELECT r " +
            "FROM Reservation r WHERE" +
            " r.idBien = ?1 AND " +
            "r.TypeBien= ?4 AND "+
            "r.annulee=false " +
            "AND " +
            "((r.dateDebut <= ?2 AND r.dateFin >= ?2) OR" +
            " (r.dateDebut <= ?3 AND r.dateFin >= ?3) OR" +
            " (r.dateDebut >= ?2 AND r.dateFin <= ?3))")
    List<Reservation> findOverlappingReservations(Integer idBien, Date dateDebut, Date dateFin,String typeBien);
    @Query("select  r from " +
            "Reservation  r " +
            "where r.id = ?2 and r.email=?1")
    Optional<Reservation> AnullerReservation(String email,Integer id);
    @Query("select r " +
            "from Reservation r " +
            "where r.email=?1 AND r.id=?2")
    Optional<Reservation>fetchById(String email,Integer id);
    @Query("select  r from " +
            "Reservation r where " +
            "r.id=?1 and r.email=?2 and r.annulee=true")
    Optional<List<Reservation>> getAnnuleeReservation(Integer id,String email);
 @Query("select r from Reservation r WHERE r.email=?1 and " +
         "lower(r.TypeBien) LIKE lower(concat('%', ?2,'%')) " +
         "and r.annulee=false")
 Optional<List<Reservation>> recherche(String email,String recherche);



}
