

# Octopus Payment



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
payMethod | Yes | String | OCTOPUS | This Parameter is used to setup payment method for the transaction.
payChannel | Yes | EnvBase.PayChannel | EnvBase.PayChannel.DIRECT | This Parameter is used to setup payment channel for the transaction. i.e (WEBVIEW, DIRECT)
language | Yes | EnvBase.Language | EnvBase.Language.ENGLISH | This Parameter is used to setup payment language for the transaction.
remark | No | String | remark | This Parameter is used to setup payment remark for the transaction.


### Payment Call Example:      
*   Initialize the PayData object and prepare the payment detail for the transaction.


```
                payData = new PayData();
                paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
                paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","OCTOPUS",EnvBase.PayChannel.DIRECT, EnvBase.Language.ENGLISH,"additional remark");

                paySDK.setRequestData(payData);
                paySDK.process();

```

### Collect Payment Response:
*   Initialize a payment event handler to capture the payment response and result.

```
                 String orderRef = "", payRef = "";
                 
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


### Handle onActivityResult in following way-

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




