package com.example.OnlineDeliveryApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.OnlineDeliveryApplication.services.UserService;

@RestController
public class UserController {
	@SuppressWarnings("unused")
	@Autowired
	private UserService userService;
}
