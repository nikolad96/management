package com.example.management.model;


import com.example.management.model.audit.AuditEntityListener;
import com.example.management.model.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditEntityListener.class)
@Table(name="employees")
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;


    @Override
    public Long getObjectId() {
        return getId();
    }
}
