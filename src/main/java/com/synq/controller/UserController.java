package com.synq.controller;

import com.synq.entity.User;
import com.synq.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/dashboard")
    public String userDashboard()
    {
        return "user/dashboard";
    }

    @GetMapping("/profile")
    public String userProfile(Model model , Authentication authentication)
    {
        String email = authentication.getName();
        User user = userRepo.findByEmail(email).orElse(null);
        model.addAttribute("User",user);
        return "user/profile";

    }
}
