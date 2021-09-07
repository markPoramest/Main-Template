package com.template.freelance.Services;

import java.util.Date;
import java.text.Format;
import java.text.SimpleDateFormat;

public class DateUtility {
    static String getDateString(Date date){
        Format formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
