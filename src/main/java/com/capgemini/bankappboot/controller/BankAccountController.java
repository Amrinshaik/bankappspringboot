package com.capgemini.bankappboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankappboot.entities.Customer;
import com.capgemini.bankappboot.exceptions.LowBalanceException;
import com.capgemini.bankappboot.service.BankAccountService;

@Controller
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@RequestMapping(value = "transferAmountPage", method = RequestMethod.GET)
	public String getTransferAmountPage(HttpSession session, HttpServletRequest request, Model model) {
		session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		model.addAttribute("account", customer.getAccount());
		return "transferamount";
	}

	@RequestMapping(value = "/transferAmount", method = RequestMethod.POST)
	public String fundTransfer(HttpSession session, HttpServletRequest request, Model model,
			@RequestParam long fromAccount, @RequestParam long toAccount, @RequestParam double amount)
			throws LowBalanceException {
	//	Customer customer = (Customer) session.getAttribute("customer");
		bankAccountService.fundTransfer(fromAccount, toAccount, amount);
		//session.setAttribute("customer", customer);
		request.setAttribute("success", true);
		return "transfersuccessful";

	}

	@RequestMapping(value = "checkBalancePage", method = RequestMethod.GET)
	public String getCheckBalancePage(HttpSession session, HttpServletRequest request, Model model) {
		session = request.getSession();
		// BankAccount bankaccount;
		Customer customer = (Customer) session.getAttribute("customer");
		double balance = bankAccountService.getBalance(customer.getAccount().getAccountId());
		model.addAttribute("balance", balance);
		// System.out.println("balance");
		return "checkbalance";
	}

	@RequestMapping(value = "displayProfilePage", method = RequestMethod.GET)
	public String getdisplayProfilePage() {
		return "displayprofile";
	}
}
