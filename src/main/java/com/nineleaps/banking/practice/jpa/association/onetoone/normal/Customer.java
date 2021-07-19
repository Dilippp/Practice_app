package com.nineleaps.banking.practice.jpa.association.onetoone.normal;

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
// One-to-one association that maps a foreign key column
@Setter
@Getter
@Table(name = "customer_normal")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;

    @OneToOne(optional = false)
    @JoinColumn(name = "customer_record_id", unique = true, nullable = false, updatable = false)
    private CustomerRecord customerRecord;
}
