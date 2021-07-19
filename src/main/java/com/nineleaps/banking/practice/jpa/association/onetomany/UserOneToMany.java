package com.nineleaps.banking.practice.jpa.association.onetomany;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_one_to_many")
@Getter
@Setter
// an example of entity inside entity
// to learn about value class in entities, check UserDetailsCollection, UserDetails etc
public class UserOneToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name_one_to_many")
    private String userName;

    @OneToMany(/*mappedBy = "userOneToMany"*/ ) // for bidirectional
    /*@JoinTable( // used to customize the table name and its columns
    name = "user_vehicle",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "vehicle_id"))*/
    // 3rd table created
    @JoinColumn(name = "vehicle_id")
    private Collection<VehicleOneToMany> vehicleOneToMany = new ArrayList<>();
}
