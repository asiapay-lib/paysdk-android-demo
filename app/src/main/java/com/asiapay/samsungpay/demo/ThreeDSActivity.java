package com.asiapay.samsungpay.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.textfield.TextInputLayout;

public class ThreeDSActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnThreeDSPay;

    TextInputLayout txtcustomerEmail;
    TextInputLayout txtmobilePhoneCountryCode;
    TextInputLayout txtmobilePhoneNumber;
    TextInputLayout txthomePhoneCountryCode;
    TextInputLayout txthomePhoneNumber;
    TextInputLayout txtworkPhoneCountryCode;
    TextInputLayout txtworkPhoneNumber;
    TextInputLayout txtbillingCountryCode;
    TextInputLayout txtbillingState;
    TextInputLayout txtbillingCity;
    TextInputLayout txtbillingLine1;
    TextInputLayout txtbillingLine2;
    TextInputLayout txtbillingLine3;
    TextInputLayout txtbillingPostalCode;
    TextInputLayout txtdeliveryTime;
    TextInputLayout txtdeliveryEmail;
    TextInputLayout txtshippingDetails;
    TextInputLayout txtshippingCountryCode;
    TextInputLayout txtshippingState;
    TextInputLayout txtshippingCity;
    TextInputLayout txtshippingLine1;
    TextInputLayout txtshippingLine2;
    TextInputLayout txtshippingLine3;
    TextInputLayout txtgiftCardAmount;
    TextInputLayout txtgiftCardCount;
    TextInputLayout txtpreOrderReason;
    TextInputLayout txtpreOrderReadyDate;
    TextInputLayout txtacctCreateDate;
    TextInputLayout txtacctAgeInd;
    TextInputLayout txtacctLastChangeDate;
    TextInputLayout txtacctLastChangeInd;
    TextInputLayout txtacctPwChangeDate;
    TextInputLayout txtacctPwChangeInd;
    TextInputLayout txtacctPurchaseCount;
    TextInputLayout txtacctCardProvisionAttempt;
    TextInputLayout txtacctNumTransDay;
    TextInputLayout txtacctNumTransYear;
    TextInputLayout txtacctPaymentAcctDate;
    TextInputLayout txtacctPaymentAcctInd;
    TextInputLayout txtacctShippingAddrLastChangeDate;
    TextInputLayout txtacctShippingAddrLastChangeInd;
    TextInputLayout txtacctIsShippingAcctNameSame;
    TextInputLayout txtacctIsSuspiciousAcct;
    TextInputLayout txtacctAuthMethod;
    TextInputLayout txtacctAuthTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_ds);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {

        btnThreeDSPay = findViewById(R.id.btn_threedst);
        btnThreeDSPay.setOnClickListener(this);

        txtcustomerEmail = findViewById(R.id.cus_email);
        txtmobilePhoneCountryCode = findViewById(R.id.country_code);
        txtmobilePhoneNumber = findViewById(R.id.home_phone);

        txthomePhoneCountryCode = findViewById(R.id.country_code_homephone);
        txthomePhoneNumber = findViewById(R.id.home_phone);
        txtworkPhoneCountryCode = findViewById(R.id.country_code_work);
        txtworkPhoneNumber = findViewById(R.id.country_phone_work);

        txtbillingCountryCode = findViewById(R.id.billing_countrycode);
        txtbillingState = findViewById(R.id.billing_state);
        txtbillingCity = findViewById(R.id.billing_city);
        txtbillingLine1 = findViewById(R.id.billing_line1);
        txtbillingLine2 = findViewById(R.id.billing_line2);
        txtbillingLine3 = findViewById(R.id.billing_line3);
        txtbillingPostalCode = findViewById(R.id.billing_postalcode);
        txtdeliveryTime = findViewById(R.id.dlvry_time);
        txtdeliveryEmail = findViewById(R.id.delivery_email);
        txtshippingDetails = findViewById(R.id.shipping_detls);
        txtshippingCountryCode = findViewById(R.id.shpng_countrycode);
        txtshippingState = findViewById(R.id.shpng_state);
        txtshippingCity = findViewById(R.id.shpng_city);
        txtshippingLine1 = findViewById(R.id.shpng_line1);
        txtshippingLine2 = findViewById(R.id.shpng_line2);
        txtshippingLine3 = findViewById(R.id.shpng_line3);

        txtgiftCardAmount = findViewById(R.id.giftcard_amount);
        txtgiftCardCount = findViewById(R.id.gift_card_count);
        txtpreOrderReason = findViewById(R.id.preorder_reason);
        txtpreOrderReadyDate = findViewById(R.id.preorder_readydate);
        txtacctCreateDate = findViewById(R.id.acc_create_date);
        txtacctAgeInd = findViewById(R.id.acc_age_ind);
        txtacctLastChangeDate = findViewById(R.id.acc_date_change);
        txtacctLastChangeInd = findViewById(R.id.acc_change_ind);
        txtacctPwChangeDate = findViewById(R.id.acc_pw_change_date);
        txtacctPwChangeInd = findViewById(R.id.acc_pw_change_ind);
        txtacctPurchaseCount = findViewById(R.id.acc_purch_count);
        txtacctCardProvisionAttempt = findViewById(R.id.acc_card_prov);
        txtacctNumTransDay = findViewById(R.id.acc_num_transd);
        txtacctNumTransYear = findViewById(R.id.acc_num_transy);
        txtacctPaymentAcctDate = findViewById(R.id.acc_payment_date);
        txtacctPaymentAcctInd = findViewById(R.id.acc_payment_ind);
        txtacctShippingAddrLastChangeDate = findViewById(R.id.last_changedshpn_add);
        txtacctShippingAddrLastChangeInd = findViewById(R.id.shipping_last_addr_change_ind);
        txtacctIsShippingAcctNameSame = findViewById(R.id.is_acc_shipng_name_same);
        txtacctIsSuspiciousAcct = findViewById(R.id.is_suspiciousacc);
        txtacctAuthMethod = findViewById(R.id.auth_method);
        txtacctAuthTimestamp = findViewById(R.id.auth_timestamp);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_threedst:

                if (validateThreeDsPayData()) {
                    Intent intent = getIntent();

                    ThreeDSData threeDSData = new ThreeDSData(txtcustomerEmail.getEditText().getText().toString(), txtmobilePhoneCountryCode.getEditText().getText().toString(), txtmobilePhoneNumber.getEditText().getText().toString(), txthomePhoneCountryCode.getEditText().getText().toString(), txthomePhoneNumber.getEditText().getText().toString(), txtworkPhoneCountryCode.getEditText().getText().toString(),
                            txtworkPhoneNumber.getEditText().getText().toString(),
                            txtbillingCountryCode.getEditText().getText().toString(), txtbillingState.getEditText().getText().toString(), txtbillingCity.getEditText().getText().toString(), txtbillingLine1.getEditText().getText().toString(), txtbillingLine2.getEditText().getText().toString(), txtbillingLine3.getEditText().getText().toString(), txtbillingPostalCode.getEditText().getText().toString(), txtdeliveryTime.getEditText().getText().toString(), txtdeliveryEmail.getEditText().getText().toString(), txtshippingDetails.getEditText().getText().toString(), txtshippingCountryCode.getEditText().getText().toString(), txtshippingState.getEditText().getText().toString(), txtshippingCity.getEditText().getText().toString(), txtshippingLine1.getEditText().getText().toString(), txtshippingLine2.getEditText().getText().toString(), txtshippingLine3.getEditText().getText().toString(), txtgiftCardAmount.getEditText().getText().toString(), txtgiftCardCount.getEditText().getText().toString(), txtpreOrderReason.getEditText().getText().toString(), txtpreOrderReadyDate.getEditText().getText().toString(), txtacctCreateDate.getEditText().getText().toString(), txtacctAgeInd.getEditText().getText().toString(), txtacctLastChangeDate.getEditText().getText().toString(), txtacctLastChangeInd.getEditText().getText().toString(), txtacctPwChangeDate.getEditText().getText().toString(), txtacctPwChangeInd.getEditText().getText().toString(), txtacctPurchaseCount.getEditText().getText().toString(), txtacctCardProvisionAttempt.getEditText().getText().toString(), txtacctNumTransDay.getEditText().getText().toString(), txtacctNumTransYear.getEditText().getText().toString(), txtacctPaymentAcctDate.getEditText().getText().toString(), txtacctPaymentAcctInd.getEditText().getText().toString(), txtacctShippingAddrLastChangeDate.getEditText().getText().toString(), txtacctShippingAddrLastChangeInd.getEditText().getText().toString(), txtacctIsShippingAcctNameSame.getEditText().getText().toString(), txtacctIsSuspiciousAcct.getEditText().getText().toString(), txtacctAuthMethod.getEditText().getText().toString(), txtacctAuthTimestamp.getEditText().getText().toString());


                    intent.putExtra("Data", threeDSData);
                    setResult(RESULT_OK, intent);

                    finish();
                }
                break;
        }
    }

    private boolean validateThreeDsPayData() {

        return PaySdkUtils.hasText(txtcustomerEmail) &&
                PaySdkUtils.hasText(txtmobilePhoneCountryCode) &&
                PaySdkUtils.hasText(txtmobilePhoneNumber) &&
                PaySdkUtils.hasText(txthomePhoneCountryCode) &&
                PaySdkUtils.hasText(txthomePhoneNumber) &&
                PaySdkUtils.hasText(txtworkPhoneCountryCode) &&
                PaySdkUtils.hasText(txtworkPhoneNumber) &&
                PaySdkUtils.hasText(txtbillingCountryCode) &&
                PaySdkUtils.hasText(txtbillingState) &&
                PaySdkUtils.hasText(txtbillingCity) &&
                PaySdkUtils.hasText(txtbillingLine1) &&
                PaySdkUtils.hasText(txtbillingLine2) &&
                PaySdkUtils.hasText(txtbillingLine3) &&
                PaySdkUtils.hasText(txtbillingPostalCode) &&
                PaySdkUtils.hasText(txtdeliveryTime) &&
                PaySdkUtils.hasText(txtdeliveryEmail) &&
                PaySdkUtils.hasText(txtshippingDetails) &&
                PaySdkUtils.hasText(txtshippingCountryCode) &&
                PaySdkUtils.hasText(txtshippingState) &&
                PaySdkUtils.hasText(txtshippingCity) &&
                PaySdkUtils.hasText(txtshippingLine1) &&
                PaySdkUtils.hasText(txtshippingLine2) &&
                PaySdkUtils.hasText(txtshippingLine3) &&
                PaySdkUtils.hasText(txtgiftCardAmount) &&
                PaySdkUtils.hasText(txtgiftCardCount) &&
                PaySdkUtils.hasText(txtpreOrderReason) &&
                PaySdkUtils.hasText(txtpreOrderReadyDate) &&
                PaySdkUtils.hasText(txtacctCreateDate) &&
                PaySdkUtils.hasText(txtacctAgeInd) &&
                PaySdkUtils.hasText(txtacctLastChangeDate) &&
                PaySdkUtils.hasText(txtacctLastChangeInd) &&
                PaySdkUtils.hasText(txtacctPwChangeDate) &&
                PaySdkUtils.hasText(txtacctPwChangeInd) &&
                PaySdkUtils.hasText(txtacctPurchaseCount) &&
                PaySdkUtils.hasText(txtacctCardProvisionAttempt) &&
                PaySdkUtils.hasText(txtacctNumTransDay) &&
                PaySdkUtils.hasText(txtacctNumTransYear) &&
                PaySdkUtils.hasText(txtacctPaymentAcctDate) &&
                PaySdkUtils.hasText(txtacctPaymentAcctInd) &&
                PaySdkUtils.hasText(txtacctShippingAddrLastChangeDate) &&
                PaySdkUtils.hasText(txtacctShippingAddrLastChangeInd) &&
                PaySdkUtils.hasText(txtacctIsShippingAcctNameSame) &&
                PaySdkUtils.hasText(txtacctIsSuspiciousAcct) &&
                PaySdkUtils.hasText(txtacctAuthMethod) &&
                PaySdkUtils.hasText(txtacctAuthTimestamp);
    }
}
