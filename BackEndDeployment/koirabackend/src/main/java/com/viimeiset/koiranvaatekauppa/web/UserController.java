package com.viimeiset.koiranvaatekauppa.web;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.viimeiset.koiranvaatekauppa.domain.SignupForm;
import com.viimeiset.koiranvaatekauppa.domain.AppUser;
import com.viimeiset.koiranvaatekauppa.domain.AppUserRepository;

import jakarta.validation.*;


@Controller
public class UserController {
	@Autowired
    private AppUserRepository repository; 
	
    @RequestMapping(value = "signup")
    public String addStudent(Model model){
    	model.addAttribute("signupform", new SignupForm());
        return "signup";
    }	
    
    /**
     * Create new user
     * Check if user already exists & form validation
     * 
     * @param signupForm
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
    	if (!bindingResult.hasErrors()) {
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) {		
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);
	
		    	AppUser newUser = new AppUser();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole(signupForm.getRole());
		    	newUser.setEtunimi(signupForm.getEtunimi());
		    	newUser.setSukunimi(signupForm.getSukunimi());
		    	newUser.setSahkoposti(signupForm.getSahkoposti());
		    	
		    	if (repository.findByUsername(signupForm.getUsername()) == null) {
		    		repository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");    	
	    			return "signup";		    		
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");    	
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";    	
    }    
    
    @GetMapping("/muokkaaasiakasta/{userId}")
    public String editUserForm(@PathVariable Long userId, SignupForm signupForm, Model model) {
    Optional<AppUser> user = repository.findById(userId);
    if (user.isPresent()) {
    	model.addAttribute("user", user.get());
    	return "edituser";
    }
    return "asiakaslista";
    }
    
    @PostMapping("/muokkaaasiakasta/{userId}")
    public String editUser(@PathVariable Long userId, AppUser updatedUser, Model model) {
        Optional<AppUser> userOptional = repository.findById(userId);
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

        if (userOptional.isPresent()) {
            AppUser existingUser = userOptional.get();
            existingUser.setEtunimi(updatedUser.getEtunimi());
            existingUser.setSukunimi(updatedUser.getSukunimi());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPasswordHash(bc.encode(updatedUser.getPasswordHash()));
            existingUser.setRole(updatedUser.getRole());
            existingUser.setSahkoposti(updatedUser.getSahkoposti());

            repository.save(existingUser);
            System.out.println(existingUser);
            return "redirect:/asiakaslista";
        } else {
            return "asiakaslista";
        }
    }
    
    @PostMapping("/poistaasiakas/{userId}")
    public String deleteUser(@PathVariable Long userId) {
    	repository.delete(repository.findById(userId).get());
    	return "redirect:/asiakaslista";
    }
}