package com.capgemini.bankappboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankappboot.entities.Customer;
import com.capgemini.bankappboot.exceptions.UserNotFoundException;
import com.capgemini.bankappboot.service.CustomerService;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start() {
		return "welcome";
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "login";
	}

	@RequestMapping(value = "/homePage", method = RequestMethod.GET)
	public String gethomePage() {
		return "home";
	}

	@RequestMapping(value = "/editProfilePage", method = RequestMethod.GET)
	public String getEditPage(HttpSession session, HttpServletRequest request, Model model) {
		session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		model.addAttribute("customer", customer);
		return "editprofile";
	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String updateProfile(@ModelAttribute Customer customer, HttpServletRequest request) {
		if (customerService.updateProfile(customer) != null) {
			return "profileeditsuccess";
		}
		return "error";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Customer customer, HttpServletRequest request, HttpSession session) throws UserNotFoundException {
	//	System.out.println(customer);
		Customer cust = customerService.authenticate(customer);
		//System.out.println(cust);
		session = request.getSession();
		if (cust != null) {
			session.setAttribute("customer", cust);
			return "home";
		}
		return "illegal";
	}

	@RequestMapping(value = "changePasswordPage", method = RequestMethod.GET)
	public String changePasswordPage() {
		return "changepassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String updatePassword(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmPassword) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customerService.updatePassword(customer, oldPassword, newPassword)) {
			session.setAttribute("customer", customer);
			request.setAttribute("passwordeditsuccess", true);
		} else {
			request.setAttribute("passwordeditsuccess", false);
		}
		return "passwordeditsuccess";
	}

	@RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
	public String getlogoutPage(HttpSession session) {
		session.invalidate();
		return "welcome";
	}

}
