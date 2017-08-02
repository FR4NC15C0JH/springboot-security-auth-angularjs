package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;

//@Controller
@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody User user ){
		if(StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())){
			return new ResponseEntity<>(new User(), HttpStatus.OK);
		}
		if(userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword()) == null){
			return new ResponseEntity<>(new User(), HttpStatus.OK);
		}
		return new ResponseEntity<>(userService.findUserByEmail(user.getEmail()), HttpStatus.OK);
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ModelAndView login(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("login");
//		return modelAndView;
//	}
	
//	@RequestMapping(value={"/register"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ModelAndView register(){
//		ModelAndView modelAndView = new ModelAndView();
//		User user = new User();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("register");
//		return modelAndView;
//	}
//	
//	@RequestMapping(value={"/register"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult){
//		ModelAndView modelAndView = new ModelAndView();
//		User userExists = userService.findUserByEmail(user.getEmail());
//		if(userExists != null){
//			bindingResult
//				.rejectValue("email", "error.user","There is already a user registered with the email provided");
//		}
//		if(bindingResult.hasErrors()){
//			modelAndView.setViewName("register");
//		}else {
//			userService.saveUser(user);
//			modelAndView.addObject("successMessage", "User has been registered successfully");
//			modelAndView.addObject("user", new User());
//			modelAndView.setViewName("register");
//		}
//		return modelAndView;
//	}
//	
//	@RequestMapping(value="/admin/home", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ModelAndView home(){
//		ModelAndView modelAndView = new ModelAndView();
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		User user = userService.findUserByEmail(authentication.getName());
//		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//		modelAndView.setViewName("admin/home");
//		return modelAndView;
//	}
}