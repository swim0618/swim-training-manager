package com.example.swim_training_manager.controller;

import com.example.swim_training_manager.entity.TrainingSet;
import com.example.swim_training_manager.entity.enumtype.IntensityType;
import com.example.swim_training_manager.entity.enumtype.StrokeType;
import com.example.swim_training_manager.form.TrainingSetForm;
import com.example.swim_training_manager.service.TrainingSetService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TrainingSetController {

    private final TrainingSetService trainingSetService;

    public TrainingSetController(TrainingSetService trainingSetService) {
        this.trainingSetService = trainingSetService;
    }

    @GetMapping("/sessions/{sessionId}/sets/new")
    public String newForm(@PathVariable("sessionId") Long sessionId, Model model) {
        model.addAttribute("trainingSetForm", new TrainingSetForm());
        model.addAttribute("formAction", "/sessions/" + sessionId + "/sets");
        model.addAttribute("pageTitle", "セット追加");
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("strokeTypes", StrokeType.values());
        model.addAttribute("intensityTypes", IntensityType.values());
        return "sets/form";
    }

    @PostMapping("/sessions/{sessionId}/sets")
    public String create(
            @PathVariable("sessionId") Long sessionId,
            @Valid @ModelAttribute("trainingSetForm") TrainingSetForm trainingSetForm,
            BindingResult bindingResult,
            Principal principal,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/sessions/" + sessionId + "/sets");
            model.addAttribute("pageTitle", "セット追加");
            model.addAttribute("sessionId", sessionId);
            model.addAttribute("strokeTypes", StrokeType.values());
            model.addAttribute("intensityTypes", IntensityType.values());
            return "sets/form";
        }

        trainingSetService.create(principal.getName(), sessionId, trainingSetForm);
        return "redirect:/sessions/" + sessionId;
    }

    @GetMapping("/sets/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Principal principal, Model model) {
        TrainingSet trainingSet = trainingSetService.findById(principal.getName(), id);
        Long sessionId = trainingSet.getTrainingSession().getId();

        model.addAttribute("trainingSetForm", trainingSetService.toForm(trainingSet));
        model.addAttribute("formAction", "/sets/" + id);
        model.addAttribute("pageTitle", "セット編集");
        model.addAttribute("sessionId", sessionId);
        model.addAttribute("strokeTypes", StrokeType.values());
        model.addAttribute("intensityTypes", IntensityType.values());
        return "sets/form";
    }

    @PostMapping("/sets/{id}")
    public String update(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("trainingSetForm") TrainingSetForm trainingSetForm,
            BindingResult bindingResult,
            Principal principal,
            Model model
    ) {
        TrainingSet trainingSet = trainingSetService.findById(principal.getName(), id);
        Long sessionId = trainingSet.getTrainingSession().getId();

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/sets/" + id);
            model.addAttribute("pageTitle", "セット編集");
            model.addAttribute("sessionId", sessionId);
            model.addAttribute("strokeTypes", StrokeType.values());
            model.addAttribute("intensityTypes", IntensityType.values());
            return "sets/form";
        }

        trainingSetService.update(principal.getName(), id, trainingSetForm);
        return "redirect:/sessions/" + sessionId;
    }

    @PostMapping("/sets/{id}/delete")
    public String delete(@PathVariable("id") Long id, Principal principal) {
        TrainingSet trainingSet = trainingSetService.findById(principal.getName(), id);
        Long sessionId = trainingSet.getTrainingSession().getId();

        trainingSetService.delete(principal.getName(), id);
        return "redirect:/sessions/" + sessionId;
    }
}