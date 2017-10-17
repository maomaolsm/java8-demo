import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by maomao on 17/8/22.
 */
public class JavaLocalDate {

    public void page247() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        LocalDate today = LocalDate.now();

        int year1 = date.get(ChronoField.YEAR);
        int month1 = date.get(ChronoField.MONTH_OF_YEAR);
        int day1 = date.get(ChronoField.DAY_OF_MONTH);

        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate date1 = LocalDate.parse("2014-03-18");
        LocalTime time1 = LocalTime.parse("13:45:20");
        System.out.println(date1 + " and " + time1);

        /**
         * LocalDateTime
         */
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date1, time1);
        LocalDateTime dt3 = date1.atTime(13, 45, 20);
        LocalDateTime dt4 = date1.atTime(time1);
        LocalDateTime dt5 = time1.atDate(date1);

        LocalDate date2 = dt1.toLocalDate();
        LocalTime time2 = dt1.toLocalTime();


    }

    /**
     * 打印解析日期
     */
    public void page255() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("page255 " + s1 + " and " + s2 + " and " + date);//page255 20140318 and 2014-03-18 and 2014-03-18

        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-08-18", DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(date1 + " and " + date2);

        /**
         * 按照某个模式创建DateTimeFormatter
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
        LocalDate date3 = LocalDate.of(2014, 9, 10);
        String formatterDate = date3.format(formatter);
        LocalDate date4 = LocalDate.parse(formatterDate, formatter);
        System.out.println(formatterDate + " and " + date4);

        /**
         * 创建一个本地化的DateTimeFormatter
         */
        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate date5 = LocalDate.of(2017, 1, 2);
        String formattedDate = date5.format(italianFormatter);
        LocalDate date6 = LocalDate.parse(formattedDate, italianFormatter);
        System.out.println(date5 + " and " + formattedDate + " and " + date6);

        /**
         * 构造一个DataTimeFormatter
         */
        DateTimeFormatter italianFormatter1 = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);
        String formattedDate1 = date5.format(italianFormatter1);// 2. gennaio 2017
        System.out.println(formattedDate1);
    }

    /**
     * 处理不同的时区和历法
     */
    public void page256() {

        System.out.println("page 256");

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();
        System.out.println(romeZone + " and " + zoneId);

        /**
         * 为时间点添加时区信息
         */
        LocalDate date = LocalDate.of(2017, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone); // 2017-03-18T00:00+01:00[Europe/Rome]

        LocalDateTime dateTime = LocalDateTime.of(2017, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone); // 2017-03-18T13:45+01:00[Europe/Rome]

        Instant instant = Instant.now(); // 2017-09-22T07:06:51.003Z
        ZonedDateTime zdt3 = instant.atZone(romeZone); // 2017-09-22T09:05:04.639+02:00[Europe/Rome]
        System.out.println(zdt1 + " and " + zdt2 + " and " + zdt3);
        System.out.println(instant);

        Instant instantFromDateTime = dateTime.toInstant(ZoneOffset.of("+02:00"));

        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);

        System.out.println(instantFromDateTime + " and " + timeFromInstant);

    }

    /**
     * 利用和UTC/格林尼治时间的固定偏差计算时区
     */
    public void page257() {
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.MARCH, 18, 13, 45);
        OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(dateTime, newYorkOffset);
        System.out.println(dateTimeInNewYork);
    }

    public static void main(String[] args) {
        JavaLocalDate javaLocalDate = new JavaLocalDate();
        javaLocalDate.page247();
        javaLocalDate.page255();
        javaLocalDate.page256();
        javaLocalDate.page257();
    }
}
