package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.model.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
public class FrontController {

	@Autowired
	private AuthenticationService authenticationService;
	// TODO: Task 2, Task 3, Task 4, Task 6

	@GetMapping(path="/")
	public String landingPage(HttpSession session) {
		User user = new User();
		session.setAttribute("user", user);
		return "view0";
	}
	
	@PostMapping(path="/login",
				consumes = " application/x-www-form-urlencoded")
	public String login(@Valid User user, BindingResult result) {
		int loginCount = 0;

		if(result.hasErrors()) {
			return "view0";
		}

		try {
		authenticationService.authenticate(user.getUsername(), user.getPassword());} 
		catch(Exception e) {
			user.addAttempt();
			e.printStackTrace();
			return "view0";
		}

		return 
	}




	

	
}	
