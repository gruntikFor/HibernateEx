package com.gruntik.hibernateex.explicitposttag;

import com.gruntik.hibernateex.entity.manytomany.explicitposttag.Post;
import com.gruntik.hibernateex.entity.manytomany.explicitposttag.Tag;
import com.gruntik.hibernateex.service.CommonServices;
import com.gruntik.hibernateex.util.HibernateUtil;
import org.assertj.core.util.Lists;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PersonAddressTests {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Session session = sessionFactory.openSession();

    static CommonServices cs = new CommonServices();

    static {
        cs.setSession(session);
    }

    @Test
    void saveToManyToMany() {
        System.out.println("------------------------");

        Post post1 = new Post("apple post");
        Post post2 = new Post("samsung post");

        Tag tag = new Tag("tech");
        Tag tag2 = new Tag("giant");
        Tag tag3 = new Tag("fruit");

        post1.getTags().addAll(Lists.newArrayList(tag, tag2, tag3));
        post2.getTags().addAll(Lists.newArrayList(tag));

        cs.saveEntity(tag);
        cs.saveEntity(tag2);
        cs.saveEntity(tag3);

        cs.saveEntity(post1);
        cs.saveEntity(post2);

        List<Post> posts = cs.findAllByClass(Post.class);
        System.out.println(posts);

        for (Post post : posts) {
            System.out.println(post.getTags());
        }

        System.out.println("****delete");
        posts.get(0).getTags().remove(tag3);
        System.out.println("****delete");

        List<Post> postsUpdated = cs.findAllByClass(Post.class);
        System.out.println(postsUpdated);

        for (Post post : postsUpdated) {
            System.out.println(post.getTags());
        }

        System.out.println("------------------------");
    }

}
