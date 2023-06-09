package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.model.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
public class FrontController {

	@Autowired
	private AuthenticationService authenticationService;
	// TODO: Task 2, Task 3, Task 4, Task 6

	//Getting the landingpage
	@GetMapping(path="/")
	public String landingPage(HttpSession session, Model model) {
		User user = new User();
		session.setAttribute("user", user);
		model.addAttribute("user", user);
		return "view0";
	}
	
	@PostMapping(path="/login",
				consumes = "application/x-www-form-urlencoded")
	public String login(@Valid User user, BindingResult result, Model model, HttpSession session, RedirectAttributes rdrAttr) {

		User previousUser = (User) session.getAttribute("user");

		//display any errors with validation
		if(result.hasErrors()) {
			return "view0";
		}

		//Check if Captcha is correct if there is a captcha i.e. failed one or more attempts
		if (user.getLoginAttempts() > 0 && !user.isCorrect()) {
			user.addAttempt();
			return "view0";
		}

		//Check if user is on the disabled list
		if (authenticationService.isLocked(user.getUsername())) {
			return "view2";
		}

		//try catch block to countercheck number of login attempts.
		//Catch block used to generate different view for different scenarios i.e. failed attempts == 3
		try {
		authenticationService.authenticate(user.getUsername(), user.getPassword());} 
		catch(Exception e) {
			user.addAttempt();
			//Creating new Captcha after failed attempt
			if (user.getLoginAttempts() == 3) {
				authenticationService.disableUser(user.getUsername());
				model.addAttribute("user", user);
				return "view2";
			}
			e.printStackTrace();
			return "view0";
		}

		//set user to be authenticated
		user.authenticated();
		session.setAttribute("user", user);
		rdrAttr.addFlashAttribute("user", user);
		return "view1";
	}




	

	
}	
