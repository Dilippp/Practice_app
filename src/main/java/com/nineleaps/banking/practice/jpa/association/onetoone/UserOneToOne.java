package com.nineleaps.banking.practice.jpa.association.onetoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_one_to_one")
@Getter
@Setter
// an example of entity inside entity
// to learn about value class in entities, check UserDetailsCollection, UserDetails etc
public class UserOneToOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name_one_to_one")
    private String userName;

    @OneToOne(optional = false) // association can't be null
    @JoinColumn(name = "vehicle_id") // vehicle_id instead of vehicleOneToOne_vehicle_id
    private VehicleOneToOne vehicleOneToOne;
}
