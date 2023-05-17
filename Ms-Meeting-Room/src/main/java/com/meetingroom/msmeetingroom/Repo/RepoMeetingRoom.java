package com.meetingroom.msmeetingroom.Repo;


import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RepoMeetingRoom extends JpaRepository<RoomMeeting,Integer> {
    Optional<RoomMeeting> findByNameRoom(String name);
    @Query("SELECT rm.idMR FROM RoomMeeting rm WHERE rm.nameRoom = :name")
    Optional<Integer> GetIdByName(@Param("name") String name);
    Optional<RoomMeeting> readAllByNameRoom(String name);
}
