package com.michaelfotiadis.crossyscore.ui.components.create.mascot;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewDataBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.ImageUtils;

import java.util.Random;

import butterknife.ButterKnife;

/**
 *
 */
public class ListMascotViewBinder extends BaseViewDataBinder<ListMascotViewHolder, Mascot> {

    private static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;

    private final ImageUtils mImageUtils;

    public ListMascotViewBinder(final Context context, final IntentDispatcher intentDispatcher) {
        super(context, intentDispatcher);
        mImageUtils = new ImageUtils(context);
    }

    @Override
    public void bind(final ListMascotViewHolder holder, final Mascot item) {

        ButterKnife.bind(holder, holder.getRoot());

        if (item != null) {
            mImageUtils.loadImageToViewReflectively(
                    holder.image, item.getName(), ImageUtils.IMAGE_TYPE.LIST);
            holder.title.setText(item.getName());
            holder.subTitle.setText(item.getRelease());
        }

    }

    @Override
    public void reset(final ListMascotViewHolder holder) {
        holder.image.setImageDrawable(ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER));
        holder.title.setText("");
        holder.subTitle.setText("");
    }
}

