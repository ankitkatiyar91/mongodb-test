package org.jboss.as.quickstarts.kitchensink.service;

import jakarta.persistence.EntityManager;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class MemberRegistration {

    private final Logger log = Logger.getLogger(MemberRegistration.class.getName());

    @Autowired
    private EntityManager em;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void register(Member member) {
        log.info("Registering " + member.getName());
        em.persist(member);
        eventPublisher.publishEvent(member);
    }
}
