package tw.com.lixin.wm_casino.tools;

import java.util.Locale;


public class LocaleUtils {



    public static Locale sLocale;

    public static void setLocale(Locale locale) {
        sLocale = locale;
        if(sLocale != null) {
            Locale.setDefault(sLocale);
        }
    }

}
