package com.asiapay.samsungpay.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;



public class SuccessAct extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        textView=findViewById(R.id.textView);
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent){

        String appLinkAction=intent.getAction();
        Uri appLinkData=intent.getData();
        showDeepLinkOffer(appLinkAction, appLinkData);
    }

    private void showDeepLinkOffer(String appLinkAction,Uri appLinkData) {
        // 1
        if (Intent.ACTION_VIEW == appLinkAction && appLinkData != null) {
            // 2
            String promotionCode = appLinkData.getQueryParameter("order");
            if (promotionCode.isEmpty()) {

                textView.setText("Empty Data");

            } else {
                textView.setText("Code is "+promotionCode);

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}