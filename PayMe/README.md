

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
                payData.setPayMethod("PayMe");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");
                
                //Optional Parameters
                payData.setCallbackSuccess("https://abc.com//success");
                payData.setCallbackCancel("https://abc.com//cancelled");
                payData.setCallbackError("https://abc.com//error");
                payData.setCallbackFail("https://abc.com//fail");
                payData.setRemark("additional remark");
                
                paySDK.setRequestData(payData);
                paySDK.process();
                
                
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
