package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.data.models.User;
import com.michaelfotiadis.crossyscore.ui.components.create.mascot.ListMascotAdapter;
import com.michaelfotiadis.crossyscore.ui.components.create.player.ListUserAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseViewController;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 */
public class CreateController extends BaseViewController {

    @Bind(R.id.spinner_mascot)
    protected Spinner mMascotSpinner;
    @Bind(R.id.spinner_player)
    protected Spinner mUserSpinner;

    private final ListMascotAdapter mMascotAdapter;
    private final ListUserAdapter mUserAdapter;

    public CreateController(final Activity activity, final View view) {
        super(activity, view);
        ButterKnife.bind(this, view);

        mMascotAdapter = new ListMascotAdapter(activity);
        mMascotSpinner.setAdapter(mMascotAdapter);

        mUserAdapter = new ListUserAdapter(activity);
        mUserSpinner.setAdapter(mUserAdapter);

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
