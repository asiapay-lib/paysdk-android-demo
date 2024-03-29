package com.asiapay.samsungpay.demo;

public class PaySdkConstants {

    public enum CARD_TYPE {
        visa, master, maestro, un_identified
    }

    public static final String NEW_MEMBER="New Member Pay";
    public static final String OLD_MEMBER= "Old Member Pay";
    public static final String PROMO_CODE= "Promo Code";
    public static final String E_VOUCHER= "E Voucher";
    public static final String INSTALLMENT_PAY= "Installment Pay";
    public static final String SCHEDULE_PAY= "Schedule Pay";
    public static final String THREE_DS= "Three DS 2.0";
    public static final String QUERY_ACTION= "Transaction Status";


    public enum PAY_METHOD{
        NEW_MEMBER
        ,OLD_MEMBER,PROMO_CODE,INSTALLMENT_PAY,SCHEDULE_PAY,THREE_DS
    }
}
