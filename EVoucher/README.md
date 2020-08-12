

#### EVoucher
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
                
                Map<String, String> extraData = new HashMap<String, String>();
                
                //For EVoucher add below extra parameters
                extraData.put("eVoucher", "T");
                extraData.put("eVClassCode", data.getStringExtra("eVClassCode"));

                payData.setExtraData(extraData);
                        
                paySDK.setRequestData(payData);
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/90011789-d80fcd80-dcbf-11ea-9ff6-b875b0558141.png)  ![image](https://user-images.githubusercontent.com/57220911/90011886-ff669a80-dcbf-11ea-8a6d-f9211ec33bc8.png)
![image](https://user-images.githubusercontent.com/57220911/90012027-3b99fb00-dcc0-11ea-8ede-e4399f10cb28.png)




