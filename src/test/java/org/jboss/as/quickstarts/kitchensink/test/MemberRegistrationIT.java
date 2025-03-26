package org.jboss.as.quickstarts.kitchensink.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.logging.Logger;

@SpringBootTest
public class MemberRegistrationIT {

    @Autowired
    private MemberRegistration memberRegistration;

    @Autowired
    private Logger log;

    @Test
    public void testRegister() throws Exception {
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        memberRegistration.register(newMember);
        assertNotNull(newMember.getId(), "Member ID should not be null after registration");

        log.info(newMember.getName() + " was persisted with id " + newMember.getId());
    }
}