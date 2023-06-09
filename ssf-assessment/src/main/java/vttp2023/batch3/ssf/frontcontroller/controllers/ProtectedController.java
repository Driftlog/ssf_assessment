package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import vttp2023.batch3.ssf.frontcontroller.model.User;

@Controller
@SessionAttributes("user")
@RequestMapping(path="/protected")
public class ProtectedController {

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected

	@GetMapping(path="{resourceID}")
	public String getResource(@PathVariable(name="resourceID", required = false) String resourceID, 
					@ModelAttribute("user") final User user) {
		if (user.isAuthenticated()) {
			return "view1";
		} else {
			return "view0";
		}
	}

}
