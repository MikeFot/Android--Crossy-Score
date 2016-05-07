package com.michaelfotiadis.crossyscore.data.provider;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.data.models.ImageContainer;
import com.michaelfotiadis.crossyscore.data.models.ImageContainerImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public final class ImageContainerProvider {

    private ImageContainerProvider() {
    }

    public static List<ImageContainer> getImages() {

        final List<ImageContainer> images = new ArrayList<>();


        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_01)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_02)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_03)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_04)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_05)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_06)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_07)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_08)
                        .build());
        images.add(
                ImageContainerImpl.newBuilder()
                        .withResId(R.drawable.avatar_09)
                        .build());


        return images;
    }

}
