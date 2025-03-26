package org.jboss.as.quickstarts.kitchensink.controller;

import jakarta.validation.Valid;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {

    @Autowired
    private MemberRegistration memberRegistration;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newMember", new Member());
        return "register"; // Assumes a Thymeleaf template named "register.html"
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newMember") Member newMember,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            memberRegistration.register(newMember);
            redirectAttributes.addFlashAttribute("message", "Registered! Registration successful.");
        } catch (Exception e) {
            String errorMessage = getRootErrorMessage(e);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }

        return "redirect:/register";
    }

    private String getRootErrorMessage(Exception e) {
        String errorMessage = "Registration failed. See server log for more information";
        Throwable t = e;
        while (t != null) {
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        return errorMessage;
    }
}