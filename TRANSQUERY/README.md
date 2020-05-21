

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

                //NOTE : FOr Trans Query Response will come in below format 
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
![image](https://user-images.githubusercontent.com/57220911/78635357-ac20ad80-78c3-11ea-9d3d-9a77e83e5031.png)  ![image](https://user-images.githubusercontent.com/57220911/78635373-b3e05200-78c3-11ea-8a0c-68b4ba0fb404.png)
