package com.nineleaps.banking.practice.jpa.inheritance.default_behavior;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vehicle_Default {

    @Id @GeneratedValue private int vehicleId;
    private String vehicleNameDefault;
}
