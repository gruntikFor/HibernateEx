package com.gruntik.hibernateex.entity.manytomanynew;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "addresses")
@NoArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "person_new")
@Entity
public class PersonNew {

    @Id
    Long id;
    String name;

    @OneToMany(
            mappedBy = "personNew",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<PersonAddress> addresses = new ArrayList<>();

    public PersonNew(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addAddress(AddressNew address) {
        PersonAddress personAddress = new PersonAddress(this, address);
        addresses.add(personAddress);
        address.getPersons().add(personAddress);
    }

    public void removeAddress(AddressNew address) {
        PersonAddress personAddress = new PersonAddress(this, address);
        address.getPersons().remove(personAddress);
        addresses.remove(personAddress);
        personAddress.setPersonNew(null);
        personAddress.setAddressNew(null);
    }

}
