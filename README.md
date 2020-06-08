
## PaySDK -   
##### Android SDK which seamlessly integrate PayDollar Payment Gateway

### Overview

##### AsiaPay offers comprehensive and flexible payment services and solutions.Via our state-of the-art secure multi-currency, multi-lingual and multi-channel processing platform, we offer payment acceptance flexibility for Direct Merchant Account or Master Account offerings, with options for credit card and direct debit acceptance, whether online, MOTO or call centre and popular mobile wallets.

### How it works
#### The cardholder inputs their bank account/card details on the PayDollar Checkout form in order to pay for a product/service.The transaction amount is routed via the card networks to PayDollar's acquiring banking partners. Once PayDollar receives the amount, it is settled to your bank account after fees deduction.

### Get Started

1. [PaySdk Configuration](#paysdk-configuration)
2. [Add Security Key](#add-security-key)
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
    implementation(name: 'PaySDK-2.1.0', ext: 'aar')
    implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
    implementation 'com.google.code.gson:gson:2.3.1'
    implementation 'org.bouncycastle:bcprov-jdk15on:1.60'
    implementation 'com.android.volley:volley:1.1.1'
    implementation 'com.fasterxml.jackson.core:jackson-core:2.7.3'
    implementation 'com.fasterxml.jackson.core:jackson-annotations:2.7.3'
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.7.3'
    implementation 'org.greenrobot:eventbus:3.0.0'
    implementation 'com.google.android.gms:play-services-ads:11.8.0'
    implementation 'com.google.android.gms:play-services-location:11.8.0'
    implementation 'com.google.android.material:material:1.2.0-alpha04'
	``` 


### Add Security Key

Create the paysdk.properties file in the assets folder with the following attribute.

sdk_rsa_publickey=<<SECURITY_KEY>>

### Initialize Payload

* Instantiate PaySDK class.
	```  
    PaySDK paySDK = new PaySDK(getApplicationContext());
    ```

* Use following payment options.
 

    

Payment Option | Example
--- | --- 
Direct Payment | [Direct Payment](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/DirectPay/README.md)
Webview Payment | [Webview Payment](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/Webview/README.md)
Add 3DS  | [3DS](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/3DS/README.md)
AliPay  | [AliPay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/AliPay/README.md)
Member Pay | [Member Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/MemberPay/README.md)
Installment Pay | [Installmet Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/InstallmentPay/README.md)
Schedule Pay | [Schedule Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/SchedulePay/README.md)
Promo Pay | [Promo Pay](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/PromoPay/README.md)
Trans Query | [Trans Query](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/TRANSQUERY/README.md)
Pay Methods | [Pay Methods](https://github.com/asiapay-lib/paysdk-android-demo/tree/master/PayMethod/README.md)

### Collect Payment Result

```
               paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {
                    }

                    @Override
                    public void onError(Data data) {
                    }
                });
```

For Complete Integration guide follow [PaySdk_Android_IntegrationGuide.docx](https://github.com/asiapay-lib/paysdk-android-lib/files/4444819/PaySdk_ANdroid_IntegrationGuide.docx)

### Support
Asiapay is a tech company. All our engineers handle support too. You can write to us at it@pesopay.com ,it@paydollar.com , it@siampy.com and expect a response from the devs from the Android SDK.

### License
MIT Licensed. LICENSE file added to repo.



                
                


