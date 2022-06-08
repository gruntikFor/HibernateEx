package com.gruntik.hibernateex.entity.manytomany.explicitposttag;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToMany(mappedBy = "tags")
    List<Post> posts = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
