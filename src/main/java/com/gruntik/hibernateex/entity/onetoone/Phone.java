package com.gruntik.hibernateex.entity.onetoone;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    Long id;

    @Column(name = "`number`")
    String number;

    @OneToOne(mappedBy = "phone", cascade = CascadeType.MERGE)
    PhoneDetail phoneDetail;

    public Phone(Long id, String number) {
        this.id = id;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (id != null ? !id.equals(phone.id) : phone.id != null) return false;
        return number != null ? number.equals(phone.number) : phone.number == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
