package org.jboss.as.quickstarts.kitchensink.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.Set;

class MemberIT {
        private static Validator validator;

        @BeforeAll
        public static void setupValidator() {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
        }

        @Test
        public void testValidMember() {
            Member member = new Member();
            member.setName("Alice");
            member.setEmail("alice@example.com");
            member.setPhoneNumber("1234567890");

            Set<ConstraintViolation<Member>> violations = validator.validate(member);
            assertTrue(violations.isEmpty(), "No validation errors expected");
        }

        @Test
        public void testNameShouldNotContainNumbers() {
            Member member = new Member();
            member.setName("Alice123");
            member.setEmail("alice@example.com");
            member.setPhoneNumber("1234567890");

            Set<ConstraintViolation<Member>> violations = validator.validate(member);
            assertFalse(violations.isEmpty(), "Expected validation errors due to numbers in name");
        }

        @Test
        public void testEmailShouldNotBeEmpty() {
            Member member = new Member();
            member.setName("Alice");
            member.setEmail("");
            member.setPhoneNumber("1234567890");

            Set<ConstraintViolation<Member>> violations = validator.validate(member);
            assertFalse(violations.isEmpty(), "Expected validation error for empty email");
        }

        @Test
        public void testInvalidPhoneNumberLength() {
            Member member = new Member();
            member.setName("Alice");
            member.setEmail("alice@example.com");
            member.setPhoneNumber("123");

            Set<ConstraintViolation<Member>> violations = validator.validate(member);
            assertFalse(violations.isEmpty(), "Expected validation error due to short phone number");
        }

        @Test
        public void testPhoneNumberWithLetters() {
            Member member = new Member();
            member.setName("Alice");
            member.setEmail("alice@example.com");
            member.setPhoneNumber("abc1234567");

            Set<ConstraintViolation<Member>> violations = validator.validate(member);
            assertFalse(violations.isEmpty(), "Expected validation error due to letters in phone number");
        }
    }
