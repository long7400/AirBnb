package com.airbnb.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateTimeUtils {

    private static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern("[yyyy-MM-dd HH:mm:ss]" +
                    "[yyyy-MM-dd'T'HH:mm:ssXXX]" +
                    "[dd/MM/yyyy hh:mm:ss a]" +
                    "[yyyy/MM/dd hh:mm:ss a]" +
                    "[dd-MM-yyyy hh:mm:ss a]" +
                    "[yyyy-MM-dd hh:mm:ss a]" +
                    "[yyyy-MM-dd'T'HH:mm:ss+0000]" +
                    "[yyyy-MM-dd HH:mm:ss.SSSSSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SSSSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SSSS]" +
                    "[yyyy-MM-dd HH:mm:ss.SSS]" +
                    "[" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern() + "]" +
                    "[" + DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.getPattern() + "]" +
                    "[" + DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern() + "]" +
                    "[" + DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.getPattern() + "]" +
                    "[" + DateFormatUtils.ISO_8601_EXTENDED_TIME_TIME_ZONE_FORMAT.getPattern() + "]" +
                    "[" + DateFormatUtils.SMTP_DATETIME_FORMAT.getPattern() + "]"
            );


    public static String getLocalDateTimeWithPattern(LocalDateTime localDateTime, String pattern) {
        if (Objects.isNull(localDateTime)) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isBlank(pattern)) {
            return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getZonedDateTimeWithPattern(String pattern) {
        ZonedDateTime txnDateTimeZone = ZonedDateTime.now(ZoneId.systemDefault());
        return txnDateTimeZone.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parse(String value) {
        return parse(value, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime parse(String value, DateTimeFormatter format) {
        if (Strings.isEmpty(value)) {
            return null;
        }

        return LocalDateTime.parse(value, format);
    }

}