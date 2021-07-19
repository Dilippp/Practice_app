package com.nineleaps.banking.practice.jpa.collection;

import com.nineleaps.banking.practice.jpa.embedded.Address;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "user_details_collection")
@Getter
@Setter
// This implementation is the case where user lived in many places till now, including current once
// also.
public class UserDetailsCollection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_name_collection")
    private String userName;

    @ElementCollection(
            fetch = FetchType.EAGER
            /** default is LAZY */
            ) // it will save the addresses in the different table
    // table name would be class name + field name
    // UserDetailsCollection_addresses
    @JoinTable(
            name = "user_address"
            /** default UserDetailsCollection_addresses to user_address */
            ,
            joinColumns =
                    @JoinColumn(
                            name = "user_id"
                            /** default fk, UserDetailsCollection_userId to user_id */
                            )) // not mandatory, hibernate with go with default as above
    // private Set<Address> addresses = new HashSet<>(); //Set doesn't support indexing i.e pk so
    // move to ArrayList
    @GenericGenerator(name = "sequence-gen", strategy = "sequence")
    @CollectionId(
            columns = {@Column(name = "address_id")},
            generator = "sequence-gen",
            type = @Type(type = "long"))
    // specific to hibernate
    private Collection<Address> addresses = new ArrayList<>();
}
