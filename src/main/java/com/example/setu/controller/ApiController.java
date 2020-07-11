package com.example.setu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.setu.pojo.AddPaymentInfoRequestParameter;
import com.example.setu.pojo.AddUseRequestrParamter;
import com.example.setu.pojo.FetchBillRequestParameter;
import com.example.setu.pojo.UpdatePaymentParameters;
import com.example.setu.services.PaymentService;
import com.example.setu.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

	@Autowired
	UserService userService;

	@Autowired
	PaymentService paymentService;

	@PostMapping("/fetch-bill")
	public ResponseEntity<Map<String, Object>> fetchBill(@RequestBody @Validated FetchBillRequestParameter parameter, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("status", "invalid-param-values");
			return new ResponseEntity<Map<String,Object>>(data, HttpStatus.BAD_REQUEST);
		}
		return userService.getBill(parameter);
	}

	@PostMapping("/payment-update")
	public ResponseEntity<Map<String, Object>> updatePayment(@RequestBody @Validated UpdatePaymentParameters parameter, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("status", "invalid-param-values");
			return new ResponseEntity<Map<String,Object>>(data, HttpStatus.BAD_REQUEST);
		}
		return paymentService.updatePayment(parameter);
	}

	@PostMapping("/add-user")
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody @Validated AddUseRequestrParamter parameter, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("status", "invalid-param-values");
			return new ResponseEntity<Map<String,Object>>(data, HttpStatus.BAD_REQUEST);
		}
		return userService.addNewUser(parameter);
	}

	@PostMapping("/add-bill")
	public ResponseEntity<Map<String, Object>> addPayment(
			@RequestBody @Validated AddPaymentInfoRequestParameter parameter, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("status", "invalid-param-values");
			return new ResponseEntity<Map<String,Object>>(data, HttpStatus.BAD_REQUEST);
		}
		return paymentService.addBillForUser(parameter);
	}
}
