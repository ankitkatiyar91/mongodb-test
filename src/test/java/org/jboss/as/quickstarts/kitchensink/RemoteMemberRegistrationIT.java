package org.jboss.as.quickstarts.kitchensink;

import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SpringBootActivator.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RemoteMemberRegistrationIT {

    @Value("${local.server.port}")
    private int port;

    private String getBaseUri() {
        return "http://localhost:" + port + "/api/members";
    }

    @Test
    public void testRegister() {
        RestTemplate restTemplate = new RestTemplate();
        Member newMember = new Member();
        newMember.setName("Jane Doe");
        newMember.setEmail("jane@mailinator.com");
        newMember.setPhoneNumber("2125551234");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Member> request = new HttpEntity<>(newMember, headers);

        ResponseEntity<String> response = restTemplate.exchange(getBaseUri(), HttpMethod.POST, request, String.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
//        assertEquals("", response.getBody());
    }
}
