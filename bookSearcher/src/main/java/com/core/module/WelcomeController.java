package com.core.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class WelcomeController {
	@RequestMapping
	public String main() {
		System.out.println("test");
		return "test";
	}
}
