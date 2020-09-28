package com.moon.java.jdk8time;

import org.junit.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * JDK 8的日期和时间类 LocalDate、LocalTime、LocalDateTime 的基础使用示例
 * <p>
 * Java 8 推出了全新的日期时间API。新API基于ISO标准日历系统，java.time包下的所有类都是不可变类型而且线程安全。
 * <p>
 * Java 8 以上处理日期、日历和时间的方式一直存在不少问题，将 java.util.Date设定为可变类型，以及SimpleDateFormat的非线程安全使其应用非常受限。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-9-28 15:09
 * @description
 */
public class Demo01LocalDateTime {

    // ***************************************************
    //     LocalDate、LocalTime、LocalDateTime基础使用
    // ***************************************************

    /* LocalDate类: 表示日期，有年月日信息 */
    @Test
    public void localDateTest() {
        // 工厂方法LocalDate.of()可以创建任意日期，该方法需要传入年、月、日做参数，返回对应的LocalDate实例。
        LocalDate date = LocalDate.of(2018, 8, 8);
        System.out.println("指定日期: " + date);

        // 通过静态工厂方法LocalDate.now()获取当天日期
        LocalDate now = LocalDate.now();
        System.out.println("今天的日期：" + now);

        // 获取年信息
        System.out.println("year: " + now.getYear());
        // 获取月信息信息（值为Month的枚举类）
        System.out.println("Month枚举: " + now.getMonth());
        // 获取月信息（值为1~12），注：与Date类不一样，Date获取的月份是从0开始
        System.out.println("month: " + now.getMonthValue());
        // 获取日信息
        System.out.println("day: " + now.getDayOfMonth());
    }

    /* ocalTime: 表示时间，有时分秒的信息 */
    @Test
    public void localTimeTest() {
        // 通过静态工厂方法LocalTime.of()获取指定时间对象
        LocalTime time = LocalTime.of(13, 26, 39);
        System.out.println("指定时间time: " + time);

        // 通过静态工厂方法LocalTime.now()获取当前时间
        LocalTime now = LocalTime.now();
        System.out.println("当前时间: " + now);

        System.out.println("hour: " + now.getHour()); // 获取时
        System.out.println("minute: " + now.getMinute()); // 获取分
        System.out.println("second: " + now.getSecond()); // 获取秒
        System.out.println("nano: " + now.getNano()); // 获取毫秒
    }

    /* LocalDateTime: 相当于 LocalDate + LocalTime 具有年月日 时分秒的信息 */
    @Test
    public void localDateTimeTest() {
        // 通过静态工厂方法LocalDateTime.of()获取指定日期时间对象
        LocalDateTime dateTime = LocalDateTime.of(2018, 7, 12, 13, 28, 59);
        System.out.println("指定的日期时间: " + dateTime);

        // 通过静态工厂方法LocalDateTime.now()获取当前日期时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前的日期时间: " + now);

        System.out.println("year: " + now.getYear()); // 获取年
        System.out.println("month: " + now.getMonthValue()); // 获取月
        System.out.println("day: " + now.getDayOfMonth()); // 获取日
        System.out.println("hour: " + now.getHour()); // 获取时
        System.out.println("minute: " + now.getMinute()); // 获取分
        System.out.println("second: " + now.getSecond()); // 获取秒
    }

