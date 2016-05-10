package com.michaelfotiadis.crossyscore.core.data.parsers.mascots;

import android.content.Context;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.common.models.mascot.MascotImpl;
import com.michaelfotiadis.crossyscore.core.utils.SdkConstants;
import com.michaelfotiadis.crossyscore.core.utils.stream.AssetReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MascotsParser {

    private final Context mContext;

    public MascotsParser(final Context context) {
        this.mContext = context;
    }

    public List<Mascot> getMascots() {

        final AssetReader assetReader = new AssetReader(mContext);

        final List<Mascot> mascots = new ArrayList<>();

        try {
            final InputStream stream = assetReader.getStream("mascots.csv");

            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, SdkConstants.ENCODING_UTF8));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    final String[] row = line.split(",");

                    final Mascot mascot = MascotImpl.newBuilder()
                            .withOrder(Long.valueOf(row[0]))
                            .withName(row[1])
                            .withRelease(row[2])
                            .build();
                    mascots.add(mascot);
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    stream.close();
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (final IOException e) {
            e.printStackTrace();
        }

        return mascots;
    }

}
