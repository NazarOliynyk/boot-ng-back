package oktenweb.bootngback.controllers;

import oktenweb.bootngback.models.AccountCredentials;
import oktenweb.bootngback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StartUpController {

//    @GetMapping("/")
//    public String home(){
//        System.out.println("forward:/index.html");
//        return "forward:/index.html";
//
//    }

    @GetMapping("/")
    public String saveUser(){
        return "saveUser";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveUser")
    public String saveUser(AccountCredentials accountCredentials){
        accountCredentials.setPassword(passwordEncoder.encode(accountCredentials.getPassword())); // to encode the pass
        userService.save(accountCredentials);
        return "redirect:/saveUser";
    }


//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }



}
