package com.jdbSpringBootCaseStudy.controller;

import org.springframework.stereotype.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.jdbSpringBootCaseStudy.dao.ProductRepository;
import com.jdbSpringBootCaseStudy.dao.UserRepository;
import com.jdbSpringBootCaseStudy.model.Product;
import com.jdbSpringBootCaseStudy.model.User;
import com.jdbSpringBootCaseStudy.services.ProductService;
import com.jdbSpringBootCaseStudy.services.UserService;

@Controller // control all the method
@SessionAttributes("sUser")
public class JspController {

	@Autowired
	UserRepository uRepo;
	ProductRepository pRepo;
	
	// SessionAttribute requires existing user object
	@ModelAttribute("sUser")
	public User setupsUser() {
		// setup business logic
		// initialize
		User user = new User();

		return user;
	}

	@RequestMapping("/test")
	public String test() {
	return "test";
	}
	
	@GetMapping(value = { "/", "/home" })
	public String goHome() {
		return "home";
	}

	@RequestMapping(value = { "/welcome" })
	public ModelAndView toWelcome() {
		ModelAndView mav = new ModelAndView("welcome");
		return mav;
	}

	@GetMapping(value = { "/aboutus" })
	public String goAboutus() {
		return "aboutus";
	}

	@RequestMapping(value = { "/howitworks" })
	public String goHowitworks() {
		return "howitworks";
	}

	@GetMapping("/login")
	public ModelAndView getLogin() {
		ModelAndView mav = new ModelAndView("login");
		User user = new User();
		mav.addObject("sUser", user);
		return mav;
	}

	@PostMapping(value = "/login")
	public ModelAndView doLogin(
			@RequestParam("email") String email, 
			@RequestParam("password") String password) {

		ModelAndView mav = new ModelAndView();
		String message = null;
		
		User userFound = uRepo.findByuEmail(email);
		
		if (userFound == null) {
			message = "Email or Password does not match. Please try again.";

			mav = new ModelAndView("login");
			mav.addObject("messageResult", message);
			return mav;
		}
		
		mav = new ModelAndView("welcome");
		mav.addObject("sUser", userFound);
		mav.addObject("messageResult", message);

		return mav;
	}

