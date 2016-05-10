package com.michaelfotiadis.crossyscore.ui.components.mascotpicker.recycler;

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

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class MascotPickerRecyclerViewAdapter extends BaseRecyclerViewAdapter<Mascot, MascotPickerRecyclerViewHolder> {

    private final MascotPickerRecyclerBinder mBinder;

    public MascotPickerRecyclerViewAdapter(final Activity activity, final IntentDispatcher intentDispatcher) {
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
                AppLog.d("Sending result: " + mascot.getName());
                final Bundle data = new Bundle();
                data.putParcelable(AppConstants.EXTRA_1, mascot);
                final Intent intent = new Intent();
                intent.putExtras(data);
                getActivity().setResult(Activity.RESULT_OK, intent);
                getActivity().finish();
            }
        });
        mBinder.bind(holder, mascot);

    }

    private List<Mascot> filter(List<Mascot> models, String query) {
        query = query.toLowerCase();

        final List<Mascot> filteredModelList = new ArrayList<>();
        for (Mascot model : models) {
            final String text = model.getName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}
