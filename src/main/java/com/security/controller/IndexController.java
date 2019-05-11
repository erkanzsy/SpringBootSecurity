package com.security.controller;

import com.security.db.UserRepository;
import com.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("admin")
    public String admin(Model model) {
        model.addAttribute("user", new User());
        return "admin";
    }

    @GetMapping("profile")
    public String profil(){
        return "profil";
    }

    @GetMapping("management")
    public String management(){
        return "management";
    }

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @PostMapping("register")
    public String register(@ModelAttribute User user,Model model){
        if (userRepository.findByUsername(user.getUsername()) != null){
            model.addAttribute("user",user);
            model.addAttribute("message","Kullanıcı zaten var");
            return "admin";
        }else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(user.getRoles().toUpperCase());
            user.setPermissions(user.getPermissions().toUpperCase());
            userRepository.save(user);
            model.addAttribute("message",user.getUsername() + " adlı kullanıcı kaydedildi");
            return "index";
        }
    }

}
