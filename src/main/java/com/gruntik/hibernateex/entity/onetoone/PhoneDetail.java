package com.gruntik.hibernateex.entity.onetoone;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "phone_detail")
public class PhoneDetail {

    @Id
    Long id;
    String provider;
    String technology;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "phone_id")
    Phone phone;

    public PhoneDetail(Long id, String provider, String technology) {
        this.id = id;
        this.provider = provider;
        this.technology = technology;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneDetail that = (PhoneDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (provider != null ? !provider.equals(that.provider) : that.provider != null) return false;
        return technology != null ? technology.equals(that.technology) : that.technology == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (technology != null ? technology.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneDetail{" + "id=" + id + ", provider='" + provider + '\'' + ", technology='" + technology + '\'' + '}';
    }
}
