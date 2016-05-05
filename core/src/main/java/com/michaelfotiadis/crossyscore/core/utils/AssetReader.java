package com.michaelfotiadis.crossyscore.core.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.michaelfotiadis.crossyscore.core.data.parsers.CoreGson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 *
 */
public class AssetReader {

    private final Context mContext;
    private final Gson mGson;

    public AssetReader(final Context context) {
        this(context, CoreGson.get());
    }

    private AssetReader(final Context context, final Gson gson) {
        this.mContext = context.getApplicationContext();
        this.mGson = gson;
    }

    public boolean exists(final String path) {

        boolean methodReturn = false;
        try {
            final InputStream stream = mContext.getAssets().open(path);
            StreamUtils.close(stream);

            methodReturn = true;
        } catch (final IOException ex) {
            //
        }

        return methodReturn;
    }

    public <T> T fromJson(final String path, final Class<T> classOfT) {
        final String string = readToString(path, SdkConstants.ENCODING_UTF8);
        return mGson.fromJson(string, (Type) classOfT);
    }

    public InputStream getStream(final String path) throws IOException {
        return mContext.getAssets().open(path);
    }

    public String readAsString(final String path) {
        return readToString(path, SdkConstants.ENCODING_UTF8);
    }

    private String readToString(final String path, final String encoding) {
        BufferedReader reader = null;
        final StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open(path), encoding));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } catch (final IOException e) {
            SdkLog.e("Error reading Resource: " + path, e);
        } finally {
            StreamUtils.close(reader);
        }

        return sb.toString();
    }
}
