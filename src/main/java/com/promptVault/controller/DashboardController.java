package com.promptVault.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.promptVault.model.User;
import com.promptVault.service.PromptService;

@Controller
public class DashboardController {

    private final PromptService promptService;

    public DashboardController(PromptService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", user.getUsername());
        model.addAttribute("prompts", promptService.getAllPrompts());

        return "dashboard";
    }
}
