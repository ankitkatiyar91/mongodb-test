package org.jboss.as.quickstarts.kitchensink;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")
public class MemberRegistrationIT {

    @Autowired
    private MemberService memberService;

    @Test
    public void testRegister() throws Exception {
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane1@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        memberService.register(newMember);
        assertNotNull(newMember.getId(), "Member ID should not be null after registration");
    }
}