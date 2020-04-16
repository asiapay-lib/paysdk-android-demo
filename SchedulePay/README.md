


# Schedule Pay

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
                extraData.put("schType","Day");  // Value could be Day/Month/Year
                extraData.put("schStatus","Active"); // Value could be Active/Suspend
                extraData.put("nSch","1");
                extraData.put("sMonth","4");
                extraData.put("sDay","26");
                extraData.put("sYear","2019");
                extraData.put("eMonth","");
                extraData.put("eDay","");
                extraData.put("eYear","");
                extraData.put("name","Name");
                extraData.put("email","kit@gmail.com");

                extraData.put("appId","SP");
                extraData.put("appRef","2018102409220001");  //appRef should be used only once



                payData.setExtraData(extraData);

                paySDK.setRequestData(payData);
                
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/79414145-7d8f8a80-7fc7-11ea-8177-f8e1ee005fc7.png)![image](https://user-images.githubusercontent.com/57220911/79414155-83856b80-7fc7-11ea-9db2-51a20ef6df77.png)



