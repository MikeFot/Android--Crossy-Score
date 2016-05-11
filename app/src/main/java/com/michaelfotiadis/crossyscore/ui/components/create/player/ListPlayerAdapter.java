package com.michaelfotiadis.crossyscore.ui.components.create.player;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.common.models.player.Player;
import com.michaelfotiadis.crossyscore.ui.core.common.list.BaseListAdapter;

import butterknife.ButterKnife;

public class ListPlayerAdapter extends BaseListAdapter<ListPlayerViewHolder, Player> {

    public ListPlayerAdapter(final Activity activity) {
        super(activity);
    }

    @Override
    public View getDropDownView(final int position,
                                final View convertView,
                                final ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    protected ListPlayerViewBinder createBinder() {
        return new ListPlayerViewBinder(getActivity(), this.createIntentDispatcher());
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position,
                        View convertView,
                        final ViewGroup parent) {

        final ListPlayerViewHolder holder; // to reference the child views for later actions

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(ListPlayerViewHolder.getLayoutId(), null);
            // cache view fields into the holder
            holder = new ListPlayerViewHolder(convertView);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            // view already exists, get the holder instance from the view
            holder = (ListPlayerViewHolder) convertView.getTag();
        }

        getBinder().bind(holder, getItem(position));

        return convertView;
    }

}