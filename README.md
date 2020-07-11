
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
###### /api/v1/fetch-bill
```sh
Request body : { "mobileNumber":"MOBILE_NUMBER"}
Response : { "data": { "dueAmount": "AMOUNT", "dueDate": "DUE_DATE", "refID": "REFERENCE_ID", "customerName": "CUSTOMER_NAME" }, "status": "STATUS"}
```
#### Update payment
###### /api/v1/payment-update
```sh
Request body : { "refId":"REFERENCE_ID", "transaction":{ "id":"ID", "amountPaid":"AMOUNT", "date":"PAID_DATE" }}
Response : { "data": { "ackID": "ACKNOWLEDGE_ID" }, "status": "SUCCESS"}
```

#### Add user 
###### /api/v1/add-user
```sh
Request : { "mobileNumber":"MOBILE_NUMBER", "userName":"Demo"}
Response : { "message": "user-added-successfully", "status": "SUCCESS"}
```

#### Add bill 
###### /api/v1/add-bill
```sh
Request : { "mobileNumber":"MOBILE_NUMBER", "dueAmount":"AMOUNT", "dueDate":"DUE_DATE"}
Response : { "message": "bill-added-successfully", "status": "SUCCESS"}
```


### Note 
Use **X-API-KEY** for key authentication in header

### Error Response 
- Auth Error Response 
```sh
{
    "errorCode": "auth-error",
    "status": "ERROR"
}
```

- Invalid parameter
```sh
{
    "errorCode": "invalid-api-parameters",
    "status": "ERROR"
}
```


- Invalid Reference Id
```sh
{
    "erroCode": "invalid-ref-id",
    "status": "ERROR"
}
```
- Amount mismatch
```sh
{
    "erroCode": "amount-mismatch",
    "status": "ERROR"
}
```

- Internal Server error
```sh
{
    "erroCode": "unhandled-error",
    "status": "ERROR"
}
```

- Invalid Path
```sh
{
    "erroCode": "path-not-found",
    "status": "ERROR"
}
```
