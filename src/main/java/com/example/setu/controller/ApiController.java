package com.example.setu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Map<String, Object>> fetchBill(@RequestBody @Validated FetchBillRequestParameter parameter) {
		return userService.getBill(parameter);
	}

	@PostMapping("/payment-update")
	public ResponseEntity<Map<String, Object>> updatePayment(@RequestBody @Validated UpdatePaymentParameters parameter) {
		return paymentService.updatePayment(parameter);
	}

	@PostMapping("/add-user")
	public ResponseEntity<Map<String, Object>> addUser(@RequestBody @Validated AddUseRequestrParamter parameter) {
		return userService.addNewUser(parameter);
	}

	@PostMapping("/add-bill")
	public ResponseEntity<Map<String, Object>> addPayment(
			@RequestBody @Validated AddPaymentInfoRequestParameter parameter) {
		return paymentService.addBillForUser(parameter);
	}
}
