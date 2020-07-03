package com.company;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.util.Date;

import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;

import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        // java.util.Date
            // -> not thread safe!
            // -> java.time.* immutable & has no setter methods
            // 1. 1990 ->

        // java.text.DateFormat | java.textSimpleDataFormat | java.util.Calendar
            // -> Poor time zone handling
            // -> write less code

        timeAPIDemo();
        ChromoUnitsDemo();
        ZonedDateTimeDemo();
        TemporalAdjustersDemo();
        FormattingDatesDemo();

    }

    public static void timeAPIDemo() {
        // What's the current time / date?
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("Current DateTime: " + currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println("Date: " + date1);

        LocalTime time1 = LocalTime.parse("20:15:00");
        System.out.println( time1 );

        Month month = currentTime.getMonth();
        int day = currentTime.getDayOfMonth();
        int minutes = currentTime.getMinute();

        LocalDate xmas = LocalDate.of(2020, Month.DECEMBER, 24);
        System.out.println("XMAS is on " + xmas);

        LocalDateTime dummy = currentTime.withDayOfMonth(10).withYear(2021);
    }

    public static void ChromoUnitsDemo() {
        LocalDate  today = LocalDate.now();

        // add 1 week to the current date time
        LocalDate nextWeek =  today.plus(1, ChronoUnit.WEEKS);

        // add 1 week to the current date time
        LocalDate nextMonth =  today.plus(1, ChronoUnit.MONTHS);


    }

    public static void ZonedDateTimeDemo() {
        // Date Java 1.7
        Date currentDate = new Date();
        System.out.println("Current date " + currentDate);

        // Instant i.e. currrent date in milliseconds
        Instant now = currentDate.toInstant();
        ZoneId currentZone = ZoneId.systemDefault();

        // +---------- NEW API
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, currentZone);
        System.out.println("Local date: " + localDateTime);
    }

    public static void TemporalAdjustersDemo() {
        LocalDate date = LocalDate.now();
        // Get the current date
        System.out.println("Current date: " + date);

        // get the next tuesday
        LocalDate nextFriday = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("Next friday: " + nextFriday);

        LocalDate firstInYear = LocalDate.of(date.getYear(), date.getMonth(), 1);
        LocalDate secondSaturday = firstInYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                .with(TemporalAdjusters.next(DayOfWeek.SATURDAY));

        System.out.println( "Second Saturday on : " + secondSaturday);
    }

    public static void FormattingDatesDemo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        System.out.println(formatter.format(zonedDateTime));

        // DateTimeFormatter
        // LocalDate
        // LocalTime

        String result = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(2020,9,5));
        System.out.println(result);
    }
}
