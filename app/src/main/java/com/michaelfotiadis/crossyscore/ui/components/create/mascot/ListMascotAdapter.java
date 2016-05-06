package com.michaelfotiadis.crossyscore.ui.components.create.mascot;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.list.BaseListAdapter;

import butterknife.ButterKnife;

public class ListMascotAdapter extends BaseListAdapter<ListMascotViewHolder, Mascot> {

    public ListMascotAdapter(final Activity activity) {
        super(activity);
    }

    @Override
    public View getDropDownView(final int position,
                                final View convertView,
                                final ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    protected ListMascotViewBinder createBinder() {
        return new ListMascotViewBinder(getActivity(), this.createIntentDispatcher());
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position,
                        View convertView,
                        final ViewGroup parent) {

        final ListMascotViewHolder holder; // to reference the child views for later actions

        if (convertView == null) {
            convertView = getLayoutInflater().inflate(ListMascotViewHolder.getLayoutId(), null);
            // cache view fields into the holder
            holder = new ListMascotViewHolder(convertView);
            ButterKnife.bind(holder, convertView);
            convertView.setTag(holder);
        } else {
            // view already exists, get the holder instance from the view
            holder = (ListMascotViewHolder) convertView.getTag();
        }

        getBinder().bind(holder, getItem(position));

        return convertView;
    }

}