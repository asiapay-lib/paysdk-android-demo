package com.asiapay.SDKApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asiapay.sdk.PaySDK;
import com.asiapay.sdk.integration.CardDetails;
import com.asiapay.sdk.integration.Data;
import com.asiapay.sdk.integration.EnvBase;
import com.asiapay.sdk.integration.PayData;
import com.asiapay.sdk.integration.PayResult;
import com.asiapay.sdk.integration.PaymentResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDirectCall;
    Button btnHostedCall;
    PaySDK paySDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDirectCall = findViewById(R.id.btn_pay1);
        btnDirectCall.setOnClickListener(this);

        btnHostedCall = findViewById(R.id.btn_pay2);
        btnHostedCall.setOnClickListener(this);

        paySDK = new PaySDK(getApplicationContext());
    }

    @Override
    public void onClick(View v) {
        PayData payData = new PayData();
        if (v.getId() == R.id.btn_pay2) {
            payData.setChannel(EnvBase.PayChannel.WEBVIEW);
        } else {
            payData.setChannel(EnvBase.PayChannel.DIRECT);

            CardDetails cardDetails = new CardDetails();
            cardDetails.setCardNo("1234567890123456");
            cardDetails.setEpMonth("01");
            cardDetails.setEpYear("2020");
            cardDetails.setSecurityCode("123");
            cardDetails.setCardHolder("abc abc");
            payData.setCardDetails(cardDetails);
        }
        payData.setEnvType(EnvBase.EnvType.SANDBOX);
        payData.setAmount("10");
        payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
        payData.setCurrCode(EnvBase.Currency.HKD);
        payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
        payData.setOrderRef("abcde12345");
        payData.setPayMethod("VISA");
        payData.setLang(EnvBase.Language.ENGLISH);
        payData.setMerchantId("1");
        payData.setRemark("additional remark");
        paySDK.setRequestData(payData);
        paySDK.responseHandler(new PaymentResponse() {
            @Override
            public void getResponse(final PayResult payResult) {
                Toast.makeText(MainActivity.this, payResult.getErrMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(final Data data) {
                Toast.makeText(MainActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        paySDK.process();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        paySDK.cleanMemory();
    }
}
