package org.jboss.as.quickstarts.kitchensink.data;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberListProducer {

    @Autowired
    private MemberRepository memberRepository;

    @Getter
    private List<Member> members;

    @EventListener
    public void onMemberListChanged(Member member) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        members = memberRepository.findAllByOrderByNameAsc();
    }

}