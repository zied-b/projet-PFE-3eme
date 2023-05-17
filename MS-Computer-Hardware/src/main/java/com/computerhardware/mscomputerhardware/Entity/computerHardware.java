package com.computerhardware.mscomputerhardware.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class computerHardware {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idMi;
    private String ref;
    private String nameProduct;
    private String description ;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image ;

}
