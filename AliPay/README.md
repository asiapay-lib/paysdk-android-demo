
#### Payment via AliPay

* Add alipaySdk-15.5.9-20181123210601.aar  to libs folder

* Add following dependencies in gradle file

  implementation(name: 'alipaySdk-15.5.9-20181123210601', ext: 'aar')


* Setup paySDK Configuration:

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




* Setup Payment Data:

```

paySDK.setPayData(String Amount,EnvBase.Currency currency,EnvBase.PayType payType,String orderRef, String payMethod,EnvBase.PayChannel payChannel,EnvBase.Language language,String remark);

```

       




* Payment Call Example:      

```
payData = new PayData();
paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","ALIPAYAPP",EnvBase.PayChannel.DIRECT, EnvBase.Language.ENGLISH,"additional remark");
paySDK.setRequestData(payData);
paySDK.process();

```

Kindly select payment method according to below description -:

AliPay Payment Methods | Description
--- | --- 
ALIPAYHKAPP | FOR ALIPAY HK.
ALIPAYCNAPP | FOR ALIPAY CHINA. 
ALIPAYAPP | FOR ALIPAY GLOBAL


* Collect Payment Response
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
