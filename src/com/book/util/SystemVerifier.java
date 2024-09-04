package com.book.util;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemVerifier {

    public static InputVerifier emptyVerify(String fieldName,Integer minLen,Integer maxLen){
        return new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                input.setVerifyInputWhenFocusTarget(false);
                String text = ((JTextComponent)input).getText();

                int len = text.trim().length();
                if(len == 0){
                    JOptionPane.showMessageDialog(null,fieldName + "Cannot be empty","System Prompt",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if(minLen!= null && len < minLen){
                    JOptionPane.showMessageDialog(null,fieldName + "Cannot be less than the minimum length","System Prompt",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                if(maxLen != null && len > maxLen){
                    JOptionPane.showMessageDialog(null,fieldName + "Cannot be larger than the maximum length","System Prompt",JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                return true;
            }
        };
    }
}

