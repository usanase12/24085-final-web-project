package io.cedricksonga.springpro.controller;

import io.cedricksonga.springpro.beans.UserRegistrationBean;
import io.cedricksonga.springpro.model.User;
import io.cedricksonga.springpro.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Lazy
@Controller
@RequestMapping("/registration")
public class UserController {
    private final UserInterface userInterface;
    @Autowired
    public UserController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
    @GetMapping
    public String showRegistrationForm(){
        return "signup";
    }

    @ModelAttribute("user")
    public UserRegistrationBean userRegistrationBean(){
        return new UserRegistrationBean();
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user")UserRegistrationBean bean){
        User foundUser = userInterface.findByEmail(bean.getEmail());
        if (foundUser != null && (foundUser.getEmail().equalsIgnoreCase(bean.getEmail()))) {
            return "redirect:/registration?error";
        }
        userInterface.saveAccount(bean);
        return "redirect:/registration?success";

    }

}
