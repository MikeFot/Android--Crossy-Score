package com.michaelfotiadis.crossyscore.ui.components.addplayer.avatar;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.data.models.ImageContainer;
import com.michaelfotiadis.crossyscore.ui.core.common.list.BaseListAdapter;

import butterknife.ButterKnife;

public class ListAvatarAdapter extends BaseListAdapter<ListAvatarViewHolder, ImageContainer> {

    public ListAvatarAdapter(final Activity activity) {
        super(activity);
    }

    @Override
    public View getDropDownView(final int position,
                                final View convertView,
                                final ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    protected ListAvatarViewBinder createBinder() {
        return new ListAvatarViewBinder(getActivity(), this.createIntentDispatcher());
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position,
                        View convertView,
                        final ViewGroup parent) {

        final ListAvatarViewHolder holder; // to reference the child views for later actions

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(ListAvatarViewHolder.getLayoutId(), null);
            // cache view fields into the holder
            holder = new ListAvatarViewHolder(convertView);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            // view already exists, get the holder instance from the view
            holder = (ListAvatarViewHolder) convertView.getTag();
        }

        getBinder().bind(holder, getItem(position));

        return convertView;
    }

}