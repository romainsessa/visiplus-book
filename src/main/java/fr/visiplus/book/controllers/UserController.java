package fr.visiplus.book.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.visiplus.book.dtos.LoginRequest;
import fr.visiplus.book.dtos.RegisterRequest;
import fr.visiplus.book.dtos.UserDTO;
import fr.visiplus.book.services.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody LoginRequest login) {
		return ResponseEntity.of(userService.login(login));		
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest register) {
		return ResponseEntity.ok(userService.register(register));
	}

}
