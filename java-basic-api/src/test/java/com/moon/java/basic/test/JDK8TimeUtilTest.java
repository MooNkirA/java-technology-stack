package com.moon.java.basic.test;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Java 8 推出了全新的日期时间API。新API基于ISO标准日历系统，java.time包下的所有类都是不可变类型而且线程安全。
 * <p>
 * Java 8 以上处理日期、日历和时间的方式一直存在不少问题，将 java.util.Date设定为可变类型，以及SimpleDateFormat的非线程安全使其应用非常受限。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-7-12 21:59
 * @description
 */
public class JDK8TimeUtilTest {

    /* Java 8中获取今天的日期、年、月、日信息 */
    @Test
    public void localDateTest() {
        // 通过静态工厂方法LocalDate.now()获取当天日期
        LocalDate now = LocalDate.now();
        System.out.println("今天的日期：" + now); // 今天的日期：2020-07-12

        // 获取年信息
        int year = now.getYear();
        // 获取月信息（值为1~12），注：与Date类不一样，Date获取的月份是从0开始
        int month = now.getMonthValue();
        // 获取日信息
        int day = now.getDayOfMonth();

        System.out.println("year:" + year); // year:2020
        System.out.println("month:" + month); // month:7
        System.out.println("day:" + day); // day:12
    }

    /* Java 8中处理特定日期 */
    @Test
    public void localDatGetAappointedDayTest() {
        // 工厂方法LocalDate.of()可以创建任意日期，该方法需要传入年、月、日做参数，返回对应的LocalDate实例。
        LocalDate date = LocalDate.of(2020, 7, 18);
        System.out.println("指定日期:" + date); // 2020-07-18
    }

    /* Java 8中判断两个日期是否相等 */
    @Test
    public void localDatEqualsTest() {
        // 工厂方法LocalDate.of()可以创建任意日期，该方法需要传入年、月、日做参数，返回对应的LocalDate实例。
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2020, 7, 13);

