

# Octopus Payment

```
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

                            // method to get the URI 
                            String octopusuri = paySDK.decodeData(payResult.getErrMsg());

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                                    .parse(octopusuri));
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
if(requestCode == OCTOPUS_APP_REQUEST_CODE && resultCode == Activity.RESULT_OK)
{
            showAlert("Transaction Completed.");
}

```

![image](https://user-images.githubusercontent.com/57220911/90011624-8b2bf700-dcbf-11ea-9f26-83510582ed23.png)




