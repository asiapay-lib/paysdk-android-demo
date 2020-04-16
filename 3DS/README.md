
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


Add above parameters with Direct Payment.
