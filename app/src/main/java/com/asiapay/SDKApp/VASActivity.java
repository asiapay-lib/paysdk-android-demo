package com.asiapay.SDKApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import static com.asiapay.SDKApp.PaySdkConstants.INSTALLMENT_PAY;
import static com.asiapay.SDKApp.PaySdkConstants.NEW_MEMBER;
import static com.asiapay.SDKApp.PaySdkConstants.OLD_MEMBER;
import static com.asiapay.SDKApp.PaySdkConstants.PROMO_CODE;
import static com.asiapay.SDKApp.PaySdkConstants.SCHEDULE_PAY;

public class VASActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMemberPay;
    Button btnInstallmentPay;
    Button btnSchedulePay;
    Button btnPromoPay;



    LinearLayout llMemberPay;
    LinearLayout llPromoCode;
    LinearLayout llInstallPay;
    LinearLayout llSchedulePay;

    TextInputLayout textPromocode;
    TextInputLayout textPromoRuleCode;
    TextInputLayout textMemberID;
    TextInputLayout textPromoOriginalAmnt;
    TextInputLayout textInstallPeriod;
    TextInputLayout textInstallOnly;
    TextInputLayout textMemberToken;
    TextInputLayout textScheduleType;
    TextInputLayout textScheduleStatus;
    TextInputLayout textScheduleNumber;
    TextInputLayout textScheduleStartDay;
    TextInputLayout textScheduleStartMonth;
    TextInputLayout textScheduleStartYear;
    TextInputLayout textScheduleEndDay;
    TextInputLayout textScheduleEndMonth;
    TextInputLayout textScheduleEndYear;
    TextInputLayout textScheduleUserName;
    TextInputLayout textScheduleUserEmail;
    TextInputLayout textScheduleAppId;
    TextInputLayout textScheduleAppRef;


    String paymentMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        paymentMode = getIntent().getStringExtra("mode");

        showLayout(paymentMode);

    }

    private void showLayout(String paymentMode) {

        switch (paymentMode) {
            case NEW_MEMBER:
                llMemberPay.setVisibility(View.VISIBLE);
                break;
            case OLD_MEMBER:
                llMemberPay.setVisibility(View.VISIBLE);
                textMemberToken.setVisibility(View.VISIBLE);
                btnMemberPay.setText("Old Member Pay");
                break;
            case SCHEDULE_PAY:
                llSchedulePay.setVisibility(View.VISIBLE);
                break;
            case PROMO_CODE:
                llPromoCode.setVisibility(View.VISIBLE);
                break;
            case INSTALLMENT_PAY:
                llInstallPay.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void init() {

        btnMemberPay = findViewById(R.id.btn_memberpay);
        btnSchedulePay = findViewById(R.id.btn_schpay);
        btnInstallmentPay = findViewById(R.id.btn_installpay);
        btnPromoPay = findViewById(R.id.btn_promopay);


        btnMemberPay.setOnClickListener(this);
        btnSchedulePay.setOnClickListener(this);
        btnInstallmentPay.setOnClickListener(this);
        btnPromoPay.setOnClickListener(this);

        llMemberPay = findViewById(R.id.ll_memberpay);
        llPromoCode = findViewById(R.id.ll_promocode);
        llInstallPay = findViewById(R.id.ll_installpay);
        llSchedulePay = findViewById(R.id.ll_schedulepay);

        textPromocode = findViewById(R.id.promotion_code);
        textPromoRuleCode = findViewById(R.id.promotion_rule_code);
        textMemberID = findViewById(R.id.memberid);
        textPromoOriginalAmnt = findViewById(R.id.promotion_org_amount);
        textInstallPeriod = findViewById(R.id.install_period);
        textInstallOnly = findViewById(R.id.install_only);
        textMemberToken = findViewById(R.id.memberpay_token);
        textScheduleType = findViewById(R.id.sch_type);
        textScheduleStatus = findViewById(R.id.sch_status);
        textScheduleNumber = findViewById(R.id.sch_number);
        textScheduleStartDay = findViewById(R.id.sch_sday);
        textScheduleStartMonth = findViewById(R.id.sch_smonth);
        textScheduleStartYear = findViewById(R.id.sch_syear);
        textScheduleEndDay = findViewById(R.id.sch_eday);
        textScheduleEndMonth = findViewById(R.id.sch_emonth);
        textScheduleEndYear = findViewById(R.id.sch_eyear);
        textScheduleUserName = findViewById(R.id.sch_name);
        textScheduleUserEmail = findViewById(R.id.sch_email);
        textScheduleAppId = findViewById(R.id.sch_appid);
        textScheduleAppRef = findViewById(R.id.sch_appref);

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

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_memberpay:

                if (validateMemberPayData()) {
                    Intent intent = getIntent();

                    if (paymentMode.contains("Old")) {
                        intent.putExtra("memberPay_memberId", textMemberID.getEditText().getText().toString());
                        intent.putExtra("memberPay_token", textMemberToken.getEditText().getText().toString());
                        setResult(RESULT_OK, intent);
                    } else {
                        intent.putExtra("memberPay_memberId", textMemberID.getEditText().getText().toString());
                        setResult(RESULT_OK, intent);
                    }

                    finish();
                }
                break;
            case R.id.btn_schpay:

                if (validateSchedulePayData()) {
                    Intent intent = getIntent();

                    intent.putExtra("schType", textScheduleType.getEditText().getText().toString());
                    intent.putExtra("schStatus", textScheduleStatus.getEditText().getText().toString());
                    intent.putExtra("nSch", textScheduleNumber.getEditText().getText().toString());
                    intent.putExtra("sMonth", textScheduleStartMonth.getEditText().getText().toString());
                    intent.putExtra("sDay", textScheduleStartDay.getEditText().getText().toString());
                    intent.putExtra("sYear", textScheduleStartYear.getEditText().getText().toString());
                    intent.putExtra("eMonth", textScheduleEndMonth.getEditText().getText().toString());
                    intent.putExtra("eDay", textScheduleEndDay.getEditText().getText().toString());
                    intent.putExtra("name", textScheduleUserName.getEditText().getText().toString());
                    intent.putExtra("email", textScheduleUserEmail.getEditText().getText().toString());
                    intent.putExtra("appId", textScheduleAppId.getEditText().getText().toString());
                    intent.putExtra("appRef", textScheduleAppRef.getEditText().getText().toString());

                    setResult(RESULT_OK, intent);

                    finish();

                }
                break;
            case R.id.btn_promopay:
                if (validatePromoPayData()) {
                    Intent intent = getIntent();

                    intent.putExtra("promotionCode", textPromocode.getEditText().getText().toString());
                    if (textPromoRuleCode.getEditText().getText().toString().trim().length() > 0) {
                        intent.putExtra("promotionRuleCode", textPromoRuleCode.getEditText().getText().toString());
                    } else {
                        intent.putExtra("promotionRuleCode", "");
                    }
                    if (textPromoOriginalAmnt.getEditText().getText().toString().trim().length() > 0) {
                        intent.putExtra("promotionOriginalAmt", textPromoOriginalAmnt.getEditText().getText().toString());
                    } else {
                        intent.putExtra("promotionOriginalAmt", "");
                    }

                    setResult(RESULT_OK, intent);

                    finish();

                }
                break;
            case R.id.btn_installpay:

                if (validateInstallPayData()) {
                    Intent intent = getIntent();

                    intent.putExtra("installOnly", textInstallOnly.getEditText().getText().toString());
                    intent.putExtra("installment_period", textInstallPeriod.getEditText().getText().toString());
                    setResult(RESULT_OK, intent);

                    finish();
                }
                break;

        }
    }

    private boolean validateMemberPayData() {

        boolean isMid, isToken;

        isMid = PaySdkUtils.hasText(textMemberID);
        isToken = PaySdkUtils.hasText(textMemberToken);

        return isMid && isToken;
    }

    private boolean validateInstallPayData() {

        boolean isinstOnly, isPeriod;

        isinstOnly = PaySdkUtils.hasText(textInstallOnly);
        isPeriod = PaySdkUtils.hasText(textInstallPeriod);

        return isinstOnly && isPeriod;
    }

    private boolean validatePromoPayData() {

        boolean isPromoCode;

        isPromoCode = PaySdkUtils.hasText(textPromocode);

        return isPromoCode;
    }

    private boolean validateSchedulePayData() {


        boolean isType, isStatus, isNumberofSch, isStartMonth, isStartDay, isStartYear, isEndMonth, isEndDay, isName, isEmailId, isAppId, isAppRef;

        isType = PaySdkUtils.hasText(textScheduleType);
        isStatus = PaySdkUtils.hasText(textScheduleStatus);
        isNumberofSch = PaySdkUtils.hasText(textScheduleNumber);
        isStartMonth = PaySdkUtils.hasText(textScheduleStartMonth);
        isStartDay = PaySdkUtils.hasText(textScheduleStartDay);
        isStartYear = PaySdkUtils.hasText(textScheduleStartYear);
        isEndMonth = PaySdkUtils.hasText(textScheduleEndMonth);
        isEndDay = PaySdkUtils.hasText(textScheduleEndDay);
        isName = PaySdkUtils.hasText(textScheduleUserName);
        isEmailId = PaySdkUtils.hasText(textScheduleUserEmail);
        isAppId = PaySdkUtils.hasText(textScheduleAppId);
        isAppRef = PaySdkUtils.hasText(textScheduleAppRef);


        return isType && isStatus && isNumberofSch && isStartMonth && isStartDay && isStartYear && isEndMonth && isEndDay && isName && isEmailId && isAppId && isAppRef;
    }
}
