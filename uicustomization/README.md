### UI Customization

[Webview Payment](https://github.com/asiapay-lib/paysdk-android-demo/blob/master/Webview/README.md)

#### To customize look & feel of toolbar add customization object to paydata before calling process() method.
   

                Factory factory = new com.asiapay.sdk.integration.xecure3ds.Factory();
                ConfigParameters configParameters = factory.newConfigParameters();
                UiCustomization uiCustomization = factory.newUiCustomization();

                ToolbarCustomization toolbarCustomization = factory.newToolbarCustomization();
                if (toolbarCustomization != null) {
                    toolbarCustomization.setHeaderText("Payment Page");
                    toolbarCustomization.setBackgroundColor("#ff8000");
                    toolbarCustomization.setTextColor("#ffffff");
                    toolbarCustomization.setButtonText("Cancel");
                    toolbarCustomization.setTextFontName("pacifico.ttf");
                    uiCustomization.setToolbarCustomization(toolbarCustomization);
                }


                payData.setConfigParameters(configParameters);
                payData.setUiCustomization(uiCustomization);
                payData.setActivity(AuthActivity.this);
                paySDK.setRequestData(payData);


![image](https://user-images.githubusercontent.com/57220911/116494410-62d04100-a8be-11eb-8ed9-db924c8afa36.png)
