
# Pay Method
* "Pay Method" function is utilized to retrieve a list of acceptable payment methods available for a specific merchant ID.


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


### Payment Call Example:      
*   Initialize the PayData object and prepare the payment detail for the transaction.

```
                PayData payData = new PayData();
                paySDK.setPayConfig(PaymentActivity.this,payData,"35001282",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);

                payData.setRemark(" ");
                
                paySDK.setRequestData(payData);
                
                paySDK.query(EnvBase.Action.PAY_METHOD);

```

### Collect Payment Response:
*   Initialize a payment event handler to capture the payment response and result.

```
                //NOTE : For allowed Pay Method Response will come in below format 
                        paySDK.payMethodResponseHandler(new PayMethodResponse() {
                            @Override
                            public void getResponse(PayMethodResult payMethodResult) {

                                cancelProgressDialog();
                                showAlert(payMethodResult.getMethods());
                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });
  ```
