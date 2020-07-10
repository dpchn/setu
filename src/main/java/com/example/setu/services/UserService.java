package com.example.setu.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.setu.dao.PaymentsDao;
import com.example.setu.dao.UserDao;
import com.example.setu.models.Payments;
import com.example.setu.models.User;
import com.example.setu.pojo.AddUseRequestrParamter;
import com.example.setu.pojo.ApiResponse;
import com.example.setu.pojo.FetchBillRequestParameter;
import com.example.util.SetuUtils;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	PaymentsDao paymentsDao;

	Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * Method to get bill of particular contact no.
	 * 
	 * @param parameter
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> getBill(FetchBillRequestParameter parameter) {
		Map<String, Object> apiResponse = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			User user = userDao.findByContactNumberAndIsDeleted(parameter.getContactNo(), false);
			if (user != null) {
				Payments payments = paymentsDao.findByUserAndIsPaid(user, false);
				if (payments != null) {
					String refId = SetuUtils.generateUnique("AX", "yyyyMMdd");
					Map<String, String> data = new HashMap<String, String>();
					data.put("customerName", user.getName());
					data.put("dueAmount", SetuUtils.getStringValue(payments.getDueAmount()));
					data.put("dueDate", payments.getDueDate());
					data.put("refID", refId);
					apiResponse.put("data", data);
					payments.setRefId(refId);
					paymentsDao.save(payments);
					apiResponse.put("status", "SUCCESS");
				} else {
					apiResponse.put("status", "ERROR");
					apiResponse.put("erroCode", "no bill found");
					httpStatus = HttpStatus.BAD_REQUEST;
				}
			} else {
				apiResponse.put("status", "ERROR");
				apiResponse.put("erroCode", "customer-not-found");
				httpStatus = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			apiResponse.put("status", "ERROR");
			apiResponse.put("erroCode", "unhandled-error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error(
					"Exception while bill fetch : " + e.getMessage() + " contactNO. : " + parameter.getContactNo());
		}
		return new ResponseEntity<Map<String, Object>>(apiResponse, httpStatus);
	}

	public ResponseEntity<Map<String, Object>> addNewUser(AddUseRequestrParamter parameter) {
		Map<String, Object> apiResponse = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			User user = userDao.findByContactNumberAndIsDeleted(parameter.getContactNo(), false);
			if (user == null) {
				user = new User();
				user.setContactNumber(parameter.getContactNo());
				user.setName(parameter.getUserName());
				userDao.save(user);
				apiResponse.put("status", "SUCCESS");
				apiResponse.put("message", "user-added-successfully");
			} else {
				apiResponse.put("status", "ERROR");
				apiResponse.put("erroCode", "user-already-exist");
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			apiResponse.put("status", "ERROR");
			apiResponse.put("erroCode", "unhandled-error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Exception while  add user: " + e.getMessage() + " contactNO. : " + parameter.getContactNo());
		}
		return new ResponseEntity<Map<String, Object>>(apiResponse, httpStatus);
	}
}
