package com.nineleaps.banking.practice.jpa.association.manytomany;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "PostManyMany")
@Table(name = "post_many_many")
@Getter
@Setter
// Implementing the ManyToMany JPA and Hibernate association using a Set
// Instead of a List, we can use a Set.
// https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
public class Post {

    @Id @GeneratedValue private Long id;

    private String title;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        return id != null && id.equals(((Post) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * If you worry about the lack of a predefined entry order, then you need to use a SortedSet
     * instead of Set while providing either a @SortNatural or a @SortComparator.
     *
     * <p>For instance, if the Tag entity implements Comparable, you can use the @SortNatural
     * annotation as illustrated by the following example: @ManyToMany(cascade =
     * {CascadeType.PERSIST, CascadeType.MERGE}) @JoinTable(name = "post_tag", joinColumns
     * = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "tag_id")
     * ) @SortNatural private SortedSet<Tag> tags = new TreeSet<>();
     */
}
