
# Direct Payment

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
                payData = new PayData();
                
                paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);

                paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","VISA",EnvBase.PayChannel.DIRECT, EnvBase.Language.ENGLISH,"additional remark");


                CardDetails cardDetails=new CardDetails();
                cardDetails.setCardNo("4548890133258926");
                cardDetails.setEpMonth("08");
                cardDetails.setEpYear("2020");
                cardDetails.setSecurityCode("123");
                cardDetails.setCardHolder("test Card");
                payData.setCardDetails(cardDetails);
                paySDK.setRequestData(payData);

                paySDK.process();

```



### Collect Payment Response:
*   Initialize a payment event handler to capture the payment response and result.

```
paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {
                    }

                    @Override
                    public void onError(Data data) {
                    }
                });
  ```
![image](https://user-images.githubusercontent.com/57220911/78635357-ac20ad80-78c3-11ea-9d3d-9a77e83e5031.png)  ![image](https://user-images.githubusercontent.com/57220911/78635373-b3e05200-78c3-11ea-8a0c-68b4ba0fb404.png)
