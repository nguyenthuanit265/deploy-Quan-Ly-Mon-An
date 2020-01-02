package com.myclass;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;

@SpringBootApplication
public class QuanLyCanteenApplication {

	private static final ModelMap model = null;
	private static final HttpServletRequest req = null;

	public static void main(String[] args) {
		//displayUsernameNavbar(model, req);
		SpringApplication.run(QuanLyCanteenApplication.class, args);

	}

//	public static void displayUsernameNavbar(ModelMap model, HttpServletRequest req) {
//		CustomUserDetails employee = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal();
//		System.out.println("name log in home page: " + employee.getUsername());
//		HttpSession session = req.getSession();
//		session.setAttribute("USER_LOGIN", employee.getUsername());
//		model.addAttribute("USER_LOGIN", employee.getUsername());
//	}
}
