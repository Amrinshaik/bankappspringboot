package com.capgemini.bankappboot.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankappboot.exceptions.UserNotFoundException;


@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value=UserPrincipalNotFoundException.class)
	public String handleException(UserNotFoundException ex,HttpServletRequest request) {
		request.setAttribute("errorpage",ex.toString());
		return "errorpage";
	}
}

