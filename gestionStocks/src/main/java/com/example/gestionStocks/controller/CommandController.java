package com.example.gestionStocks.controller;

import com.example.gestionStocks.repo.ArticleEntity;
import com.example.gestionStocks.service.CommandService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CommandController {
    @Autowired
    private CommandService service;


    @GetMapping("/store/home")
    public String getStocksPage(HttpSession session, org.springframework.ui.Model model) {
        List<ArticleEntity> commands = service.getCommands();
        model.addAttribute("commands", commands);
        session.setAttribute("commands", service.getCommands());
        return "gestionStocks";
    }

    @PostMapping("/store/reappro")
    public String reapprovisionnement() {
        service.createCommand("Chaise", 20);
        service.createCommand("Table", 2);
        service.createCommand("Rideau", 5);
        return "redirect:/store/home";
    }


}
