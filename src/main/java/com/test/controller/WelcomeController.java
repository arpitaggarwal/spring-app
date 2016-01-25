package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String welcomeUser() {
		return "user";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String welcomeAdmin() {
		return "admin";
	}
}
