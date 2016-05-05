package com.michaelfotiadis.crossyscore.ui.components.create;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.adapter.BaseRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.utils.view.ViewUtils;


/*package*/ class RecyclerViewAdapter extends BaseRecyclerViewAdapter<Mascot, MascotViewHolder> {

    public RecyclerViewAdapter(final Activity activity) {
        this(activity, createIntentDispatcher(activity));
    }

    public RecyclerViewAdapter(final Activity activity,
                               final IntentDispatcher intentDispatcher) {
        super(activity, intentDispatcher);
    }

    @Override
    protected boolean isItemValid(final Mascot item) {
        return true;
    }

    @Override
    public void onBindViewHolder(final MascotViewHolder holder, final int position) {
        final Mascot item = getItem(position);

        holder.getLabel().setText(item.getName());
        ViewUtils.setTextOrHide(holder.getDesc(), item.getRelease());

        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //getIntentDispatcher().dispatch(v, item.getIntent());
            }
        });
    }

    @Override
    public MascotViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(MascotViewHolder.getLayoutId(), parent, false);
        return new MascotViewHolder(view);
    }
}