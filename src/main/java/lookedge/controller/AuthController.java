package lookedge.controller;

import lookedge.form.SignupForm;
import lookedge.model.Account;
import lookedge.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by wgx on 16/9/26.
 */
@Controller
public class AuthController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "signin")
    public String signin() {
        return "signin";
    }
    @RequestMapping(value="signup")
    public String signup(Model model){
        model.addAttribute(new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String signup(@Valid @ModelAttribute SignupForm signupForm, BindingResult errors, RedirectAttributes ra) {
        if(accountService.checkUserExists(signupForm.getEmail())) {
            errors.rejectValue("email", "Duplicate");
        }

        if (errors.hasErrors()) {
            return "signup";
        }

        Account account = accountService.save(signupForm.createAccount());
        accountService.signin(account);
        return "redirect:/";
    }

}
