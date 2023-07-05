package com.meetingroom.msmeetingroom.Service.Interface;

import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import com.meetingroom.msmeetingroom.Request.RequestUpdateMeetingRoom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InterfaceMeetingRoom {

    public List<RoomMeeting> fetchAllMeetingRoom();
    public ResponseEntity<?> addMeetingRoom(String name,
                                                 Integer capacity,
                                                 String description,
                                                 String equipment,
                                                 MultipartFile file) throws IOException;

    ResponseEntity<?>  updateMeetingRoom(RequestUpdateMeetingRoom meetingRoom);

    public ResponseEntity<?> updateImage(Integer id , MultipartFile file) throws IOException;
    ResponseEntity<?>Delete(Integer id);

}
