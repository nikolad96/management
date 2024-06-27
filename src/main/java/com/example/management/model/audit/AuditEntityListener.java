package com.example.management.model.audit;

import com.example.management.aspect.LoggingAspect;
import com.example.management.repository.AuditLogsRepository;
import com.example.management.util.BeanUtil;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;

import static com.example.management.model.audit.Action.*;

public class AuditEntityListener {

    private static final Logger logger = LoggerFactory.getLogger(AuditEntityListener.class);

    @PostPersist
    public void prePersist(Auditable target){
        perform(CREATE, target.lastModifiedBy, target.getObjectId(), target.getClass().getSimpleName());
    }

    @PostUpdate
    public void preUpdate(Auditable target){
        perform(UPDATE,target.lastModifiedBy, target.getObjectId(), target.getClass().getSimpleName());
    }
    @PostRemove
    public void preRemove(Auditable target){
        perform(DELETE,target.lastModifiedBy, target.getObjectId(), target.getClass().getSimpleName());
    }

    @Transactional
    private void perform(Action action, String username, Long id, String classType){
        Date timeStamp = new Date();
        logger.info("Action {} on {} {} performed by User {} at {}", action, classType, id, username, timeStamp);
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        entityManager.persist(new AuditLogs(action, id, timeStamp, classType));

    }
}
