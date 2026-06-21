package com.promptVault.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.promptVault.exception.PromptNotFoundException;
import com.promptVault.model.Category;
import com.promptVault.model.FlaggedKeyword;
import com.promptVault.model.Prompt;
import com.promptVault.model.PromptSubmission;
import com.promptVault.model.User;
import com.promptVault.repository.CategoryRepository;
import com.promptVault.repository.FlaggedKeywordRepository;
import com.promptVault.repository.PromptRepository;
import com.promptVault.repository.PromptSubmissionRepository;
import com.promptVault.repository.UserRepository;
import com.promptVault.service.PromptModerationService;
import com.promptVault.service.PromptService;
import com.promptVault.service.SimulatedAiService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PromptController {

    private final PromptRepository promptRepository;
    private final PromptService promptService;
    private final PromptModerationService moderationService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final SimulatedAiService simulatedAiService;
    private final PromptSubmissionRepository promptSubmissionRepository;
    private final FlaggedKeywordRepository flaggedKeywordRepository;

    public PromptController(PromptRepository promptRepository, PromptService promptService,
            PromptModerationService moderationService, CategoryRepository categoryRepository,
            UserRepository userRepository, SimulatedAiService simulatedAiService,
            PromptSubmissionRepository promptSubmissionRepository, FlaggedKeywordRepository flaggedKeywordRepository) {
        this.promptRepository = promptRepository;
        this.promptService = promptService;
        this.moderationService = moderationService;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.simulatedAiService = simulatedAiService;
        this.promptSubmissionRepository = promptSubmissionRepository;
        this.flaggedKeywordRepository = flaggedKeywordRepository;
    }

    // @GetMapping({ "/prompts" })
    // public String getAllPrompts(Model model) {
    // List<Prompt> listPrompts = promptRepository.findAll();
    // model.addAttribute("listPrompts", listPrompts);
    // return "index";

    // }

    @GetMapping("/prompts/new")
    public String showCreateForm(Model model) {
        model.addAttribute("prompt", new Prompt());
        model.addAttribute(
                "categories",
                categoryRepository.findAll());

        return "prompts/form";
    }

    @PostMapping("/prompts/save")
    public String savePrompt(@ModelAttribute Prompt prompt, HttpSession session) {

        boolean flagged = moderationService.containsFlaggedKeywords(
                prompt.getPromptText());

        prompt.setFlagged(flagged);

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/login";
        }

        prompt.setOwner(loggedInUser);

        if (prompt.getCategory() != null &&
                prompt.getCategory().getId() != null) {

            Category category = categoryRepository
                    .findById(prompt.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            prompt.setCategory(category);
        } else {
            prompt.setCategory(null);
        }

        prompt.setCreatedAt(LocalDateTime.now());
        prompt.setUpdatedAt(LocalDateTime.now());

        promptService.savePrompt(prompt);

        return "redirect:/dashboard";
    }

    @GetMapping("/prompts/delete/{id}")
    public String deletePrompt(@PathVariable Long id) {
        promptService.deletePrompt(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/prompts/edit/{id}")
    public String showEditPromptForm(@PathVariable Long id, Model model) {

        Prompt prompt = promptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prompt not found"));

        model.addAttribute("prompt", prompt);
        model.addAttribute(
                "categories",
                categoryRepository.findAll());

        return "prompts/edit-prompt";
    }

    @PostMapping("/prompts/update/{id}")
    public String updatePrompt(
            @PathVariable Long id,
            @ModelAttribute Prompt promptDetails) {

        Prompt prompt = promptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prompt not found"));

        prompt.setTitle(promptDetails.getTitle());
        prompt.setPromptText(promptDetails.getPromptText());
        prompt.setShared(promptDetails.getShared());
        // prompt.setCategory(promptDetails.getCategory());
        Category category = categoryRepository
                .findById(promptDetails.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        prompt.setCategory(category);

        prompt.setUpdatedAt(LocalDateTime.now());

        promptRepository.save(prompt);

        return "redirect:/dashboard";
    }

    @GetMapping("/shared-prompts")
    public String sharedPrompts(Model model) {

        model.addAttribute(
                "prompts",
                promptService.findAllShared());

        return "prompts/shared-prompts";
    }

    @PostMapping("/prompts/{id}/submit")
    public String submitPrompt(
            @PathVariable Long id,
            HttpSession session,
            Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        Prompt prompt = promptService.findByIdAndUser(id, user);

        boolean flagged = false;

        String content = prompt.getPromptText().toLowerCase();

        List<FlaggedKeyword> keywords = flaggedKeywordRepository.findAll();
        for (FlaggedKeyword keyword : keywords) {

            if (content.contains(
                    keyword.getKeyword().toLowerCase())) {

                flagged = true;
                prompt.setFlaggedKeyword(keyword.getKeyword());
                break;
            }
        }

        prompt.setFlagged(flagged);
        promptService.savePrompt(prompt);

        String response = simulatedAiService.generateResponse(
                prompt.getPromptText());

        PromptSubmission submission = new PromptSubmission();

        submission.setPrompt(prompt);
        submission.setUser(user);
        submission.setResponseText(response);

        promptSubmissionRepository.save(submission);

        model.addAttribute("prompt", prompt);
        model.addAttribute("response", response);

        if (flagged) {
            model.addAttribute(
                    "warning",
                    "Warning: This prompt may contain sensitive information.");
        }

        return "prompts/ai-response";
    }

    @GetMapping("/history")
    public String history(
            Model model,
            HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        model.addAttribute(
                "submissions",
                promptSubmissionRepository
                        .findByUserOrderBySubmittedAtDesc(user));

        return "prompts/history";
    }

    // // Create a new prompt
    // @PostMapping("/prompts")
    // public Prompt newPrompt(@Valid @RequestBody Prompt newPrompt) {
    // return promptRepository.save(newPrompt);
    // }

    // // Get a Single Book
    // @GetMapping("/prompts/{id}")
    // public Prompt getPromptById(@PathVariable(value = "id") Long promptId) throws
    // PromptNotFoundException {
    // return promptRepository.findById(promptId).orElseThrow(() -> new
    // PromptNotFoundException(promptId));
    // }

    // // Update an Existing Book
    // @PutMapping("/prompts/{id}")
    // public Prompt updatePrompt(@PathVariable(value = "id") Long promptId, @Valid
    // @RequestBody Prompt promptDetails)
    // throws PromptNotFoundException {
    // Prompt prompt = promptRepository.findById(promptId).orElseThrow(() -> new
    // PromptNotFoundException(promptId));
    // prompt.setTitle(promptDetails.getTitle());
    // prompt.setPromptText(promptDetails.getPromptText());
    // // prompt.setIsbn(userDetails.getIsbn());
    // Prompt updatedPrompt = promptRepository.save(prompt);
    // return updatedPrompt;
    // }

    // // Delete a Book
    // @DeleteMapping("/prompts/{id}")
    // public ResponseEntity<?> deletePrompt(@PathVariable(value = "id") Long
    // promptId)
    // throws PromptNotFoundException {
    // Prompt prompt = promptRepository.findById(promptId).orElseThrow(() -> new
    // PromptNotFoundException(promptId));
    // promptRepository.delete(prompt);
    // return ResponseEntity.ok().build();
    // }

}