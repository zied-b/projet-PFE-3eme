package com.meetingroom.msmeetingroom.Controller;


import com.meetingroom.msmeetingroom.Entity.RoomMeeting;
import com.meetingroom.msmeetingroom.Repo.RepoMeetingRoom;
import com.meetingroom.msmeetingroom.Request.RequestUpdateDescription;
import com.meetingroom.msmeetingroom.Request.RequestUpdateName;
import com.meetingroom.msmeetingroom.Service.Interface.InterfaceMeetingRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URISyntaxException;
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
    @GetMapping("/meetingRoom/{name}")
    public Optional<RoomMeeting> fetchByName(@PathVariable(value = "name") String name) {
        return interfaceMeetingRoom.fetchByName(name);
    }

    @Override
    @GetMapping("/meetingRoom")
    public List<RoomMeeting> fetchAllMeetingRoom() {
        return interfaceMeetingRoom.fetchAllMeetingRoom();
    }

    @Override
    @PostMapping("/meetingRoom")
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    public ResponseEntity<String> addMeetingRoom(@RequestParam String name,
                                                 @RequestParam String description,
                                                 @RequestParam MultipartFile file) throws IOException {
        return interfaceMeetingRoom.addMeetingRoom(name,description,file);

    }

    @Override
    @PutMapping("meetingRoom/update/name")
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    public ResponseEntity<String> updateName(@RequestBody RequestUpdateName updateName) {
        return interfaceMeetingRoom.updateName(updateName);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    @PutMapping("meetingRoom/update/description")
    public ResponseEntity<String> updateDescription(@RequestBody RequestUpdateDescription updateDescription) {
        return interfaceMeetingRoom.updateDescription(updateDescription);
    }

    @Override
    @PreAuthorize("hasAuthority('SCOPE_ASR')")
    @PutMapping("meetingRoom/update/image")
    public ResponseEntity<String> updateImage(@RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        return interfaceMeetingRoom.updateImage(name,file);
    }

    @Override
    public ResponseEntity<?> Delete(Integer id) {
        return interfaceMeetingRoom.Delete(id);
    }


}
