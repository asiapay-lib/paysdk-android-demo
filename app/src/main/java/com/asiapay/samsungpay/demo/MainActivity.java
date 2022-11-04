package com.asiapay.samsungpay.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.asiapay.samsungpay.demo.expandableview.ExpandableRelativeLayout;
import com.asiapay.sdk.PaySDK;
import com.asiapay.sdk.model.CardDetails;
import com.asiapay.sdk.model.Data;
import com.asiapay.sdk.enums.EnvBase;
import com.asiapay.sdk.model.PayData;
import com.asiapay.sdk.model.PayResult;
import com.asiapay.sdk.integration.PaymentResponse;
import com.asiapay.sdk.integration.xecure3ds.Factory;
import com.asiapay.sdk.integration.xecure3ds.ThreeDSParams;
import com.asiapay.sdk.integration.xecure3ds.spec.ConfigParameters;
import com.asiapay.sdk.integration.xecure3ds.spec.UiCustomization;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , View.OnTouchListener {
    Button btnDirectCall;
    private ProgressDialog progressDialog;
    PaySDK paySDK;
    ThreeDSParams threeDSParams;
    PayData payData;

    TextInputLayout cardNumberLayout, cvvLayout, nameLayout;
    AppCompatEditText txtCvv;
    TextInputEditText txtCardNumber, name;
    TextView tvDateError, tvCVVError;

    TextView tvDirectCall,tvWebviewCall,tvAlipay,tvWechat,tv;
    ExpandableRelativeLayout expandableDC;


    Spinner spnrMonth, spnrYear;
    View viewMonth, viewYear;

    LinearLayout llCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDirectCall = findViewById(R.id.btn_pay_3ds);
        btnDirectCall.setOnClickListener(this);

        spnrMonth=findViewById(R.id.spnr_month);
        spnrYear=findViewById(R.id.spnr_year);

        spnrMonth.setOnTouchListener(this);
        spnrYear.setOnTouchListener(this);



        initControls();

        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize PaySdk
        paySDK = new PaySDK(getApplicationContext());


       //Add 3DS params
        add3DSParams();

    }

    private void initControls() {

        llCard=findViewById(R.id.card_layout);
        cardNumberLayout = llCard.findViewById(R.id.card_number_layout);
        nameLayout = llCard.findViewById(R.id.text_input_layout);
        name = llCard.findViewById(R.id.edit_text);

        viewMonth = llCard.findViewById(R.id.view_month);
        viewYear = llCard.findViewById(R.id.view_year);

        cvvLayout=findViewById(R.id.text_cvv_layout);
        tvDateError=findViewById(R.id.tv_expdate_error);
        tvCVVError=findViewById(R.id.tv_cvv_error);

        txtCardNumber=findViewById(R.id.card_number);
        txtCvv=findViewById(R.id.txt_cvv);

        expandableDC=findViewById(R.id.expandable_dc);
        tvDirectCall=findViewById(R.id.tv_directcall);
        tvDirectCall.setOnClickListener(this);

        txtCardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setCardTypeIconCC(PaySdkUtils.getCardType(charSequence.toString(), "cc",
                        false));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void setCardTypeIconCC(PaySdkConstants.CARD_TYPE cType) {
        switch (cType) {
            case visa:
                txtCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.visa_svg_p2, 0);
                break;
            case master:
                txtCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mastercard_svg_p2, 0);
                break;
            case maestro:
                txtCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.maestro_svg_p2, 0);
                break;
            default:
                txtCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                break;
        }
    }
    private void init() {

        ArrayAdapter monthsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_date, getResources().getStringArray(R.array.months_payment));
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter yearAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item_date, getResources().getStringArray(R.array.year_payment));
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrMonth.setAdapter(monthsAdapter);
        spnrYear.setAdapter(yearAdapter);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_directcall:
                expandableDC.toggle();
                break;
            case R.id.btn_pay_3ds:
                //directCall();
                //cardValidation();
                webviewCall();
                break;

        }

    }

    void webviewCall(){

        payData = new PayData();
        payData.setChannel(EnvBase.PayChannel.WEBVIEW);
        payData.setEnvType(EnvBase.EnvType.SANDBOX);
        payData.setAmount("10");
        payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
        payData.setCurrCode(EnvBase.Currency.HKD);
        payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
        payData.setOrderRef("1234465465");
        payData.setPayMethod("VISA");
        payData.setLang(EnvBase.Language.ENGLISH);
        payData.setMerchantId("88146271");
        payData.setRemark(" ");


        // Optional Parameter (For Value-Added Service)
        Map<String, String> extraDataHosted = new HashMap<String, String>();

        payData.setExtraData(extraDataHosted);

        paySDK.setRequestData(payData);

        paySDK.process();

        paySDK.responseHandler(new PaymentResponse() {


            @Override
            public void getResponse(PayResult payResult) {
                try {
      Toast.makeText(MainActivity.this, "Result: "+payResult.getErrMsg(), Toast.LENGTH_SHORT).show();
} catch (Exception e) {
   
}

          
            }

            @Override
            public void onError(Data data) {
try {
 Toast.makeText(MainActivity.this,"Result: "+data.getError(),Toast.LENGTH_SHORT).show();
} catch (Exception e) {
   
}
               
            }
        });


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

    private void add3DSParams(){
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
    public boolean onTouch(View view1, MotionEvent motionEvent) {
        switch (view1.getId()) {
            case R.id.spnr_month:
            case R.id.spnr_year:
                View view = getCurrentFocus();

                break;
        }
        return false;
    }

    void directCall(){

        cardValidation();

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
                payData.setMerchantId("88146271");
                //payData.setMerchantId("88146231");
                payData.setRemark(currentDateAndTime);

                CardDetails cardDetails = new CardDetails();
                cardDetails.setCardNo("4012000000020090");
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
                        try {
cancelProgressDialog();

                        Toast.makeText(MainActivity.this,"Success "+ payResult.getErrMsg(),Toast.LENGTH_SHORT).show();
} catch (Exception e) {
   
}
                        
                    }

                    @Override
                    public void onError(Data data) {

try {
cancelProgressDialog();
                        Toast.makeText(getApplicationContext(), "Error "+data.getMessage(),Toast.LENGTH_SHORT ).show();        
} catch (Exception e) {
   
}
                        
                    }
                });
                paySDK.process();

            }
        }, 100);
    }

    void cardValidation(){

        boolean postForm = true;
        String cardNumText = txtCardNumber.getText().toString();
        if (TextUtils.isEmpty(cardNumText) || cardNumText.matches("^[0]+$")) {
            cardNumberLayout.setErrorEnabled(true);
            cardNumberLayout.setError("Please enter a valid Card Number");
            postForm = false;
            txtCardNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else if (!PaySdkUtils.validateLuhn(cardNumText) || TextUtils.isEmpty(cardNumText)) {
            cardNumberLayout.setErrorEnabled(true);
            cardNumberLayout.setError("Please enter a valid Card Number");
            postForm = false;
        } else {
            cardNumberLayout.setErrorEnabled(false);
            cardNumberLayout.setError(null);
        }
        if (TextUtils.isEmpty(name.getText().toString().trim())) {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError("Please enter Name of the Card Holder");
            postForm = false;
        } else if (!PaySdkUtils.isValidUsername(name.getText().toString())) {
            nameLayout.setErrorEnabled(true);
            nameLayout.setError("Please enter valid Name of the Card Holder");
            postForm = false;
        } else {
            nameLayout.setErrorEnabled(false);
            nameLayout.setError(null);
        }
        if ((spnrMonth.getSelectedItemPosition() == 0
                || spnrYear.getSelectedItemPosition() == 0)
                || (spnrYear.getSelectedItemPosition() == 1
                && spnrMonth.getSelectedItemPosition() <= (Calendar
                .getInstance().get(Calendar.MONTH)))) {
            tvDateError.setVisibility(View.VISIBLE);
            viewMonth.setBackgroundColor(getResources().getColor(R.color.nav_circular_notify_pending));
            viewYear.setBackgroundColor(getResources().getColor(R.color.nav_circular_notify_pending));
            postForm = false;
        } else {
            tvDateError.setVisibility(View.GONE);
            viewMonth.setBackgroundColor(getResources().getColor(R.color.spinner_underline));
            viewYear.setBackgroundColor(getResources().getColor(R.color.spinner_underline));
        }
        if ((TextUtils.isEmpty(txtCvv.getText())
                || txtCvv.getText().toString().trim().length() < 3)) {
            cvvLayout.setErrorEnabled(true);
            cvvLayout.setError("Invalid CVV");
            postForm = false;
        } else {
            cvvLayout.setErrorEnabled(false);
            cvvLayout.setError(null);
        }
    }
}
