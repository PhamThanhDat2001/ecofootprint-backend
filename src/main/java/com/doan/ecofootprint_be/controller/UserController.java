package com.doan.ecofootprint_be.controller;


import com.doan.ecofootprint_be.dto.UserDTO;
import com.doan.ecofootprint_be.entity.Users;
import com.doan.ecofootprint_be.form.ChangePasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordForm;
import com.doan.ecofootprint_be.form.ForgetPasswordRequestForm;
import com.doan.ecofootprint_be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1")
@Validated
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/user/{id}")
	public Users getUserById(@PathVariable int id){
		return userService.getUserById(id);
	}
	@PostMapping("/user/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO dto) {

		// create User
		userService.createUser(dto.toEntity());

		return new ResponseEntity<>("We have sent 1 email. Please check email to active account!", HttpStatus.OK);
	}

	@GetMapping("/user/activeUser")
	public ResponseEntity<?> activeUserViaEmail(@RequestParam String token) {

		// active user
		userService.activeUser(token);

		return new ResponseEntity<>("Active success!", HttpStatus.OK);
	}
	// resend confirm
	@GetMapping("/user/userRegistrationConfirmRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> sendConfirmRegistrationViaEmail(@RequestParam String email) {

		userService.sendConfirmUserRegistrationViaEmail(email);

		return new ResponseEntity<>("We have sent 1 email. Please check email to active account!", HttpStatus.OK);
	}
	@PostMapping("/resetPasswordRequest")
	// validate: email exists, email not active
	public ResponseEntity<?> sendResetPasswordViaEmail(@RequestBody ForgetPasswordRequestForm form) {

		userService.resetPasswordViaEmail(form);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}
	@PostMapping("/resetPassword")
	// validate: check exists, check not expired
	public ResponseEntity<?> resetPasswordViaEmail(@RequestBody ForgetPasswordForm form) {

		// reset password
		userService.resetPassword(form);

		return new ResponseEntity<>("Reset Password success!", HttpStatus.OK);
	}
	// resend reset password
	@GetMapping("/resendResetPassword")
	// validate: email exists, email not active
	public ResponseEntity<?> resendResetPasswordViaEmail(@RequestParam String email) {

		userService.sendResetPasswordViaEmail(email);

		return new ResponseEntity<>("We have sent an email. Please check email to reset password!", HttpStatus.OK);
	}
	@PostMapping("/change_password/{id}")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordForm form, @PathVariable(name = "id") int id) {
		if(userService.changePassword(id, form)) {
			return ResponseEntity.ok("Change password succsessfully");
		}
		return ResponseEntity.badRequest().body("Password incorect !!!");
	}
}
