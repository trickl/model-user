package com.trickl.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import lombok.extern.java.Log;

/** A utility class for parsing and formatting dates without a time component. */
@Log
public final class DateNoTimeFormat {

  private static final DateFormat[] SUPPORTED_FORMATS =
      new DateFormat[] {
        DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US),
        DateFormat.getDateInstance(DateFormat.SHORT, Locale.US),
        DateFormat.getDateInstance(DateFormat.LONG, Locale.US),
        DateFormat.getDateInstance(DateFormat.FULL, Locale.US),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US),
        new SimpleDateFormat("yyyy-MM-dd", Locale.US),
        new SimpleDateFormat("yyyyMMdd", Locale.US),
        new SimpleDateFormat("dd-MMM-yyyy", Locale.US),
        new SimpleDateFormat("d MMMM yyyy", Locale.US),
        new SimpleDateFormat("yyyy", Locale.US)
      };

  private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyyMMdd");

  private DateNoTimeFormat() {}

  public static String format(Date date) {
    return DEFAULT_FORMAT.format(date);
  }

  /**
   * Parse a date from a string.
   *
   * @param text The text to parse
   * @return The parsed date
   * @throws ParseException If the date cannot be parsed
   */
  public static Date parse(String text) throws ParseException {
    Date date = null;
    String value = text;

    // Replace non-breaking space with a breaking space
    value = value.replace('\u00A0', ' ');

    for (DateFormat format : SUPPORTED_FORMATS) {
      format.setLenient(true);
      ParsePosition parsePosition = new ParsePosition(0);

      // Won't throw a parse exception, just returns null
      date = (Date) format.parseObject(value, parsePosition);

      // Check the date isn't obvious garbage
      // TODO Use an attribute on this class to set these date boundaries
      // rather than hard-code them
      if (new GregorianCalendar(1500, 1, 1).after(date)
          || new GregorianCalendar(2500, 1, 1).before(date)) {
        date = null;
      }

      if (date != null) {
        break;
      }
    }

    if (date == null) {
      throw new RuntimeException("Unable to parse Date '" + value + "'");
    }

    return removeTime(date);
  }

  private static Date removeTime(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }
}
