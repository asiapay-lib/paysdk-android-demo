

# PayMe

```

                void handlePayment() {
                
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("1.81");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("PayMe");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");
                
                // handle result callback with deeplink 
               payData.setSuccessUrl("xxx://abc//success");
               payData.setCancelUrl("xxx://abc//cancelled");
               payData.setFailUrl("xxx://abc//error");
               payData.setErrorUrl("xxx://abc//fail");
                
                //Optional Parameters
                payData.setRemark("additional remark");
                
                paySDK.setRequestData(payData);
                paySDK.process();
                
                }


         
```

![image](https://user-images.githubusercontent.com/57220911/104548750-77faca00-5657-11eb-8250-ba3763594cdf.png) ![image](https://user-images.githubusercontent.com/57220911/104548765-82b55f00-5657-11eb-909b-fbbe698051bb.png) ![image](https://user-images.githubusercontent.com/57220911/104548772-86e17c80-5657-11eb-8dba-cebdd7a77643.png)


For Deeplinking Demo kindly check  https://github.com/seema-asiapay/deeplinking-sample/blob/master/app/src/main/AndroidManifest.xml
