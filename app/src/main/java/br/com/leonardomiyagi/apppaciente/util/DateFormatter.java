package br.com.leonardomiyagi.apppaciente.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lmiyagi on 6/29/17.
 */

public class DateFormatter {

    public static String formatToApiDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
    }
}
