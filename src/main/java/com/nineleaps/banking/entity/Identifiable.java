package com.nineleaps.banking.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class Identifiable<T> implements Serializable {

    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Identifiable)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        Identifiable<T> other = (Identifiable<T>) o;
        return getId() != null && Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
