package com.nineleaps.banking.practice.jpa.inheritance.joined_table;

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
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle_Joined_Table {

    @Id @GeneratedValue private int vehicleId;
    private String vehicleNameJoinedTable;
}
