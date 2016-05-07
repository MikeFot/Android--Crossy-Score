package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class MascotLoader extends DataFeedLoaderAbstract<Mascot> {

    public MascotLoader(final Activity activity) {
        super(activity);
    }

    @Override
    public void loadData() {

        final List<Mascot> mascots = CrossyCore.getDataProvider().getMascots();

        if (mascots == null) {
            notifyError(new UiDataLoadError("Null mascots found", UiDataLoadError.ErrorKind.NO_DATA, false));
        } else {
            AppLog.d("Loaded " + mascots.size() + " mascots");
            notifySuccess(mascots);
        }

    }
}
