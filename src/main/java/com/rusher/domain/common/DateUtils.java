package com.rusher.domain.common;

import java.util.*;

public class DateUtils {
    public static final long SECOND = 1000;
    public static final long SECONDS_IN_MINUTE = 60;
    public static final long MINUTE = SECONDS_IN_MINUTE * SECOND;
    public static final long MINUTE_IN_HOUR = 60;
    public static final long HOUR = MINUTE_IN_HOUR * MINUTE;
    public static final long HOURS_IN_DAY = 24;
    public static final long DAY = HOURS_IN_DAY * HOUR;

    public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_FULL_NO_MS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_FULL_T = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    private static final int DATE_CACHE_YEARS = 10;
    private static Date START_DAY;
    private static Date END_DAY;

    private static final List<String> DATE_LIST_OF_STRING = new ArrayList<>();
    private static final List<Date> DATE_LIST_OF_DATE = new ArrayList<>();
    private static final Map<String, Date> DATE_CACHE_BY_STRING = new ConcurrentHashMap<>();
    private static final Map<Long, String> DATE_CACHE_BY_DATE = new ConcurrentHashMap<>();
    private static final Map<Long, Integer> YEAR_CACHE = new ConcurrentHashMap<>();
    private static final Map<Long, Integer> MONTH_CACHE = new ConcurrentHashMap<>();
    private static final Map<Long, Integer> DAY_CACHE = new ConcurrentHashMap<>();
    private static final Map<Long, Week> WEEK_CACHE = new ConcurrentHashMap<>();

    private static DateOperator operator;
    private static boolean test = false;
    private static DateOperator operatorForTestUnregister;
    private static boolean auto = false;

    public static synchronized void registerOperatorForTest(DateOperator operator) {
        DateUtils.operator = operator;
        if (operator != null) {
            DateUtils.test = true;
            START_DAY = new Date();
            END_DAY = START_DAY;
        }

        if (operator == null && operatorForTestUnregister != null) {
            DateUtils.operator = DateUtils.operatorForTestUnregister;
            DateUtils.test = false;
        }
    }

    public static synchronized void registerOperator(DateOperator operator) {
        if (operator == null) {
            throw new NullPointerException();
        }
        if (DateUtils.operator == operator) {
            return;
        }
        if (DateUtils.operator != null) {
            if (!auto) {
                throw new DateException("duplicate date operator");
            }
        }
        DateUtils.operator = operator;
        DateUtils.operatorForTestUnregister = operator;
        DateUtils.test = false;
        DateUtils.auto = false;
        initDates();
    }

    private static DateOperator getOperator() {
        if (operator != null) {
            return operator;
        }

        synchronized (DateUtils.class) {
            if (operator != null) {
                return operator;
            }

            try {
                Class.forName("com.derbysoft.warrior.common.date.SystemDateOperator");
                DateUtils.auto = true;
            } catch (ClassNotFoundException e) {
                ExceptionUtils.throwRuntimeExceptionOrError(e);
            }

            return operator;
        }
    }

    private static synchronized void initDates() {
        if (START_DAY != null) {
            return;
        }
        START_DAY = operator.parse((operator.year(new Date()) - 2) + "-01-01", DATE_FORMAT_YMD);
        END_DAY = operator.parse((operator.year(new Date()) + DATE_CACHE_YEARS) + "-12-31", DATE_FORMAT_YMD);
        for (long time = START_DAY.getTime(); time <= END_DAY.getTime(); time += DAY) {
            final Date date = toDay(new Date(time));
            year(date);
            month(date);
            day(date);
            week(date);

            final String stringDate = format(date);
            DATE_LIST_OF_STRING.add(stringDate);
            DATE_LIST_OF_DATE.add(date);
        }
    }

