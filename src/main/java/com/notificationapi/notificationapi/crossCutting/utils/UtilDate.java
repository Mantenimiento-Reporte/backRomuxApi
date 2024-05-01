package com.notificationapi.notificationapi.crossCutting.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class UtilDate {
        private static final String DEFAULT_VALUE_DATE_AS_STRING = "0001-01-01";
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        private static final LocalDate DEFAULT_VALUE_DATE;

        static {
            DEFAULT_VALUE_DATE = LocalDate.parse(DEFAULT_VALUE_DATE_AS_STRING, FORMATTER);
        }

        public static LocalDate fromStringToLocalDate(final String dateValue) {
            return LocalDate.parse(dateValue, FORMATTER);
        }

        public static LocalDate getDefaultValueDate() {
            return DEFAULT_VALUE_DATE;
        }
    }


