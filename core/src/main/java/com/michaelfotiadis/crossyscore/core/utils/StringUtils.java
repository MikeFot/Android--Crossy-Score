package com.michaelfotiadis.crossyscore.core.utils;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.michaelfotiadis.crossyscore.core.utils.stream.StreamUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@SuppressWarnings("unused")
public final class StringUtils {

    private static final String NULL = "null";

    private StringUtils() {
        // DO NOT INSTANTIATE
    }

    public static String collectionToString(final int... array) {
        final String[] strArr = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            strArr[i] = String.valueOf(array[i]);
        }

        return collectionToString(strArr);
    }

    public static String collectionToString(final String... array) {
        return collectionToString(Arrays.asList(array));
    }

    public static String collectionToString(final Collection<String> array) {
        final StringBuilder builder = new StringBuilder();
        builder.append('[');

        boolean isFirst = true;

        for (final String s : array) {
            if (!isFirst) {
                builder.append(',');
            } else {
                isFirst = false;
            }
            builder.append(s);
        }

        builder.append(']');
        return builder.toString();
    }

    public static String[] concatArrays(final String[] A, final String[] B) {
        if (A == null) {
            return B;
        }

        if (B == null) {
            return A;
        }

        final int aLen = A.length;
        final int bLen = B.length;
        final String[] C = new String[aLen + bLen];
        System.arraycopy(A, 0, C, 0, aLen);
        System.arraycopy(B, 0, C, aLen, bLen);
        return C;
    }

    public static int countMatches(final String string, final String target) {
        return string.length() - string.replace(target, "").length();
    }

    public static void dumpBundle(final Bundle b) {
        final Set<String> keys = b.keySet();

        for (final String key : keys) {
            final Object obj = b.get(key);
            final String value = obj == null ? NULL : String.valueOf(obj.toString());

            SdkLog.d("^ PARCEL: KEY='" + key + "', VALUE=" + value);
        }
    }

    public static String fromStream(final InputStream is) throws IOException {
        if (is != null) {
            final Writer writer = new StringWriter();

            final char[] buffer = new char[1024];
            try {
                final Reader reader = new BufferedReader(new InputStreamReader(is, SdkConstants.ENCODING_UTF8));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                StreamUtils.close(is);
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public static String getHumanReadableByteCount(final long bytes, final boolean si) {
        final int unit = si ? 1000 : 1024;

        if (bytes < unit) {
            return bytes + " B";
        }

        final int exp = (int) (Math.log(bytes) / Math.log(unit));
        final String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");

        return String.format(Locale.US, "%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static String getHumanReadableTime(final long ms) {
        final int seconds = (int) ((ms / 1000) % 60);
        final int minutes = (int) (((ms / 1000) / 60) % 60);
        final int hours = (int) ((((ms / 1000) / 60) / 60) % 24);

        final String sec;
        final String min;
        final String hrs;
        if (seconds < 10) {
            sec = "0" + seconds;
        } else {
            sec = "" + seconds;
        }
        if (minutes < 10) {
            min = "0" + minutes;
        } else {
            min = "" + minutes;
        }
        if (hours < 10) {
            hrs = "0" + hours;
        } else {
            hrs = "" + hours;
        }

        if (hours == 0) {
            return min + ":" + sec;
        } else {
            return hrs + ":" + min + ":" + sec;
        }
    }

    public static String getIntentExtrasAsString(final Intent intent) {
        if (intent == null) {
            return "Intent is null!";
        }
        final Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return "Intent extras are null!";
        }

        final StringBuilder sb = new StringBuilder();

        for (final String key : bundle.keySet()) {
            final Object value = bundle.get(key);
            final String text = value == null ? "" : value.toString();
            final String clazz = value == null ? NULL : value.getClass().getName();
            sb.append(String.format("Intent data ['%s', '%s', '%s']\n",
                    key,
                    text,
                    clazz));
        }

        return sb.toString().trim();
    }

    public static String getStringFromFile(final String filePath) throws Exception {
        final File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File '" + filePath + "' does not exist");
        }

        if (file.isDirectory()) {
            throw new IllegalArgumentException("File '" + filePath + "' must not be a directory");
        }

        final FileInputStream fin = new FileInputStream(file);
        final String ret = StreamUtils.convertStreamToString(fin);
        //Make sure you close all streams.
        StreamUtils.close(fin);
        return ret;
    }

    public static boolean has(final TextView textView) {
        if (textView == null) {
            return false;
        }
        if (textView.getText() == null) {
            return false;
        }

        try {
            return has(textView.getText().toString());
        } catch (final NullPointerException e) {
            return false;
        }
    }

    public static boolean has(final String text) {
        if (text == null) {
            return false;
        }
        if (text.length() < 1) {
            return false;
        }

        //noinspection RedundantIfStatement
        if (text.trim().length() > 0 && !(NULL.equalsIgnoreCase(text.trim()))) {
            return true;
        } else {
            return false;
        }
    }

    public static String padLeft(final String s, final int n) {
        return String.format("%1$" + n + "s", s);
    }

    public static String padRight(final String s, final int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String pickFirstNonEmpty(final String... strings) {
        for (final String string : strings) {
            if (StringUtils.has(string)) {
                return string;
            }
        }

        return null;
    }

    public static String readHtml(final String remoteUrl) {
        SdkLog.d("Looking for path " + remoteUrl);
        String out = "";
        BufferedReader in = null;
        try {
            final URL url = new URL(remoteUrl);
            SdkLog.d("URL is " + url);
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                SdkLog.d(str);
                out += str;
            }
        } catch (final IOException e) {
            SdkLog.e(e.getLocalizedMessage());
        } finally {
            StreamUtils.close(in);
        }
        return out;
    }

    public static void replaceAll(final StringBuilder builder, final String from, final String to) {
        int index = builder.indexOf(from);
        while (index != -1) {
            builder.replace(index, index + from.length(), to);
            index += to.length(); // Move to the end of the replacement
            index = builder.indexOf(from, index);
        }
    }

    public static String titleCase(final String string) {
        String result = "";
        for (int i = 0; i < string.length(); i++) {
            final String next = string.substring(i, i + 1);
            if (i == 0) {
                result += next.toUpperCase();
            } else {
                result += next.toLowerCase();
            }
        }
        return result;
    }

    public static InputStream toStream(final String string, final String encoding) {
        if (has(encoding)) {
            try {
                return new ByteArrayInputStream(string.getBytes(encoding));
            } catch (final UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return new ByteArrayInputStream(string.getBytes());
        }
    }

    public static String toString(final Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    public static List<String> toStringList(final String source, final String delimiter) {
        final List<String> methodResult = new ArrayList<>();
        if (source != null) {
            final String[] bits = source.split(delimiter);
            Collections.addAll(methodResult, bits);
        }
        return methodResult;
    }

    /**
     * Trims the given string from the start end of the input if it is found there
     *
     * @param input      The string to trim
     * @param trimString The string to remove from the start and end
     * @return The trimmed string
     */
    public static String trim(final String input, final String trimString) {
        String output = input;

        if (output.startsWith(trimString)) {
            output = output.substring(1);
        }

        if (output.endsWith(trimString)) {
            output = output.substring(0, input.length() - (trimString.length() + 1));
        }

        return output;
    }

    @SuppressWarnings("deprecation")
    public static String urlEncode(final String text) {

        try {
            return URLEncoder.encode(text, SdkConstants.ENCODING_UTF8);
        } catch (final UnsupportedEncodingException e) {
            SdkLog.e("^ STRING: UnsupportedEncodingException while URL encoding text: " + e.getMessage());
            e.printStackTrace();
            return URLEncoder.encode(text);
        }
    }

}