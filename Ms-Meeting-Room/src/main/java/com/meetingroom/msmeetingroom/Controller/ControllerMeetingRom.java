package com.meetingroom.msmeetingroom.Controller;


import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import com.meetingroom.msmeetingroom.Repo.RepoMeetingRoom;
import com.meetingroom.msmeetingroom.Request.RequestUpdateMeetingRoom;
import com.meetingroom.msmeetingroom.Service.Interface.InterfaceMeetingRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ControllerMeetingRom implements InterfaceController{
    private final InterfaceMeetingRoom interfaceMeetingRoom;
    private final RepoMeetingRoom meetingRoom;


    @GetMapping("/meetingRoom/id/{name}")
    public Optional<RoomMeeting> fetchById(@PathVariable String name) {
        return null;
    }


    @Override
    @GetMapping("/meetingRoom")
    public List<RoomMeeting> fetchAllMeetingRoom() {
        return interfaceMeetingRoom.fetchAllMeetingRoom();
    }

    @Override
    @PostMapping("/meetingRoom")
    public ResponseEntity<?> addMeetingRoom(@RequestParam String name,
                                            @RequestParam Integer capacity,
                                            @RequestParam String description,
                                            @RequestParam String equipment,
                                            @RequestParam MultipartFile file) throws IOException {
        return interfaceMeetingRoom.addMeetingRoom(name,capacity,description,equipment,file);
    }


    @Override
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    @PutMapping("meetingRoom/update/information")
    public ResponseEntity<?> updateMeetingRoom(@RequestBody RequestUpdateMeetingRoom meetingRoom) {
        return interfaceMeetingRoom.updateMeetingRoom(meetingRoom);
    }


    @Override
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    @PutMapping("meetingRoom/update/image")
    public ResponseEntity<?> updateImage(@RequestParam Integer id, @RequestParam MultipartFile file) throws IOException {
        return interfaceMeetingRoom.updateImage(id,file);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    @DeleteMapping("meetingRoom/delete")
    public ResponseEntity<?> Delete(@RequestParam Integer id) {
        return interfaceMeetingRoom.Delete(id);
    }

    @GetMapping("/meetingRoom/id")
    public ResponseEntity<?>fetchById(@RequestParam Integer id){
        return ResponseEntity.ok().body(meetingRoom.findById(id));
    }
}
