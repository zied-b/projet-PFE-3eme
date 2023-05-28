package com.meetingroom.msmeetingroom.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor

public class RoomMeeting {
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMR;

    @Basic(optional = false)
    private String nameRoom;
    @Basic(optional = false)
    private String description;

    private String Equipment;
    private  Integer Capacity;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] image;
}
