
#### Payment via AliPay

*	Add alipaySdk-15.5.9-20181123210601.aar  to libs folder

* Add following dependencies in gradle file

  implementation(name: 'alipaySdk-15.5.9-20181123210601', ext: 'aar')
  
  implementation "com.tencent.mm.opensdk:wechat-sdk-android-with-mta:+"
  

```
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("ALIPAYHKAPP"); // FOR ALIPAY HK
                //payData.setPayMethod("ALIPAYCNAPP"); // FOR ALIPAY CHINA
                //payData.setPayMethod("ALIPAYAPP"); // FOR ALIPAY GLOBAL
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");
                payData.setRemark("additional remark");
                paySDK.setRequestData(payData);

                paySDK.process();

```
