package com.meetingroom.msmeetingroom.Service.Interface;

import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import com.meetingroom.msmeetingroom.Request.RequestUpdateDescription;
import com.meetingroom.msmeetingroom.Request.RequestUpdateName;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface InterfaceMeetingRoom {
    public Optional<RoomMeeting> fetchByName(String name);
    public List<RoomMeeting> fetchAllMeetingRoom();
    public ResponseEntity<String> addMeetingRoom(String name, String description, MultipartFile file) throws IOException;

    public ResponseEntity<String> updateName(RequestUpdateName updateName);
    public ResponseEntity<String> updateDescription(RequestUpdateDescription updateDescription);

    public ResponseEntity<String> updateImage(String name , MultipartFile file) throws IOException;
    ResponseEntity<?>Delete(Integer id);

}
