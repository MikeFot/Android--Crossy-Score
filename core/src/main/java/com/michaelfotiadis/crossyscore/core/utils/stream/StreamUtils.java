package com.michaelfotiadis.crossyscore.core.utils.stream;

import com.michaelfotiadis.crossyscore.core.utils.SdkConstants;
import com.michaelfotiadis.crossyscore.core.utils.SdkLog;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public final class StreamUtils {

    private static Charset CHARSET = Charset.forName(SdkConstants.ENCODING_UTF8);

    private StreamUtils() {
        // DO NOT INSTANTIATE
    }

    public static String convertByteBufferToString(final ByteBuffer buffer) {
        //noinspection UnusedAssignment
        String data = "";
        try {
            final int old_position = buffer.position();
            data = CHARSET.newDecoder().decode(buffer).toString();
            // reset buffer's position to its original so it is not altered:
            buffer.position(old_position);
        } catch (final Exception e) {
            e.printStackTrace();
            return "";
        }
        return data;
    }

    public static String convertStreamToString(final FileInputStream is) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        final StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        close(reader);
        return sb.toString();
    }

    public static void close(final Closeable closable) {
        if (closable == null) {
            return;
        }
        try {
            closable.close();
        } catch (final Exception e) {
            SdkLog.e("^ NET: Error while closing closable.", e);
        }
    }

    public static ByteBuffer convertStringToByteBuffer(final String msg) {
        try {
            return CHARSET.newEncoder().encode(CharBuffer.wrap(msg));
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("unused")
    private static String debugBinary(final byte[] actual, final byte[] expected) {
        @SuppressWarnings("StringBufferReplaceableByString")
        final StringBuilder sb = new StringBuilder();

        sb.append("Got '");
        sb.append(byteToBinary(actual[0]));
        sb.append(' ');
        sb.append(byteToBinary(actual[1]));
        sb.append("' Expected '");
        sb.append(byteToBinary(expected[0]));
        sb.append(' ');
        sb.append(byteToBinary(expected[1]));
        sb.append("'");

        return sb.toString();
    }

    private static String byteToBinary(final byte bite) {
        return String.format("%8s", Integer.toBinaryString(bite & 0xFF)).replace(' ', '0');
    }
}