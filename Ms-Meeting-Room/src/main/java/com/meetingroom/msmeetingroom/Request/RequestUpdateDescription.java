package com.meetingroom.msmeetingroom.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateDescription {
    private String name ;
    private String Description ;
}
