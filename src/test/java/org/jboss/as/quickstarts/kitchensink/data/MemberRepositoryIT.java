package org.jboss.as.quickstarts.kitchensink.data;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class MemberRepositoryIT {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testSaveValidMember() {
        Member member = new Member();
        member.setName("Bob");
        member.setEmail("bob@example.com");
        member.setPhoneNumber("1234567890");

        Member saved = memberRepository.save(member);
        assertNotNull(saved.getId());
    }
}
