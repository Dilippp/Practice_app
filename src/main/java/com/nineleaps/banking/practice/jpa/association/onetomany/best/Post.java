package com.nineleaps.banking.practice.jpa.association.onetomany.best;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "PostMany")
@Table(name = "post_many")
@Getter
@Setter
// The best way to map a @OneToMany association is to rely on the
// @ManyToOne side to propagate all entity state changes:
// https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
public class Post {
    /**
     * There are several things to note on the aforementioned mapping:
     *
     * <p>The @ManyToOne association uses FetchType.LAZY because, otherwise, we’d fall back to EAGER
     * fetching which is bad for performance. The parent entity, Post, features two utility methods
     * (e.g. addComment and removeComment) which are used to synchronize both sides of the
     * bidirectional association. You should always provide these methods whenever you are working
     * with a bidirectional association as, otherwise, you risk very subtle state propagation
     * issues. The child entity, PostComment, implement the equals and hashCode methods. Since we
     * cannot rely on a natural identifier for equality checks, we need to use the entity identifier
     * instead for the equals method. However, you need to do it properly so that equality is
     * consistent across all entity state transitions, which is also the reason why the hashCode has
     * to be a constant value. Because we rely on equality for the removeComment, it’s good practice
     * to override equals and hashCode for the child entity in a bidirectional association.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostComment> comments = new ArrayList<>();

    // utility method
    public void addComment(PostComment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}
