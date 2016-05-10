package com.michaelfotiadis.crossyscore.utils.date;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Calendar;

/**
 *
 */
public class DateUtils {

    public static String getTimeAgoForScore(final Long time) {
        if (time == null) {
            return "Unknown";
        }

        final DateTime end = new DateTime(Calendar.getInstance().getTimeInMillis());
        final DateTime start = new DateTime((time));
        final Interval interval = new Interval(start, end);
        if (interval.toPeriod().getYears() > 1) {
            return (interval.toPeriod().getYears()) + " years ago";
        }
        if (interval.toPeriod().getYears() == 1) {
            return (interval.toPeriod().getYears()) + " year ago";
        }
        if (interval.toPeriod().getMonths() > 1) {
            return (interval.toPeriod().getMonths()) + " months ago";
        }
        if (interval.toPeriod().getMonths() == 1) {
            return (interval.toPeriod().getMonths()) + " month ago";
        }
        if (interval.toPeriod().getWeeks() > 1) {
            return (interval.toPeriod().getWeeks()) + " weeks ago";
        }
        if (interval.toPeriod().getWeeks() == 1) {
            return (interval.toPeriod().getWeeks()) + " week ago";
        }
        if (interval.toPeriod().getDays() > 1) {
            return (interval.toPeriod().getDays()) + " days ago";
        }
        if (interval.toPeriod().getDays() == 1) {
            return (interval.toPeriod().getDays()) + " day ago";
        }
        if (interval.toPeriod().getHours() > 1) {
            return (interval.toPeriod().getHours()) + " hours ago";
        }
        if (interval.toPeriod().getHours() == 1) {
            return (interval.toPeriod().getHours()) + " hour ago";
        }
        if (interval.toPeriod().getMinutes() > 1) {
            return (interval.toPeriod().getMinutes()) + " minutes ago";
        }
        if (interval.toPeriod().getMinutes() == 1) {
            return (interval.toPeriod().getMinutes()) + " minute ago";
        }
        return ("Just now");
    }

}
