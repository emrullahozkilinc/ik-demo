package com.emr.ikdemobackend.entity;

import com.emr.ikdemobackend.entity.enums.LeaveType;
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

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
public class Dayoff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employee employee;

    @Column(name = "leave_type")
    @Enumerated(EnumType.STRING)
    private LeaveType leaveType;

    @Column(name = "days_of_leave")
    private int daysOfLeave;
    @Column(name = "date_of_start")
    private LocalDateTime dateOfStart;

    @Column(name = "date_of_end")
    private LocalDateTime dateOfEnd;

    @Column(name = "date_of_return")
    private LocalDateTime dateOfReturn;

    private String description;

    @CreationTimestamp
    private LocalDateTime created_at;

    @UpdateTimestamp
    private LocalDateTime updated_at;



    public Dayoff(Employee employee, LeaveType leaveType, int daysOfLeave, LocalDateTime dateOfStart,
                  LocalDateTime dateOfEnd, LocalDateTime dateOfReturn, String description) {
        this.employee = employee;
        this.leaveType = leaveType;
        this.daysOfLeave = daysOfLeave;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.dateOfReturn = dateOfReturn;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dayoff dayoff = (Dayoff) o;
        return id != null && Objects.equals(id, dayoff.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
