

# PayMe

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
                
                //Optional Parameters
                payData.setCallbackSuccess("successUrl");
                payData.setCallbackCancel("cancelUrl");
                payData.setCallbackError("errorUrl");
                payData.setCallbackFail("failUrl");
                payData.setRemark("additional remark");
                
                paySDK.setRequestData(payData);
                paySDK.process();
                
                
                //For PayMe we need to use encoded response to show callback url
                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        try {
                        
                            //Decode getErrMsg() to get callback Url
                            String callbackUrl=paySDK.decodeData(payResult.getErrMsg());
                            
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(callbackUrl));
                            startActivity(intent);

                        }catch (Exception e){
                            Log.d("Error",e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Data data) {

                        Toast.makeText(AuthActivity.this, data.getError(), Toast.LENGTH_SHORT).show();

                    }
                });
                

```
![image](https://user-images.githubusercontent.com/57220911/78635357-ac20ad80-78c3-11ea-9d3d-9a77e83e5031.png)  ![image](https://user-images.githubusercontent.com/57220911/78635373-b3e05200-78c3-11ea-8a0c-68b4ba0fb404.png)
