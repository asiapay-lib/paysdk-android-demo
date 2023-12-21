
#### Payment via AliPay

*	Add alipaySdk-15.5.9-20181123210601.aar  to libs folder

* Add following dependencies in gradle file

  implementation(name: 'alipaySdk-15.5.9-20181123210601', ext: 'aar')


* Setup paySDK Configuration

```
			paySDK.setPayConfig(Activity activity, PayData payData, String merchantId, EnvBase.EnvType EnvType, EnvBase.PayGate PayGate);

```


* Setup Payment Data

```
			paySDK.setPayData(String Amount,EnvBase.Currency currency,EnvBase.PayType payType,String orderRef, String payMethod,EnvBase.PayChannel payChannel,EnvBase.Language language,String remark);

```
       

* Payment Call Example      

```
			payData = new PayData();
			paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
			paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","ALIPAYAPP",EnvBase.PayChannel.DIRECT, EnvBase.Language.ENGLISH,"additional remark");
			paySDK.setRequestData(payData);
			paySDK.process();

```

* Replace the payment method as per your requirement

  "ALIPAYHKAPP" ==>  // FOR ALIPAY HK
  "ALIPAYCNAPP" ==>  // FOR ALIPAY CHINA
  "ALIPAYAPP" ==>  // FOR ALIPAY GLOBAL


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
