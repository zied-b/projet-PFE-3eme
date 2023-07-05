package com.meetingroom.msmeetingroom.Service;


import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import com.meetingroom.msmeetingroom.Repo.RepoMeetingRoom;
import com.meetingroom.msmeetingroom.Request.RequestUpdateMeetingRoom;
import com.meetingroom.msmeetingroom.Service.Interface.InterfaceMeetingRoom;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceMeetingRoom implements InterfaceMeetingRoom {
    private final RepoMeetingRoom repoMeetingRoom;




    @Override
    public List<RoomMeeting> fetchAllMeetingRoom() {
        return repoMeetingRoom.findAll();
    }

    @Override
    public ResponseEntity<?> addMeetingRoom(String name, Integer capacity, String description, String equipment, MultipartFile file) throws IOException {
        Optional<Integer> id = repoMeetingRoom.GetIdByName(name);
        if (id.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        RoomMeeting roomMeeting= new RoomMeeting();
        roomMeeting.setNameRoom(name);
        roomMeeting.setEquipment(equipment);
        roomMeeting.setCapacity(capacity);
        roomMeeting.setImage(file.getBytes());
        roomMeeting.setDescription(description);
        repoMeetingRoom.save(roomMeeting);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> updateMeetingRoom(RequestUpdateMeetingRoom meetingRoom) {
        Optional<RoomMeeting>optionalRoomMeeting = repoMeetingRoom.findById(meetingRoom.getId());
        if (optionalRoomMeeting.isPresent()){
            if(optionalRoomMeeting.get().getNameRoom().equals(meetingRoom.getName())){
                RoomMeeting meeting = optionalRoomMeeting.get();
                meeting.setDescription(meetingRoom.getDescription());
                meeting.setCapacity(meetingRoom.getCapacity());
                meeting.setEquipment(meetingRoom.getEquipment());
                repoMeetingRoom.save(meeting);
                meeting = repoMeetingRoom.findById(meetingRoom.getId()).get();
                return ResponseEntity.ok().body(meeting);

            }else {
                Optional<Integer> id= repoMeetingRoom.GetIdByName(meetingRoom.getName());
                if (id.isPresent()){
                    return ResponseEntity.status(HttpStatus.CONFLICT).build();
                }
                else {
                    RoomMeeting meeting = optionalRoomMeeting.get();
                    meeting.setNameRoom(meetingRoom.getName());
                    meeting.setDescription(meetingRoom.getDescription());
                    meeting.setCapacity(meetingRoom.getCapacity());
                    meeting.setEquipment(meetingRoom.getEquipment());
                    repoMeetingRoom.save(meeting);
                    meeting = repoMeetingRoom.findById(meetingRoom.getId()).get();
                    return ResponseEntity.ok().body(meeting);

                }

            }
        }
       return ResponseEntity.notFound().build();
    }


    @Override
    public ResponseEntity<?> updateImage(Integer id,MultipartFile file) throws IOException {
        if (repoMeetingRoom.findById(id).isPresent()){
            RoomMeeting meetingRoom = repoMeetingRoom.findById(id).get();
            meetingRoom.setImage(file.getBytes());
            repoMeetingRoom.save(meetingRoom);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(repoMeetingRoom.findById(id).get());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<?> Delete(Integer id) {
        if (repoMeetingRoom.findById(id).isPresent()){
            RoomMeeting roomMeeting=repoMeetingRoom.findById(id).get();
            repoMeetingRoom.delete(roomMeeting);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }


}