        // 直接调用LocalDate对象的equals()方法判断两个日期是否相等
        if (now.equals(date)) {
            System.out.println("两个日期相等");
        } else {
            System.out.println("两个日期不相等");
        }
    }

    /*
     * Java 8中检查是否周期性日期事件
     *  MonthDay对象：用于每年重复周期性事件，即月+日，如生日、节日等
     *  YearMonth对象：用于，还可以用这个类得到当月共有多少天。
     */
    @Test
    public void monthDayAndYearMonthtest() {
        /* MonthDay对象测试部分 */
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2020, 7, 12);

        // 通过静态工厂方法MonthDay.of(Month month, int dayOfMonth)，获取指定月与日的MonthDay对象
        MonthDay birthday = MonthDay.of(date.getMonth(), date.getDayOfMonth());
        // 通过静态方法MonthDay.from(TemporalAccessor temporal)，获取指定某年的的MonthDay对象
        MonthDay currentMonthDay = MonthDay.from(now);

        // 比较两个MonthDay对象
        if (currentMonthDay.equals(birthday)) {
            System.out.println("是你的生日");
        } else {
            System.out.println("你的生日还没有到");
        }

        /* YearMonth对象测试部分 */
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth()); // Days in month year 2020-07: 31
        YearMonth creditCardExpiry = YearMonth.of(2020, Month.JULY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry); // Your credit card expires on 2020-07

        // YearMonth实例的lengthOfMonth()方法可以返回当月的天数，在判断2月有28天还是29天时非常有用
        YearMonth yearMonth = YearMonth.of(2020, Month.FEBRUARY);
        if (yearMonth.lengthOfMonth() == 29) {
            System.out.println(yearMonth.getYear() + "是闰年");
        } else {
            System.out.println(yearMonth.getYear() + "非闰年");
        }
    }

    /* Java 8中检查是否闰年 */
    @Test
    public void isLeapYeartest() {
        // 除了通过YearMonth实例的lengthOfMonth()返回的天数判断是否闰年，还可以使用LocalDate的isLeapYear()方法直接判断是否为闰年
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.printf("%d is Leap year", today.getYear());
        } else {
            System.out.printf("%d is not a Leap year", today.getYear());
        }
    }

    /* Java 8中获取当前时间LocalTime */
    @Test
    public void localTimetest() {
        // 通过LocalTime.now()获取当前时间，但只包含时间信息，没有日期
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:" + time); // 23:17:31.130
    }

    /* LocalTime计算时间与LocalDate计算日期测试 */
    @Test
    public void computeTimeAndDatetest() {
        // 通过LocalTime.now()获取当前时间，但只包含时间信息，没有日期
        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间:" + time); // 23:30:22.677

        // 通过增加小时、分、秒来计算将来的时间。Java 8除了不变类型和线程安全的好处之外，还提供如更好的plusHours()方法替换add()，并且是兼容的。
        // 注意，这些方法返回一个全新的LocalTime实例，由于其不可变性，返回后一定要用变量赋值。
        LocalTime plusTime = time.plusHours(3);
        System.out.println("3个小时后的时间为:" + plusTime); // 02:30:22.677

        LocalTime minusTime = time.minusHours(2);
        System.out.println("2个小时前的时间为:" + minusTime); // 21:30:22.677

        // LocalDate日期不包含时间信息
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期为:" + today); // 2020-07-12

        // 通过使用LocalDate的plus()方法来增加天、周、月，ChronoUnit类声明了这些时间单位。由于LocalDate也是不变类型，返回后一定要用变量赋值。
        LocalDate plusDate = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为:" + plusDate); //  2020-07-19

        LocalDate minusDate = today.minus(3, ChronoUnit.DAYS);
        System.out.println("3天前的日期为:" + minusDate); // 2020-07-09

        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期 : " + previousYear); // 2019-07-12

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:" + nextYear); // 2021-07-12

        /* 注：可以用同样的方法增加（或减少）1个月、1年、1小时、1分钟甚至一个世纪 */
    }

    /*
     * 计算两个日期之间的天数、周数或月数
     *      在Java 8中可以用java.time.Period类来做计算。
     */
    @Test
    public void computeTimeDifferencetest() {
        LocalDate today = LocalDate.now();
        System.out.println("Today is : " + today); // 2020-07-13
        LocalDate dateToCompute = LocalDate.of(2021, 12, 14);

        // 计算两个日期的差值
        Period periodBetweenTwoDate = Period.between(today, dateToCompute);
        // 输出结果为：1。getYears()计算的差值直接为年份数相减
        System.out.println("Years left between today and dateToCompute : " + periodBetweenTwoDate.getYears());
        // 输出结果为：5。getMonths()计算的差值直接为月份数相减，年不在计算范围内
        System.out.println("Months left between today and dateToCompute : " + periodBetweenTwoDate.getMonths());
        // 输出结果为：1。getDays()计算的差值直接为天数相减，月与年不在计算范围内
        System.out.println("Days left between today and dateToCompute : " + periodBetweenTwoDate.getDays());
    }

    /*
     * Java 8的Clock时钟类
     *      Java 8增加了一个 Clock 时钟类用于获取当时的时间戳，或当前时区下的日期时间信息。
     *      JDK8以后，可以用 Clock 对象相应的方法替换 System.currentTimeInMillis() 和 TimeZone.getDefault() 。
     */
    @Test
    public void clockTest() {
        // Returns the current time based on your system clock and set to UTC.（根据您的系统时钟返回当前时间，并将其设置为UTC。）
        Clock clock = Clock.systemUTC();
        // 获取当前时间的毫秒值, 相关于JDK8以前的System.currentTimeInMillis()方法
        System.out.println("Clock的millis()方法获取的毫秒值: " + clock.millis()); // 1594568628639
        System.out.println("System.currentTimeInMillis()的毫秒值: " + clock.millis()); // 1594568628639

        // Returns time based on system clock zone（根据系统时钟区域返回时间）
        Clock defaultZoneClock = Clock.systemDefaultZone();
        System.out.println("系统所在时钟区域的Clock的毫秒值: " + defaultZoneClock.millis()); // 1594568628711
    }

    /* 判断日期是早于还是晚于另一个日期 */
    @Test
    public void compareDateTest() {
        // 在Java 8中，LocalDate类的isBefore()和isAfter()用于比较日期。如调用isBefore()方法时，如果给定日期小于当前日期则返回true
        LocalDate today = LocalDate.now();
        System.out.println("当前日期:" + today); // 2020-07-12

        LocalDate tomorrow = LocalDate.of(2020, 7, 13);
        System.out.println(tomorrow + "是否在当前日期之后:" + tomorrow.isAfter(today)); // 2020-07-13是否在当前日期之后:true
        // isAfter()判断是否在指定日期之后
        if (tomorrow.isAfter(today)) {
            System.out.println("之后的日期:" + tomorrow); // 之后的日期:2020-07-13
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        System.out.println(yesterday + "是否在当前日期之后:" + yesterday.isBefore(today)); // 2020-07-11是否在当前日期之后:true
        // isBefore()判断是否在指定日期之前
        if (yesterday.isBefore(today)) {
            System.out.println("之前的日期:" + yesterday); // 之前的日期:2020-07-11
        }
    }

    /*
     * Java 8中处理时区
     *   Java 8不仅分离了日期和时间，也把时区分离出来了。
     *   现在有一系列单独的类如ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。
     *   这在Java 8以前都是 GregorianCalendar类来做的。
     */
    @Test
    public void zoneDateTimeTest() {
        // Date and time with timezone in Java 8（Java 8中带时区的日期和时间）
        ZoneId america = ZoneId.of("America/New_York");  // 指定美国时区
        LocalDateTime localtDateAndTime = LocalDateTime.now(); // 创建时间对象

        // 获取带有指定时区的时间对象（ZonedDateTime）
        ZonedDateTime dateTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateTimeInNewYork); // 2020-07-13T00:02:12.498-04:00[America/New_York]
    }

    /* Instant类获取当前的时间戳 */
    @Test
    public void instantTest() {
        // Instant类有一个静态工厂方法now()会返回当前的时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant : " + timestamp); // 2020-07-13T13:27:27.755Z
        // 调用Instant对象的toEpochMilli()方法，获取毫秒值
        System.out.println("What is value of this instant.toEpochMilli() : " + timestamp.toEpochMilli()); // 1594646847755

        /*
         * Instant类时间戳信息里同时包含了日期和时间，这和java.util.Date很像。
         *      实际上Instant类确实等同于 Java 8之前的Date类，可以使用Date类和Instant类各自的转换方法互相转换
         *      例如：Date.from(Instant) 将Instant转换成java.util.Date，Date.toInstant()则是将Date类转换成Instant类。
         */
        Date dateFromInstant = Date.from(timestamp);
        System.out.println("Instant转成Date：" + dateFromInstant); // Mon Jul 13 21:27:27 CST 2020

        Instant dateToInstant = new Date().toInstant();
        System.out.println("Date转成Instant：" + dateToInstant); // 2020-07-13T13:27:27.843Z
    }

    /*
     * Java 8 预定义的格式化工具去解析或格式化日期、字符串与日期类型互转
     */
    @Test
    public void dateTimeFormatterTest() {
        // 日期字符串
        String dayString = "20200714";

        /*
         * LocalDate的静态方法parse(CharSequence text, DateTimeFormatter formatter)
         *      作用：将字符串转成LocalDate日期对象
         *      参数text：待解析的日期文本
         *      参数formatter：DateTimeFormatter格式器，该格式器有一些静态属性为指定解析时日期的格式
         */
        LocalDate dateFormatted = LocalDate.parse(dayString, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("字符串 '%s' 格式化后的日期为：%s%n", dayString, dateFormatted); // 字符串 '20200714' 格式化后的日期为：2020-07-14

        /* 字符串与日期类型互转测试 */
        // 获取日期对象
        LocalDateTime now = LocalDateTime.now();
        // 创建转换格式器，指定日期对象转成字符串时的格式
        DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // 日期对像转字符串
        String localDateTimeToString = now.format(dateToStrFormatter);
        System.out.printf("LocalDateTime对象 '%s' 转字符串 '%s'%n", now, localDateTimeToString); // LocalDateTime对象 '2020-07-13T22:14:57.281' 转字符串 '2020/07/13 22:14:57'

        // 待转换的日期字符串
        String dateString = "2020-08-01 14:13:12";
        // 创建转换格式器，指定字符串转日期对象时，字符串的格式
        DateTimeFormatter strToDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // 字符串转日期对象
        LocalDate stringToLocalDate = LocalDate.parse(dateString, strToDateFormatter);
        LocalDateTime stringToLocalDateTime = LocalDateTime.parse(dateString, strToDateFormatter);
        System.out.printf("字符串 '%s' 转LocalDate对象 '%s'%n", dateString, stringToLocalDate); // 字符串 '2020-08-01 14:13:12' 转LocalDate对象 '2020-08-01'
        System.out.printf("字符串 '%s' 转LocalDateTime对象 '%s'%n", dateString, stringToLocalDateTime); // 字符串 '2020-08-01 14:13:12' 转LocalDateTime对象 '2020-08-01T14:13:12'
    }


}
