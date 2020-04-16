

# New Member

```
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("VISA");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");


                CardDetails cardDetails=new CardDetails();
                cardDetails.setCardNo("1234567890123456");
                cardDetails.setEpMonth("01");
                cardDetails.setEpYear("2020");
                cardDetails.setSecurityCode("123");
                cardDetails.setCardHolder("abc abc");
                payData.setCardDetails(cardDetails);
                payData.setRemark("additional remark");

                Map<String,String> extraDataMP=new HashMap<>();
                extraData.put("memberPay_service","T");
                extraData.put("memberPay_memberId","member01");
                extraData.put("addNewMember","True"); // If merchant making payment for first time 
                then value should be true else false

                payData.setExtraData(extraData);

                paySDK.setRequestData(payData);
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/79412899-2b993580-7fc4-11ea-854e-377b98cea3c1.png)

# Old Member

```
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("VISA");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");


                CardDetails cardDetails=new CardDetails();
            
                cardDetails.setSecurityCode("123");
                payData.setCardDetails(cardDetails);
                payData.setRemark("additional remark");

                Map<String,String> extraData=new HashMap<>();
                extraData.put("memberPay_service","T");
                extraData.put("memberPay_memberId","member01");
                extraData.put("addNewMember","false"); // If merchant making payment for first time 
                then value should be true else false

                extraData.put("memberPay_token","123456");

                payData.setExtraData(extraData);
                paySDK.setRequestData(payData);
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/79413526-ccd4bb80-7fc5-11ea-8c17-b40de0582077.png)
