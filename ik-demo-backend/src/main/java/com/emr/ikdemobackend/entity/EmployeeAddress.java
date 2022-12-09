package com.emr.ikdemobackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "employee_address")
public class EmployeeAddress {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String address;

    private String city;

    private String country;

    @Column(name = "postal_code")
    private int postalCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public EmployeeAddress(String address, String city, String country, int postalCode) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EmployeeAddress that = (EmployeeAddress) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
