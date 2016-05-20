package com.michaelfotiadis.crossyscore.ui.components.score.recycler;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.core.CrossyCore;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewbinder.BaseRecyclerViewBinder;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.viewbinder.ItemCallbacks;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.ImageUtils;
import com.michaelfotiadis.crossyscore.utils.date.DateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;

/**
 *
 */
public class ScoreUiWrapperRecyclerBinder extends BaseRecyclerViewBinder<ScoreUiWrapperRecyclerViewHolder, ScoreUiWrapper> {

    private static final int DEFAULT_IMAGE_PLACEHOLDER = R.drawable.ic_android_light_blue_300_18dp;

    private final ImageUtils mImageUtils;
    private final ItemCallbacks<ScoreUiWrapper> mItemCallbacks;

    protected ScoreUiWrapperRecyclerBinder(final Context context,
                                           final IntentDispatcher intentDispatcher,
                                           final ItemCallbacks<ScoreUiWrapper> itemCallbacks) {
        super(context, intentDispatcher);
        mImageUtils = new ImageUtils(context);
        mItemCallbacks = itemCallbacks;
    }

    @Override
    public void bind(final ScoreUiWrapperRecyclerViewHolder holder, final ScoreUiWrapper item) {

        ButterKnife.bind(holder, holder.getRoot());

        if (item != null) {
            holder.scoreText.setText(String.valueOf(item.getValue()));
            holder.imageAvatar.setImageDrawable(getDrawable(item.getPlayerResId()));
            holder.imageMascot.setImageDrawable(getDrawable(item.getMascotResId()));

            mImageUtils.loadImageToViewReflectively(
                    holder.imageMascot, item.getMascotName(), ImageUtils.IMAGE_TYPE.LIST);

            holder.playerName.setText(item.getPlayerName());
            holder.mascotName.setText(item.getMascotName());
            holder.timeStamp.setText(DateUtils.getTimeAgoForLong(item.getTimeStamp()));

            setupMoreMenu(holder, item);
        }
    }

    @Override
    public void reset(final ScoreUiWrapperRecyclerViewHolder holder) {
        holder.scoreText.setText("");
        holder.imageAvatar.setImageDrawable(getDrawable(null));
        holder.imageMascot.setImageDrawable(getDrawable(null));
        holder.playerName.setText("");
        holder.mascotName.setText("");
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


    private void setupMoreMenu(final ScoreUiWrapperRecyclerViewHolder holder,
                               final ScoreUiWrapper item) {
        holder.moreView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                showOptionsPopup(v);
            }

            private void showOptionsPopup(final View v) {
                final PopupMenu popup = new PopupMenu(v.getContext(), v);
                final MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_score_list_item, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(final MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.action_score_item_delete) {

                            final List<String> ids = new ArrayList<>();
                            ids.add(String.valueOf(item.getTimeStamp()));
                            CrossyCore.getDataProvider().getScores().delete(ids);
                            mItemCallbacks.onRemovalRequested(item);
                            return true;
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });
    }

}

