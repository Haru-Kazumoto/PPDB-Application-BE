package dev.pack.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class StringUtils {

   public  static Date toDate(String date) throws ParseException {
       return new SimpleDateFormat("yyyy-MM-dd").parse(date);
   }
}
