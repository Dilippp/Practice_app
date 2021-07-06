package com.nineleaps.banking.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class AbstractModel {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_timestamp", nullable = false)
    @UpdateTimestamp
    private Date updatedTimestamp;
}
