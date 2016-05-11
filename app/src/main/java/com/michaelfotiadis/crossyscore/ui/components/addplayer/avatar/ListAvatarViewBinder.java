package com.michaelfotiadis.crossyscore.ui.components.addplayer.avatar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.data.models.ImageContainer;
import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewDataBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import butterknife.ButterKnife;

/**
 *
 */
public class ListAvatarViewBinder extends BaseViewDataBinder<ListAvatarViewHolder, ImageContainer> {

    private static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;

    protected ListAvatarViewBinder(final Context context, final IntentDispatcher intentDispatcher) {
        super(context, intentDispatcher);
    }

    private static void setTint(final Drawable drawable, final Integer tint) {
        if (tint != null) {
            DrawableCompat.setTint(drawable, tint);
        }
    }

    @Override
    public void bind(final ListAvatarViewHolder holder,
                     final ImageContainer item) {

        ButterKnife.bind(holder, holder.getRoot());

        if (item != null) {
            holder.image.setImageDrawable(getDrawable(item.getResId(), item.getTintColor()));
        } else {
            reset(holder);
        }

    }

    private Drawable getDrawable(final Integer resId, final Integer tint) {
        final Drawable drawable;
        if (resId == null) {
            drawable = ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER);
        } else {
            drawable = ActivityCompat.getDrawable(getContext(), resId);
        }
        setTint(drawable, tint);
        return drawable;
    }

    @Override
    public void reset(final ListAvatarViewHolder holder) {
        holder.image.setImageDrawable(ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER));
    }
}

