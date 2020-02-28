package com.asiapay.SDKApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.asiapay.sdk.PaySDK;
import com.asiapay.sdk.integration.CardDetails;
import com.asiapay.sdk.integration.Data;
import com.asiapay.sdk.integration.EnvBase;
import com.asiapay.sdk.integration.PayData;
import com.asiapay.sdk.integration.PayResult;
import com.asiapay.sdk.integration.PaymentResponse;
import com.asiapay.sdk.integration.xecure3ds.Factory;
import com.asiapay.sdk.integration.xecure3ds.ThreeDSParams;
import com.asiapay.sdk.integration.xecure3ds.spec.ConfigParameters;
import com.asiapay.sdk.integration.xecure3ds.spec.UiCustomization;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDirectCall;
    private ProgressDialog progressDialog;
    PaySDK paySDK;
    ThreeDSParams threeDSParams;
    PayData payData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDirectCall = findViewById(R.id.btn_pay);
        btnDirectCall.setOnClickListener(this);
        paySDK = new PaySDK(getApplicationContext());
        threeDSParams = new ThreeDSParams();
        threeDSParams.setCustomerEmail("example@example.com");
        threeDSParams.setMobilePhoneCountryCode("852");
        threeDSParams.setMobilePhoneNumber("9000000000");
        threeDSParams.setHomePhoneCountryCode("852");
        threeDSParams.setHomePhoneNumber("8000000000");
        threeDSParams.setWorkPhoneCountryCode("852");
        threeDSParams.setWorkPhoneNumber("7000000000");
        threeDSParams.setDeliveryEmail("example@example.com");
        threeDSParams.setBillingCountryCode("344");
        threeDSParams.setBillingState("");
        threeDSParams.setBillingCity("Hong Kong");
        threeDSParams.setBillingLine1("BillingLine1");
        threeDSParams.setBillingLine2("BillingLine2");
        threeDSParams.setBillingLine3("BillingLine3");
        threeDSParams.setBillingPostalCode("121245");
        threeDSParams.setShippingDetails("01");
        threeDSParams.setShippingCountryCode("344");
        threeDSParams.setShippingState("");
        threeDSParams.setShippingCity("Hong Kong");
        threeDSParams.setShippingLine1("ShippingLine1");
        threeDSParams.setShippingLine2("ShippingLine2");
        threeDSParams.setShippingLine3("ShippingLine3");
        threeDSParams.setAcctCreateDate("20190401");
        threeDSParams.setAcctAgeInd("01");
        threeDSParams.setAcctCreateDate("20190401");
        threeDSParams.setAcctAgeInd("01");
        threeDSParams.setAcctLastChangeDate("20190401");
        threeDSParams.setAcctLastChangeInd("01");
        threeDSParams.setAcctPwChangeDate("20190401");
        threeDSParams.setAcctPwChangeInd("01");
        threeDSParams.setAcctPurchaseCount("10");
        threeDSParams.setAcctCardProvisionAttempt("0");
        threeDSParams.setAcctNumTransDay("0");
        threeDSParams.setAcctNumTransYear("1");
        threeDSParams.setAcctPaymentAcctDate("20190401");
        threeDSParams.setAcctPaymentAcctInd("01");
        threeDSParams.setAcctShippingAddrLastChangeDate("20190401");
        threeDSParams.setAcctShippingAddrLastChangeInd("01");
        threeDSParams.setAcctIsShippingAcctNameSame("T");
        threeDSParams.setAcctIsSuspiciousAcct("F");
        threeDSParams.setAcctAuthMethod("01");
        threeDSParams.setAcctAuthTimestamp("20190401");
        threeDSParams.setDeliveryTime("04");
        threeDSParams.setPreOrderReason("01");
        threeDSParams.setPreOrderReadyDate("20190401");
        threeDSParams.setGiftCardAmount("1");
        threeDSParams.setGiftCardCurr(EnvBase.Currency.HKD);
        threeDSParams.setGiftCardCount("1");

    }

    @Override
    public void onClick(View v) {
        showProgressDialog("Processing 3DS 2.0 payment, please wait...");

        new Handler().postDelayed(new Runnable() {
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                String currentDateAndTime = sdf.format(new Date());

                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount("1");
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef(currentDateAndTime);
                payData.setPayMethod("3DSSDK");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId("123454");
                payData.setRemark(currentDateAndTime);

                CardDetails cardDetails = new CardDetails();
                cardDetails.setCardNo("1234567890123456");
                cardDetails.setEpMonth("07");
                cardDetails.setEpYear("2030");
                cardDetails.setSecurityCode("123");
                cardDetails.setCardHolder("test Card");

                payData.setCardDetails(cardDetails);

                Map<String, String> extraDataMP = new HashMap<>();
                payData.setExtraData(extraDataMP);
                payData.setThreeDSParams(threeDSParams);

                Factory factory = new com.asiapay.sdk.integration.xecure3ds.Factory();
                ConfigParameters configParameters = factory.newConfigParameters();
                UiCustomization uiCustomization = factory.newUiCustomization();
                payData.setConfigParameters(configParameters);
                payData.setUiCustomization(uiCustomization);
                payData.setActivity(MainActivity.this);

                paySDK.setRequestData(payData);
                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {
                        cancelProgressDialog();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setCancelable(true);
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        if (payResult.isSuccess()) {
                            builder.setMessage(payResult.getSuccessMsg());
                        } else {
                            builder.setMessage(payResult.getErrMsg());
                        }
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                    @Override
                    public void onError(Data data) {

                    }
                });
                paySDK.process();

            }
        }, 100);



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        paySDK.cleanMemory();
    }

    private void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(MainActivity.this);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