    // ***************************************************
    //   LocalDate、LocalTime、LocalDateTime日期修改与计算
    // ***************************************************
    @Test
    public void computeLocalDateTimeTest() {
        // LocalDate、LocalTime、LocalDateTime都同样的修改方式，调用withXxxx方法修改相应的属性
        LocalDateTime now = LocalDateTime.now();
        // 修改当前时间的年属性，修改返回新的日期时间对象，不会影响原日期时间对象
        LocalDateTime dateTime = now.withYear(2222);
        System.out.println("dateTime = " + dateTime);
        System.out.println("now == dateTime: " + (now == dateTime)); // false

        /*
         * 增加或减去日期、时间
         *   plusXxxx: 增加指定的时间
         *   minusXxxx: 减去指定的时间
         */
        // 经过测试，增加指定时间后，如果日期出现跨天的话，日期也会增加
        System.out.println("当前时间：" + now + " ，加上2年：" + now.plusYears(2));
        System.out.println("当前时间：" + now + " ，加上5月：" + now.plusMonths(5));
        System.out.println("当前时间：" + now + " ，加上20天：" + now.plusDays(20));
        System.out.println("当前时间：" + now + " ，加上10小时：" + now.plusHours(10));
        System.out.println("当前时间：" + now + " ，加上20分钟：" + now.plusMinutes(20));
        System.out.println("当前时间：" + now + " ，加上30秒：" + now.plusSeconds(30));
        // 经过测试，减去指定时间后，如果日期出现跨天的话，日期也会减少
        System.out.println("当前时间：" + now + " ，减去2年：" + now.minusYears(2));
        System.out.println("当前时间：" + now + " ，减去5月：" + now.minusMonths(5));
        System.out.println("当前时间：" + now + " ，减去29天：" + now.minusDays(29));
        System.out.println("当前时间：" + now + " ，减去17小时：" + now.minusHours(17));
        System.out.println("当前时间：" + now + " ，减去20分钟：" + now.minusMinutes(20));
        System.out.println("当前时间：" + now + " ，减去30秒：" + now.minusSeconds(30));
    }

    // ***************************************************
    //   LocalDate、LocalTime、LocalDateTime日期比较
    // ***************************************************
    @Test
    public void compareLocalDateTimeTest() {
        LocalDateTime dateTime = LocalDateTime.of(2018, 7, 12, 13, 28, 59);
        LocalDateTime now = LocalDateTime.now();

        // 日期对象a,b 调用 a.isAfter(b)，用于判断a日期是否在b日期之后
        System.out.println(now.isAfter(dateTime)); // true
        // 日期对象a,b 调用 a.isBefore(b)，用于判断a日期是否在b日期之前
        System.out.println(now.isBefore(dateTime)); // false
        // 日期对象a,b 调用 a.isEqual(b)，用于判断a日期是否在b日期相等
        System.out.println(now.isEqual(dateTime)); //  false
    }

    // ************************************************************
    //    LocalDate、LocalTime、LocalDateTime日期时间格式化与解析
    // ************************************************************
    @Test
    public void parseAndFormatLocalDateTimeTest() {
        // JDK8 日期的格式化对象是DateTimeFormatter
        // 创建一个日期时间
        LocalDateTime now = LocalDateTime.now();

        // 创建日期格式化对象，使用JDK自带的时间格式
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 使用DateTimeFormatter类的静态ofPattern()方法，创建日期格式化对象，指定自定义格式
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");

        // 调用日期时间对象的format方法，按指定的格式将日期时间对象转成字符串
        System.out.println("日期对象转成JDK自带ISO_LOCAL_DATE_TIME格式字符串：" + now.format(isoFormatter));
        System.out.println("日期对象转成自定义格式字符串：" + now.format(customFormatter));

        /*
         * 日期时间解析：LocalDateTime类的parse静态方法，将日期时间字符串转成对象
         *      text参数：待转换的日期时间字符串
         *      formatter参数：日期格式化对象DateTimeFormatter，指定字符串相应的格式
         */
        LocalDateTime parseDateTime = LocalDateTime.parse("2020年09月20日 15时16分16秒", customFormatter);
        System.out.println("日期时间字符串转成对象：" + parseDateTime);

        /* 测试多线程下，解析日期是否正常 */
        for (int i = 0; i < 50; i++) {
            new Thread(() ->
                    System.out.println("多线程解析日期 = " + LocalDateTime.parse("2020年09月20日 15时16分16秒", customFormatter))
            ).start();
        }
    }

