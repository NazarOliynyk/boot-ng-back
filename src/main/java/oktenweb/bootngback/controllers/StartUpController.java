package oktenweb.bootngback.controllers;

import oktenweb.bootngback.dao.ContactDAO;
import oktenweb.bootngback.models.AccountCredentials;
import oktenweb.bootngback.models.Contact;
import oktenweb.bootngback.services.ContactService;
import oktenweb.bootngback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        return "saveContact";
    }

//    @Autowired
//    private UserService userService;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @CrossOrigin(origins = "*")
//    @PostMapping("/saveUser")
//    public AccountCredentials saveUser(@RequestBody AccountCredentials accountCredentials){
//        System.out.println("Account Credentials Object: "+accountCredentials.getUsername());
//        System.out.println("Account Credentials Object: "+accountCredentials.getPassword());
//        accountCredentials.setPassword(passwordEncoder.encode(accountCredentials.getPassword())); // to encode the pass
//        userService.save(accountCredentials);
//
//        AccountCredentials accountCred = new AccountCredentials();
//        accountCred.setUsername(accountCredentials.getUsername());
//        accountCred.setPassword(accountCredentials.getPassword());
//        return accountCred;
//    }

    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactDAO contactDAO;
    @CrossOrigin(origins = "*")
    @PostMapping("/saveContact")
    public String saveContact(@RequestBody Contact contact){
        System.out.println("contatct: "+contact.toString());
        contactDAO.save(contact);
        return "redirect:/";
    }
   // registration controller does not return value to
   // the front end unless it is RestController
   // that is why it is moved to MainController
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }
}
