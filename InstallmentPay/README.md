

# Installment Pay

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

                Map<String,String> extraData=new HashMap<>();
                extraData.put("installment_service","T");
                extraData.put("installment_period","6");
                extraData.put("installOnly","T");


                payData.setExtraData(extraData);

                paySDK.setRequestData(payData);
                
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/79413782-82077380-7fc6-11ea-9bbf-d0a6a99707b1.png)


