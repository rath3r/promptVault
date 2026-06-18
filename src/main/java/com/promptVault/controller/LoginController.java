package com.promptVault.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.promptVault.model.User;
import com.promptVault.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpSession session) {

        User user = userRepository.findByUsername(username);

        if (user != null
                && Boolean.TRUE.equals(user.getEnabled())
                && user.getPassword().equals(password)) {

            session.setAttribute("loggedInUser", user);
            model.addAttribute("username", user.getUsername());

            return "redirect:dashboard";
        }

        return "login";
    }
}
