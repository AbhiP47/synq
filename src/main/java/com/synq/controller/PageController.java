package com.synq.controller;

import com.synq.entity.User;
import com.synq.forms.UserForm;
import com.synq.service.UserService;
import com.synq.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {
    @Autowired
    private UserServiceImpl userService;

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
        return "services";
    }

    @GetMapping("/contact")
    public String contact()
    {
        System.out.println("contact");
        return "contact";
    }

    @GetMapping("/login")
    public String login()
    {
        System.out.println("login");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }

    @RequestMapping(value="/do-register", method= RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm , HttpSession session) // Data Binding
    {
        System.out.println(userForm );
        /*
        Steps:
        fetch  the form data
        validate form data
        save to database
        message for successful registration
        redirection to login page
         */
//        User user = User.builder()
//                .name(userForm.getName())
//                        .email(userForm.getEmail())
//                                .password(userForm.getPassword())
//                                        .about(userForm.getAbout())
//                                                .phoneNumber(userForm.getPhoneNumber())
//                                                        .profilePic("https://imgs.search.brave.com/J1XPhA-Al_wGSPHxqwWvH0IVUHufLQHGn-wKxuV9ScE/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vb3Jp/Z2luYWxzLzI0LzIz/Lzk4LzI0MjM5ODY2/Yzg0OTUxNTg2NjRi/OWQyZjM4NWMxYzM5/LmpwZw")
//                                                                .build();
//
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://imgs.search.brave.com/J1XPhA-Al_wGSPHxqwWvH0IVUHufLQHGn-wKxuV9ScE/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pLnBp/bmltZy5jb20vb3Jp/Z2luYWxzLzI0LzIz/Lzk4LzI0MjM5ODY2/Yzg0OTUxNTg2NjRi/OWQyZjM4NWMxYzM5/LmpwZw");

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser);
        session.setAttribute("message","Registration Successful");
        return "redirect:/register";
    }

}
