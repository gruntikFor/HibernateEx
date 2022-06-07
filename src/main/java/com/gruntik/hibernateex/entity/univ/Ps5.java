package com.gruntik.hibernateex.entity.univ;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ps5")
public class Ps5 {

    @Id
    private Long id;
    private Integer price;

    @OneToOne(mappedBy = "ps5")
    private Store store;

    public Ps5() {
    }

    public Ps5(Long id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Ps5{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
