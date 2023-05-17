package com.computerhardware.mscomputerhardware.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterielInformatique {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
    private Integer idMi;
    private String ref;
    private String nameProduct;
    private String description ;


}
