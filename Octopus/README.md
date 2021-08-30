

# Octopus Payment

```
                String orderRef = "", payRef = "";
                PayData payData = new PayData();
                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayMethod("OCTOPUS");
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setOrderRef(getOrderRef());
                payData.setRemark("test remark");
                payData.setActivity(PaymentActivity.this);

                paySDK.setRequestData(payData);
                paySDK.process();
                
                 paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        cancelProgressDialog();

                        try {
                            //orderRef and payRef value is required in onActivityResult method to query transaction status
                            orderRef = payResult.getRef();
                            payRef = payResult.getPayRef();
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                                    .parse(payResult.getErrMsg()));
                            startActivityForResult(intent, OCTOPUS_APP_REQUEST_CODE);
                            
                        }catch (Exception e){

                            Log.d(TAG, "octopus: exp "+e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Data data) {

                        cancelProgressDialog();
                        //showAlert(data.getMessage()+data.getError());
                    }
                });

```

## Handle onActivityResult in following way-

```
if(requestCode == OCTOPUS_APP_REQUEST_CODE)
{
    payData = new PayData();
    payData.setMerchantId(textMerchantId.getEditText().getText().toString());
    payData.setEnvType(envType);
    payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
    payData.setOrderRef(orderRef);//this value we have received in getResponse callback method of "paySDK.responseHandler"
    payData.setPayRef(payRef);//this value we have received in getResponse callback method of "paySDK.responseHandler"
    payData.setRemark("");

    paySDK.setRequestData(payData);
    paySDK.query(EnvBase.Action.TX_QUERY);

    paySDK.queryResponseHandler(new QueryResponse() {
        @Override
        public void getResponse(TransactionStatus transactionStatus) {
            if (transactionStatus.getDetail() != null) {
                Toast.makeText(AuthActivity.this, transactionStatus.getDetail().get(0).getOrderStatus(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onError(Data data) {
            Toast.makeText(AuthActivity.this, data.getError(), Toast.LENGTH_LONG).show();
        }
    });
}

```

![image](https://user-images.githubusercontent.com/57220911/90011624-8b2bf700-dcbf-11ea-9f26-83510582ed23.png)




