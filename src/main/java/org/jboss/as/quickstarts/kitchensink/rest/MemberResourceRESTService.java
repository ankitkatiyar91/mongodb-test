package org.jboss.as.quickstarts.kitchensink.rest;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.jboss.as.quickstarts.kitchensink.data.MemberRepository;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/members")
@Validated
public class MemberResourceRESTService {

    private final Logger log = Logger.getLogger(MemberResourceRESTService.class.getName());

    @Autowired
    private MemberRepository repository;

    @Autowired
    private MemberRegistration registration;

    @GetMapping
    public ResponseEntity<List<Member>> listAllMembers() {
        return ResponseEntity.ok(repository.findAllOrderedByName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> lookupMemberById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMember(@Valid @RequestBody Member member) {
        try {
            if (emailAlreadyExists(member.getEmail())) {
                throw new ValidationException("Email taken");
            }
            registration.register(member);
            return ResponseEntity.status(HttpStatus.CREATED).body(member); // ideally this should give CREATED(201) status and body as member. Maybe downstream need to change
        } catch (ConstraintViolationException e) {
            return createViolationResponse(e);
        } catch (ValidationException e) {
            Map<String, String> response = new HashMap<>();
            response.put("email", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    private ResponseEntity<Map<String, String>> createViolationResponse(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation -> {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    private boolean emailAlreadyExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}