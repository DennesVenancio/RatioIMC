package kurgomobi.ratioimc.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
 
public abstract class Mask {
    public static String unmask(String s) {
        return s.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[/]", "").replaceAll("[(]", "")
                .replaceAll("[)]", "");
    }
 
    public static TextWatcher insert(final String mask, final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
 
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                String str = Mask.unmask(s.toString());
                String mask = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() > old.length()) {
                        mask += m;
                        continue;
                    }
                    try {
                        mask += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mask);
                ediTxt.setSelection(mask.length());
            }
 
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
            }
 
            public void afterTextChanged(Editable s) {
            }
        };
    }
}