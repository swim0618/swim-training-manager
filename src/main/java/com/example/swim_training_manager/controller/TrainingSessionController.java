package com.example.swim_training_manager.controller;

import com.example.swim_training_manager.entity.TrainingSession;
import com.example.swim_training_manager.form.TrainingSessionForm;
import com.example.swim_training_manager.service.TrainingSessionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/sessions")
public class TrainingSessionController {

    private final TrainingSessionService trainingSessionService;

    public TrainingSessionController(TrainingSessionService trainingSessionService) {
        this.trainingSessionService = trainingSessionService;
    }

    @GetMapping
    public String list(Model model, Principal principal) {
        model.addAttribute("sessions", trainingSessionService.findAllByLoginUser(principal.getName()));
        return "sessions/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("trainingSessionForm", new TrainingSessionForm());
        model.addAttribute("formAction", "/sessions");
        model.addAttribute("pageTitle", "練習記録 新規登録");
        return "sessions/form";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute TrainingSessionForm trainingSessionForm,
            BindingResult bindingResult,
            Principal principal,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/sessions");
            model.addAttribute("pageTitle", "練習記録 新規登録");
            return "sessions/form";
        }

        TrainingSession saved = trainingSessionService.create(principal.getName(), trainingSessionForm);
        return "redirect:/sessions/" + saved.getId();
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Principal principal, Model model) {
        TrainingSession session = trainingSessionService.findById(principal.getName(), id);
        model.addAttribute("session", session);
        model.addAttribute("sets", trainingSessionService.findSets(id, principal.getName()));
        return "sessions/detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Principal principal, Model model) {
        TrainingSession session = trainingSessionService.findById(principal.getName(), id);
        model.addAttribute("trainingSessionForm", trainingSessionService.toForm(session));
        model.addAttribute("formAction", "/sessions/" + id);
        model.addAttribute("pageTitle", "練習記録 編集");
        return "sessions/form";
    }

    @PostMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute TrainingSessionForm trainingSessionForm,
            BindingResult bindingResult,
            Principal principal,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/sessions/" + id);
            model.addAttribute("pageTitle", "練習記録 編集");
            return "sessions/form";
        }

        trainingSessionService.update(principal.getName(), id, trainingSessionForm);
        return "redirect:/sessions/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Principal principal) {
        trainingSessionService.delete(principal.getName(), id);
        return "redirect:/sessions";
    }
}