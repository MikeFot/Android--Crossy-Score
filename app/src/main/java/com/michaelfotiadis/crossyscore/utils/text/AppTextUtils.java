package com.michaelfotiadis.crossyscore.utils.text;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public final class AppTextUtils {

    private AppTextUtils() {
    }

    /**
     * VERY UGLY. THIS NEEDS A REGEXP
     *
     * @param text1 The container text
     * @param text2 The text we are looking for
     * @return true if {@param text1} contains {@param text2}
     */
    public static boolean containsIgnoreCase(final String text1, final String text2) {
        return text1.toLowerCase(Locale.US).contains(text2.toLowerCase(Locale.US));
    }

    public static String delimit(final String delimiter, final String... strings) {
        return delimit(delimiter, new ArrayList<>(Arrays.asList(strings)));
    }

    public static String delimit(final String delimiter, final Collection<String> strings) {
        final StringBuilder sb = new StringBuilder();

        boolean firstRun = true;

        for (final String string : strings) {
            if (!firstRun) {
                sb.append(delimiter);
            }

            sb.append(string);
            firstRun = false;
        }
        return sb.toString();
    }

    public static String getFirstNotEmpty(final String... texts) {
        for (final String text : texts) {
            if (!TextUtils.isEmpty(text)) {
                return text;
            }
        }

        return null;
    }

    public static String getHumanReadableByteCount(final long bytes,
                                                   final boolean si) {
        final int unit = si ? 1000 : 1024;
        if (bytes < unit) {
            return bytes + " B";
        } else {
            final int exp = (int) (Math.log(bytes) / Math.log(unit));
            final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
            return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
        }
    }

    public static String getHumanReadableTime(long ms) {
        final long days = TimeUnit.MILLISECONDS.toDays(ms);
        ms -= TimeUnit.DAYS.toMillis(days);
        final long hours = TimeUnit.MILLISECONDS.toHours(ms);
        ms -= TimeUnit.HOURS.toMillis(hours);
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(ms);
        ms -= TimeUnit.MINUTES.toMillis(minutes);
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(ms);

        final StringBuilder sb = new StringBuilder();
        sb.append(days);
        sb.append("d ");
        sb.append(hours);
        sb.append("h ");
        sb.append(minutes);
        sb.append("m ");
        sb.append(seconds);
        sb.append('s');

        return (sb.toString());
    }
}
