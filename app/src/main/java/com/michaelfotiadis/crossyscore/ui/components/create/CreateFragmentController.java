package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.ui.components.create.mascot.ListMascotAdapter;
import com.michaelfotiadis.crossyscore.ui.components.create.player.ListUserAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseViewController;

import java.util.List;

/**
 *
 */
public class CreateFragmentController extends BaseViewController {

    private final ListMascotAdapter mMascotAdapter;
    private final ListUserAdapter mUserAdapter;

    private final CreateFragmentViewHolder mHolder;
    private final CreateFragmentViewBinder mBinder;

    public CreateFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mHolder = new CreateFragmentViewHolder(view);
        mBinder = new CreateFragmentViewBinder(activity, createIntentDispatcher());

        mBinder.bind(mHolder);

        mMascotAdapter = new ListMascotAdapter(activity);
        mHolder.mascotSpinner.setAdapter(mMascotAdapter);

        mUserAdapter = new ListUserAdapter(activity);
        mHolder.userSpinner.setAdapter(mUserAdapter);

    }

    public void clearMascots() {
        mMascotAdapter.clear();
    }

    public void setUsers(final List<User> items) {
        mUserAdapter.setItems(items);
    }

    public void setMascots(final List<Mascot> items) {
        mMascotAdapter.setItems(items);
    }

}
