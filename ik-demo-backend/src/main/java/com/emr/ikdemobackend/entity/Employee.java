package com.emr.ikdemobackend.entity;

import com.emr.ikdemobackend.entity.enums.Levels;
import jakarta.persistence.*;
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
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.CascadeType.REMOVE;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "employee")
    private List<Dayoff> dayoffs;

    @OneToMany(mappedBy = "employee")
    private List<Shift> shifts;

    @OneToMany(mappedBy = "employee")
    private List<Spending> spendings;

    @OneToOne(cascade = REMOVE)
    private EmployeeAddress address;

    private String firstName;

    private String lastName;

    private int nationalId;

    private String position;

    private LocalDate startDate;

    private LocalDate bornDate;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private Levels level;

    private String title;

    private String department;

    private String email;

    private String phone;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;

    public Employee(String firstName, String lastName, int nationalId, String position, LocalDate startDate,
                    LocalDate bornDate, BigDecimal salary, Levels level, String title, String department,
                    String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.position = position;
        this.startDate = startDate;
        this.bornDate = bornDate;
        this.salary = salary;
        this.level = level;
        this.title = title;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return id != null && Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
