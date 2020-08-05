package com.asiapay.SDKApp.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.asiapay.sdk.integration.paymentoption.PaymentOptionActivity;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {

    private final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, "wxa89ca4d0b0508980");
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onReq(BaseReq req) {
        Toast.makeText(this, req.getType(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResp(BaseResp resp) {

       /* Toast.makeText(this, resp.errCode+"---"+resp.errStr+"---"+resp.openId+"---"+resp.transaction,Toast.LENGTH_SHORT).show();
        finish();*/

       /* Gson gson=new Gson();
        String strBaseResp= gson.toJson(resp);

        when (baseResp?.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                val code = (baseResp as SendAuth.Resp).code
                viewModel.get().getWeChatToken(code)
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                finish()
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                finish()
            }
        }*/

        switch (resp.errCode){

            case BaseResp.ErrCode.ERR_OK:
                Toast.makeText(this, "Payment successful!",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "User canceled",Toast.LENGTH_SHORT).show();
                finish();
                break;

            case BaseResp.ErrCode.ERR_COMM:
                Toast.makeText(this, "Error during payment",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                Toast.makeText(this, "Payment Failed",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this, "Auth Denied",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_UNSUPPORT:
                Toast.makeText(this, "Error Unsupport",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case BaseResp.ErrCode.ERR_BAN:
                Toast.makeText(this, "Error Ban",Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                Toast.makeText(this, "Unknown result",Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

    }

}
