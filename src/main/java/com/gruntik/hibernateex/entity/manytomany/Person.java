package com.gruntik.hibernateex.entity.manytomany;

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
@Table(name = "person")
@Entity
public class Person {

    @Id
    Long id;
    String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    List<Address> addresses = new ArrayList<>();

    public Person(Long id, String name) {
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