	// For validation
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields();
		binder.setRequiredFields("uLastName", "uFirstName", "uEmail", "uPassword");
	}

	@GetMapping("/register")
	public ModelAndView goRegister() {

		ModelAndView mav = new ModelAndView("register");
		User user = new User();
		mav.addObject("userKey", user);

		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView doRegister(@Valid @ModelAttribute("userKey") User user, BindingResult errors) {

		ModelAndView mav = new ModelAndView();
		String message ;
		
		if (errors.hasErrors()) {
			mav.setViewName("register");
			return mav;
			
		} else {

			User newUser = new User(user.getuFirstName(), user.getuLastName(), user.getuEmail(), user.getuPassword());
			
			try {
				uRepo.save(newUser);
				 message =  "User Added ";
			} catch ( PersistenceException e) {
				e.getMessage();	
				 message = "try again, not saved";
			}
			
			mav.addObject("messageResult", message);
			mav.setViewName("welcome");

			return mav;
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		return "logout";
	}

	@GetMapping("/profile")
	public String goProfile() {

		return "profile";
	}

	@PostMapping("/profile")
	public ModelAndView profile(@SessionAttribute("sUser") User sUser) {

		ModelAndView mav = new ModelAndView("profile");
		return mav;
	}

	@RequestMapping("/updateprofile")
	public ModelAndView updateMyProfile(
			@SessionAttribute("sUser") User user,
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("email") String email,
			@RequestParam("password") String password) {

		boolean result = true;
		String message;
		
		User newUser = new User(firstName, lastName, email, password);
		try {
		uRepo.save(newUser);
		 message = " >> Successfully Updated User: " + user.getuFirstName() + " << ";

		 
		} catch (PersistenceException e) {
			e.getMessage();	
			message =  "  >> Unable to Update user: \"" + user.getuFirstName() + "\" .  << ";
		}
		
		String color = result ? "green" : "red";

		ModelAndView mav = new ModelAndView("profile");
		mav.addObject("sUser", user);
		mav.addObject("messageResult", message);
		mav.addObject("textColor", color);

		return mav;
	}

//User Admin
	// Prints a All Users to the View
	@RequestMapping("/showUsers")
	public ModelAndView showAllUsers() {

		List<User> userList = uRepo.findAll();

		ModelAndView mav = new ModelAndView("allusers");
		mav.addObject("userList", userList);
		return mav;
	}

	@GetMapping("/adduser")
	public String goAddUser() {
		return "adduser";
	}

	@RequestMapping(value = "/adduserform")
	public ModelAndView addUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("password") String password, @RequestParam("email") String email) {

		User newUser = new User(firstName, lastName, email, password);
		
		boolean result;
		try {
		uRepo.save(newUser);
		result = true;
		} catch (PersistenceException e) {
			e.getMessage();	
			result = false;
		}
		String message = result ? " >> Successfully Added User: " + firstName + " << "
				: "  >> Unable to add user \"" + firstName + "\" . Try using a different email address.<<<";
		String color = result ? "green" : "red";
		
		List<User> userList = uRepo.findAll();
		
		ModelAndView mav = new ModelAndView("allusers");
		mav.addObject("messageResult", message);
		mav.addObject("textColor", color);
		mav.addObject("userList", userList);

		return mav;
	}

	@RequestMapping("/deleteuser/{urlEmail}")
	public ModelAndView confirmDeleteUser(@PathVariable("urlEmail") String email) {
		
		User user = uRepo.findByuEmail(email);

		ModelAndView mav = new ModelAndView("confirmdeleteuser");

		if (user == null) {
			String message = "Invalid Email or Password. Please try again.";
			mav = new ModelAndView("allusers");
			mav.addObject("messageResult", message);
		} else {

			mav.addObject("firstName", user.getuFirstName());
			mav.addObject("lastName", user.getuLastName());
			mav.addObject("email", user.getuEmail());
		}
		return mav;
	}

	@RequestMapping("/removeUser/{urlEmail}")
	public ModelAndView deleteUser(@PathVariable("urlEmail") String email) {
		System.out.println("email : " + email);
		ModelAndView mav = new ModelAndView("allusers");


		boolean result;
		
		try {
			uRepo.deleteByuEmail(email);
			result = true;
		} catch (PersistenceException e) {
			e.getMessage();	
			result = false;
		} 
		
		String message = result ? " >> Successfully Deleted User: " + email + " << "
				: "  >> Unable to Delete user \"" + email + "\" . Try using a different email. << ";
		String color = result ? "green" : "red";

		List<User> userList = uRepo.findAll();

		mav.addObject("messageResult", message);
		mav.addObject("textColor", color);
		mav.addObject("userList", userList);
		return mav;
	}

	@RequestMapping("/updateUser/{urlEmail}")
	public ModelAndView updateUser(@PathVariable("urlEmail") String email) {
		UserService userService = new UserService();
		User user = userService.getUserByEmail(email);
		ModelAndView mav = new ModelAndView();
		String message;

		if (user == null) {
			message = " >> Encountered issue accessing user : " + email + ". Please try another user ";

			List<User> userList = userService.getAllUsers();

			mav.addObject("messageResult", message);
			mav.addObject("textColor", "red");
			mav.addObject("userList", userList);

			mav.setViewName("allusers");
			return mav;
		}

		mav.addObject("user", user);
		mav.addObject("firstName", user.getuFirstName());
		mav.addObject("lastName", user.getuLastName());
		mav.addObject("email", user.getuEmail());
		mav.setViewName("updateUser");

		return mav;
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.POST)
	public ModelAndView updateUserForm(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password) {

		boolean result = true;

		User newUser = new User(firstName, lastName, email, password);

		System.out.println("user: " + newUser.toString());

		if (newUser != null) {
			uRepo.save(newUser);
			result = true;
		}

		String message = result ? " >> Successfully Updated User " + newUser.getuFirstName() + " << "
				: "  >> Unable to Update user \"" + newUser.getuFirstName() + "\" .  << ";
		String color = result ? "green" : "red";
		List<User> userList = uRepo.findAll();

		ModelAndView mav = new ModelAndView("allusers");
		mav.addObject("messageResult", message);
		mav.addObject("textColor", color);
		mav.addObject("userList", userList);

		return mav;
	}

	// Products Admin

	@GetMapping("/addproduct")
	public ModelAndView goAddproduct() {
		ModelAndView mav = new ModelAndView("addproduct");
		return mav;
	}

	@RequestMapping("/updateproduct/{urlId}")
	public ModelAndView updateproduct(@PathVariable("urlId") int id) {
		ProductService productService = new ProductService();
		Product product = productService.getProductById(id);

		ModelAndView mav = new ModelAndView("updateproduct");
		mav.addObject("product", product);
		mav.addObject("id", product.getpId());
		mav.addObject("category", product.getpCategory());
		mav.addObject("name", product.getpName());
		mav.addObject("size", product.getpSize());
		mav.addObject("weight", product.getpWeight());
		mav.addObject("serving", product.getpServing());
		mav.addObject("summary", product.getpSummary());
		mav.addObject("description", product.getpDescription());
		mav.addObject("price", product.getpPrice());

		return mav;
	}

	@RequestMapping(value = "/updateproductform/{urlId}")
	public ModelAndView updateproductform(@PathVariable("urlId") int id, @RequestParam("category") String category,
			@RequestParam("name") String name, @RequestParam("size") String size, @RequestParam("weight") String weight,
			@RequestParam("serving") String serving, @RequestParam("summary") String summary,
			@RequestParam("description") String description, @RequestParam("price") double price) {

		boolean result;

		Product product = new Product(id, category, name, size, weight, serving, summary, description, price);

		if (product != null) {
			pRepo.save(product);
			result=true;
			System.out.println("product is not null");
		} else {
			result=false;
		}

		String message = result ? " >> Successfully Updated Product:  " + product.getpName() + " << "
				: "  >> Unable to Update user \"" + product.getpName() + "\" .  << ";
		String color = result ? "green" : "red";
		
		List<Product> productList = pRepo.findAll();
		ModelAndView mav = new ModelAndView("allproducts");
		mav.addObject("messageResult", message);
		mav.addObject("textColor", color);
		mav.addObject("productList", productList);

		return mav;
	}


	// Prints a All Products to the View
	@GetMapping("/showProducts")
	public ModelAndView getAllProducts() {

		System.out.println(pRepo.findAll().toString());
		System.out.println(pRepo.findBypId(1));

		List<Product> productList = pRepo.findAll();
		
		if (productList != null) {
		ModelAndView mav = new ModelAndView("allproducts");
		mav.addObject("productList", productList);
		}
		
		ModelAndView mav = new ModelAndView("allproducts");
		mav.addObject("productList", productList);

		return mav;
	}

	@RequestMapping("/removeProduct/{urlId}")
	public ModelAndView deleteEmployee(@PathVariable("urlId") int id) {
		System.out.println("Product id from url = " + id);

boolean result;

try {
		pRepo.deleteById(id);
		result = true;
}  catch ( PersistenceException e) {
	e.getMessage();	
result=false;

}

		String message = result ? "Product Removed= " + id : "Unable to remove product " + id;

		List<Product> productList = pRepo.findAll();
				
		ModelAndView mav = new ModelAndView("allproducts");
		mav.addObject("messageResult", message);
		mav.addObject("id", id);
		mav.addObject("productList", productList);
		return mav;

	}

	@RequestMapping(value = "/addproductform")
	public ModelAndView addproductform(@RequestParam("category") String category, @RequestParam("name") String name,
			@RequestParam("size") String size, @RequestParam("weight") String weight,
			@RequestParam("serving") String serving, @RequestParam("summary") String summary,
			@RequestParam("description") String description, @RequestParam("price") double price) {
		
		boolean result; 

		Product product = new Product(category, name, size, weight, serving, summary, description, price);
				
		try {
				pRepo.save(product);
				result = true;
		} catch ( PersistenceException e) {
			e.getMessage();	
			result = false;
		}

		String message = result ? " >> Successfully Added Product: " + name + " << "
				: "  >> Unable to add product \"" + name + "\" . <<";
		String color = result ? "green" : "red";

		List<Product> productList = pRepo.findAll();
				
		ModelAndView mav = new ModelAndView("allproducts");
		mav.addObject("category", category);
		mav.addObject("name", name);
		mav.addObject("size", size);
		mav.addObject("weight", weight);
		mav.addObject("serving", serving);
		mav.addObject("summary", summary);
		mav.addObject("description", description);
		mav.addObject("price", price);
		mav.addObject("productList", productList);
		mav.addObject("messageResult", message);
		mav.addObject("color", color);

		return mav;
	}

	@RequestMapping("/confirmdeleteproduct/{urlId}")
	public ModelAndView confirmdeleteproduct(@PathVariable("urlId") int id) {
		
		Product product = pRepo.findBypId(id);
				
		System.out.println("confirmdeleteproduct::product " + product);

		ModelAndView mav = new ModelAndView("confirmdeleteproduct");
		mav.addObject("id", product.getpId());
		mav.addObject("category", product.getpCategory());
		mav.addObject("name", product.getpName());
		mav.addObject("size", product.getpSize());
		mav.addObject("weight", product.getpWeight());
		mav.addObject("serving", product.getpServing());
		mav.addObject("summary", product.getpSummary());
		mav.addObject("description", product.getpDescription());
		mav.addObject("price", product.getpPrice());
		mav.addObject("product", product);

		return mav;
	}

	// Shopping

	@RequestMapping("/products")
	public String  goProducts() {
		return "products";
	}

	@RequestMapping(value = "/product/vegetables")
	public ModelAndView doVegetables() {

		List<Product> productList = pRepo.findBypCategory("Veggie");
				
		ModelAndView mav = new ModelAndView("product");
		mav.addObject("productList", productList);
		mav.addObject("category", "Vegetable");
		mav.addObject("textColor", "style= \"color: #398439;\"");
		mav.addObject("btnClass", "btn-success");

		return mav;
	}

	@RequestMapping(value = "/product/fruit")
	public ModelAndView doFruits() {

		List<Product> productList = pRepo.findBypCategory("fruit");

		ModelAndView mav = new ModelAndView("product");
		mav.addObject("productList", productList);
		mav.addObject("category", "Fruit");
		mav.addObject("textColor", "style= \"color: #ac2925;\"");
		mav.addObject("btnClass", "btn-danger");

		return mav;
	}

	@RequestMapping(value = "/product/Mixed")
	public ModelAndView domixed() {

		List<Product> productList = pRepo.findBypCategory("Mixed");

		ModelAndView mav = new ModelAndView("product");
		mav.addObject("productList", productList);
		mav.addObject("category", "Mixed");

		mav.addObject("textColor", "style= \"color: #d58512;\"");
		mav.addObject("btnClass", "btn-warning");

		return mav;
	}

	@RequestMapping(value = "/cart")
	public ModelAndView goCart(@SessionAttribute("sUser") User sUser) {

		UserService userService = new UserService();
		List<Product> productList = userService.getUserProducts(sUser);
		
		ModelAndView mav = new ModelAndView("cart");
		mav.addObject("productList", productList);

		return mav;
	}

	@RequestMapping(value = "/addtocart/{urlId}", method = RequestMethod.POST)
	public ModelAndView addToCart(@PathVariable("urlId") int id, @SessionAttribute("sUser") User sUser) {

		UserService userService = new UserService();
		System.out.println("sUser : " + sUser);
		boolean result = userService.addProductToUser(sUser, id);

		String message = result ? "Successfully Added " + id : "Failed to add " + id;
		List<Product> productList = userService.getUserProducts(sUser);

		ModelAndView mav = new ModelAndView("cart");
		mav.addObject("productList", productList);
		mav.addObject("messageResult", message);

		return mav;
	}

	@RequestMapping(value = "/deleteitemfromcart/{urlId}")
	public ModelAndView deleteitemfromcart(@PathVariable("urlId") int id, @SessionAttribute("sUser") User sUser) {

		UserService userService = new UserService();
		boolean result = userService.removeProductFromUser(sUser, id);
		String message = result ? "Successfully removed item #" + id + " from Cart" : "Failed to remove item # " + id;

		List<Product> productList = userService.getUserProducts(sUser);

		ModelAndView mav = new ModelAndView("cart");
		mav.addObject("productList", productList);
		mav.addObject("messageResult", message);

		return mav;

	}

	@RequestMapping(value = "/checkout")
	public ModelAndView goCheckout(@SessionAttribute("sUser") User sUser) {

		UserService userService = new UserService();
		boolean result = userService.removeAllProductsFromUser(sUser);
		String message = result ? "Successfully cleared the Cart" : "Failed to clear the Cart";

		ModelAndView mav = new ModelAndView("checkout");

		return mav;
	}

}
