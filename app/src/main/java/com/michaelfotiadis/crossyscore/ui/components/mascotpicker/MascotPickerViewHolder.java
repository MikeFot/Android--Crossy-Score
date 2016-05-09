package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.michaelfotiadis.crossyscore.R;
import com.michaelfotiadis.crossyscore.ui.core.common.viewholder.BaseViewHolder;

import butterknife.Bind;

/**
 *
 */
public class MascotPickerViewHolder extends BaseViewHolder {

    @Bind(R.id.search)
    protected EditText searchView;
    @Bind(R.id.recycler_view)
    protected RecyclerView recyclerView;

    public MascotPickerViewHolder(final View view) {
        super(view);
    }
}
