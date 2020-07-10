
# Billing system design to pay bill

List of Apis
 - Add user
 - Add bill
 - Fetch bill
 - Pay bill

### Configuration
Change credentials in application.properties with your credentials

### Api Request and Response body

#### Fetch Bill
```sh
Request body : { "contactNo":"CONTACT_NUMBER"}
Response : { "data": { "dueAmount": "AMOUNT", "dueDate": "DUE_DATE", "refID": "REFERENCE_ID", "customerName": "CUSTOMER_NAME" }, "status": "STATUS"}
```
#### Update payment
```sh
Request body : { "refId":"REFERENCE_ID", "transaction":{ "id":"ID", "amountPaid":"AMOUNT", "date":"PAID_DATE" }}
Response : { "data": { "ackID": "ACKNOWLEDGE_ID" }, "status": "SUCCESS"}
```

#### Add user 
```sh
Request : { "contactNo":"CONTACT_NUMBER", "userName":"Demo"}
Response : { "message": "user-added-successfully", "status": "SUCCESS"}
```

#### Add bill 
```sh
Request : { "contactNo":"CONTACT_NUMBER", "dueAmount":"AMOUNT", "dueDate":"DUE_DATE"}
Response : { "message": "bill-added-successfully", "status": "SUCCESS"}
```
