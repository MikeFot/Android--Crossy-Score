package com.michaelfotiadis.crossyscore.ui.components.create.player;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewDataBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.date.DateUtils;

import java.util.Random;

/**
 *
 */
public class ListPlayerViewBinder extends BaseViewDataBinder<ListPlayerViewHolder, Player> {

    private static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;

    protected ListPlayerViewBinder(final Context context, final IntentDispatcher intentDispatcher) {
        super(context, intentDispatcher);
    }

    private static String calculateJoinedText(final Long registeredOn) {
        return String.format("Joined %s", DateUtils.getTimeAgoForLong(registeredOn));
    }

    @Override
    public void bind(final ListPlayerViewHolder holder, final Player item) {
        if (item != null) {
            holder.image.setImageDrawable(getDrawable(item.getDrawableResId()));
            holder.title.setText(item.getName());
            holder.subTitle.setText(item.getAlias());
            holder.joinedText.setText(calculateJoinedText(item.getRegisteredOn()));
        }
    }

    private Drawable getDrawable(final Integer resId) {
        final Drawable drawable;
        if (resId == null) {
            drawable = ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER);
            final Random rnd = new Random();
            final int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            DrawableCompat.setTint(drawable, color);
        } else {
            drawable = ActivityCompat.getDrawable(getContext(), resId);
        }
        return drawable;
    }

    @Override
    public void reset(final ListPlayerViewHolder holder) {
        holder.image.setImageDrawable(ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER));
        holder.title.setText("");
        holder.subTitle.setText("");
    }
}

