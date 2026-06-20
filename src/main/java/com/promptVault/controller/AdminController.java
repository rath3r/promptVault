package com.promptVault.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.promptVault.model.Category;
import com.promptVault.model.FlaggedKeyword;
import com.promptVault.model.Prompt;
import com.promptVault.model.User;
import com.promptVault.repository.CategoryRepository;
import com.promptVault.repository.FlaggedKeywordRepository;
import com.promptVault.repository.PromptRepository;
import com.promptVault.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PromptRepository promptRepository;
    private final FlaggedKeywordRepository flaggedKeywordRepository;

    public AdminController(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            PromptRepository promptRepository,
            FlaggedKeywordRepository flaggedKeywordRepository) {

        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.promptRepository = promptRepository;
        this.flaggedKeywordRepository = flaggedKeywordRepository;
    }

    @GetMapping("/admin")
    public String adminDashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null || !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        return "admin/dashboard";
    }

    @GetMapping("/admin/users")
    public String manageUsers(HttpSession session, Model model) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null ||
                !"ADMIN".equals(loggedInUser.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("users",
                userRepository.findAll());

        return "admin/users";
    }

    @PostMapping("/admin/users/{id}/enable")
    public String enableUser(
            @PathVariable Long id,
            HttpSession session) {

        User admin = (User) session.getAttribute("loggedInUser");

        if (admin == null ||
                !"ADMIN".equals(admin.getRole())) {
            return "redirect:/login";
        }

        User user = userRepository.findById(id).orElseThrow();

        user.setEnabled(true);

        userRepository.save(user);

        return "redirect:/admin/users";
    }

    @PostMapping("/admin/users/{id}/disable")
    public String disableUser(
            @PathVariable Long id,
            HttpSession session) {

        User admin = (User) session.getAttribute("loggedInUser");

        if (admin == null ||
                !"ADMIN".equals(admin.getRole())) {
            return "redirect:/login";
        }

        User user = userRepository.findById(id).orElseThrow();

        if (user.getId().equals(admin.getId())) {
            return "redirect:/admin/users";
        }

        user.setEnabled(false);

        userRepository.save(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/categories")
    public String categories(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("categories",
                categoryRepository.findAll());

        model.addAttribute("category",
                new Category());

        return "admin/categories";
    }

    @PostMapping("/admin/categories")
    public String createCategory(
            @ModelAttribute Category category,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        categoryRepository.save(category);

        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/categories/{id}/delete")
    public String deleteCategory(
            @PathVariable Long id,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        categoryRepository.deleteById(id);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/prompts")
    public String allPrompts(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute(
                "prompts",
                promptRepository.findAll());

        return "admin/prompts";
    }

    @GetMapping("/admin/prompts/flagged")
    public String flaggedPrompts(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute(
                "prompts",
                promptRepository.findByFlaggedTrue());

        model.addAttribute(
                "categories",
                categoryRepository.findAll());

        return "admin/flagged-prompts";
    }

    @PostMapping("/admin/prompts/{promptId}/category")
    public String assignCategory(
            @PathVariable Long promptId,
            @RequestParam Long categoryId,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        Prompt prompt = promptRepository.findById(promptId)
                .orElseThrow();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow();

        prompt.setCategory(category);

        promptRepository.save(prompt);

        return "redirect:/admin/prompts/flagged";
    }

    @GetMapping("/admin/flagged-keywords")
    public String flaggedKeyword(
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        model.addAttribute("flaggedKeywords",
                flaggedKeywordRepository.findAll());

        model.addAttribute("flaggedKeyword",
                new FlaggedKeyword());

        return "admin/flagged-keywords";
    }

    @PostMapping("/admin/flagged-keywords")
    public String createFlaggedKeyword(
            @ModelAttribute FlaggedKeyword flaggedKeyword,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        flaggedKeywordRepository.save(flaggedKeyword);

        return "redirect:/admin/flagged-keywords";
    }

    @PostMapping("/admin/flagged-keywords/{id}/delete")
    public String deleteFlaggedKeyword(
            @PathVariable Long id,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null ||
                !"ADMIN".equals(user.getRole())) {
            return "redirect:/login";
        }

        flaggedKeywordRepository.deleteById(id);

        return "redirect:/admin/flagged-keywords";
    }
}