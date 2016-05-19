package com.michaelfotiadis.crossyscore.ui.components.mascotpicker.recycler;

import android.content.Context;
import android.support.v4.app.ActivityCompat;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewbinder.BaseRecyclerViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.ImageUtils;

/**
 *
 */
public class MascotPickerRecyclerBinder extends BaseRecyclerViewBinder<MascotPickerRecyclerViewHolder, Mascot> {

    private final ImageUtils mImageUtils;

    protected MascotPickerRecyclerBinder(final Context context, final IntentDispatcher intentDispatcher) {
        super(context, intentDispatcher);
        mImageUtils = new ImageUtils(context);
    }

    @Override
    public void bind(final MascotPickerRecyclerViewHolder holder, final Mascot item) {
        if (item != null) {

            mImageUtils.loadImageToViewReflectively(
                    holder.image, item.getName(), ImageUtils.IMAGE_TYPE.LIST);
            holder.title.setText(item.getName());
            holder.subTitle.setText(item.getRelease());
        }
    }

    @Override
    public void reset(final MascotPickerRecyclerViewHolder holder) {
        holder.image.setImageDrawable(ActivityCompat.getDrawable(
                getContext(), ImageUtils.DEFAULT_IMAGE_PLACEHOLDER));
        holder.title.setText("");
        holder.subTitle.setText("");
    }

}

