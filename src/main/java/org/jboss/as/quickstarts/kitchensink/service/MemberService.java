package org.jboss.as.quickstarts.kitchensink.service;

import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class MemberService {

    private final Logger log = Logger.getLogger(MemberService.class.getName());

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional
    public void register(Member member) {
        log.info("Registering " + member.getName());
        member = memberRepository.save(member);
        log.info("Created new member with ID: " + member.getId());
        eventPublisher.publishEvent(member); // I don't think this is useful
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAllByOrderByNameAsc();
    }

    public boolean emailAlreadyExists(String email) {
        return memberRepository.findByEmail(email).isPresent();
    }
}
