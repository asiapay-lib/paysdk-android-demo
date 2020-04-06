package com.asiapay.SDKApp;

import android.os.Parcel;
import android.os.Parcelable;

public class ThreeDSData implements Parcelable {


    private String customerEmail;

    private String mobilePhoneCountryCode;

    private String mobilePhoneNumber;

    private String homePhoneCountryCode;

    private String homePhoneNumber;

    private String workPhoneCountryCode;

    private String workPhoneNumber;

    private String billingCountryCode;

    private String billingState;

    private String billingCity;

    private String billingLine1;

    private String billingLine2;

    private String billingLine3;

    private String billingPostalCode;

    private String deliveryTime;

    private String deliveryEmail;


    private String shippingDetails;

    private String shippingCountryCode;


    private String shippingState;


    private String shippingCity;


    private String shippingLine1;

    private String shippingLine2;

    private String shippingLine3;

    private String giftCardAmount;

    private String giftCardCount;

    private String preOrderReason;

    private String preOrderReadyDate;

    private String acctCreateDate;


    private String acctAgeInd;

    private String acctLastChangeDate;

    private String acctLastChangeInd;

    private String acctPwChangeDate;

    private String acctPwChangeInd;

    private String acctPurchaseCount;


    private String acctCardProvisionAttempt;

    private String acctNumTransDay;

    private String acctNumTransYear;


    private String acctPaymentAcctDate;


    private String acctPaymentAcctInd;

    private String acctShippingAddrLastChangeDate;


    private String acctShippingAddrLastChangeInd;


    private String acctIsShippingAcctNameSame;

    private String acctIsSuspiciousAcct;

    private String acctAuthMethod;

    private String acctAuthTimestamp;


    public ThreeDSData(Parcel parcel) {

        customerEmail = parcel.readString();

        mobilePhoneCountryCode = parcel.readString();

        mobilePhoneNumber = parcel.readString();

        homePhoneCountryCode = parcel.readString();

        homePhoneNumber = parcel.readString();

        workPhoneCountryCode = parcel.readString();

        workPhoneNumber = parcel.readString();

        billingCountryCode = parcel.readString();

        billingState = parcel.readString();

        billingCity = parcel.readString();

        billingLine1 = parcel.readString();

        billingLine2 = parcel.readString();

        billingLine3 = parcel.readString();

        billingPostalCode = parcel.readString();

        deliveryTime = parcel.readString();

        deliveryEmail = parcel.readString();


        shippingDetails = parcel.readString();

        shippingCountryCode = parcel.readString();


        shippingState = parcel.readString();


        shippingCity = parcel.readString();


        shippingLine1 = parcel.readString();

        shippingLine2 = parcel.readString();


        shippingLine3 = parcel.readString();


        giftCardAmount = parcel.readString();


        giftCardCount = parcel.readString();

        preOrderReason = parcel.readString();

        preOrderReadyDate = parcel.readString();

        acctCreateDate = parcel.readString();


        acctAgeInd = parcel.readString();

        acctLastChangeDate = parcel.readString();

        acctLastChangeInd = parcel.readString();

        acctPwChangeDate = parcel.readString();

        acctPwChangeInd = parcel.readString();

        acctPurchaseCount = parcel.readString();


        acctCardProvisionAttempt = parcel.readString();

        acctNumTransDay = parcel.readString();

        acctNumTransYear = parcel.readString();


        acctPaymentAcctDate = parcel.readString();


        acctPaymentAcctInd = parcel.readString();

        acctShippingAddrLastChangeDate = parcel.readString();


        acctShippingAddrLastChangeInd = parcel.readString();


        acctIsShippingAcctNameSame = parcel.readString();

        acctIsSuspiciousAcct = parcel.readString();

        acctAuthMethod = parcel.readString();

        acctAuthTimestamp = parcel.readString();


    }

