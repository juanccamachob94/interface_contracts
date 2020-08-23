package mx.com.upax.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import static java.util.Calendar.*;
import java.util.Date;

public class DateTime {
  public static int getDiffYears(Date first, Date last) {
      Calendar a = getCalendar(first);
      Calendar b = getCalendar(last);
      int diff = b.get(YEAR) - a.get(YEAR);
      if (a.get(MONTH) > b.get(MONTH) ||
          (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
          diff--;
      }
      return diff;
  }

  public static Calendar getCalendar(Date date) {
      Calendar cal = Calendar.getInstance(Locale.US);
      cal.setTime(date);
      return cal;
  }

  public static Date today() {
    return new Date();
  }

  public static Date sanitizedDate(String strDate) {
    try {
      return (new SimpleDateFormat("yyyy-MM-dd")).parse(strDate);
    } catch(Exception e) {
      return null;
    }
  }

  public static String yyyymmddDate(Date date) {
    try {
      return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    } catch(Exception e) {
      return null;
    }
  }
}
