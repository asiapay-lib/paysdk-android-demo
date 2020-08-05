package com.asiapay.SDKApp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.asiapay.sdk.PaySDK;
import com.asiapay.sdk.integration.CardDetails;
import com.asiapay.sdk.integration.Data;
import com.asiapay.sdk.integration.EnvBase;
import com.asiapay.sdk.integration.PayData;
import com.asiapay.sdk.integration.PayMethodResponse;
import com.asiapay.sdk.integration.PayMethodResult;
import com.asiapay.sdk.integration.PayResult;
import com.asiapay.sdk.integration.PaymentResponse;
import com.asiapay.sdk.integration.QueryResponse;
import com.asiapay.sdk.integration.TransactionStatus;
import com.asiapay.sdk.integration.googlepay.GooglePay;
import com.asiapay.sdk.integration.googlepay.PaymentsUtil;
import com.asiapay.sdk.integration.xecure3ds.ThreeDSParams;
import com.asiapay.sdk.integration.xecure3ds.spec.ConfigParameters;
import com.asiapay.sdk.integration.xecure3ds.spec.Factory;
import com.asiapay.sdk.integration.xecure3ds.spec.UiCustomization;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.asiapay.SDKApp.PaySdkConstants.E_VOUCHER;
import static com.asiapay.SDKApp.PaySdkConstants.INSTALLMENT_PAY;
import static com.asiapay.SDKApp.PaySdkConstants.NEW_MEMBER;
import static com.asiapay.SDKApp.PaySdkConstants.OLD_MEMBER;
import static com.asiapay.SDKApp.PaySdkConstants.PROMO_CODE;
import static com.asiapay.SDKApp.PaySdkConstants.QUERY_ACTION;
import static com.asiapay.SDKApp.PaySdkConstants.SCHEDULE_PAY;
import static com.asiapay.SDKApp.PaySdkConstants.THREE_DS;
import static com.asiapay.sdk.PaySDK.LOAD_PAYMENT_DATA_REQUEST_CODE;

