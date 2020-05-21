

# Transaction Query

```
                PayData payData = new PayData();
                payData = new PayData();
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setEnvType(selectedEnvType);
                payData.setPayGate(selectedPayGate);
                payData.setOrderRef(textOrderRef.getEditText().getText().toString());
                payData.setRemark(" ");
                paySDK.setRequestData(payData);
                
                paySDK.query("TX_QUERY");

                //NOTE : For Trans Query Response will come in below format 
                        paySDK.queryResponseHandler(new QueryResponse() {
                            @Override
                            public void getResponse(TransactionStatus transactionStatus) {

                                cancelProgressDialog();
                                showAlert(transactionStatus.getResultCode());
                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });

```

![image](https://user-images.githubusercontent.com/57220911/82582763-207f8980-9bb0-11ea-8ab3-3f7619bcd98d.png) ![image](https://user-images.githubusercontent.com/57220911/82582688-0b0a5f80-9bb0-11ea-9336-f9d3c44cdbfd.png)
