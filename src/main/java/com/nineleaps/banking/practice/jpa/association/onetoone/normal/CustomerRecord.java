package com.nineleaps.banking.practice.jpa.association.onetoone.normal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "customer_record")
// https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
public class CustomerRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerRecordId;

    @OneToOne(/*optional = false,*/ mappedBy = "customerRecord") // owning side
    // @JoinColumn(name = "customer_id") //no need to use it as we can retrieve both the entities
    public Customer customer;
}