    private static String getFormatPattern(String date) {
        if (date.length() == 10 && date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return DATE_FORMAT_YMD;
        } else if (date.length() == 19 && date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            return DATE_FORMAT_FULL_NO_MS;
        } else if (date.length() == 23 && date.charAt(10) == ' ' && date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}")) {
            return DATE_FORMAT_FULL;
        } else if (date.length() == 23 && date.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}")) {
            return DATE_FORMAT_FULL_T;
        } else {
            throw new IllegalArgumentException("Illegal date format:" + date);
        }
    }

    private static List<String> getDatesOfString(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            return new ArrayList<>();
        }

        final int startIndex = fromIndex < 0 ? 0 : fromIndex;
        final int endIndex = toIndex >= DATE_LIST_OF_STRING.size() ? DATE_LIST_OF_STRING.size() - 1 : toIndex;

        final List<String> list = new ArrayList<>(toIndex - fromIndex + 1);
        for (int i = fromIndex; i < startIndex; i++) {
            list.add(format(addDays(START_DAY, i)));
        }
        for (int i = startIndex; i <= endIndex; i++) {
            list.add(DATE_LIST_OF_STRING.get(i));
        }
        for (int i = endIndex + 1; i <= toIndex; i++) {
            list.add(format(addDays(START_DAY, i)));
        }
        return list;
    }

    private static List<Date> getDatesOfDate(int fromIndex, int toIndex) {
        if (toIndex < fromIndex) {
            return new ArrayList<>();
        }

        final int startIndex = fromIndex < 0 ? 0 : fromIndex;
        final int endIndex = toIndex >= DATE_LIST_OF_DATE.size() ? DATE_LIST_OF_DATE.size() - 1 : toIndex;
        final List<Date> list = new ArrayList<>(toIndex - fromIndex + 1);
        for (int i = fromIndex; i < startIndex; i++) {
            list.add(addDays(START_DAY, i));
        }
        for (int i = startIndex; i <= endIndex; i++) {
            list.add(DATE_LIST_OF_DATE.get(i));
        }
        for (int i = endIndex + 1; i <= toIndex; i++) {
            list.add(addDays(START_DAY, i));
        }
        return list;
    }

    public static Set<String> getDates(Set<String> dates, String startDate, String endDate) {
        final Date start = parse(startDate);
        final Date end = parse(endDate);
        final Set<String> rs = new HashSet<>(dates.size());
        for (String date : dates) {
            final Date d = parse(date);
            if (!d.before(start) && !d.after(end)) {
                rs.add(date);
            }
        }
        return rs;
    }

    public static Set<Date> getDates(Set<Date> dates, Date startDate, Date endDate) {
        final Set<Date> rs = new HashSet<>(dates.size());
        for (Date d : dates) {
            if (!d.before(startDate) && !d.after(endDate)) {
                rs.add(d);
            }
        }
        return rs;
    }

    public static List<String> getDates(int days, String toDate) {
        if (days < 1) {
            return new ArrayList<>();
        }

        if (days == 1) {
            return ListUtils.newArrayList(toDate);
        }

        final int toIndex = (int) nights(START_DAY, parse(toDate));
        final int fromIndex = toIndex - days + 1;
        return getDatesOfString(fromIndex, toIndex);
    }

    public static List<String> getDates(String fromDate, int days) {
        if (days < 1) {
            return new ArrayList<>();
        }

        if (days == 1) {
            return ListUtils.newArrayList(fromDate);
        }

        final int fromIndex = (int) nights(START_DAY, parse(fromDate));
        final int toIndex = fromIndex + days - 1;
        return getDatesOfString(fromIndex, toIndex);
    }

    public static List<String> getDates(String fromDate, String toDate) {
        final int fromIndex = (int) nights(START_DAY, parse(fromDate));
        final int toIndex = (int) nights(START_DAY, parse(toDate));
        return getDatesOfString(fromIndex, toIndex);
    }

    public static List<Date> getDates(int days, Date toDate) {
        if (days < 1) {
            return new ArrayList<>();
        }

        if (days == 1) {
            return ListUtils.newArrayList(toDate);
        }

        final int toIndex = (int) nights(START_DAY, toDate);
        final int fromIndex = toIndex - days + 1;
        return getDatesOfDate(fromIndex, toIndex);
    }

    public static List<Date> getDates(Date fromDate, int days) {
        if (days < 1) {
            return new ArrayList<>();
        }

        if (days == 1) {
            return ListUtils.newArrayList(fromDate);
        }

        final int fromIndex = (int) nights(START_DAY, fromDate);
        final int toIndex = fromIndex + days - 1;
        return getDatesOfDate(fromIndex, toIndex);
    }

    public static List<Date> getDates(Date fromDate, Date toDate) {
        final int fromIndex = (int) nights(START_DAY, fromDate);
        final int toIndex = (int) nights(START_DAY, toDate);
        return getDatesOfDate(fromIndex, toIndex);
    }

    public static boolean isDateFormat(String date) {
        try {
            getFormatPattern(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isDateFormat(String date, String format) {
        try {
            getFormatPattern(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Date today() {
        return getOperator().today();
    }

    public static Date todayUTC() {
        return today(TimeZoneUtils.UTC);
    }

    public static Date today(TimeZone timeZone) {
        return getOperator().today(timeZone);
    }

    public static Date now() {
        return getOperator().now();
    }

    public static Date nowUTC() {
        return now(TimeZoneUtils.UTC);
    }

    public static Date now(TimeZone timeZone) {
        return getOperator().now(timeZone);
    }

    public static Date toDay(Date date) {
        return getOperator().toDay(date);
    }

    public static Date toDate(Date date, TimeZone timeZone) {
        return getOperator().toDate(date, timeZone);
    }

    public static Date toDate(Date date, TimeZone fromTimeZone, TimeZone toTimeZone) {
        return new Date(date.getTime() - (fromTimeZone.getRawOffset() - toTimeZone.getRawOffset()));
    }

    public static long currentTimeMillis() {
        return getOperator().currentTimeMillis();
    }

    public static long currentTimeMillis(TimeZone timeZone) {
        return getOperator().currentTimeMillis(timeZone);
    }

    public static long nights(Date from, Date to) {
        return getOperator().nights(from, to);
    }

    public static long nights(String from, String to) {
        return getOperator().nights(parse(from), parse(to));
    }

    public static long days(Date from, Date to) {
        return nights(from, to) + 1;
    }

    public static long days(String from, String to) {
        return nights(from, to) + 1;
    }

    public static Date addSeconds(Date date, int n) {
        return getOperator().addSeconds(date, n);
    }

    public static Date addMinutes(Date date, int n) {
        return getOperator().addMinutes(date, n);
    }

    public static Date addHours(Date date, int n) {
        return getOperator().addHours(date, n);
    }

    public static String addDays(String date, int n) {
        return format(getOperator().addDays(parse(date), n));
    }

    public static Date addDays(Date date, int n) {
        return getOperator().addDays(date, n);
    }

    public static String nextDay(String date) {
        return addDays(date, 1);
    }

    public static Date nextDay(Date date) {
        return addDays(date, 1);
    }

    public static Date addMonths(Date date, int n) {
        return getOperator().addMonths(date, n);
    }

    public static Date addYears(Date date, int n) {
        return getOperator().addYears(date, n);
    }

    public static Date parse(String date) {
        if (date == null) {
            return null;
        }

        if (test) {
            return getOperator().parse(date, DATE_FORMAT_YMD);
        }

        Date value = DATE_CACHE_BY_STRING.get(date);
        if (value == null) {
            final String formatPattern = getFormatPattern(date);
            if (!formatPattern.equals(DATE_FORMAT_YMD)) {
                return getOperator().parse(date, formatPattern);
            }

            value = getOperator().parse(date, DATE_FORMAT_YMD);
            DATE_CACHE_BY_DATE.put(value.getTime(), date);
            DATE_CACHE_BY_STRING.put(date, value);
        }
        return value;
    }

    public static Date parse(String date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null || (format.length() == DATE_FORMAT_YMD.length() && format.equals(DATE_FORMAT_YMD))) {
            return parse(date);
        }
        return getOperator().parse(date, format);
    }

    public static String format(Date date) {
        if (date == null) {
            return null;
        }

        if (test) {
            return getOperator().format(date, DATE_FORMAT_YMD);
        }

        final long dateTime = toDay(date).getTime();
        String value = DATE_CACHE_BY_DATE.get(dateTime);
        if (value == null) {
            value = getOperator().format(date, DATE_FORMAT_YMD);
            DATE_CACHE_BY_DATE.put(dateTime, value);
            DATE_CACHE_BY_STRING.put(value, new Date(dateTime));
        }
        return value;
    }

    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null || (format.length() == DATE_FORMAT_YMD.length() && format.equals(DATE_FORMAT_YMD))) {
            return format(date);
        }
        return getOperator().format(date, format);
    }

    public static int year(Date date) {
        if (test) {
            return getOperator().year(date);
        }

        final long dateTime = toDay(date).getTime();
        Integer value = YEAR_CACHE.get(dateTime);
        if (value == null) {
            value = getOperator().year(date);
            YEAR_CACHE.put(dateTime, value);
        }
        return value;
    }

    public static int month(Date date) {
        if (test) {
            return getOperator().month(date);
        }

        final long dateTime = toDay(date).getTime();
        Integer value = MONTH_CACHE.get(dateTime);
        if (value == null) {
            value = getOperator().month(date);
            MONTH_CACHE.put(dateTime, value);
        }
        return value;
    }

    public static Week week(Date date) {
        if (test) {
            return getOperator().week(date);
        }

        final long dateTime = toDay(date).getTime();
        Week value = WEEK_CACHE.get(dateTime);
        if (value == null) {
            value = getOperator().week(date);
            WEEK_CACHE.put(dateTime, value);
        }
        return value;
    }

    public static int day(Date date) {
        if (test) {
            return getOperator().day(date);
        }

        final long dateTime = toDay(date).getTime();
        Integer value = DAY_CACHE.get(dateTime);
        if (value == null) {
            value = getOperator().day(date);
            DAY_CACHE.put(dateTime, value);
        }
        return value;
    }

    public static int hour(Date date) {
        return getOperator().hour(date);
    }

    public static int minute(Date date) {
        return getOperator().minute(date);
    }

    public static int second(Date date) {
        return getOperator().second(date);
    }

    public static Date min(Date a, Date b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return a.before(b) ? a : b;
    }

    public static Date max(Date a, Date b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return a.after(b) ? a : b;
    }
}
