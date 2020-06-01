
# Pay Method

```
                PayData payData = new PayData();
                payData = new PayData();
                
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setEnvType(selectedEnvType);
                payData.setPayGate(selectedPayGate);
     
                payData.setRemark(" ");
                
                paySDK.setRequestData(payData);
                
                paySDK.query(EnvBase.Action.PAY_METHOD);

                //NOTE : For allowed Pay Method Response will come in below format 
                        paySDK.payMethodResponseHandler(new PayMethodResponse() {
                            @Override
                            public void getResponse(PayMethodResult payMethodResult) {

                                cancelProgressDialog();
                                showAlert(payMethodResult.getMethods());
                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });

```
