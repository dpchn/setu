package com.example.setu.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.setu.dao.PaymentsDao;
import com.example.setu.dao.TransactionDetailsDao;
import com.example.setu.dao.UserDao;
import com.example.setu.models.Payments;
import com.example.setu.models.TransactionDetails;
import com.example.setu.models.User;
import com.example.setu.pojo.AddPaymentInfoRequestParameter;
import com.example.setu.pojo.Transaction;
import com.example.setu.pojo.UpdatePaymentParameters;
import com.example.util.SetuUtils;

@Service
public class PaymentService {

	@Autowired
	UserDao userDao;

	@Autowired
	PaymentsDao paymentsDao;

	@Autowired
	TransactionDetailsDao transactionDetailsDao;

	Logger logger = LoggerFactory.getLogger(PaymentService.class);

	/**
	 * Method to update bill using refId
	 * 
	 * @param parameters
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> updatePayment(UpdatePaymentParameters parameters) {
		Map<String, Object> apiResponse = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		Payments payment = paymentsDao.findByRefIdAndIsPaid(parameters.getRefId(), false);
		try {
			if (payment != null) {
				double amount = SetuUtils.getDoubleValue(parameters.getTransaction().getAmountPaid());
				if (amount == payment.getDueAmount()) {
					TransactionDetails transactionDetails = makeTransaction(payment, parameters);
					if (transactionDetails != null && transactionDetails.getStatus().equalsIgnoreCase("SUCCESS")) {
						payment.setDueAmount(0);
						payment.setPaid(true);
						payment.setPaidOn(parameters.getTransaction().getDate());
						payment.setAckId(transactionDetails.getAckId());
						paymentsDao.save(payment);
						Map<String, String> data = new HashMap<String, String>();
						data.put("ackID", transactionDetails.getAckId());
						apiResponse.put("data", data);
						apiResponse.put("status", "SUCCESS");
					} else {
						apiResponse.put("status", "ERROR");
						apiResponse.put("erroCode", "transaction-failed");
						httpStatus = HttpStatus.BAD_REQUEST;
					}
				} else {
					apiResponse.put("status", "ERROR");
					apiResponse.put("erroCode", "amount-mismatch");
					httpStatus = HttpStatus.BAD_REQUEST;
				}
			} else {
				TransactionDetails transaction = transactionDetailsDao
						.findByTransactionIdAndRefId(parameters.getTransaction().getId(), parameters.getRefId());
				if (transaction != null) {
					Map<String, String> data = new HashMap<String, String>();
					data.put("ackID", transaction.getAckId());
					apiResponse.put("data", data);
					apiResponse.put("status", "SUCCESS");
				} else {
					apiResponse.put("status", "ERROR");
					apiResponse.put("erroCode", "invalid-ref-id");
					httpStatus = HttpStatus.NOT_FOUND;
				}
			}
		} catch (Exception e) {
			apiResponse.put("status", "ERROR");
			apiResponse.put("erroCode", "unhandled-error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Exception while bill update : " + e.getMessage() + " contactNo. : " + parameters.getRefId());
		}
		return new ResponseEntity<Map<String, Object>>(apiResponse, httpStatus);
	}

	/**
	 * Method to make any transaction
	 * 
	 * @param payment
	 * @param transactionParameters
	 */
	private TransactionDetails makeTransaction(Payments payment, UpdatePaymentParameters parameters) {
		List<TransactionDetails> transactionList = transactionDetailsDao.findByTransactionIdAndStatus(parameters.getTransaction().getId(), "SUCCESS");
		TransactionDetails transactionDetails=new TransactionDetails();
		try {
			if (transactionList.isEmpty()) {
				String ackId = SetuUtils.generateUnique("AX", "yyMMdd");
				transactionDetails.setTransactionId(parameters.getTransaction().getId());
				transactionDetails.setAckId(ackId);
				transactionDetails.setRefId(parameters.getRefId());
				transactionDetails.setStatus("SUCCESS");
				transactionDetails.setPayment(payment);
			} else {
				transactionDetails.setStatus("FAIL");
			}
		} catch (Exception e) {
			logger.error("Exception : " + e.getMessage());
			transactionDetails.setStatus("FAIL");
		}
		transactionDetailsDao.save(transactionDetails);
		return transactionDetails;
	}

	/**
	 * Method to add bill for User
	 * 
	 * @param parameters
	 * @return
	 */
	public ResponseEntity<Map<String, Object>> addBillForUser(AddPaymentInfoRequestParameter parameters) {
		Map<String, Object> apiResponse = new HashMap<String, Object>();
		HttpStatus httpStatus = HttpStatus.OK;
		User user = userDao.findByContactNumberAndIsDeleted(parameters.getMobileNumber(), false);
		try {
			if (user != null) {
				Payments payment = paymentsDao.findByUserAndIsPaid(user, false);
				if (payment == null) {
					payment = new Payments();
					System.out.println("amount : " + SetuUtils.getDoubleValue(parameters.getDueAmount()));
					payment.setDueAmount(SetuUtils.getDoubleValue(parameters.getDueAmount()));
					payment.setDueDate(parameters.getDueDate());
					payment.setUser(user);
					paymentsDao.save(payment);
					apiResponse.put("status", "SUCCESS");
					apiResponse.put("message", "bill-added-successfully");
				} else {
					apiResponse.put("status", "ERROR");
					apiResponse.put("erroCode", "unpaid-bill-already-exist");
					httpStatus = HttpStatus.CONFLICT;
				}
			} else {
				apiResponse.put("status", "ERROR");
				apiResponse.put("erroCode", "invalid-contact-no");
				httpStatus = HttpStatus.BAD_REQUEST;
			}

		} catch (Exception e) {
			apiResponse.put("status", "ERROR");
			apiResponse.put("erroCode", "unhandled-error");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			logger.error("Exception while bill add : " + SetuUtils.getStackTrace(e) + " contactNo. : "
					+ parameters.getMobileNumber());
		}
		return new ResponseEntity<Map<String, Object>>(apiResponse, httpStatus);
	}

}
