package com.michaelfotiadis.crossyscore.core.data.parsers.gson;

import com.google.gson.stream.JsonReader;
import com.michaelfotiadis.crossyscore.core.utils.SdkConstants;
import com.michaelfotiadis.crossyscore.core.utils.stream.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

public final class CoreJsonConverters {

    private CoreJsonConverters() {
        // DO NOT INSTANTIATE
    }

    public static <T> String serialize(final T object) {
        return CoreGson.get().toJson(object);
    }

    public static <T> T deserialize(final InputStream stream, final Type type) throws IOException {
        return deserializeInternal(
                new BufferedReader(
                        new InputStreamReader(
                                stream, SdkConstants.ENCODING_UTF8
                        )
                ),
                type);
    }

    private static <T> T deserializeInternal(final BufferedReader br, final Type type) {
        final JsonReader reader = new JsonReader(br);
        reader.setLenient(false);

        final T result = CoreGson.get().fromJson(reader, type);

        StreamUtils.close(br);

        return result;
    }

    public static <T> T deserialize(final InputStream stream, final Class<T> clazz) throws IOException {
        return deserializeInternal(
                new BufferedReader(
                        new InputStreamReader(
                                stream, SdkConstants.ENCODING_UTF8
                        )
                ),
                clazz);
    }

    private static <T> T deserializeInternal(final BufferedReader br, final Class<T> clazz) {
        final JsonReader reader = new JsonReader(br);
        reader.setLenient(false);

        final T result = CoreGson.get().fromJson(reader, clazz);

        StreamUtils.close(br);

        return result;
    }

    public static <T> T deserialize(final String string, final Type type) {
        return CoreGson.get().fromJson(string, type);
    }

    public static <T> T deserialize(final String string, final Class<T> clazz) {
        return CoreGson.get().fromJson(string, clazz);
    }
}
