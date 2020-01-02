package com.myclass.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myclass.entity.Employee;
import com.myclass.entity.Product;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("")
	public String get(Model model) {
		List<User> users = userRepository.myFindAll();
		users.remove(0);
		model.addAttribute("users", users);
		
		return "user/index";
	}
	@GetMapping("add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		
		return "user/add";
	}

	@PostMapping("add")
	public String add(Model model, @Valid @ModelAttribute("user") User user, BindingResult errors) {
		// Kiểm tra nhập liệu
		if (errors.hasErrors()) {
		
			return "user/add";
		}

		userRepository.save(user);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/user";
	}

	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		System.out.println("ID BEFORE: " + id);
		// model.addAttribute("id", id);
		model.addAttribute("user", userRepository.findById(id));
		
		return "user/edit";
	}

	@PostMapping("edit")
	public String edit(Model model, @Valid @ModelAttribute("user") User user, BindingResult errors) {
		// Bắt lỗi nhập liệu
		if (errors.hasErrors()) {
			System.out.println("Them moi that bai");
			
			return "user/edit";
		}
		System.out.println("ID AFTER: " + user.getId());
		// Cập nhật User
		userRepository.saveAndFlush(user);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/user";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		// Xóa User theo id
		Optional<User> user=userRepository.findById(id);
		user.get().setDelete(true);
		userRepository.saveAndFlush(user.get());
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/user";
	}
	
	@GetMapping("is-delete")
	public String isDelete(Model model) {
		model.addAttribute("users", userRepository.findAllIsDelete());
		return "user/is-delete";
	}
	
	@GetMapping("restore/{id}")
	public String Restore(@PathVariable("id") int id, Model model) {
		System.out.println("ID Restore: " + id);
		Optional<User> user=userRepository.findById(id);
		user.get().setDelete(false);
		userRepository.saveAndFlush(user.get());
		//productRepository.deleteById(id);
		// Chuyển hướng về trang danh sách
		return "redirect:/admin/user";
		// Chuyển hướng về trang danh sách
	
	}
}
