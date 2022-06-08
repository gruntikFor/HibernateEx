package com.gruntik.hibernateex.entity.manytomany.onetomany;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "PersonAddress")
@Table
public class PersonAddress implements Serializable {

    @Id
    @ManyToOne
    PersonNew personNew;

    @Id
    @ManyToOne
    AddressNew addressNew;

}
