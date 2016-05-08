package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.AppConstants;
import com.michaelfotiadis.crossyscore.utils.AppLog;

/**
 *
 */
public class MascotPickerRecyclerViewAdapter extends BaseRecyclerViewAdapter<Mascot, MascotPickerRecyclerViewHolder> {

    private final MascotPickerRecyclerBinder mBinder;
    private Integer resultCode;

    protected MascotPickerRecyclerViewAdapter(final Activity activity, final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
        mBinder = new MascotPickerRecyclerBinder(activity, intentDispatcher);
    }

    @Override
    protected boolean isItemValid(final Mascot item) {
        return item != null && item.getId() != null;
    }

    @Override
    public MascotPickerRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(MascotPickerRecyclerViewHolder.getLayoutId(), parent, false);

        return new MascotPickerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MascotPickerRecyclerViewHolder holder,
                                 final int position) {

        final Mascot mascot = getItem(position);

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AppLog.d("Sending result: " + mascot.getId());
                final Bundle data = new Bundle();
                data.putParcelable(AppConstants.EXTRA_1, mascot);
                final Intent intent = new Intent();
                intent.putExtras(data);
                getActivity().setResult(Activity.RESULT_OK, intent);
            }
        });

        mBinder.bind(holder, mascot);
    }

    public void setResultCode(final int resultCode) {
        this.resultCode = resultCode;
    }
}
