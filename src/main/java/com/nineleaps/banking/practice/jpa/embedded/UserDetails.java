package com.nineleaps.banking.practice.jpa.embedded;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_details")
@Getter
@Setter
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name")
    private String userName;

    @Embedded // it will tell hibernate that address's fields should be included in the user table
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "home_street_name")),
        @AttributeOverride(name = "city", column = @Column(name = "home_city_name")),
        @AttributeOverride(name = "state", column = @Column(name = "home_state_name")),
        @AttributeOverride(name = "pinCode", column = @Column(name = "home_pin_code")),
    })
    private Address homeAddress;

    // let say same Address class is used for home address and office address
    // then need to override the properties as below

    @Embedded // it will tell hibernate that address's fields should be included in the user table
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "office_street_name")),
        @AttributeOverride(name = "city", column = @Column(name = "office_city_name")),
        @AttributeOverride(name = "state", column = @Column(name = "office_state_name")),
        @AttributeOverride(name = "pinCode", column = @Column(name = "office_pin_code")),
    })
    private Address officeAddress;
}
