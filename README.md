
## PaySDK -   
##### Android SDK which seamlessly integrate PayDollar Payment Gateway

### Overview

##### AsiaPay offers comprehensive and flexible payment services and solutions.Via our state-of the-art secure multi-currency, multi-lingual and multi-channel processing platform, we offer payment acceptance flexibility for Direct Merchant Account or Master Account offerings, with options for credit card and direct debit acceptance, whether online, MOTO or call centre and popular mobile wallets.

### How it works
#### The cardholder inputs their bank account/card details on the PayDollar Checkout form in order to pay for a product/service.The transaction amount is routed via the card networks to PayDollar's acquiring banking partners. Once PayDollar receives the amount, it is settled to your bank account after fees deduction.

### Get Started

1. [PaySdk Configuration](#paysdk-configuration)
2. [Add Security Key & Domain](#add-security-key-and-domain)
3. [Initialize Payload](#initialize-payload)
4. [Collect Payment Result](#collect-payment-result)

### PaySdk Configuration

*  Requirements
Android API level 19 (4.4 - Kitkat) & above 
Android Gradle Plugin 3.5.1

* Download the latest sdk from https://github.com/asiapay-lib/paysdk-android-lib

*	Copy paydollarsdk-release.aar to libs folder. 

* 	Add below line to project’s gradle file:
	```  
    repositories {
			flatDir {
			dirs'libs'
			} 
        }
	```
	
* Add following libraries to gradle file

	```  
    implementation(name: 'PaySDK-2.4.2.5', ext: 'aar')
    implementation(name: 'alipaySdk-15.6.2-20190416165036', ext: 'aar')
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.google.code.gson:gson:2.3.1'
    implementation 'org.bouncycastle:bcprov-jdk15on:1.60'
    implementation 'com.android.volley:volley:1.1.1'

    implementation 'org.greenrobot:eventbus:3.0.0'
    implementation 'com.google.android.material:material:1.2.0-alpha04'
    implementation 'com.google.android.material:material:1.0.0-beta01'

    implementation 'com.google.android.gms:play-services-ads:17.2.1'
    implementation 'com.google.android.gms:play-services-location:16.0.0'

    // for google pay
    implementation 'com.google.android.gms:play-services-wallet:16.0.1'

    //WeChat
    implementation 'com.tencent.mm.opensdk:wechat-sdk-android-with-mta:+'
	``` 


### Add Security Key and Domain

Create the paysdk.properties file in the assets folder with the following attribute.

merchant_rsa_publickey=<<SECURITY_KEY>>

domain=<<SDK Server Domain (Optional)>>
	

#### NOTE: Naming of the property file and its attributes must be same.

### Initialize Payload

* Instantiate PaySDK class.
	```  
    PaySDK paySDK = new PaySDK(getApplicationContext());
    ```

* Use following payment services.
 

    

Payment Services | Example
--- | --- 
Direct Payment | [Direct Payment](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/DirectPay/README.md)
Webview Payment | [Webview Payment](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/Webview/README.md)
3Ds 2.0 Extra Paramters  | [3DS](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/3DS/README.md)
Alipay  | [Alipay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/AliPay/README.md)
WeChat Pay  | [WeChat Pay](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/WeChat/README.md)
Google Pay™  | [Google Pay™](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/GooglePay/README.md)
Octopus | [Octopus](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/Octopus)
Member Pay | [Member Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/MemberPay/README.md)
Installment Pay | [Installmet Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/InstallmentPay/README.md)
Schedule Pay | [Schedule Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/SchedulePay/README.md)
Promo Pay | [Promo Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/PromoPay/README.md)
Transaction Query | [Transaction Query](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/TRANSQUERY/README.md)
Payment Methods Query | [Payment Methods Query](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/PayMethod/README.md)
EVoucher | [EVoucher](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/EVoucher)
PayMe | [PayMe](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/PayMe)

### Collect Payment Result

```
               paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {
		    
		    //For PayMe handle response in following way
		    
		     String callbackUrl = paySDK.decodeData(payResult.getErrMsg());
                     Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(callbackUrl));
		     startActivity(intent);
                    }

                    @Override
                    public void onError(Data data) {
                    }
                });
```

## Related Sample
[Deeplink Demo](https://github.com/asiapay-lib/android-deeplink-demo)

### Support
Asiapay is a tech company. All our engineers handle support too. You can write to us at it@pesopay.com ,it@paydollar.com , it@siampy.com and expect a response from the devs from the Android SDK.

### License
MIT Licensed. LICENSE file added to repo.



                
                


