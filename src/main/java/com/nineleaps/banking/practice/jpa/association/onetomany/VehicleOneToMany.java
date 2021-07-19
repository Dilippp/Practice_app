package com.nineleaps.banking.practice.jpa.association.onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle_one_to_many")
@Getter
@Setter
public class VehicleOneToMany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id")
    private int vehicleId;

    @Column(name = "vehicle_name_many")
    private String vehicleName;

    @ManyToOne // one user can have many vehicle
    private UserOneToMany userOneToMany;
}
