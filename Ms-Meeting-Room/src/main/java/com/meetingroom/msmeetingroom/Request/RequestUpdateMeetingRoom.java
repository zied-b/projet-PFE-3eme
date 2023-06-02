package com.meetingroom.msmeetingroom.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateMeetingRoom {
    private Integer id;
    private  String name;
    private  String equipment;
    private  String  description;
    private  Integer capacity;
}
