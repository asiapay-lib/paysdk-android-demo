package com.asiapay.paysdkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.asiapay.sdk.PaySDK;
import com.asiapay.sdk.integration.CardDetails;
import com.asiapay.sdk.integration.Data;
import com.asiapay.sdk.integration.EnvBase;
import com.asiapay.sdk.integration.PayData;
import com.asiapay.sdk.integration.PayResult;
import com.asiapay.sdk.integration.PaymentResponse;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnPay;

    private PaySDK paySDK;
    PayData payData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paySDK = new PaySDK(getApplicationContext());  // key added to paysdk.properties

        //or pass rsa public key here
        //paySDK=new PaySDK(getApplicationContext(),"sfghjhgdjhgdh","12345678");


        btnPay=findViewById(R.id.btn_pay);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("10");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef("abcde12345");
                payData.setPayMethod("VISA");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("12345678");

                CardDetails cardDetails = new CardDetails();
                cardDetails.setCardNo("1234567890123456");
                cardDetails.setEpMonth("01");
                cardDetails.setEpYear("2020");
                cardDetails.setSecurityCode("123");
                cardDetails.setCardHolder("Abc Xyz");


                payData.setCardDetails(cardDetails);

                payData.setRemark(" ");

                // Optional Parameter (For Value-Added Service) Refer Doc
                Map<String, String> extraData = new HashMap<String, String>();


                payData.setExtraData(extraData);

                paySDK.setRequestData(payData);

                paySDK.process();

                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        Toast.makeText(MainActivity.this, payResult.getErrMsg(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Data data) {

                        Toast.makeText(MainActivity.this, data.getError(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
