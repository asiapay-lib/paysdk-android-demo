

#### Payment via WeChat

*	Create your app in the admin panel: https://open.weixin.qq.com

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

Add below code in onCreate method of WXPayEntryActivity

```

api = WXAPIFactory.createWXAPI(this, "Wechat_app_id");
api.handleIntent(getIntent(), this);

```

*  Add below PayData to PaySdk

```
                PayData payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setActivity(CurrentActivity.this);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("WECHATAPP");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("1");
                payData.setRemark("additional remark");
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


