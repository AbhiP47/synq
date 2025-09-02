package com.synq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {
    @RequestMapping("/home")
    public String home(Model model)
    {
        // sending data to view
        model.addAttribute("name","Abhinav");
        model.addAttribute("course","B.Tech");
        model.addAttribute("gitrepo","https://github.com/chotabheeeeem/journalApp/blob/springbootv3/src/main/java/net/engineeringdigest/journalApp/config/SpringSecurity.java");
        System.out.println("Home page handler");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model)
    {
        model.addAttribute("isLogin",false);
        System.out.println("About page loading");
        return "about";
    }

    @RequestMapping("/services")
    public String servicePage()
    {
        System.out.println("Service page loading");
        return "service";
    }
}