    // ************************************************************
    //                    Instant类：时间戳
    // ************************************************************
    @Test
    public void instantTest() {
        // Instant内部保存了秒和纳秒(一般不是给用户使用的，而是方便程序做一些统计的)
        // Instant类有一个静态工厂方法now()会返回当前的时间戳
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant : " + timestamp); // 2020-09-28T09:22:33.732Z

        // 增加秒
        Instant plus = timestamp.plusSeconds(20);
        System.out.println("plus = " + plus); // 2020-09-28T09:22:53.732Z

        // 减去秒
        Instant minus = timestamp.minusSeconds(20);
        System.out.println("minus = " + minus); // 2020-09-28T09:22:13.732Z

        // 调用Instant对象的getEpochSecond()方法，获取秒值
        System.out.println("What is value of this instant.getEpochSecond() : " + timestamp.getEpochSecond());
        // 调用Instant对象的getNano()方法，获取纳秒值
        System.out.println("What is value of this instant.getNano() : " + timestamp.getNano());
        // 调用Instant对象的toEpochMilli()方法，获取毫秒值
        System.out.println("What is value of this instant.toEpochMilli() : " + timestamp.toEpochMilli()); // 1594646847755

        /*
         * Instant类时间戳信息里同时包含了日期和时间，这和java.util.Date很像。
         *      实际上Instant类确实等同于 Java 8之前的Date类，可以使用Date类和Instant类各自的转换方法互相转换
         *      例如：Date.from(Instant) 将Instant转换成java.util.Date，Date.toInstant()则是将Date类转换成Instant类。
         */
        Date dateFromInstant = Date.from(timestamp);
        System.out.println("Instant转成Date：" + dateFromInstant); // Mon Sep 28 17:22:33 CST 2020

        Instant dateToInstant = new Date().toInstant();
        System.out.println("Date转成Instant：" + dateToInstant); // 2020-09-28T09:22:33.811Z
    }

    // ************************************************************
    //            Duration类与Period类：计算日期时间差类
    // ************************************************************

    /*
     * 计算两个日期之间的天数、周数或月数
     *      在Java 8中可以用java.time.Period类来做计算。
     * 计算两个时间之间的天数、小时、分钟、秒、毫秒
     *      在Java 8中可以用java.time.Duration类来做计算。
     *  注：都调用以上两个的between()方法实现，都是后面参数减前端的参数
     */
    @Test
    public void computeTimeDifferenceTest() {
        LocalDate today = LocalDate.now();
        System.out.println("Today is : " + today); // 2020-07-13
        LocalDate dateToCompute = LocalDate.of(2021, 12, 14);

        // 计算两个日期的差值
        Period periodBetweenTwoDate = Period.between(today, dateToCompute);
        // getYears()计算的差值直接为年份数相减
        System.out.println("Years left between today and dateToCompute : " + periodBetweenTwoDate.getYears());
        // getMonths()计算的差值直接为月份数相减，年不在计算范围内
        System.out.println("Months left between today and dateToCompute : " + periodBetweenTwoDate.getMonths());
        // getDays()计算的差值直接为天数相减，月与年不在计算范围内
        System.out.println("Days left between today and dateToCompute : " + periodBetweenTwoDate.getDays());

        LocalTime nowTime = LocalTime.now();
        System.out.println("Now is : " + nowTime); // 2020-07-13
        LocalTime timeToCompute = LocalTime.of(20, 12, 14);
        // 计算两个时间的差值
        Duration durationBetweenTwoTime = Duration.between(nowTime, timeToCompute);
        // toDays()计算两个时间相差的天数
        System.out.println("Days left between nowTime and timeToCompute : " + durationBetweenTwoTime.toDays());
        // toHours()计算两个时间相差的小时数
        System.out.println("Hours left between nowTime and timeToCompute : " + durationBetweenTwoTime.toHours());
        // toMinutes()计算两个时间相差的分钟数
        System.out.println("Minutes left between nowTime and timeToCompute : " + durationBetweenTwoTime.toMinutes());
        // toMillis()计算两个时间相差的秒数
        System.out.println("Seconds left between nowTime and timeToCompute : " + durationBetweenTwoTime.toMillis());
        // toNanos()计算两个时间相差的毫秒数
        System.out.println("Milliseconds left between nowTime and timeToCompute : " + durationBetweenTwoTime.toNanos());
    }

