![paydollar2](https://user-images.githubusercontent.com/57220911/68009559-4000a480-fca8-11e9-8ed1-545a4b6e4cfd.png)

#### PayDollar is a global payment gateway that accepts payments from more than 200 countries.The PaySDK ensures payment is authorised by 3DS 2.0

- [Features](#features)
- [Requirements](#requirements)
- [Integration](#integration)



## Features

- Direct Client Side Connection
- Client Post through Browser Side Connection
- 3DS Integration
- Payment via Alipay


## Requirements

- minSdkVersion 19


## Integration

#### Step 1

Merchant need to integrate certificate. This certificate will be provided when merchant apply for the SDK service from [PayDollar Dashboard](https://www.paydollar.com/b2c2/eng/merchant/index.jsp).

Add `paysdk.properties` file in project Assests folder and set value of certificate.

e.g
merchant_rsa_publickey=GDJDFGHJHFGJHGJAQEF6H57F6JKNP489TFNKGH9874HNFDKLH98YHJVH78E67JNJVH98DFJJKDH099FDJKF

<!--img width="406" alt="Screenshot 2019-11-07 at 7 01 41 PM" src="https://user-images.githubusercontent.com/57219745/68393070-29b78480-0191-11ea-923a-19445f25fe52.png"-->

#### Step 2

Add `paydollarsdk-release.aar` in project lib folder and add below code in grade file

     repositories {
        flatDir {
          dirs 'libs'
      }
       }

Add below line in the dependencies to project’s gradle file

```
    implementation(name: 'paydollarsdk-release', ext: 'aar')
    
    implementation 'com.google.code.gson:gson:2.3.1'
    implementation 'com.google.android.gms:play-services-ads:11.8.0'
    implementation 'com.google.android.gms:play-services-location:11.8.0'
    implementation 'com.google.android.material:material:1.2.0-alpha04'
    implementation 'com.android.volley:volley:1.1.1'
    implementation 'org.bouncycastle:bcprov-jdk15on:1.60'
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.fasterxml.jackson.core:jackson-core:2.7.3'
    implementation 'com.fasterxml.jackson.core:jackson-annotations:2.7.3'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.7.3'
    implementation 'org.greenrobot:eventbus:3.0.0'

```

#### Step 3

Instantiate PaySDK class with context.

<!--##### Java -->

```PaySDK paySDK = new PaySDK(getApplicationContext());```

OR

You can also pass the SDK public key at the initialize process

```PaySDK paySDK = new PaySDK(getApplicationContext(),sdkPublicKey)```

<!--##### Kotlin

You can also implement same in Kotlin.

```var paySDK: PaySDK = PaySDK(applicationContext)```
-->

### Payment method 

Creating PayData for payment and process.


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
                paySDK.setRequestData(payData);
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/78635133-26046700-78c3-11ea-83cb-b6bad3511485.png) ![image](https://user-images.githubusercontent.com/57220911/78635172-39173700-78c3-11ea-86e2-75c870031954.png)


# Direct Payment
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
                paySDK.setRequestData(payData);
                paySDK.process();

```
![image](https://user-images.githubusercontent.com/57220911/78635357-ac20ad80-78c3-11ea-9d3d-9a77e83e5031.png)  ![image](https://user-images.githubusercontent.com/57220911/78635373-b3e05200-78c3-11ea-8a0c-68b4ba0fb404.png)

#### 3DS Integration
```
ThreeDSParams threeDSParams= new ThreeDSParams();
threeDSParams.setCustomerEmail("example@example.com");
threeDSParams.setMobilePhoneCountryCode("852");
threeDSParams.setMobilePhoneNumber("9000000000");
threeDSParams.setHomePhoneCountryCode("852");
threeDSParams.setHomePhoneNumber("8000000000");
threeDSParams.setWorkPhoneCountryCode("852");
threeDSParams.setWorkPhoneNumber("7000000000");
threeDSParams.setDeliveryEmail("example@example.com");
threeDSParams.setBillingCountryCode("344");
threeDSParams.setBillingState("");
threeDSParams.setBillingCity("Hong Kong");
threeDSParams.setBillingLine1("BillingLine1");
threeDSParams.setBillingLine2("BillingLine2");
threeDSParams.setBillingLine3("BillingLine3");
threeDSParams.setBillingPostalCode("121245");
threeDSParams.setShippingDetails("01");
threeDSParams.setShippingCountryCode("344");
threeDSParams.setShippingState("");
threeDSParams.setShippingCity("Hong Kong");
threeDSParams.setShippingLine1("ShippingLine1");
threeDSParams.setShippingLine2("ShippingLine2");
threeDSParams.setShippingLine3("ShippingLine3");
threeDSParams.setAcctCreateDate("20190401");
threeDSParams.setAcctAgeInd("01");
threeDSParams.setAcctCreateDate("20190401");
threeDSParams.setAcctAgeInd("01");
threeDSParams.setAcctLastChangeDate("20190401");
threeDSParams.setAcctLastChangeInd("01");
threeDSParams.setAcctPwChangeDate("20190401");
threeDSParams.setAcctPwChangeInd("01");
threeDSParams.setAcctPurchaseCount("10");
threeDSParams.setAcctCardProvisionAttempt("0");
threeDSParams.setAcctNumTransDay("0");
threeDSParams.setAcctNumTransYear("1");
threeDSParams.setAcctPaymentAcctDate("20190401");
threeDSParams.setAcctPaymentAcctInd("01");
threeDSParams.setAcctShippingAddrLastChangeDate("20190401");
threeDSParams.setAcctShippingAddrLastChangeInd("01");
threeDSParams.setAcctIsShippingAcctNameSame("T");
threeDSParams.setAcctIsSuspiciousAcct("F");
threeDSParams.setAcctAuthMethod("01");
threeDSParams.setAcctAuthTimestamp("20190401");
threeDSParams.setDeliveryTime("04");
threeDSParams.setPreOrderReason("01");
threeDSParams.setPreOrderReadyDate("20190401");
threeDSParams.setGiftCardAmount("1");
threeDSParams.setSdkMaxTimeout("05");
threeDSParams.setGiftCardCurr(EnvBase.Currency.HKD);
threeDSParams.setGiftCardCount("1");
payData.setThreeDSParams(threeDSParams);
Factory factory = new com.asiapay.sdk.integration.xecure3ds.Factory();
ConfigParametersconfigParameters = factory.newConfigParameters();
UiCustomizationuiCustomization = factory.newUiCustomization();
payData.setConfigParameters(configParameters);
payData.setUiCustomization(uiCustomization);
payData.setActivity(AuthActivity.this);

```
![image](https://user-images.githubusercontent.com/57220911/78636191-78df1e00-78c5-11ea-9be7-5de20c6848e2.png)  ![image](https://user-images.githubusercontent.com/57220911/78636204-7e3c6880-78c5-11ea-8f8e-d5225703cbfb.png)


![image](https://user-images.githubusercontent.com/57220911/78636216-85637680-78c5-11ea-925a-d320e58723a5.png)  ![image](https://user-images.githubusercontent.com/57220911/78636231-8bf1ee00-78c5-11ea-80a5-5d6abcd9762f.png)


Add above parameters with [Direct Payment](#Direct-Payment).


#### Payment via AliPay
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

#### Payment via WeChat
```
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("WECHATPAY");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");
                payData.setRemark("additional remark");
                paySDK.setRequestData(payData);

                paySDK.process();

```

### Payment response

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
For detailed description kindly follow [PayDollar Guide](http://paydollar.com/pdf/op/enpdintguide.pdf).
                
                


