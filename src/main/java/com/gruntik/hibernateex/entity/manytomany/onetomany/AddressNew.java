package com.gruntik.hibernateex.entity.manytomany.onetomany;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "persons")
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "address_new")
public class AddressNew {

    @Id
    Long id;
    String city;

    @Column(name = "`numberr")
    String number;

    @OneToMany(
            mappedBy = "addressNew",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<PersonAddress> persons = new ArrayList<>();

    public AddressNew(Long id, String city, String number) {
        this.id = id;
        this.city = city;
        this.number = number;
    }

}
