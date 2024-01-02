

# Payment via WeChat

*	Create your app in the admin panel: https://open.weixin.qq.com

*   Add below line in gradle file
```
 implementation 'com.tencent.mm.opensdk:wechat-sdk-android:+'
```

* Create Activity  WXPayEntryActivity under package wxapi & export in Manifest
e.g

```

       <activity
        android:name=".wxapi.WXPayEntryActivity"
        android:exported="true"
        android:launchMode="singleTop" />

```

 For example, if the package name is com.example.myapp, then the full path to this activity should be: com.example.myapp.wxapi.WXPayEntryActivity
 
 
 * Use Wechat_app_id in  WXPayEntryActivity
 
 ```
 //Declare IWXAPI
 private IWXAPI api;

```

Add below code in onCreate method of [WXPayEntryActivity](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/app/src/main/java/com/asiapay/SDKApp/wxapi/WXPayEntryActivity.java)

```

api = WXAPIFactory.createWXAPI(this, "Wechat_app_id");
api.handleIntent(getIntent(), this);

```


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
Amount | Yes | String | 10.00 | This Parameter is used to setup transaction amount.
currency | Yes | EnvBase.Currency | EnvBase.Currency.HKD | This Parameter is used to setup currency for the transaction.
payType | Yes | EnvBase.PayType | EnvBase.PayType.NORMAL_PAYMENT | This Parameter is used setup the payment type for the transaction. i.e (NORMAL_PAYMENT, HOLD_PAYMENT)
orderRef | Yes | String | 156487515598 | This Parameter is used to setup the order reference number for the transaction.
payMethod | Yes | String | WECHATAPP | This Parameter is used to setup payment method for the transaction.
payChannel | Yes | EnvBase.PayChannel | EnvBase.PayChannel.DIRECT | This Parameter is used to setup payment channel for the transaction. i.e (WEBVIEW, DIRECT)
language | Yes | EnvBase.Language | EnvBase.Language.ENGLISH | This Parameter is used to setup payment language for the transaction.
remark | No | String | remark | This Parameter is used to setup payment remark for the transaction.


### Payment Call Example:      
*   Initialize the PayData object and prepare the payment detail for the transaction.

```
                PayData payData = new PayData();
                paySDK.setPayConfig(PaymentActivity.this,payData,"1",EnvBase.EnvType.SANDBOX,EnvBase.PayGate.PAYDOLLAR);
                paySDK.setPayData("10",EnvBase.Currency.HKD,EnvBase.PayType.NORMAL_PAYMENT,"abcde12345","WECHATAPP",EnvBase.PayChannel.DIRECT, EnvBase.Language.ENGLISH,"additional remark");
                paySDK.setRequestData(payData);
                paySDK.process();

```

 * Implement interface IWXAPIEventHandler in WXPayEntryActivity & override required methods

```

    @Override
    protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    setIntent(intent);
    api.handleIntent(intent, this);
    }

```

```
//Here you will get wechat response
@Override
public void onResp(BaseResp resp) {
 switch (resp.errCode){
        case BaseResp.ErrCode.ERR_OK:
        // Here you will get Payment Succes response.
            break;
        case BaseResp.ErrCode.ERR_USER_CANCEL:
            // Here you will get Payment Cancel response.
            break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                // Here you will get Payment Denied response.
                break;
    }
}

```


