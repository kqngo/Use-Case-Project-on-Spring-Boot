package com.jdbSpringBootCaseStudy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jdbSpringBootCaseStudy.dao.UserRepository;
import com.jdbSpringBootCaseStudy.model.User;

@RestController
public class RestfulController {
	
	@Autowired
	UserRepository repo;
	
	@DeleteMapping("/User/{aid}")
	public String deleteUser(@PathVariable int aid) {
		User a = repo.getOne(aid);
		
		repo.delete(a);
		return "deleted";
	}
	
	@PutMapping(path="/User", consumes= {"application/json"})
	public User saveOrUpdateUser(@RequestBody User User) {
		repo.save(User);
		return User;
	}
	
	@PostMapping("/User")
	public User addUser(@RequestBody User User) {
		repo.save(User);		
		return User;
	}
	
		
	@GetMapping(path="/Users")
	public List<User> getUsers() {

		return repo.findAll();
	}
	
	@RequestMapping("/User/{aid}")
	public Optional<User> getUser(@PathVariable("aid") int aid) {

		return repo.findById(aid);
	}
	
}
