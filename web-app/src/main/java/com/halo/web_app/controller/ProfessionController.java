package com.halo.web_app.controller;

import com.halo.web_app.model.Profession;
import com.halo.web_app.enums.Convention;
import com.halo.web_app.enums.ProfessionType;
import com.halo.web_app.service.ProfessionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/professions")
public class ProfessionController {

    private final ProfessionService professionService;

    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @GetMapping
    public String showListProfessions(Model model,
                                      @ModelAttribute("success") String success,
                                      @ModelAttribute("error") String error) {
        model.addAttribute("professions", professionService.findAll());
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        return "admin/professions/list-professions";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("profession", new Profession());
        model.addAttribute("conventions", Convention.values());
        model.addAttribute("types", ProfessionType.values());
        return "admin/professions/create-profession";
    }

    @PostMapping("/create")
    public String createProfession(@Valid @ModelAttribute Profession profession,
                                   BindingResult br,
                                   Model model,
                                   RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("conventions", Convention.values());
            model.addAttribute("types", ProfessionType.values());
            return "admin/professions/create-profession";
        }
        try {
            professionService.create(profession);
            ra.addFlashAttribute("success", "✅ Profession créée !");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "❌ " + e.getMessage());
            return "redirect:/admin/professions/create";
        }
        return "redirect:/admin/professions";
    }

    @GetMapping("/edit/{id}")
    public String showEditProfession(@PathVariable Long id,
                                     Model model,
                                     RedirectAttributes ra) {
        try {
            model.addAttribute("profession", professionService.byId(id));
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "❌ Profession introuvable");
            return "redirect:/admin/professions";
        }
        model.addAttribute("conventions", Convention.values());
        model.addAttribute("types", ProfessionType.values());
        return "admin/professions/edit-profession";
    }

    @PostMapping("/edit/{id}")
    public String editProfession(@PathVariable Long id,
                                 @Valid @ModelAttribute Profession profession,
                                 BindingResult br,
                                 Model model,
                                 RedirectAttributes ra) {
        if (br.hasErrors()) {
            model.addAttribute("conventions", Convention.values());
            model.addAttribute("types", ProfessionType.values());
            return "admin/professions/edit-profession";
        }
        try {
            professionService.update(id, profession);
            ra.addFlashAttribute("success", "✅ Profession mise à jour !");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "❌ " + e.getMessage());
            return "redirect:/admin/professions/edit/" + id;
        }
        return "redirect:/admin/professions";
    }

    @PostMapping("/delete/{id}")
    public String deleteProfession(@PathVariable Long id, RedirectAttributes ra) {
        try {
            professionService.delete(id);
            ra.addFlashAttribute("success", "✅ Métier supprimé avec succès.");
        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("error", "❌ " + e.getMessage());
        }
        return "redirect:/admin/professions";
    }
}
