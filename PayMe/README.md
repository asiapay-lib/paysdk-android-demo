

# PayMe

```

void handlePayment() {
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
                
                // handle result callback with deeplink 
                payData.setCallbackSuccess("xxx://abc//success");
                payData.setCallbackCancel("xxx://abc//cancelled");
                payData.setCallbackError("xxx://abc//error");
                payData.setCallbackFail("xxx://abc//fail");
                
                //Optional Parameters
                payData.setRemark("additional remark");
                
                paySDK.setRequestData(payData);
                paySDK.process();
}


          private void handleCallback() {
          
            paySDK.responseHandler(new PaymentResponse() {
            @Override
            public void getResponse(PayResult payResult) {

                cancelProgressDialog();

                try {
                    String callbackUrl = paySDK.decodeData(payResult.getErrMsg());
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                            .parse(callbackUrl));

                    startActivity(intent);

                    handleIntent(getIntent());
                    Log.d("PayMeData", "getResponse: "+callbackUrl);

                }catch (Exception e){
                    Log.d(TAG, "getResponse: "+e.getMessage());
                }
            }

            @Override
            public void onError(Data data) {

                cancelProgressDialog();
                showAlert(data.getMessage());
            }
        });
    }

}
     
        void handleIntent(Intent intent){

        String appLinkAction=intent.getAction();
        Uri appLinkData=intent.getData();

        // 1
        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            // 2
            String orderId = appLinkData.getQueryParameter("order");
            if (!orderId.isEmpty()) {

                String payRef;
                try {
                    payRef = paySDK.decodeData(orderId);

                    //Handle Query function
                    queryStatus(payRef);

                }catch (Exception e){
                    Log.d("paymeData", "handleIntent: "+e.getMessage());
                }
                

            }
        }

    }

    void queryStatus(String payRef){

        payData.setPayRef(payRef);

        paySDK.setRequestData(payData);

        paySDK.query(EnvBase.Action.TX_QUERY);

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


    }

```
For Deeplinking Demo kindly check  https://github.com/seema-asiapay/deeplinking-sample/blob/master/app/src/main/AndroidManifest.xml
