package com.nineleaps.banking.practice.jpa.inheritance.table_per_class;

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
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicle_Table_Per_Class {

    @Id @GeneratedValue private int vehicleId;
    private String vehicleNameTablePerClass;
}
