package org.jboss.as.quickstarts.kitchensink.controller;

import jakarta.validation.Valid;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MemberController {

    public static final String HOME_PAGE = "index";
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;

    @GetMapping
    public String showMembers(Model model) {
        loadExistingMembers(model);
        model.addAttribute("newMember", new Member()); // For registration form
        return HOME_PAGE;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newMember") Member newMember,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        logger.info("Called register post endpoint for views");
        if (result.hasErrors()) {
            loadExistingMembers(model);
            return HOME_PAGE;
        }

        try {
            if (memberService.emailAlreadyExists(newMember.getEmail())) {
                result.rejectValue("email", "error.email", "Email already taken");
                return HOME_PAGE;
            }
            memberService.register(newMember);
            redirectAttributes.addFlashAttribute("message", "Registered! Registration successful.");
        } catch (Exception e) {
            logger.error("failed to register", e);
            String errorMessage = getRootErrorMessage(e);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        } finally {
            logger.info("Loading models....");
            loadExistingMembers(model);
        }
        return "redirect:/";
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

    private void loadExistingMembers(Model model) {
        List<Member> members = memberService.getAllMembers(); // Fetch members
        model.addAttribute("members", members); // Add to model
    }
}