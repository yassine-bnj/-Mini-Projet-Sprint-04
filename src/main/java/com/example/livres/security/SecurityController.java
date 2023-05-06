package com.example.livres.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {
@GetMapping("/accessDenied")
public String error()
{
return "accessDenied";
}


@GetMapping("/login")
public String login(@RequestParam(required = false) String error,ModelMap model)
{
	
	  if (error != null) {
	        model.addAttribute("errorMessage", "veillez verifier votre username ou/et votre mot de passe!");
	    }
	
return "login";
}


}
