package com.michaelfotiadis.crossyscore.ui.components.addplayer;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.common.models.player.PlayerImpl;
import com.michaelfotiadis.crossyscore.data.models.ImageContainer;
import com.michaelfotiadis.crossyscore.ui.components.addplayer.avatar.ListAvatarAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseViewController;

import java.util.List;

/**
 *
 */
public class AddPlayerFragmentController extends BaseViewController {

    private final ListAvatarAdapter mAvatarAdapter;

    private final AddPlayerFragmentViewHolder mHolder;
    private final AddPlayerFragmentViewBinder mBinder;

    public AddPlayerFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new AddPlayerFragmentViewHolder(view);
        mBinder = new AddPlayerFragmentViewBinder(activity, createIntentDispatcher());

        mBinder.bind(mHolder);

        mAvatarAdapter = new ListAvatarAdapter(activity);
        mHolder.spinnerAvatar.setAdapter(mAvatarAdapter);

    }

    public Player createPlayer() {
        if (validate()) {

            final String name = mHolder.textName.getText().toString();
            final String alias = mHolder.textAlias.getText().toString();
            final ImageContainer image = (ImageContainer) mHolder.spinnerAvatar.getSelectedItem();

            return PlayerImpl.newBuilder()
                    .withName(name)
                    .withAlias(alias)
                    .withDrawableResId(image.getResId())
                    .withRegisteredOn(System.currentTimeMillis())
                    .build();
        } else {
            return null;
        }
    }

    public void setImages(final List<ImageContainer> images) {
        mAvatarAdapter.setItems(images);
    }

    public void setOnClickListener(final View.OnClickListener listener) {
        mBinder.setOnClickListener(listener);
    }

    private boolean validate() {
        if (TextUtils.isEmpty(mHolder.textName.getText().toString())) {
            mHolder.textName.setError("You need a name!");
            return false;
        } else {
            return true;
        }
    }
}
