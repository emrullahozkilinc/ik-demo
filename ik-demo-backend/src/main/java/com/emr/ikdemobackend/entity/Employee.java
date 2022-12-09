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

import static jakarta.persistence.CascadeType.ALL;

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

    @OneToOne(cascade = ALL)
    private EmployeeAddress address;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_id", unique = true)
    private Long nationalId;

    private String position;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "born_date")
    private LocalDate bornDate;

    private BigDecimal salary;

    @Enumerated(EnumType.STRING)
    private Levels level;

    private String title;

    private String department;

    private String email;

    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Employee(String firstName, String lastName, Long nationalId, String position, LocalDate startDate,
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
