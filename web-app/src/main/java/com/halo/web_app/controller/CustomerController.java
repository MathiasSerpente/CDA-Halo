package com.halo.web_app.controller;

import com.halo.web_app.model.Customer;
import com.halo.web_app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model, @ModelAttribute("success") String s, @ModelAttribute("error") String e) {
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("success", s); model.addAttribute("error", e);
        return "admin/customers/list-customers";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customers/create-customer";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Customer customer, BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) return "admin/customers/create-customer";
        try {
            customerService.create(customer);
            ra.addFlashAttribute("success", "✅ Client créé !");
            return "redirect:/admin/customers";
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "❌ " + ex.getMessage());
            return "redirect:/admin/customers/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        try {
            model.addAttribute("customer", customerService.findById(id));
            return "admin/customers/edit-customer";
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "❌ Client introuvable");
            return "redirect:/admin/customers";
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Valid @ModelAttribute Customer customer, BindingResult br, Model model, RedirectAttributes ra) {
        if (br.hasErrors()) return "admin/customers/edit-customer";
        try {
            customerService.update(id, customer);
            ra.addFlashAttribute("success", "✅ Client mis à jour !");
            return "redirect:/admin/customers";
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "❌ " + ex.getMessage());
            return "redirect:/admin/customers/edit/" + id;
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        try {
            customerService.delete(id);
            ra.addFlashAttribute("success", "✅ Client supprimé !");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "❌ " + ex.getMessage());
        }
        return "redirect:/admin/customers";
    }
}
