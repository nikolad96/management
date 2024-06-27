package com.example.management.model.audit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.TemporalType.TIMESTAMP;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class AuditLogs {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "object_id")
    private Long objectID;

    @Column(name = "object_type")
    private String objectType;

    @CreatedBy
    private String username;

    @Temporal(TIMESTAMP)
    private Date timestamp;

    @Enumerated(STRING)
    private Action action;
    public AuditLogs(Action action, Long objectID, Date timestamp, String objectType){
        this.action = action;
        this.objectID = objectID;
        this.timestamp = timestamp;
        this.objectType = objectType;
    }
}