    public static final Creator<ThreeDSData> CREATOR = new Creator<ThreeDSData>() {
        @Override
        public ThreeDSData createFromParcel(Parcel in) {
            return new ThreeDSData(in);
        }

        @Override
        public ThreeDSData[] newArray(int size) {
            return new ThreeDSData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(customerEmail);

        parcel.writeString(mobilePhoneCountryCode);

        parcel.writeString(mobilePhoneNumber);

        parcel.writeString(homePhoneCountryCode);

        parcel.writeString(homePhoneNumber);

        parcel.writeString(workPhoneCountryCode);

        parcel.writeString(workPhoneNumber);

        parcel.writeString(billingCountryCode);

        parcel.writeString(billingState);

        parcel.writeString(billingCity);

        parcel.writeString(billingLine1);

        parcel.writeString(billingLine2);

        parcel.writeString(billingLine3);

        parcel.writeString(billingPostalCode);

        parcel.writeString(deliveryTime);

        parcel.writeString(deliveryEmail);


        parcel.writeString(shippingDetails);

        parcel.writeString(shippingCountryCode);


        parcel.writeString(shippingState);


        parcel.writeString(shippingCity);


        parcel.writeString(shippingLine1);

        parcel.writeString(shippingLine2);


        parcel.writeString(shippingLine3);

        parcel.writeString(giftCardAmount);


        parcel.writeString(giftCardCount);

        parcel.writeString(preOrderReason);

        parcel.writeString(preOrderReadyDate);

        parcel.writeString(acctCreateDate);


        parcel.writeString(acctAgeInd);

        parcel.writeString(acctLastChangeDate);

        parcel.writeString(acctLastChangeInd);

        parcel.writeString(acctPwChangeDate);

        parcel.writeString(acctPwChangeInd);

        parcel.writeString(acctPurchaseCount);


        parcel.writeString(acctCardProvisionAttempt);

        parcel.writeString(acctNumTransDay);

        parcel.writeString(acctNumTransYear);


        parcel.writeString(acctPaymentAcctDate);


        parcel.writeString(acctPaymentAcctInd);

        parcel.writeString(acctShippingAddrLastChangeDate);


        parcel.writeString(acctShippingAddrLastChangeInd);


        parcel.writeString(acctIsShippingAcctNameSame);

        parcel.writeString(acctIsSuspiciousAcct);

        parcel.writeString(acctAuthMethod);

        parcel.writeString(acctAuthTimestamp);

    }

    public ThreeDSData(String customerEmail, String mobilePhoneCountryCode, String mobilePhoneNumber, String homePhoneCountryCode, String homePhoneNumber,
                       String workPhoneCountryCode, String workPhoneNumber, String billingCountryCode, String billingState, String billingCity, String billingLine1,
                       String billingLine2, String billingLine3, String billingPostalCode, String deliveryTime, String deliveryEmail, String shippingDetails,
                       String shippingCountryCode, String shippingState, String shippingCity, String shippingLine1, String shippingLine2, String shippingLine3,
                       String giftCardAmount, String giftCardCount, String preOrderReason, String preOrderReadyDate, String acctCreateDate, String acctAgeInd,
                       String acctLastChangeDate, String acctLastChangeInd, String acctPwChangeDate, String acctPwChangeInd, String acctPurchaseCount,
                       String acctCardProvisionAttempt, String acctNumTransDay, String acctNumTransYear, String acctPaymentAcctDate, String acctPaymentAcctInd,
                       String acctShippingAddrLastChangeDate, String acctShippingAddrLastChangeInd, String acctIsShippingAcctNameSame, String acctIsSuspiciousAcct, String acctAuthMethod, String acctAuthTimestamp) {

        this.customerEmail = customerEmail;
        this.mobilePhoneCountryCode = mobilePhoneCountryCode;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.homePhoneCountryCode = homePhoneCountryCode;
        this.homePhoneNumber = homePhoneNumber;
        this.workPhoneCountryCode = workPhoneCountryCode;
        this.workPhoneNumber = workPhoneNumber;
        this.billingCountryCode = billingCountryCode;
        this.billingState = billingState;
        this.billingCity = billingCity;
        this.billingLine1 = billingLine1;
        this.billingLine2 = billingLine2;
        this.billingLine3 = billingLine3;
        this.billingPostalCode = billingPostalCode;
        this.deliveryTime = deliveryTime;
        this.deliveryEmail = deliveryEmail;
        this.shippingDetails = shippingDetails;
        this.shippingCountryCode = shippingCountryCode;
        this.shippingState = shippingState;
        this.shippingCity = shippingCity;
        this.shippingLine1 = shippingLine1;
        this.shippingLine2 = shippingLine2;
        this.shippingLine3 = shippingLine3;
        this.giftCardAmount = giftCardAmount;
        this.giftCardCount = giftCardCount;
        this.preOrderReason = preOrderReason;
        this.preOrderReadyDate = preOrderReadyDate;
        this.acctCreateDate = acctCreateDate;
        this.acctAgeInd = acctAgeInd;
        this.acctLastChangeDate = acctLastChangeDate;
        this.acctLastChangeInd = acctLastChangeInd;
        this.acctPwChangeDate = acctPwChangeDate;
        this.acctPwChangeInd = acctPwChangeInd;
        this.acctPurchaseCount = acctPurchaseCount;
        this.acctCardProvisionAttempt = acctCardProvisionAttempt;
        this.acctNumTransDay = acctNumTransDay;
        this.acctNumTransYear = acctNumTransYear;
        this.acctPaymentAcctDate = acctPaymentAcctDate;
        this.acctPaymentAcctInd = acctPaymentAcctInd;
        this.acctShippingAddrLastChangeDate = acctShippingAddrLastChangeDate;
        this.acctShippingAddrLastChangeInd = acctShippingAddrLastChangeInd;
        this.acctIsShippingAcctNameSame = acctIsShippingAcctNameSame;
        this.acctIsSuspiciousAcct = acctIsSuspiciousAcct;
        this.acctAuthMethod = acctAuthMethod;
        this.acctAuthTimestamp = acctAuthTimestamp;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getMobilePhoneCountryCode() {
        return mobilePhoneCountryCode;
    }

    public void setMobilePhoneCountryCode(String mobilePhoneCountryCode) {
        this.mobilePhoneCountryCode = mobilePhoneCountryCode;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getHomePhoneCountryCode() {
        return homePhoneCountryCode;
    }

    public void setHomePhoneCountryCode(String homePhoneCountryCode) {
        this.homePhoneCountryCode = homePhoneCountryCode;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getWorkPhoneCountryCode() {
        return workPhoneCountryCode;
    }

    public void setWorkPhoneCountryCode(String workPhoneCountryCode) {
        this.workPhoneCountryCode = workPhoneCountryCode;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getBillingCountryCode() {
        return billingCountryCode;
    }

    public void setBillingCountryCode(String billingCountryCode) {
        this.billingCountryCode = billingCountryCode;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingLine1() {
        return billingLine1;
    }

    public void setBillingLine1(String billingLine1) {
        this.billingLine1 = billingLine1;
    }

    public String getBillingLine2() {
        return billingLine2;
    }

    public void setBillingLine2(String billingLine2) {
        this.billingLine2 = billingLine2;
    }

    public String getBillingLine3() {
        return billingLine3;
    }

    public void setBillingLine3(String billingLine3) {
        this.billingLine3 = billingLine3;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    public String getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public String getShippingCountryCode() {
        return shippingCountryCode;
    }

    public void setShippingCountryCode(String shippingCountryCode) {
        this.shippingCountryCode = shippingCountryCode;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingLine1() {
        return shippingLine1;
    }

    public void setShippingLine1(String shippingLine1) {
        this.shippingLine1 = shippingLine1;
    }

    public String getShippingLine2() {
        return shippingLine2;
    }

    public void setShippingLine2(String shippingLine2) {
        this.shippingLine2 = shippingLine2;
    }

    public String getShippingLine3() {
        return shippingLine3;
    }

    public void setShippingLine3(String shippingLine3) {
        this.shippingLine3 = shippingLine3;
    }

    public String getGiftCardAmount() {
        return giftCardAmount;
    }

    public void setGiftCardAmount(String giftCardAmount) {
        this.giftCardAmount = giftCardAmount;
    }


    public String getGiftCardCount() {
        return giftCardCount;
    }

    public void setGiftCardCount(String giftCardCount) {
        this.giftCardCount = giftCardCount;
    }

    public String getPreOrderReason() {
        return preOrderReason;
    }

    public void setPreOrderReason(String preOrderReason) {
        this.preOrderReason = preOrderReason;
    }

    public String getPreOrderReadyDate() {
        return preOrderReadyDate;
    }

    public void setPreOrderReadyDate(String preOrderReadyDate) {
        this.preOrderReadyDate = preOrderReadyDate;
    }

    public String getAcctCreateDate() {
        return acctCreateDate;
    }

    public void setAcctCreateDate(String acctCreateDate) {
        this.acctCreateDate = acctCreateDate;
    }

    public String getAcctAgeInd() {
        return acctAgeInd;
    }

    public void setAcctAgeInd(String acctAgeInd) {
        this.acctAgeInd = acctAgeInd;
    }

    public String getAcctLastChangeDate() {
        return acctLastChangeDate;
    }

    public void setAcctLastChangeDate(String acctLastChangeDate) {
        this.acctLastChangeDate = acctLastChangeDate;
    }

    public String getAcctLastChangeInd() {
        return acctLastChangeInd;
    }

    public void setAcctLastChangeInd(String acctLastChangeInd) {
        this.acctLastChangeInd = acctLastChangeInd;
    }

    public String getAcctPwChangeDate() {
        return acctPwChangeDate;
    }

    public void setAcctPwChangeDate(String acctPwChangeDate) {
        this.acctPwChangeDate = acctPwChangeDate;
    }

    public String getAcctPwChangeInd() {
        return acctPwChangeInd;
    }

    public void setAcctPwChangeInd(String acctPwChangeInd) {
        this.acctPwChangeInd = acctPwChangeInd;
    }

    public String getAcctPurchaseCount() {
        return acctPurchaseCount;
    }

    public void setAcctPurchaseCount(String acctPurchaseCount) {
        this.acctPurchaseCount = acctPurchaseCount;
    }

    public String getAcctCardProvisionAttempt() {
        return acctCardProvisionAttempt;
    }

    public void setAcctCardProvisionAttempt(String acctCardProvisionAttempt) {
        this.acctCardProvisionAttempt = acctCardProvisionAttempt;
    }

    public String getAcctNumTransDay() {
        return acctNumTransDay;
    }

    public void setAcctNumTransDay(String acctNumTransDay) {
        this.acctNumTransDay = acctNumTransDay;
    }

    public String getAcctNumTransYear() {
        return acctNumTransYear;
    }

    public void setAcctNumTransYear(String acctNumTransYear) {
        this.acctNumTransYear = acctNumTransYear;
    }

    public String getAcctPaymentAcctDate() {
        return acctPaymentAcctDate;
    }

    public void setAcctPaymentAcctDate(String acctPaymentAcctDate) {
        this.acctPaymentAcctDate = acctPaymentAcctDate;
    }

    public String getAcctPaymentAcctInd() {
        return acctPaymentAcctInd;
    }

    public void setAcctPaymentAcctInd(String acctPaymentAcctInd) {
        this.acctPaymentAcctInd = acctPaymentAcctInd;
    }

    public String getAcctShippingAddrLastChangeDate() {
        return acctShippingAddrLastChangeDate;
    }

    public void setAcctShippingAddrLastChangeDate(String acctShippingAddrLastChangeDate) {
        this.acctShippingAddrLastChangeDate = acctShippingAddrLastChangeDate;
    }

    public String getAcctShippingAddrLastChangeInd() {
        return acctShippingAddrLastChangeInd;
    }

    public void setAcctShippingAddrLastChangeInd(String acctShippingAddrLastChangeInd) {
        this.acctShippingAddrLastChangeInd = acctShippingAddrLastChangeInd;
    }

    public String getAcctIsShippingAcctNameSame() {
        return acctIsShippingAcctNameSame;
    }

    public void setAcctIsShippingAcctNameSame(String acctIsShippingAcctNameSame) {
        this.acctIsShippingAcctNameSame = acctIsShippingAcctNameSame;
    }

    public String getAcctIsSuspiciousAcct() {
        return acctIsSuspiciousAcct;
    }

    public void setAcctIsSuspiciousAcct(String acctIsSuspiciousAcct) {
        this.acctIsSuspiciousAcct = acctIsSuspiciousAcct;
    }

    public String getAcctAuthMethod() {
        return acctAuthMethod;
    }

    public void setAcctAuthMethod(String acctAuthMethod) {
        this.acctAuthMethod = acctAuthMethod;
    }

    public String getAcctAuthTimestamp() {
        return acctAuthTimestamp;
    }

    public void setAcctAuthTimestamp(String acctAuthTimestamp) {
        this.acctAuthTimestamp = acctAuthTimestamp;
    }

    public static Creator<ThreeDSData> getCREATOR() {
        return CREATOR;
    }
}

