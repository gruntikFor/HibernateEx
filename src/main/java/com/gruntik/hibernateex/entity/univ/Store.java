package com.gruntik.hibernateex.entity.univ;

import javax.persistence.*;

@Entity
@Table(name = "store")
public class Store {

    @Id
    private Long id;
    private String title;

    @OneToOne
    @JoinColumn(name = "ps5_id")
    Ps5 ps5;

    public Store() {
    }

    public Store(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Ps5 getPs5() {
        return ps5;
    }

    public void setPs5(Ps5 ps5) {
        this.ps5 = ps5;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
