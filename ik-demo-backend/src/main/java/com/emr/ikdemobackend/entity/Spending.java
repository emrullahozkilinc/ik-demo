package com.emr.ikdemobackend.entity;

import com.emr.ikdemobackend.entity.enums.SpendingType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Spending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "spending_type")
    private SpendingType spendingType;

    private BigDecimal amount;

    @Column(name = "receipt_date")
    private LocalDate receiptDate;

    @Column(name = "tax_rate")
    private int taxRate;

    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Spending(Employee employee, SpendingType spendingType,
                    BigDecimal amount, LocalDate receiptDate, int taxRate, String description) {
        this.employee = employee;
        this.spendingType = spendingType;
        this.amount = amount;
        this.receiptDate = receiptDate;
        this.taxRate = taxRate;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Spending spending = (Spending) o;
        return id != null && Objects.equals(id, spending.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
