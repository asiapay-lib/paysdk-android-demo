
# Google Pay™

## Prerequisites

#### Before you get started, review the following prerequisites:

* Install the Google Play™ services version 18.0.0 or greater.
* Add a payment method to Google.
* Please read and accept the following policy for Google Pay™: [Google Pay™ API Acceptable Use Policy](https://payments.developers.google.com/terms/aup) and [Google Pay™ API Terms of Service](https://payments.developers.google.com/terms/sellertos).
* For the "Buy with G Pay" button, please follow [Google Pay™ Android Brand Guide](https://developers.google.com/pay/api/android/guides/brand-guidelines)
* For GooglePay min api level is 24
## Sdk Setup

* Enable Google Pay™ in your app by adding below line to Manifest file.
```
<meta-data
        android:name="com.google.android.gms.wallet.api.enabled"
        android:value="true" />
        
 ```
* Add below library for Google Pay™ Services in build.gradle file.
```
implementation 'com.google.android.gms:play-services-wallet:16.0.1'

```

* Initialize PaymentClient in onCreate method 

```
// A client for interacting with the Google Pay™ API
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

## Request Payment Via Google Pay™ using PayData


### Setup paySDK Configuration:      
*   This method is used to setup the payment configurations like merchantId, environmentType and paymentGateway.

```

paySDK.setPayConfig(Activity activity, PayData payData, String merchantId, EnvBase.EnvType EnvType, EnvBase.PayGate PayGate);

```


Parameters -:

Name | Mandatory | Data Type | Sample Data | Description
--- | --- | --- | --- | ---
activity | Yes | Activity | currentActivity | This Parameter is used to setup payData activity for further uses.
payData | Yes | PayData Class | payData | This Parameter is used to setup payData POJO data.
merchantId | Yes | String | 35001282 | This Parameter is used to pass merchant id for the payment configuration.
EnvType | Yes | EnvBase.EnvType | EnvBase.EnvType.SANDBOX | This Parameter is used to setup the environment type. i.e (SANDBOX, PRODUCTION)
PayGate | Yes | EnvBase.PayGate | EnvBase.PayGate.PAYDOLLAR | This Parameter is used to setup payment Gateway type. i.e (PAYDOLLAR, SIAMPAY, PESOPAY)



### Setup Payment Data:      
*   This method is used to setup the payment basic information like Amount,currency,orderRef,payMethod etc.

```

paySDK.setPayData(String Amount,EnvBase.Currency currency,EnvBase.PayType payType,String orderRef, String payMethod,EnvBase.PayChannel payChannel,EnvBase.Language language,String remark);

```


Parameters -:

Name | Mandatory | Data Type | Sample Data | Description
--- | --- | --- | --- | ---
Amount | Yes | String | 20.00 | This Parameter is used to setup transaction amount.
currency | Yes | EnvBase.Currency | EnvBase.Currency.HKD | This Parameter is used to setup currency for the transaction.
payType | Yes | EnvBase.PayType | EnvBase.PayType.NORMAL_PAYMENT | This Parameter is used setup the payment type for the transaction. i.e (NORMAL_PAYMENT, HOLD_PAYMENT)
orderRef | Yes | String | 156487515598 | This Parameter is used to setup the order reference number for the transaction.
payMethod | Yes | String | VISA | This Parameter is used to setup payment method for the transaction.
payChannel | Yes | EnvBase.PayChannel | EnvBase.PayChannel.WEBVIEW | This Parameter is used to setup payment channel for the transaction. i.e (WEBVIEW, DIRECT)
language | Yes | EnvBase.Language | EnvBase.Language.ENGLISH | This Parameter is used to setup payment language for the transaction.
remark | No | String | remark | This Parameter is used to setup payment remark for the transaction.



Kindly select GooglePayAuth param according to below description -:

Google Pay™ Auth Method | Description
--- | --- 
GooglePayAuth.PAN_ONLY | This authentication method is associated with payment cards stored on file with the user's Google Account. Returned payment data includes personal account number (PAN) with the expiration month and the expiration year.
GooglePayAuth.CRYPTOGRAM_3DS | This authentication method is associated with cards stored as Android device tokens. Returned payment data includes a cryptogram generated on the device. 
GooglePayAuth.PAN_CRYPTO | This method will support payment cards and Android device tokens from all supported card networks , i.e GooglePayAuth.PAN_ONLY & GooglePayAuth.CRYPTOGRAM_3DS

```
payData = new PayData();
payData.setGooglePayAuth(EnvBase.GooglePayAuth.PAN_CRYPTO);
paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","GOOGLE",EnvBase.PayChannel.WEBVIEW, EnvBase.Language.ENGLISH,"additional remark");


// Set card network
ArrayList<GPayBrand> brands = new ArrayList<>();
brands.add(GPayBrand.VISA);
brands.add(GPayBrand.MASTERCARD);
brands.add(GPayBrand.AMERICANEXPRESS);
payData.setGpayBrands(brands);

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

            // Re-enables the Google Pay™ payment button.
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

 base64encodedString= paySDK.encodeData(strResp);

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