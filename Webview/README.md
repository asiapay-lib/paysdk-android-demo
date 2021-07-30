
#### WebView Payment
```
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.WEBVIEW);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("VISA");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");
                payData.setRemark("additional remark");
                payData.setShowCloseButton(true);//If not specified, then by default this value is set to false
                paySDK.setRequestData(payData);
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/78635133-26046700-78c3-11ea-83cb-b6bad3511485.png) ![image](https://user-images.githubusercontent.com/57220911/78635172-39173700-78c3-11ea-86e2-75c870031954.png)
