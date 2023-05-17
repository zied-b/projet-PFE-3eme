package com.meetingroom.msmeetingroom.Service;


import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import com.meetingroom.msmeetingroom.Repo.RepoMeetingRoom;
import com.meetingroom.msmeetingroom.Request.RequestUpdateDescription;
import com.meetingroom.msmeetingroom.Request.RequestUpdateName;
import com.meetingroom.msmeetingroom.Service.Interface.InterfaceMeetingRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceMeetingRoom implements InterfaceMeetingRoom {
    private final RepoMeetingRoom repoMeetingRoom;



    @Override
    public Optional<RoomMeeting> fetchByName(String name) {
        Optional<Integer> getIdByName =repoMeetingRoom.GetIdByName(name);
        if (getIdByName.isPresent()){
            return repoMeetingRoom.findById(getIdByName.get());
        }
        else return Optional.empty();

    }
    @Override
    public List<RoomMeeting> fetchAllMeetingRoom() {
        return repoMeetingRoom.findAll();
    }



    @Override
    public ResponseEntity<String> addMeetingRoom(String name, String description, MultipartFile file) throws IOException {
        Optional<RoomMeeting> optionalMeetingRoom=fetchByName(name);
        if(optionalMeetingRoom.isPresent()){
            return  ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("This name '"+name+"' exists");
        }
        else {
            RoomMeeting meetingRoom=new RoomMeeting();
            meetingRoom.setNameRoom(name);
            meetingRoom.setDescription(description);
            meetingRoom.setImage(file.getBytes());
            repoMeetingRoom.save(meetingRoom);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("This Meeting room '"+name+"' has been created successfully");
        }

    }



    @Override
    public ResponseEntity<String> updateName(RequestUpdateName updateName) {
        Optional<RoomMeeting> CurrentMeetingRome=fetchByName(updateName.getCurrentName());
        if(CurrentMeetingRome.isPresent()){
            Optional<RoomMeeting> newMeetingRoom=fetchByName(updateName.getNewName());
            if (newMeetingRoom.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error : This name '"+updateName.getNewName()+"' exists");
            }
            else {
                RoomMeeting meetingRoom = CurrentMeetingRome.get();
                meetingRoom.setNameRoom(updateName.getNewName());
                repoMeetingRoom.save(meetingRoom);
                return ResponseEntity.status(HttpStatus.OK)
                        .body("update name Meeting Room successfully !");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("this Room meeting'"+updateName.getCurrentName()+"' does not exist");
    }

    @Override
    public ResponseEntity<String> updateDescription(RequestUpdateDescription updateDescription) {
        if (fetchByName(updateDescription.getName()).isPresent()){
            RoomMeeting meetingRoom= fetchByName(updateDescription.getName()).get();
            meetingRoom.setDescription(updateDescription.getDescription());
            repoMeetingRoom.save(meetingRoom);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("update description Meeting Room successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("this Room meeting'"+updateDescription.getName()+"' does not exist");
    }



    @Override
    public ResponseEntity<String> updateImage(String name,MultipartFile file) throws IOException {
        if (fetchByName(name).isPresent()){
            RoomMeeting meetingRoom = fetchByName(name).get();
            meetingRoom.setImage(file.getBytes());
            repoMeetingRoom.save(meetingRoom);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("update Image Meeting Room successfully !");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("this Room meeting'"+name+"' does not exist");
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





