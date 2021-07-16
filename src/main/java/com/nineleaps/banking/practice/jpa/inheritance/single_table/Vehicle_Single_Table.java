package com.nineleaps.banking.practice.jpa.inheritance.single_table;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_name", discriminatorType = DiscriminatorType.STRING)
public class Vehicle_Single_Table {

    @Id @GeneratedValue private int vehicleId;
    private String vehicleNameSingleTable;
}
