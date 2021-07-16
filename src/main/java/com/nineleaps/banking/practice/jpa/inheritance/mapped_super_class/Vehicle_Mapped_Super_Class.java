package com.nineleaps.banking.practice.jpa.inheritance.mapped_super_class;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

// @Entity An entity can't be annotated with @Entity and @MappedSuperclass
// As we can't create a table for parent entity in this inheritance strategy
@Getter
@Setter
@MappedSuperclass
public class Vehicle_Mapped_Super_Class {

    @Id @GeneratedValue private int vehicleId;
    private String vehicleNameMappedSuperClass;
}
