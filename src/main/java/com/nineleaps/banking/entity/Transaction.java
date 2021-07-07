package com.nineleaps.banking.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "transactions", schema = "dilip")
@Setter
@Getter
public class Transaction extends AbstractModel {

    @Id
    @GeneratedValue(generator = "transaction_gen")
    @SequenceGenerator(name = "transaction_gen",
    sequenceName = "transaction_seq",
    schema = "dilip",
    allocationSize = 1,
    initialValue = 1)
    @Column(name = "transaction_id")
    private Integer id;

    @Column(name = "transaction_type", nullable = false, updatable = false)
    private String type;

    @Column(name = "amount", nullable = false, updatable = false)
    private Double amount;

    @Column(name = "description", length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Account account;
}
