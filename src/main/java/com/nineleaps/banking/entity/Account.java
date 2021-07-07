package com.nineleaps.banking.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name = "accounts", schema = "dilip")
@Getter
@Setter
public class Account extends AbstractModel {

    @Id
    @GeneratedValue(generator = "account_gen")
    @SequenceGenerator(name = "account_gen",
            sequenceName = "account_seq",
            schema = "dilip",
            allocationSize = 1,
            initialValue = 1)
    @Column(name = "account_id")
    private Integer id;

    @Column(name = "account_name", nullable = false, updatable = false)
    private String name;

    @Column(name = "account_type", nullable = false, updatable = false)
    private String type;

    @Column(name = "open_date", nullable = false, updatable = false)
    private LocalDate openDate;
}
