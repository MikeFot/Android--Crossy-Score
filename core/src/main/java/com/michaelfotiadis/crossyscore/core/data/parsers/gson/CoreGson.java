package com.michaelfotiadis.crossyscore.core.data.parsers.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.lang.reflect.Modifier;

/**
 * Custom Gson builder with adapter factory
 */
public final class CoreGson {

    private static Gson gson;

    private CoreGson() {
    }

    /**
     * Synchronized getter for the class
     *
     * @return Gson object
     */
    public static synchronized Gson get() {
        if (gson == null) {
            gson = buildGson();
        }
        return gson;
    }

    /**
     * Method which creates the Gson Builder
     *
     * @return GsonBuilder object
     */
    private static Gson buildGson() {
        final GsonBuilder gson = new GsonBuilder();
        gson.setPrettyPrinting();
        gson.serializeNulls();
        gson.excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT, Modifier.VOLATILE);

        // register the type adapter factories
        final TypeAdapterFactoryCreator creator = new TypeAdapterFactoryCreator();
        for (final TypeAdapterFactory factory : creator.getAdapters()) {
            gson.registerTypeAdapterFactory(factory);
        }

        return gson.create();
    }
}