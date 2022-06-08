package com.gruntik.hibernateex.entity.manytomany2;

import com.gruntik.hibernateex.entity.manytomany.Person;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "address_2")
public class Address2 {

    @Id
    Long id;
    String city;

    @Column(name = "`numberr")
    String number;

    @OneToMany(
            mappedBy = "address2",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Person2> persons;

    public Address2(Long id, String city, String number) {
        this.id = id;
        this.city = city;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address2 address = (Address2) o;

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        return number != null ? number.equals(address.number) : address.number == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
