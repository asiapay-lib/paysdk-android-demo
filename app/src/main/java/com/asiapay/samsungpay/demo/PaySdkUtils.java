package com.asiapay.samsungpay.demo;

import com.asiapay.samsungpay.demo.PaySdkConstants.*;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaySdkUtils {
    public static CARD_TYPE getCardType(String cardnum, String cardType,
                                                        boolean isMastroAllowed) {
        String visaRegex = "^4[0-9]*";
        String masterRegex = "^5[1-5][0-9]*";
        String mastroRegex = "^(0604|5018|5020|5021|5022|5038|5044|5046|5047|5048|5049|5081|5082|5612|5818|5893|6002|6031|6037|6038|6220|6304|6390|6759|6761|6762|6763)[0-9]*";

        if (cardnum.matches(visaRegex)) {
            return CARD_TYPE.visa;
        }
        if (cardnum.matches(masterRegex)) {
            return CARD_TYPE.master;
        }

        if (isMastroAllowed && cardType.equalsIgnoreCase("dc")) {
            if (cardnum.matches(mastroRegex)) {
                return CARD_TYPE.maestro;
            }
        }

        return CARD_TYPE.un_identified;
    }

    public static boolean isValidUsername(String userName) {
        Pattern pattern = Pattern.compile("^[A-Za-z_.'\\s]{1,}[\\.]{0,1}[A-Za-z_.'\\s]{0,}$");
        Matcher matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public static boolean validateLuhn(String str) {
        str = str.replace(" ", "");
        // if empty return false
        if (str.trim().isEmpty()) {
            return false;
        }

        try {
            int s1 = 0, s2 = 0;
            String reverse = new StringBuffer(str).reverse().toString();
            for (int i = 0; i < reverse.length(); i++) {
                int digit = Character.digit(reverse.charAt(i), 10);
                if (i % 2 == 0) {// this is for odd digits, they are 1-indexed
                    // in
                    // the algorithm
                    s1 += digit;
                } else {// add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                    s2 += 2 * digit;
                    if (digit >= 5) {
                        s2 -= 9;
                    }
                }
            }
            return (s1 + s2) % 10 == 0;
        } catch (Exception ignored) {
            return false;
        }

    }
    public static boolean isEmpty(TextInputLayout textInputLayout) {
        if (textInputLayout.getEditText().getText().toString().trim().length() > 0)
            return false;

        return true;
    }

    public static boolean hasText(TextInputLayout textInputLayout){
        if(PaySdkUtils.isEmpty(textInputLayout)){
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Please add "+textInputLayout.getHint().toString()+".");
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
            textInputLayout.setError(null);
            return true;
        }
    }
}
