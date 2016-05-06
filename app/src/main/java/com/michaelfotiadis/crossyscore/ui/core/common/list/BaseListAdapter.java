package com.michaelfotiadis.crossyscore.ui.core.common.list;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.michaelfotiadis.crossyscore.common.models.base.AppModel;
import com.michaelfotiadis.crossyscore.common.models.base.WithLongId;
import com.michaelfotiadis.crossyscore.ui.core.common.activity.BaseActivity;
import com.michaelfotiadis.crossyscore.ui.core.common.viewbinder.BaseViewHolderBinder;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcher;
import com.michaelfotiadis.crossyscore.ui.core.intent.dispatch.IntentDispatcherImpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class BaseListAdapter<VH extends BaseViewHolder, D extends AppModel> extends BaseAdapter {

    private final Activity mActivity;
    private final List<D> mItems;
    private final LayoutInflater mLayoutInflater;
    private final BaseViewHolderBinder<VH, D> mBinder;


    public BaseListAdapter(final Activity activity) {
        mActivity = activity;
        mItems = new ArrayList<>();
        mLayoutInflater = LayoutInflater.from(activity);
        mBinder = createBinder();
    }

    protected IntentDispatcher createIntentDispatcher() {
        if (mActivity instanceof BaseActivity) {
            return ((BaseActivity) mActivity).getIntentDispatcher();
        } else {
            return new IntentDispatcherImpl(mActivity);
        }
    }

    protected abstract BaseViewHolderBinder<VH, D> createBinder();

    @Override
    public View getDropDownView(final int position,
                                final View convertView,
                                final ViewGroup parent) {
        return getView(position, convertView, parent);
    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public D getItem(final int position) {
        if (mItems.size() >= position - 1) {
            return mItems.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(final int position) {

        final D item = getItem(position);

        if (item != null) {
            if (item instanceof WithLongId) {
                return ((WithLongId) item).getId();
            } else {
                return item.hashCode();
            }
        } else {
            return 0;
        }
    }

    @Override
    public abstract View getView(final int position, final View convertView, final ViewGroup parent);

    public void addItems(final List<D> items) {
        if (items != null) {
            mItems.addAll(items);
            this.notifyDataSetChanged();
        }

    }

    public void addItem(final D item) {
        if (item != null) {
            mItems.add(item);
            this.notifyDataSetChanged();
        }

    }

    public void clear() {
        mItems.clear();
        this.notifyDataSetChanged();
    }

    public Activity getActivity() {
        return mActivity;
    }

    public List<D> getItems() {
        return mItems;
    }

    public void setItems(final List<D> items) {
        mItems.clear();
        this.addItems(items);
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public BaseViewHolderBinder<VH, D> getBinder() {
        return mBinder;
    }
}
