package com.michaelfotiadis.crossyscore.data.loader;

import android.app.Activity;

import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.models.ImageContainer;
import com.michaelfotiadis.crossyscore.data.provider.ImageContainerProvider;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
public class AvatarLoader extends DataFeedLoaderAbstract<ImageContainer> {

    public AvatarLoader(final Activity activity) {
        super(activity);
    }

    @Override
    public void loadData() {

        final List<ImageContainer> images = ImageContainerProvider.getImages();

        if (images == null || images.size() == 0) {
            notifyError(new UiDataLoadError("Null images found", UiDataLoadError.ErrorKind.NO_DATA, false));
        } else {
            AppLog.d("Loaded " + images.size() + " images");
            notifySuccess(images);
        }

    }

}
