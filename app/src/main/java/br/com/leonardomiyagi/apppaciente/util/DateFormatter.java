package br.com.leonardomiyagi.apppaciente.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lmiyagi on 6/29/17.
 */

public class DateFormatter {

    public static String formatToApiDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
    }

    public static String getDateOnly(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static String getTimeOnly(Date date) {
        return new SimpleDateFormat("HH:mm").format(date);
    }

    public static Date mergeDateTime(String date, String time) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date + " " + time);
    }
}
