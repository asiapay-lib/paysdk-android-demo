

# Transaction Query
* "Transaction Query" function is utilized to retrieve status for a specific transaction or order ID.

### Setup paySDK Configuration:      
*   This method is used to setup the payment configurations like merchantId, environmentType and paymentGateway.

```

paySDK.setPayConfig(Activity activity, PayData payData, String merchantId, EnvBase.EnvType EnvType, EnvBase.PayGate PayGate);

```

Parameters -:

Name | Mandatory | Data Type | Sample Data | Description
--- | --- | --- | --- | ---
activity | Yes | Activity | currentActivity | This Parameter is used to setup payData activity for further uses.
payData | Yes | PayData Class | payData | This Parameter is used to setup payData POJO data.
merchantId | Yes | String | 35001282 | This Parameter is used to pass merchant id for the payment configuration.
EnvType | Yes | EnvBase.EnvType | EnvBase.EnvType.SANDBOX | This Parameter is used to setup the environment type. i.e (SANDBOX, PRODUCTION)
PayGate | Yes | EnvBase.PayGate | EnvBase.PayGate.PAYDOLLAR | This Parameter is used to setup payment Gateway type. i.e (PAYDOLLAR, SIAMPAY, PESOPAY)

### Setup Payment Data:      
*   This method is used to setup the query basic information.

```
               PayData payData = new PayData();
               paySDK.setPayConfig(PaymentActivity.this,payData,"35001282",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
               payData.setOrderRef("15786068");
               payData.setPayRef("11793068");
               payData.setRemark(" ");
               paySDK.setRequestData(payData);
               paySDK.query(EnvBase.Action.TX_QUERY);
```
### Collect Payment Response:
*   Initialize a query event handler to capture the query response and result.

```
               //NOTE : For Trans Query Response will come in below format 
                        paySDK.queryResponseHandler(new QueryResponse() {
                            @Override
                            public void getResponse(TransactionStatus transactionStatus) {

                            try {
                                 cancelProgressDialog();
                                showAlert(transactionStatus.getResultCode());
                            } catch (Exception e) {
                               
                            }
                               
                            }

                            @Override
                            public void onError(Data data) {

                            try {
                                 cancelProgressDialog();
                                showAlert(data.getMessage());
                            } catch (Exception e) {
                               
                            }
                                
                            }
                        });
  ```
![image](https://user-images.githubusercontent.com/57220911/82582763-207f8980-9bb0-11ea-8ab3-3f7619bcd98d.png) ![image](https://user-images.githubusercontent.com/57220911/82582688-0b0a5f80-9bb0-11ea-9336-f9d3c44cdbfd.png)
