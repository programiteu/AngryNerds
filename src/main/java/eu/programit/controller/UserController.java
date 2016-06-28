package eu.programit.controller;


import eu.programit.domain.User;
import eu.programit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;


/**
 * Created by udr013 on 12-5-2016.
 */
@Controller
public class UserController {

    @Autowired
    IUserService IUserService;

    @RequestMapping("/register")
    public String registerPage(Model model){
       Collection<User> allUsers = IUserService.getAllUsers();
        for(User user:allUsers){
            System.out.println(user);
        }
        model.addAttribute("user", new User());
        model.addAttribute("allUsers", allUsers);
        return "register";
    }

    @RequestMapping(value = {"/register/save"}, method = RequestMethod.POST)
        public String registerPage( @ModelAttribute("user") User user, Model model) {
        if(user.getPassword().equals(user.getConfirmpassword())&&user.getPassword()!=null) {
            try {
                IUserService.saveUser(user);
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                model.addAttribute("registerError", true);
                user.setUsername(null);
                return "register";
            }
        }else {
            model.addAttribute("passwordError",true);
            user.setPassword(null);
            user.setConfirmpassword(null);
            return "register";
        }
        System.out.println(user);
        System.out.println("saving user");
        model.addAttribute("registered", true);
        return "login";

    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login";//generally it's a good practice to show login screen again.
    }
}