public class PaymentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private static final int PAY_CODE = 0;
    private static final String TAG = "PaymentACt";
    public final static int OCTOPUS_APP_REQUEST_CODE = 10000; // This CODE is subject to change by SI

    // A client for interacting with the Google Pay API
    private PaymentsClient mPaymentsClient;

    String strPayMethod;
    Button btnDirectCall;
    Button btnWebviewCall;
    Button btnNewMember;
    Button btnOldMember;
    Button btnPromoCode;
    Button btnEVoucher;
    Button btnSchedulePay;
    Button btnInstallmentPay;
    Button btnThreeDS;
    Button btnQueryStatus;
    Button btnPayMethod;
    Button btnGooglePay;
    Button btnAliPay;
    Button btnAliPayHK;
    Button btnWeChat;
    Button btnOctopus;
    Button btnMemberDirectPay;


    PayData payData;

    TextInputLayout textOrderRef;
    TextInputLayout textAmount;
    TextInputLayout textMerchantId;
    TextInputLayout textCardNo;
    TextInputLayout textExpiryMonth;
    TextInputLayout textExpiryYear;
    TextInputLayout textCardHolderName;
    TextInputLayout textSecurityCode;


    Spinner spCurrency;
    Spinner spnrPayGate;
    Spinner spnrEnvType;

    EnvBase.Currency selectedCurrency;
    // EnvBase.PayChannel selectedPayChannel;
    EnvBase.PayGate selectedPayGate;
    EnvBase.EnvType selectedEnvType;

    private PaySDK paySDK;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.payment_layout);

        //Initialize PaySDK
        paySDK = new PaySDK(getApplicationContext());

        //Initialize PaymentCLient for GooglePay with Environment Specification
        mPaymentsClient = PaymentsUtil.createPaymentsClient(this, EnvBase.EnvType.PRODUCTION);

        //Implement isGooglePayAvailable to know wheter your app is ready for GooglePay transaction or not

        PaymentsUtil.isGooglePayAvailable(this, mPaymentsClient, new PaymentsUtil.ICheckGooglePay() {
            @Override
            public void success() {

                //Visible GooglePay Button here
            }

            @Override
            public void error() {

            }
        });


        btnDirectCall = findViewById(R.id.btn_directcall);
        btnWebviewCall = findViewById(R.id.btn_webview);
        btnNewMember = findViewById(R.id.btn_new_member);
        btnOldMember = findViewById(R.id.btn_old_member);
        btnPromoCode = findViewById(R.id.btn_promocode);
        btnEVoucher = findViewById(R.id.btn_evoucher);
        btnSchedulePay = findViewById(R.id.btn_schedule_pay);
        btnInstallmentPay = findViewById(R.id.btn_installment);
        btnThreeDS = findViewById(R.id.btn_threeds);
        btnQueryStatus = findViewById(R.id.btn_querystatus);
        btnPayMethod = findViewById(R.id.btn_paymethods);
        btnGooglePay = findViewById(R.id.btn_google_pay);
        btnAliPay = findViewById(R.id.btn_alipay);
        btnAliPayHK = findViewById(R.id.btn_alipayhk);
        btnWeChat = findViewById(R.id.btn_wechat);
        btnOctopus = findViewById(R.id.btn_octopus);
        btnMemberDirectPay = findViewById(R.id.btn_memberpay_direc);

        textOrderRef = findViewById(R.id.et_orderref);
        textAmount = findViewById(R.id.et_amount);
        textMerchantId = findViewById(R.id.et_mid);
        textCardNo = findViewById(R.id.et_cardno);
        textExpiryMonth = findViewById(R.id.et_month);
        textExpiryYear = findViewById(R.id.et_year);
        textCardHolderName = findViewById(R.id.et_cardholder_name);
        textSecurityCode = findViewById(R.id.et_sec_code);

        spCurrency = findViewById(R.id.spCurrency);
        spnrPayGate = findViewById(R.id.spnr_paygate);
        spnrEnvType = findViewById(R.id.spnr_envtype);

        textOrderRef.getEditText().setText(getOrderRef());
        setSpinnerValue();

        btnDirectCall.setOnClickListener(this);
        btnWebviewCall.setOnClickListener(this);
        btnNewMember.setOnClickListener(this);
        btnPromoCode.setOnClickListener(this);
        btnEVoucher.setOnClickListener(this);
        btnSchedulePay.setOnClickListener(this);
        btnInstallmentPay.setOnClickListener(this);
        btnOldMember.setOnClickListener(this);
        btnThreeDS.setOnClickListener(this);
        btnQueryStatus.setOnClickListener(this);
        btnPayMethod.setOnClickListener(this);
        btnGooglePay.setOnClickListener(this);
        btnAliPay.setOnClickListener(this);
        btnAliPayHK.setOnClickListener(this);
        btnWeChat.setOnClickListener(this);
        btnOctopus.setOnClickListener(this);
        btnMemberDirectPay.setOnClickListener(this);

        spCurrency.setOnItemSelectedListener(this);
        spnrPayGate.setOnItemSelectedListener(this);
        spnrEnvType.setOnItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setSpinnerValue() {
        try {
            SpinnerData curr;
            List<SpinnerData> currencyList = new ArrayList<>();
            curr = new SpinnerData("HKD", EnvBase.Currency.HKD);
            currencyList.add(curr);
            curr = new SpinnerData("RMB", EnvBase.Currency.RMB);
            currencyList.add(curr);
            curr = new SpinnerData("USD", EnvBase.Currency.USD);
            currencyList.add(curr);
            curr = new SpinnerData("SGD", EnvBase.Currency.SGD);
            currencyList.add(curr);
            curr = new SpinnerData("YEN", EnvBase.Currency.YEN);
            currencyList.add(curr);
            curr = new SpinnerData("TWD", EnvBase.Currency.TWD);
            currencyList.add(curr);
            curr = new SpinnerData("AUD", EnvBase.Currency.AUD);
            currencyList.add(curr);
            curr = new SpinnerData("EUR", EnvBase.Currency.EUR);
            currencyList.add(curr);
            curr = new SpinnerData("GBP", EnvBase.Currency.GBP);
            currencyList.add(curr);
            curr = new SpinnerData("CAD", EnvBase.Currency.CAD);
            currencyList.add(curr);
            curr = new SpinnerData("MOP", EnvBase.Currency.MOP);
            currencyList.add(curr);
            curr = new SpinnerData("PHP", EnvBase.Currency.PHP);
            currencyList.add(curr);
            curr = new SpinnerData("THB", EnvBase.Currency.THB);
            currencyList.add(curr);
            curr = new SpinnerData("MYR", EnvBase.Currency.MYR);
            currencyList.add(curr);
            curr = new SpinnerData("IDR", EnvBase.Currency.IDR);
            currencyList.add(curr);
            curr = new SpinnerData("KRW", EnvBase.Currency.KRW);
            currencyList.add(curr);
            curr = new SpinnerData("BND", EnvBase.Currency.BND);
            currencyList.add(curr);
            curr = new SpinnerData("NZD", EnvBase.Currency.NZD);
            currencyList.add(curr);
            curr = new SpinnerData("SAR", EnvBase.Currency.SAR);
            currencyList.add(curr);
            curr = new SpinnerData("AED", EnvBase.Currency.AED);
            currencyList.add(curr);
            curr = new SpinnerData("BRL", EnvBase.Currency.BRL);
            currencyList.add(curr);
            curr = new SpinnerData("INR", EnvBase.Currency.INR);
            currencyList.add(curr);
            curr = new SpinnerData("TRY", EnvBase.Currency.TRY);
            currencyList.add(curr);
            curr = new SpinnerData("ZAR", EnvBase.Currency.ZAR);
            currencyList.add(curr);
            curr = new SpinnerData("VND", EnvBase.Currency.VND);
            currencyList.add(curr);
            curr = new SpinnerData("DKK", EnvBase.Currency.DKK);
            currencyList.add(curr);
            curr = new SpinnerData("ILS", EnvBase.Currency.ILS);
            currencyList.add(curr);
            curr = new SpinnerData("NOK", EnvBase.Currency.NOK);
            currencyList.add(curr);
            curr = new SpinnerData("RUB", EnvBase.Currency.RUB);
            currencyList.add(curr);
            curr = new SpinnerData("SEK", EnvBase.Currency.SEK);
            currencyList.add(curr);
            curr = new SpinnerData("CHF", EnvBase.Currency.CHF);
            currencyList.add(curr);
            curr = new SpinnerData("ARS", EnvBase.Currency.ARS);
            currencyList.add(curr);
            curr = new SpinnerData("CLP", EnvBase.Currency.CLP);
            currencyList.add(curr);
            curr = new SpinnerData("COP", EnvBase.Currency.COP);
            currencyList.add(curr);
            curr = new SpinnerData("CZK", EnvBase.Currency.CZK);
            currencyList.add(curr);
            curr = new SpinnerData("EGP", EnvBase.Currency.EGP);
            currencyList.add(curr);
            curr = new SpinnerData("HUF", EnvBase.Currency.HUF);
            currencyList.add(curr);
            curr = new SpinnerData("KZT", EnvBase.Currency.KZT);
            currencyList.add(curr);
            curr = new SpinnerData("LBP", EnvBase.Currency.LBP);
            currencyList.add(curr);
            curr = new SpinnerData("MXN", EnvBase.Currency.MXN);
            currencyList.add(curr);
            curr = new SpinnerData("NGN", EnvBase.Currency.NGN);
            currencyList.add(curr);
            curr = new SpinnerData("PKR", EnvBase.Currency.PKR);
            currencyList.add(curr);
            curr = new SpinnerData("PEN", EnvBase.Currency.PEN);
            currencyList.add(curr);
            curr = new SpinnerData("PLN", EnvBase.Currency.PLN);
            currencyList.add(curr);
            curr = new SpinnerData("QAR", EnvBase.Currency.QAR);
            currencyList.add(curr);
            curr = new SpinnerData("RON", EnvBase.Currency.RON);
            currencyList.add(curr);
            curr = new SpinnerData("UAH", EnvBase.Currency.UAH);
            currencyList.add(curr);
            curr = new SpinnerData("VEF", EnvBase.Currency.VEF);
            currencyList.add(curr);
            curr = new SpinnerData("LKR", EnvBase.Currency.LKR);
            currencyList.add(curr);
            curr = new SpinnerData("KWD", EnvBase.Currency.KWD);
            currencyList.add(curr);
            curr = new SpinnerData("JPY", EnvBase.Currency.JPY);
            currencyList.add(curr);


            ArrayAdapter<SpinnerData> currencyAdapter = new ArrayAdapter<SpinnerData>(this,
                    android.R.layout.simple_spinner_item, currencyList);
            currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spCurrency.setAdapter(currencyAdapter);


            //Initialize PAY_CHANNEL (Payment Channel)

            /*SpinnerData payCHannel;
            List<SpinnerData> listPayChannel=new ArrayList<>();

            payCHannel=new SpinnerData("Direct", EnvBase.PayChannel.DIRECT);
            listPayChannel.add(payCHannel);
            payCHannel=new SpinnerData("WebView", EnvBase.PayChannel.WEBVIEW);
            listPayChannel.add(payCHannel);
            payCHannel=new SpinnerData("Easypaymentform", EnvBase.PayChannel.EASYPAYMENTFORM);
            listPayChannel.add(payCHannel);
            payCHannel=new SpinnerData("ALL", EnvBase.PayChannel.ALL);
            listPayChannel.add(payCHannel);

            ArrayAdapter<SpinnerData> payChannelAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listPayChannel);
            payChannelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnrPayChannel.setAdapter(payChannelAdapter);*/

            //Initialize PAY_GATE (Payment Gateway)

            SpinnerData payGate;
            List<SpinnerData> listPayGate = new ArrayList<>();

            payGate = new SpinnerData("paydollar", EnvBase.PayGate.PAYDOLLAR);
            listPayGate.add(payGate);
            payGate = new SpinnerData("siampay", EnvBase.PayGate.SIAMPAY);
            listPayGate.add(payGate);
            payGate = new SpinnerData("pesopay", EnvBase.PayGate.PESOPAY);
            listPayGate.add(payGate);

            ArrayAdapter<SpinnerData> payGateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listPayGate);
            payGateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnrPayGate.setAdapter(payGateAdapter);

            //Initialize ENV_TYPE (Environment Type)

            SpinnerData envType;
            List<SpinnerData> listEnvType = new ArrayList<>();

            envType = new SpinnerData("Sandbox", EnvBase.EnvType.SANDBOX);
            listEnvType.add(envType);
            envType = new SpinnerData("Production", EnvBase.EnvType.PRODUCTION);
            listEnvType.add(envType);


            ArrayAdapter<SpinnerData> envTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listEnvType);
            envTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnrEnvType.setAdapter(envTypeAdapter);


        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_directcall:

                if (validatePayData() && validateCardDetaiils()) {

                    showProgressDialog("Processing payment, please wait...");

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.DIRECT);
                    basicDetails(payData);
                    payData.setPayMethod("VISA");
                    cardDetails(false);


                    payData.setRemark(" ");

                    // Optional Parameter (For Value-Added Service)
                    Map<String, String> extraData = new HashMap<String, String>();

                    payData.setExtraData(extraData);

                    paySDK.setRequestData(payData);

                    paySDK.process();

                    paySDK.responseHandler(new PaymentResponse() {
                        @Override
                        public void getResponse(PayResult payResult) {

                            cancelProgressDialog();
                            showAlert(payResult.getErrMsg());

                        }

                        @Override
                        public void onError(Data data) {

                            cancelProgressDialog();
                            showAlert(data.getMessage());
                        }
                    });

                }
                break;

            case R.id.btn_new_member:

                if (validatePayData() && validateCardDetaiils()) {
                    strPayMethod = NEW_MEMBER;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.WEBVIEW);
                    basicDetails(payData);
                   // cardDetails(false);
                    payData.setPayMethod("VISA");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }

                break;

            case R.id.btn_old_member:

                if (validatePayData() && PaySdkUtils.hasText(textSecurityCode)) {  //For old member security code is required
                    strPayMethod = OLD_MEMBER;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.WEBVIEW);
                    basicDetails(payData);
                   // cardDetails(true);
                    payData.setPayMethod("CC");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }

                break;
            case R.id.btn_memberpay_direc:

                if (validatePayData() && PaySdkUtils.hasText(textSecurityCode)) {  //For old member security code is required
                    strPayMethod = OLD_MEMBER;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.DIRECT);
                    basicDetails(payData);
                    cardDetails(true);
                    payData.setPayMethod("VISA");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }

                break;
            case R.id.btn_webview:
                if (validatePayData()) {

                    showProgressDialog("Processing payment, please wait...");

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.WEBVIEW);
                    basicDetails(payData);
                    payData.setPayMethod("CC");
                    payData.setRemark(" ");

                    // Optional Parameter (For Value-Added Service)
                    Map<String, String> extraData = new HashMap<String, String>();

                    extraData.put("redirect","30");

                    payData.setExtraData(extraData);

                    paySDK.setRequestData(payData);

                    paySDK.process();

                    paySDK.responseHandler(new PaymentResponse() {
                        @Override
                        public void getResponse(PayResult payResult) {

                            cancelProgressDialog();
                            showAlert(payResult.getErrMsg());


                        }

                        @Override
                        public void onError(Data data) {

                            cancelProgressDialog();
                            showAlert(data.getMessage());
                        }
                    });

                }

                break;
            case R.id.btn_schedule_pay:

                if (validatePayData() && validateCardDetaiils()) {
                    strPayMethod = SCHEDULE_PAY;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.DIRECT);
                    basicDetails(payData);
                    cardDetails(false);
                    payData.setPayMethod("VISA");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }

                break;

            case R.id.btn_promocode:

                if (validatePayData() && validateCardDetaiils()) {
                    strPayMethod = PROMO_CODE;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.DIRECT);
                    basicDetails(payData);
                    cardDetails(false);
                    payData.setPayMethod("VISA");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }

                break;
            case R.id.btn_evoucher:

                if (validatePayData() ) {
                    strPayMethod = E_VOUCHER;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.WEBVIEW);
                    basicDetails(payData);
                    payData.setPayMethod("VISA");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }

                break;
            case R.id.btn_installment:

                if (validatePayData() && validateCardDetaiils()) {
                    strPayMethod = INSTALLMENT_PAY;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.WEBVIEW);
                    basicDetails(payData);
                    cardDetails(false);
                    payData.setPayMethod("CC");
                    payData.setRemark(" ");


                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);
                }
                break;

            case R.id.btn_threeds:

                if (validatePayData() && validateCardDetaiils()) {
                    strPayMethod = THREE_DS;

                    payData = new PayData();
                    payData.setChannel(EnvBase.PayChannel.DIRECT);
                    basicDetails(payData);
                    cardDetails(false);
                    payData.setPayMethod("3DSSDK");
                    payData.setRemark(" ");

                    Intent intent = new Intent(PaymentActivity.this, ThreeDSActivity.class);
                    startActivityForResult(intent, PAY_CODE);

                }

                break;

            case R.id.btn_querystatus:

                if(validateQueryPayData())
                {
                    strPayMethod=QUERY_ACTION;

                    payData = new PayData();
                    payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                    payData.setEnvType(selectedEnvType);
                    payData.setPayGate(selectedPayGate);
                    payData.setOrderRef(textOrderRef.getEditText().getText().toString());
                    payData.setRemark(" ");

                    Intent intent = new Intent(PaymentActivity.this, VASActivity.class);
                    intent.putExtra("mode", strPayMethod);
                    startActivityForResult(intent, PAY_CODE);

                }
                break;

            case R.id.btn_paymethods:
                showProgressDialog("Fetching List of Pay Methods, please wait ...");

                //This will SHow list of PayMethods available
                if(PaySdkUtils.hasText(textMerchantId)) {
                    payData = new PayData();
                    payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                    payData.setEnvType(selectedEnvType);
                    payData.setPayGate(selectedPayGate);

                    payData.setRemark(" ");

                    paySDK.setRequestData(payData);

                    paySDK.query(EnvBase.Action.PAY_METHOD);

                    paySDK.payMethodResponseHandler(new PayMethodResponse() {
                        @Override
                        public void getResponse(PayMethodResult payMethodResult) {
                            cancelProgressDialog();
                            showAlert("List Of PayMethods available in payMethodResult obj");
                        }

                        @Override
                        public void onError(Data data) {
                            cancelProgressDialog();
                            showAlert(data.getError());
                        }
                    });
                }
                break;

            case R.id.btn_google_pay:
                showProgressDialog("Checking Google Pay, please wait ...");


                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(selectedEnvType);
                payData.setGooglePayAuth(EnvBase.GooglePayAuth.PAN_CRYPTO);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayGate(selectedPayGate);
                payData.setCurrCode(selectedCurrency);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setOrderRef(textOrderRef.getEditText().getText().toString());
                payData.setOrderRef(textOrderRef.getEditText().getText().toString());
                payData.setPayMethod("");  //PayMethod should be blank
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());


                payData.setRemark(" ");

                Optional<JSONObject> paymentDataRequestJson = GooglePay.getPaymentDataRequest(payData);

                if (!paymentDataRequestJson.isPresent()) {
                    return;
                }
                PaymentDataRequest request =
                        PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());
                if (request != null) {
                    AutoResolveHelper.resolveTask(
                            mPaymentsClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE);
                }
                break;

            case R.id.btn_alipay:

                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayMethod("ALIPAYAPP");
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setOrderRef(getOrderRef());
                payData.setRemark("test remark");
                payData.setActivity(PaymentActivity.this);

                paySDK.setRequestData(payData);
                paySDK.process();

                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        cancelProgressDialog();
                        showAlert(payResult.getSuccessMsg());

                    }

                    @Override
                    public void onError(Data data) {

                        cancelProgressDialog();
                        showAlert(data.getMessage());
                    }
                });

                break;

            case R.id.btn_wechat:
                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setActivity(PaymentActivity.this);
                payData.setOrderRef(getOrderRef());
                payData.setPayMethod("WECHATAPP");
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setRemark("additional remark");
                paySDK.setRequestData(payData);

                paySDK.process();

                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        cancelProgressDialog();
                        showAlert(payResult.getErrMsg());

                    }

                    @Override
                    public void onError(Data data) {

                        cancelProgressDialog();
                        showAlert(data.getMessage()+data.getError());
                    }
                });
                break;
            case R.id.btn_octopus:

                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayMethod("OCTOPUS");
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setOrderRef(getOrderRef());
                payData.setRemark("test remark");
                payData.setActivity(PaymentActivity.this);

                paySDK.setRequestData(payData);
                paySDK.process();

                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        cancelProgressDialog();
                       // showAlert(payResult.getSuccessMsg());

                        try {
                            byte[] data = Base64.decode(payResult.getErrMsg(), Base64.DEFAULT);
                            String text = new String(data, "UTF-8");

                            // method to get the URI in response data.
                            String octopusuri = text;
                            // if installed package is OctopusApp
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                                    .parse(octopusuri));
                            startActivityForResult(intent, OCTOPUS_APP_REQUEST_CODE);
                        }catch (Exception e){

                            Log.d(TAG, "octopus: exp "+e.getMessage());
                        }

                    }

                    @Override
                    public void onError(Data data) {

                        cancelProgressDialog();
                        //showAlert(data.getMessage()+data.getError());
                    }
                });

                break;
            case R.id.btn_alipayhk:

                payData = new PayData();
                payData.setChannel(EnvBase.PayChannel.DIRECT);
                payData.setEnvType(EnvBase.EnvType.SANDBOX);
                payData.setPayGate(EnvBase.PayGate.PAYDOLLAR);
                payData.setCurrCode(EnvBase.Currency.HKD);
                payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
                payData.setLang(EnvBase.Language.ENGLISH);
                payData.setAmount(textAmount.getEditText().getText().toString());
                payData.setPayMethod("ALIPAYHKAPP");
                payData.setMerchantId(textMerchantId.getEditText().getText().toString());
                payData.setOrderRef(getOrderRef());
                payData.setRemark("test remark");
                payData.setActivity(PaymentActivity.this);

                paySDK.setRequestData(payData);
                paySDK.process();

                paySDK.responseHandler(new PaymentResponse() {
                    @Override
                    public void getResponse(PayResult payResult) {

                        cancelProgressDialog();
                        showAlert(payResult.getSuccessMsg());

                    }

                    @Override
                    public void onError(Data data) {

                        cancelProgressDialog();
                        showAlert(data.getMessage()+data.getError());
                    }
                });

                break;

        }
    }

    String getOrderRef(){
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
        String format = s.format(new Date());
        return format;
    }
    private void cardDetails(boolean isOldMember) {

        CardDetails cardDetails = new CardDetails();
        if (!isOldMember) {
            cardDetails.setCardNo(textCardNo.getEditText().getText().toString());
            cardDetails.setEpMonth(textExpiryMonth.getEditText().getText().toString());
            cardDetails.setEpYear(textExpiryYear.getEditText().getText().toString());
            cardDetails.setCardHolder(textCardHolderName.getEditText().getText().toString());
        }
        cardDetails.setSecurityCode(textSecurityCode.getEditText().getText().toString());


        payData.setCardDetails(cardDetails);

    }

    private void basicDetails(PayData payData) {

        payData.setMerchantId(textMerchantId.getEditText().getText().toString());
        payData.setEnvType(selectedEnvType);
        payData.setAmount(textAmount.getEditText().getText().toString());
        payData.setPayGate(selectedPayGate);
        payData.setCurrCode(selectedCurrency);
        payData.setPayType(EnvBase.PayType.NORMAL_PAYMENT);
        payData.setOrderRef(textOrderRef.getEditText().getText().toString());
        payData.setLang(EnvBase.Language.ENGLISH);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PAY_CODE) {
            if (resultCode == RESULT_OK) {
                showProgressDialog("Processing payment, please wait...");

                Map<String, String> extraData = new HashMap<String, String>();

                switch (strPayMethod) {
                    case NEW_MEMBER:

                        extraData.put("memberPay_service", "T");
                        extraData.put("memberPay_memberId", data.getStringExtra("memberPay_memberId"));
                        extraData.put("addNewMember", "True"); // If merchant making payment for first time then value should be true else false

                        payData.setExtraData(extraData);

                        paySDK.setRequestData(payData);

                        paySDK.process();

                        paySDK.responseHandler(new PaymentResponse() {
                            @Override
                            public void getResponse(PayResult payResult) {

                                cancelProgressDialog();
                                showAlert(payResult.getErrMsg());

                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });
                        break;

                    case OLD_MEMBER:

                        extraData.put("memberPay_service", "T");
                        extraData.put("memberPay_memberId", data.getStringExtra("memberPay_memberId"));
                        extraData.put("memberPay_token", data.getStringExtra("memberPay_token"));
                        extraData.put("addNewMember", "False"); // If merchant making payment for first time then value should be true else false

                        payData.setExtraData(extraData);

                        paySDK.setRequestData(payData);

                        paySDK.process();

                        paySDK.responseHandler(new PaymentResponse() {
                            @Override
                            public void getResponse(PayResult payResult) {

                                cancelProgressDialog();
                                showAlert(payResult.getErrMsg());

                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getError());
                            }
                        });
                        break;

                    case SCHEDULE_PAY:

                        extraData.put("schType", data.getStringExtra("schType"));
                        extraData.put("schStatus", data.getStringExtra("schStatus"));
                        extraData.put("nSch", data.getStringExtra("nSch"));
                        extraData.put("sMonth", data.getStringExtra("sMonth"));
                        extraData.put("sDay", data.getStringExtra("sDay"));
                        extraData.put("sYear", data.getStringExtra("sYear"));
                        extraData.put("eMonth", data.getStringExtra("eMonth"));
                        extraData.put("eDay", data.getStringExtra("eDay"));
                        extraData.put("name", data.getStringExtra("name"));
                        extraData.put("email", data.getStringExtra("email"));
                        extraData.put("appId", data.getStringExtra("appId"));
                        extraData.put("appRef", data.getStringExtra("appRef"));


                        payData.setExtraData(extraData);

                        paySDK.setRequestData(payData);

                        paySDK.process();

                        paySDK.responseHandler(new PaymentResponse() {
                            @Override
                            public void getResponse(PayResult payResult) {

                                cancelProgressDialog();
                                showAlert(payResult.getErrMsg());

                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });
                        break;

                    case PROMO_CODE:

                        extraData.put("promotion", "T");
                        extraData.put("promotionCode", data.getStringExtra("promotionCode"));
                        extraData.put("promotionRuleCode", data.getStringExtra("promotionRuleCode"));
                        extraData.put("promotionOriginalAmt", data.getStringExtra("promotionOriginalAmt"));

                        payData.setExtraData(extraData);

                        paySDK.setRequestData(payData);

                        paySDK.process();

                        paySDK.responseHandler(new PaymentResponse() {
                            @Override
                            public void getResponse(PayResult payResult) {

                                cancelProgressDialog();
                                showAlert(payResult.getErrMsg());

                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });
                        break;

                    case E_VOUCHER:

                        extraData.put("eVoucher", "T");
                        extraData.put("eVClassCode", data.getStringExtra("eVClassCode"));

                        payData.setExtraData(extraData);

                        paySDK.setRequestData(payData);

                        paySDK.process();

                        paySDK.responseHandler(new PaymentResponse() {
                            @Override
                            public void getResponse(PayResult payResult) {

                                cancelProgressDialog();
                                showAlert(payResult.getErrMsg());

                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });
                        break;
                    case INSTALLMENT_PAY:

                        extraData.put("installment_service", "T");
                        extraData.put("installOnly", data.getStringExtra("installOnly"));
                        extraData.put("installment_period", data.getStringExtra("installment_period"));

                        payData.setExtraData(extraData);

                        paySDK.setRequestData(payData);

                        paySDK.process();

                        paySDK.responseHandler(new PaymentResponse() {
                            @Override
                            public void getResponse(PayResult payResult) {

                                cancelProgressDialog();
                                showAlert(payResult.getErrMsg());

                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });

                        break;
                    case QUERY_ACTION:

                        payData.setPayRef(data.getStringExtra("payRef"));

                        paySDK.setRequestData(payData);

                        paySDK.query(EnvBase.Action.TX_QUERY);

                        paySDK.queryResponseHandler(new QueryResponse() {
                            @Override
                            public void getResponse(TransactionStatus transactionStatus) {

                                cancelProgressDialog();
                                showAlert(transactionStatus.getResultCode());
                            }

                            @Override
                            public void onError(Data data) {

                                cancelProgressDialog();
                                showAlert(data.getMessage());
                            }
                        });

                        break;
                    case THREE_DS:

                        ThreeDSData threeDSData = data.getParcelableExtra("Data");

                        if (threeDSData != null) {
                            ThreeDSParams threeDSParams = new ThreeDSParams();
                            threeDSParams.setCustomerEmail(threeDSData.getCustomerEmail());
                            threeDSParams.setMobilePhoneCountryCode(threeDSData.getMobilePhoneCountryCode());
                            threeDSParams.setMobilePhoneNumber(threeDSData.getMobilePhoneNumber());
                            threeDSParams.setHomePhoneCountryCode(threeDSData.getHomePhoneCountryCode());
                            threeDSParams.setHomePhoneNumber(threeDSData.getHomePhoneNumber());
                            threeDSParams.setWorkPhoneCountryCode(threeDSData.getWorkPhoneCountryCode());
                            threeDSParams.setWorkPhoneNumber(threeDSData.getWorkPhoneNumber());
                            threeDSParams.setDeliveryEmail(threeDSData.getDeliveryEmail());
                            threeDSParams.setBillingCountryCode(threeDSData.getBillingCountryCode());
                            threeDSParams.setBillingState(threeDSData.getBillingState());
                            threeDSParams.setBillingCity(threeDSData.getBillingCity());
                            threeDSParams.setBillingLine1(threeDSData.getBillingLine1());
                            threeDSParams.setBillingLine2(threeDSData.getBillingLine2());
                            threeDSParams.setBillingLine3(threeDSData.getBillingLine3());
                            threeDSParams.setBillingPostalCode(threeDSData.getBillingPostalCode());
                            threeDSParams.setShippingDetails(threeDSData.getShippingDetails());
                            threeDSParams.setShippingCountryCode(threeDSData.getShippingCountryCode());
                            threeDSParams.setShippingState(threeDSData.getShippingState());
                            threeDSParams.setShippingCity(threeDSData.getShippingCity());
                            threeDSParams.setShippingLine1(threeDSData.getShippingLine1());
                            threeDSParams.setShippingLine2(threeDSData.getShippingLine2());
                            threeDSParams.setShippingLine3(threeDSData.getShippingLine3());
                            threeDSParams.setAcctCreateDate(threeDSData.getAcctCreateDate());
                            threeDSParams.setAcctAgeInd(threeDSData.getAcctAgeInd());
//                            threeDSParams.setAcctCreateDate(threeDSData.getAcctCreateDate());
//                            threeDSParams.setAcctAgeInd(threeDSData.getAcctAgeInd());
                            threeDSParams.setAcctLastChangeDate(threeDSData.getAcctLastChangeDate());
                            threeDSParams.setAcctLastChangeInd(threeDSData.getAcctLastChangeInd());
                            threeDSParams.setAcctPwChangeDate(threeDSData.getAcctPwChangeDate());
                            threeDSParams.setAcctPwChangeInd(threeDSData.getAcctPwChangeInd());
                            threeDSParams.setAcctPurchaseCount(threeDSData.getAcctPurchaseCount());
                            threeDSParams.setAcctCardProvisionAttempt(threeDSData.getAcctCardProvisionAttempt());
                            threeDSParams.setAcctNumTransDay(threeDSData.getAcctNumTransDay());
                            threeDSParams.setAcctNumTransYear(threeDSData.getAcctNumTransYear());
                            threeDSParams.setAcctPaymentAcctDate(threeDSData.getAcctPaymentAcctDate());
                            threeDSParams.setAcctPaymentAcctInd(threeDSData.getAcctPaymentAcctInd());
                            threeDSParams.setAcctShippingAddrLastChangeDate(threeDSData.getAcctShippingAddrLastChangeDate());
                            threeDSParams.setAcctShippingAddrLastChangeInd(threeDSData.getAcctShippingAddrLastChangeInd());
                            threeDSParams.setAcctIsShippingAcctNameSame(threeDSData.getAcctIsShippingAcctNameSame());
                            threeDSParams.setAcctIsSuspiciousAcct(threeDSData.getAcctIsSuspiciousAcct());
                            threeDSParams.setAcctAuthMethod(threeDSData.getAcctAuthMethod());
                            threeDSParams.setAcctAuthTimestamp(threeDSData.getAcctAuthTimestamp());
                            threeDSParams.setDeliveryTime(threeDSData.getDeliveryTime());
                            threeDSParams.setPreOrderReason(threeDSData.getPreOrderReason());
                            threeDSParams.setPreOrderReadyDate(threeDSData.getPreOrderReadyDate());
                            threeDSParams.setGiftCardAmount(threeDSData.getGiftCardAmount());
                            threeDSParams.setGiftCardCount(threeDSData.getGiftCardCount());
                            threeDSParams.setGiftCardCurr(selectedCurrency);
                            threeDSParams.setSdkMaxTimeout("05");

                            Factory factory = new com.asiapay.sdk.integration.xecure3ds.Factory();
                            ConfigParameters configParameters = factory.newConfigParameters();
                            UiCustomization uiCustomization = factory.newUiCustomization();
                            payData.setConfigParameters(configParameters);
                            payData.setUiCustomization(uiCustomization);
                            payData.setActivity(PaymentActivity.this);

                            payData.setExtraData(extraData);

                            payData.setThreeDSParams(threeDSParams);

                            paySDK.setRequestData(payData);

                            paySDK.process();

                            paySDK.responseHandler(new PaymentResponse() {
                                @Override
                                public void getResponse(PayResult payResult) {

                                    cancelProgressDialog();
                                    showAlert(payResult.getSuccessMsg());

                                }

                                @Override
                                public void onError(Data data) {


                                    cancelProgressDialog();
                                    showAlert(data.getMessage());
                                }
                            });

                            break;

                        }
                }

            }
        } else if(requestCode == LOAD_PAYMENT_DATA_REQUEST_CODE){

            switch (resultCode) {
                case Activity.RESULT_OK:
                    PaymentData paymentData = PaymentData.getFromIntent(data);
                        /*Gson gson=new Gson();
                        String strData= gson.toJson(paymentData);
                        GooglePayResp googlePayResp=gson.fromJson(strData,GooglePayResp.class);

                        Log.d("GP", "onActivityResult: "+googlePayResp.getZzbw());*/

                    // Token will be null if PaymentDataRequest was not constructed using fromJson(String).
                    final String paymentInfo = paymentData.toJson();
                    if (paymentInfo == null) {
                        return;
                    }

                    try {
                        JSONObject paymentMethodData = new JSONObject(paymentInfo).getJSONObject("paymentMethodData");
                        // If the gateway is set to "example", no payment information is returned - instead, the
                        // token will only consist of "examplePaymentMethodToken".

                        final JSONObject tokenizationData = paymentMethodData.getJSONObject("tokenizationData");
                        final String tokenizationType = tokenizationData.getString("type");
                        final String token = tokenizationData.getString("token");

                        handleGooglePay(paymentInfo);
                    }catch (Exception e)
                    {

                    }

                    //handleGooglePay(googlePayResp.getZzbw());
                    break;
                case Activity.RESULT_CANCELED:
                    // Nothing to here normally - the user simply cancelled without selecting a
                    // payment method.
                    Log.d("GP", "onActivityResult: Cancel");
                    break;
                case AutoResolveHelper.RESULT_ERROR:
                    Status status = AutoResolveHelper.getStatusFromIntent(data);
                    Log.d("GP", "onActivityResult: Error");
                    break;
                default:
                    // Do nothing.
            }
        } else if(requestCode == OCTOPUS_APP_REQUEST_CODE &&
                resultCode == Activity.RESULT_OK){
            showAlert("Transaction Completed.");
        }
    }
    void handleGooglePay(String strResp){
        String base64encodedString=null;

        try {

            byte[] byteString=strResp.getBytes("UTF-8");
            base64encodedString= android.util.Base64.encodeToString(byteString, Base64.NO_WRAP);

        } catch (Exception e) {
            e.printStackTrace();
        }



        //Pass Google Pay response to PaySdk
        Map<String, String> extraDataGP = new HashMap<String, String>();

        extraDataGP.put("eWalletPaymentData",base64encodedString);
        extraDataGP.put("eWalletService","T");
        extraDataGP.put("eWalletBrand","GOOGLE");

        payData.setExtraData(extraDataGP);

        paySDK.setRequestData(payData);

        paySDK.process();

        paySDK.responseHandler(new PaymentResponse() {
            @Override
            public void getResponse(PayResult payResult) {


                cancelProgressDialog();
                showAlert(payResult.getErrMsg());

            }

            @Override
            public void onError(Data data) {

                cancelProgressDialog();
                showAlert(data.getError());
            }
        });
    }
    private void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(PaymentActivity.this);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    private void cancelProgressDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }

    private void showAlert(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        builder.setCancelable(true);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage(msg);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean validatePayData() {

        boolean isMID, isOrdREf, isAmount;

        isMID = PaySdkUtils.hasText(textMerchantId);
        isOrdREf = PaySdkUtils.hasText(textOrderRef);
        isAmount = PaySdkUtils.hasText(textAmount);

        return isMID && isOrdREf && isAmount;
    }

    private boolean validateQueryPayData() {

        boolean isMID, isOrdREf;

        isMID = PaySdkUtils.hasText(textMerchantId);
        isOrdREf = PaySdkUtils.hasText(textOrderRef);

        return isMID && isOrdREf;
    }
    private boolean validateCardDetaiils() {
        boolean isCardNo, isMonth, isYear, isName, isCode;

        isCardNo = PaySdkUtils.hasText(textCardNo);
        isMonth = PaySdkUtils.hasText(textExpiryMonth);
        isYear = PaySdkUtils.hasText(textExpiryYear);
        isName = PaySdkUtils.hasText(textCardHolderName);
        isCode = PaySdkUtils.hasText(textSecurityCode);

        return isCardNo && isMonth && isYear && isName && isCode;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getId()) {
            case R.id.spCurrency:
                SpinnerData spinnerCurrency = (SpinnerData) adapterView.getSelectedItem();
                selectedCurrency = spinnerCurrency.getCurrency();
                break;

            case R.id.spnr_envtype:
                SpinnerData spinnerEnvType = (SpinnerData) adapterView.getSelectedItem();
                selectedEnvType = spinnerEnvType.getEnvType();
                break;

            case R.id.spnr_paygate:
                SpinnerData spinnerPayGate = (SpinnerData) adapterView.getSelectedItem();
                selectedPayGate = spinnerPayGate.getPayGate();
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
