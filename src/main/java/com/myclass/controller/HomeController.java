package com.myclass.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.dto.CustomUserDetails;
import com.myclass.dto.StatisticDto;
import com.myclass.repository.BillRepository;

@Controller
@RequestMapping("admin/home")
public class HomeController {
	@Autowired
	private BillRepository billRepository;

	@GetMapping("")
	public String index(Model model, HttpServletRequest req) {
		CustomUserDetails employee = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		System.out.println("name log in: " + employee.getUsername());
		HttpSession session = req.getSession();
		session.setAttribute("USER_LOGIN", employee.getUsername());
		
		List<StatisticDto> list = billRepository.statisticAllByYear();
		for (StatisticDto statisticDto : list) {
			System.out.println(statisticDto.toString());
		}
		

		model.addAttribute("bills", list);
		return "home/index";
	}
}
