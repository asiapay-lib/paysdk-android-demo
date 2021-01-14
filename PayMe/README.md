

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
                payData.setCallbackSuccess("xxx://abc//success");
                payData.setCallbackCancel("xxx://abc//cancelled");
                payData.setCallbackError("xxx://abc//error");
                payData.setCallbackFail("xxx://abc//fail");
                
                //Optional Parameters
                payData.setRemark("additional remark");
                
                paySDK.setRequestData(payData);
                paySDK.process();
                }


         
```
For Deeplinking Demo kindly check  https://github.com/seema-asiapay/deeplinking-sample/blob/master/app/src/main/AndroidManifest.xml
