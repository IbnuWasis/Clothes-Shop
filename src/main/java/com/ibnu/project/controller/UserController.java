package com.ibnu.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibnu.project.config.BaseController;
import com.ibnu.project.model.User;
import com.ibnu.project.services.UserService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@Slf4j
@ResponseBody
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/getList/")
	public ResponseEntity<List<User>> listAllUser(){
		List<User> allUsers = userService.findAllUsers();
		log.info(allUsers.toString());
		if(allUsers.isEmpty()) {
			log.error("User not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/getUserById/", method = RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
		log.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if(user == null) {
			log.error("User with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/createUser/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder builder){
		log.info("Creating User : {}", user);
		if(userService.isUserExist(user)) {
			log.error("Unable to create. A User with name {} already exist ", user.getUsername());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		userService.saveUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/user/getUserById/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<User>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/user/updateUser/", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user){
		log.info("Updating User with id {}", id);
		User currentUser = userService.findById(id);
		if (currentUser == null) {
			log.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentUser.setFullname(user.getFullname());
		currentUser.setPassword(user.getPassword());
		currentUser.setPhone(user.getPhone());
		currentUser.setUsername(user.getUsername());
		currentUser.setStatus(user.getStatus());
		userService.updateUser(currentUser);
		
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/deleteUser/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
		log.info("Fetching & Deleting User with id {}", id);
		
		User user = userService.findById(id);
		if (user == null) {
			log.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(user.getId());
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	

}
