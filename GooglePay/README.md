
# Google Pay

## Prerequisites

#### Before you get started, review the following prerequisites:

* Install the Google Play services version 18.0.0 or greater.
* Add a payment method to Google.
* Adhere to [Google Pay API Acceptable Use Policy](https://payments.developers.google.com/terms/aup) and the [Google Play developer policy](https://support.google.com/googleplay/android-developer/answer/9858738).

## Sdk Setup

* Enable Google Pay in your app by adding below line to Manifest file.
```
<meta-data
        android:name="com.google.android.gms.wallet.api.enabled"
        android:value="true" />
        
 ```
* Add below library for Google Pay Services in build.gradle file.
```
implementation 'com.google.android.gms:play-services-wallet:16.0.1'

```

* Initialize PaymentClient in onCreate method 

```
// A client for interacting with the Google Pay API
private PaymentsClient mPaymentsClient;
//GooglePay
mPaymentsClient = PaymentsUtil.createPaymentsClient(this);
```
* Implement isGooglePayAvailable to know wheter your app is ready for GooglePay transaction or not

```
PaymentsUtil.isGooglePayAvailable(this, mPaymentsClient, new PaymentsUtil.ICheckGooglePay() {
    @Override
    public void success() {

        //Visible GooglePay Button here
    }

    @Override
    public void error() {

    }
});

```

* Request Payment Via Google Pay using PayData

```
 		payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(selectedEnvType);
                payData.setGooglePayAuth(EnvBase.GooglePayAuth.PAN_CRYPTO);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayGate(selectedPayGate);
                payData.setCurrCode(selectedCurrency);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef(textOrderRef.getEditText().getText().toString());
                payData.setOrderRef(textOrderRef.getEditText().getText().toString());
                payData.setPayMethod("");  //PayMethod should be blank
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());


                payData.setRemark(" ");


Optional<JSONObject> paymentDataRequestJson = GooglePay.getPaymentDataRequest(payData);

if (!paymentDataRequestJson.isPresent()) {
    return;
}
PaymentDataRequest request =
        PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());
if (request != null) {
    AutoResolveHelper.resolveTask(
            mPaymentsClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE);
}

```
* Handle OnActivityResult in your Activity for GooglePay response

```

@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
        // value passed in AutoResolveHelper
        case LOAD_PAYMENT_DATA_REQUEST_CODE:
            switch (resultCode) {
                case Activity.RESULT_OK:
                    PaymentData paymentData = PaymentData.getFromIntent(data);
                    final String paymentInfo = paymentData.toJson();
                    if (paymentInfo == null) {
                        return;
                    }

		       //When Result Come pass to PaySDK		
                        handleGooglePay(paymentInfo);
                    break;
                case Activity.RESULT_CANCELED:
                    // Nothing to here normally - the user simply cancelled without selecting a
                    // payment method.

                    break;
                case AutoResolveHelper.RESULT_ERROR:
                    Status status = AutoResolveHelper.getStatusFromIntent(data);                    
                    break;
                default:
                    // Do nothing.
            }

            // Re-enables the Google Pay payment button.
            //mGooglePayButton.setClickable(true);
            break;
    }
}

```
* Defination of handleGooglePay()

```
void handleGooglePay(String strResp){
    String base64encodedString=null;


try {

 byte[] byteString=strResp.getBytes("UTF-8");
 base64encodedString= android.util.Base64.encodeToString(byteString, Base64.NO_WRAP);

} catch (Exception e) {
    e.printStackTrace();
}


    Map<String, String> extraDataGP = new HashMap<String, String>();


    extraDataGP.put("eWalletPaymentData",base64encodedString);
    extraDataGP.put("eWalletService","T");
    extraDataGP.put("eWalletBrand","GOOGLE");
    payData.setExtraData(extraDataGP);

    paySDK.setRequestData(payData);

    paySDK.process();

//Here you will get Payment response.
    paySDK.responseHandler(new PaymentResponse() {
        @Override
        public void getResponse(PayResult payResult) {


            Toast.makeText(AuthActivity.this, payResult.getErrMsg(), Toast.LENGTH_SHORT).show();
            
        }

        @Override
        public void onError(Data data) {

            Toast.makeText(AuthActivity.this,data.getError(),Toast.LENGTH_SHORT).show();

        }
    });
}

```
