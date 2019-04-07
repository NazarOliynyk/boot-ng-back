package oktenweb.bootngback.controllers;

import oktenweb.bootngback.models.AccountCredentials;
import oktenweb.bootngback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

//    @GetMapping("/")
//    public String home(){
//        return "home";
//    }

    @GetMapping("/get")
    public String get(){
        return "get it";
    }
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @CrossOrigin(origins = "*")
    @PostMapping("/saveUser")
    public AccountCredentials saveUser(@RequestBody AccountCredentials accountCredentials){
        System.out.println("Account Credentials Object: "+accountCredentials.getUsername());
        System.out.println("Account Credentials Object: "+accountCredentials.getPassword());
        accountCredentials.setPassword(passwordEncoder.encode(accountCredentials.getPassword())); // to encode the pass
        userService.save(accountCredentials);

        AccountCredentials accountCred = new AccountCredentials();
        accountCred.setUsername(accountCredentials.getUsername());
        accountCred.setPassword(accountCredentials.getPassword());
        return accountCred;
    }

}