    // ************************************************************
    //               TemporalAdjuster类：时间校正器
    // ************************************************************
    @Test
    public void temporalAdjusterTest() {
        // 获取当前日期时间
        LocalDateTime now = LocalDateTime.now();

        // TemporalAdjuster是函数式接口，使用lambda表达式创建TemporalAdjuster的实现
        TemporalAdjuster firstDayOfNextMonth = temporal -> {
            // LocalDateTime实现了 TemporalAdjuster 接口
            LocalDateTime dateTime = (LocalDateTime) temporal;
            // 返回时间校正的规则。示例：下一个月的第一天
            return dateTime.plusMonths(1).withDayOfMonth(1);
        };
        // 调用LocalDateTime对象的with方法，传入自定义时间校正器TemporalAdjuster的lambda实现
        LocalDateTime newDateTime1 = now.with(firstDayOfNextMonth);
        System.out.println("将当前日期时间调整到下一个月的第一天: " + newDateTime1);

        // 除了自定义时间校正器，JDK中TemporalAdjusters类提供了很多时间调整器
        LocalDateTime newDateTime2 = now.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println("将当前日期时间调整到下一个年的第一天: " + newDateTime2);
    }

    // ************************************************************
    //                     日期时间的时区
    // ************************************************************
    /*
     * Java 8中处理时区
     *   Java 8不仅分离了日期和时间，也把时区分离出来了。
     *   现在有一系列单独的类如ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。
     *   这在Java 8以前都是 GregorianCalendar类来做的。
     */
    @Test
    public void zoneDateTimeTest() {
        // 通过ZoneId类的getAvailableZoneIds静态方法，获取所有的时区ID
        // ZoneId.getAvailableZoneIds().forEach(System.out::println);

        // 获取计算机的当前时间。LocalDate、LocalTime、LocalDateTime是不带时区的
        LocalDateTime now = LocalDateTime.now(); // 中国使用的东八区的时区.比标准时间早8个小时
        System.out.println("不带时区的LocalDateTime: " + now); // 2020-09-28T23:37:11.298

        /*
         * 操作带时区的类：ZonedDateTime
         *  now(Clock.systemUTC()): 创建世界标准时间
         */
        ZonedDateTime zonedDateTime1 = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("世界标准时间ZonedDateTime: " + zonedDateTime1); // 2020-09-28T15:37:11.299Z

        // ZonedDateTime.now(): 使用计算机的默认的时区,创建日期时间
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now();
        System.out.println("带时区的当前时间zonedDateTime2: " + zonedDateTime2); // 2020-09-28T23:37:11.299+08:00[Asia/Shanghai]

        // ZonedDateTime.now(ZoneId zone) 使用指定的时区创建日期时间
        ZonedDateTime zonedDateTime3 = ZonedDateTime.now(ZoneId.of("America/Vancouver"));
        System.out.println("使用指定的时区创建日期时间: " + zonedDateTime3); // 2020-09-28T08:37:11.300-07:00[America/Vancouver]

        /*
         * 通过ZonedDateTime对象的withZoneSameInstant方法，修改时区
         *  注：withZoneSameInstant: 即更改时区，也更改时间
         */
        ZonedDateTime withZoneSameInstant = zonedDateTime3.withZoneSameInstant(ZoneId.of("Asia/Shanghai"));
        System.out.println("withZoneSameInstant = " + withZoneSameInstant); // 2020-09-28T23:37:11.300+08:00[Asia/Shanghai]

        /*
         * 通过ZonedDateTime对象的withZoneSameLocal方法，修改时区
         *  注：withZoneSameLocal: 只更改时区,不更改时间
         */
        ZonedDateTime withZoneSameLocal = zonedDateTime3.withZoneSameLocal(ZoneId.of("Asia/Shanghai"));
        System.out.println("withZoneSameLocal = " + withZoneSameLocal); // 2020-09-28T08:37:11.300+08:00[Asia/Shanghai]

        // Date and time with timezone in Java 8（Java 8中带时区的日期和时间）
        ZoneId america = ZoneId.of("America/New_York");  // 指定美国时区
        LocalDateTime localtDateAndTime = LocalDateTime.now(); // 创建时间对象

        // 获取带有指定时区的时间对象（ZonedDateTime）
        ZonedDateTime dateTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("Current date and time in a particular timezone : " + dateTimeInNewYork); // 2020-09-28T23:37:11.313-04:00[America/New_York]
    }

}
