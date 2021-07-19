package com.nineleaps.banking.practice.jpa.embedded;

// Address don't have meaning of its own
// it's a value object i.e it related to the user and user has a meaning so an entity

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable // it has to be embedded in some classes
// re-usable in different classes like UserDetails, Company, CompanyBranch etc
// these 3 above entities will have Address, so can be shared.
// not an entity, its a value class
public class Address {

    @Column(name = "street_name")
    private String street;

    @Column(name = "city_name")
    private String city;

    @Column(name = "state_name")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;
}
