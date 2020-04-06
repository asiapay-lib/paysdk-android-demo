package com.asiapay.SDKApp;

import com.asiapay.sdk.integration.EnvBase;

public class SpinnerData {
    String value;
    EnvBase.Currency currency;
    EnvBase.PayChannel payChannel;
    EnvBase.PayGate payGate;
    EnvBase.EnvType envType;

    SpinnerData(String value, EnvBase.Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    SpinnerData(String value, EnvBase.PayChannel payChannel){
        this.value = value;
        this.payChannel=payChannel;
    }

    SpinnerData(String value, EnvBase.PayGate payGate){
        this.value = value;
        this.payGate=payGate;
    }

    SpinnerData(String value, EnvBase.EnvType envType){
        this.value = value;
        this.envType=envType;
    }

    public String getCurrencyName() {
        return value;
    }

    public void setCurrencyName(String currencyName) {
        this.value = currencyName;
    }

    public EnvBase.Currency getCurrency() {
        return currency;
    }

    public void setCurrency(EnvBase.Currency currency) {
        this.currency = currency;
    }

    public EnvBase.PayChannel getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(EnvBase.PayChannel payChannel) {
        this.payChannel = payChannel;
    }

    public EnvBase.PayGate getPayGate() {
        return payGate;
    }

    public void setPayGate(EnvBase.PayGate payGate) {
        this.payGate = payGate;
    }

    public EnvBase.EnvType getEnvType() {
        return envType;
    }

    public void setEnvType(EnvBase.EnvType envType) {
        this.envType = envType;
    }

    @Override
    public String toString() {
        return value;
    }
}