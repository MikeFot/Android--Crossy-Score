package com.michaelfotiadis.crossyscore.ui.components.create.player;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewHolderBinder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

import java.util.Random;

/**
 *
 */
public class ListUserViewBinder extends BaseViewHolderBinder<ListUserViewHolder, User> {

    private static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;

    protected ListUserViewBinder(final Context context, final IntentDispatcher intentDispatcher) {
        super(context, intentDispatcher);
    }

    @Override
    public void bind(final ListUserViewHolder holder, final User item) {
        if (item != null) {
            holder.image.setImageDrawable(getDrawable(item.getPlayer().getDrawableResId()));
            holder.title.setText(item.getPlayer().getName());
            holder.subTitle.setText(item.getPlayer().getAlias());
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
    public void reset(final ListUserViewHolder holder) {
        holder.image.setImageDrawable(ActivityCompat.getDrawable(getContext(), DEFAULT_IMAGE_PLACEHOLDER));
        holder.title.setText("");
        holder.subTitle.setText("");
    }
}

