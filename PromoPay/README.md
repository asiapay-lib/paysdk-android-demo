


# Promo Pay

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
                extraDataPC.put("promotion","T");
                extraDataPC.put("promotionCode","TEST1");
                extraDataPC.put("promotionRuleCode","");
                extraDataPC.put("promotionOriginalAmt","");

                payData.setExtraData(extraData);

                paySDK.setRequestData(payData);
                
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/79414449-4bcaf380-7fc8-11ea-9dd8-7043567d9b77.png)




