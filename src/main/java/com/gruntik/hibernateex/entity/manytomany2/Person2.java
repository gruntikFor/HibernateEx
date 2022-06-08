package com.gruntik.hibernateex.entity.manytomany2;

import com.gruntik.hibernateex.entity.manytomany.Address;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "person_2")
@Entity
public class Person2 {

    @Id
    Long id;
    String name;

    @OneToMany(
            mappedBy = "person2",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Address2> addresses = new ArrayList<>();

    public Person2(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
