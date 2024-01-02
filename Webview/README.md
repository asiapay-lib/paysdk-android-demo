
# WebView Payment

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
*   This method is used to setup the payment basic information like Amount,currency,orderRef,payMethod etc.

```

paySDK.setPayData(String Amount,EnvBase.Currency currency,EnvBase.PayType payType,String orderRef, String payMethod,EnvBase.PayChannel payChannel,EnvBase.Language language,String remark);

```


Parameters -:

Name | Mandatory | Data Type | Sample Data | Description
--- | --- | --- | --- | ---
Amount | Yes | String | 20.00 | This Parameter is used to setup transaction amount.
currency | Yes | EnvBase.Currency | EnvBase.Currency.HKD | This Parameter is used to setup currency for the transaction.
payType | Yes | EnvBase.PayType | EnvBase.PayType.NORMAL_PAYMENT | This Parameter is used setup the payment type for the transaction. i.e (NORMAL_PAYMENT, HOLD_PAYMENT)
orderRef | Yes | String | 156487515598 | This Parameter is used to setup the order reference number for the transaction.
payMethod | Yes | String | VISA | This Parameter is used to setup payment method for the transaction.
payChannel | Yes | EnvBase.PayChannel | EnvBase.PayChannel.WEBVIEW | This Parameter is used to setup payment channel for the transaction. i.e (WEBVIEW, DIRECT)
language | Yes | EnvBase.Language | EnvBase.Language.ENGLISH | This Parameter is used to setup payment language for the transaction.
remark | No | String | remark | This Parameter is used to setup payment remark for the transaction.


### Payment Call Example:      
*   Initialize the PayData object and prepare the payment detail for the transaction.

```
                PayData payData = new PayData();
                paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
                paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","VISA",EnvBase.PayChannel.WEBVIEW, EnvBase.Language.ENGLISH,"additional remark");

                payData.setShowCloseButton(true);//If not specified, then by default this value is set to false
                //hide toolbar from Payment Page WebView
                payData.setShowToolbar(false);//If not spesified, then by default this value is set to true
                //use below attribute to customise prompt message which user will see when they decide to close Payment Page
                payData.setWebViewClosePrompt("Do you really want to close this page ?");

                paySDK.setRequestData(payData);
                paySDK.process();
                
                
```
```
//PaySDK will trigger payment result handle with failed status at WebView integration if the requested url scheme is failed to trigger.

//Merchant can prompt the message for this case and ignore the result handle if they received the error message started with "No app installed to handle the request"

                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {
                        if (payResult.getErrMsg().startsWith("No app installed to handle the request")) {
                            Toast.makeText(AuthActivity.this, payResult.getErrMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else if (payResult.getErrMsg().equals("Transaction page closed by user")) {
                            Toast.makeText(AuthActivity.this, payResult.getErrMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AuthActivity.this, String.valueOf(payResult.isSuccess()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Data data) {
                        Toast.makeText(AuthActivity.this, data.getError(), Toast.LENGTH_SHORT).show();
                    }
                });

```
![image](https://user-images.githubusercontent.com/57220911/78635133-26046700-78c3-11ea-83cb-b6bad3511485.png) ![image](https://user-images.githubusercontent.com/57220911/78635172-39173700-78c3-11ea-86e2-75c870031954.png)
