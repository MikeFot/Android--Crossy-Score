package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.components.mascotpicker.MascotPickerRecyclerViewHolder;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;

/**
 *
 */
public class ScoreUiWrapperRecyclerViewAdapter extends BaseRecyclerViewAdapter<ScoreUiWrapper, ScoreUiWrapperRecyclerViewHolder> {

    private final ScoreUiWrapperRecyclerBinder mBinder;

    protected ScoreUiWrapperRecyclerViewAdapter(final Activity activity, final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
        mBinder = new ScoreUiWrapperRecyclerBinder(activity, intentDispatcher);
    }

    @Override
    protected boolean isItemValid(final ScoreUiWrapper item) {
        return item != null && item.getId() != null;
    }

    @Override
    public ScoreUiWrapperRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        final View view = LayoutInflater.from(parent.getContext())
                .inflate(MascotPickerRecyclerViewHolder.getLayoutId(), parent, false);

        return new ScoreUiWrapperRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScoreUiWrapperRecyclerViewHolder holder,
                                 final int position) {

        /*final Mascot mascot = getItem(position);

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
        mBinder.bind(holder, mascot);*/

    }

}
