package com.michaelfotiadis.crossyscore.ui.components.mascotpicker;

import android.text.TextUtils;

import com.michaelfotiadis.crossyscore.common.models.mascot.Mascot;
import com.michaelfotiadis.crossyscore.ui.core.common.search.DataFilter;
import com.michaelfotiadis.crossyscore.ui.core.common.search.FilterFinishedCallback;
import com.michaelfotiadis.crossyscore.utils.AppLog;
import com.michaelfotiadis.crossyscore.utils.text.AppTextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class MascotSearcher implements DataFilter<Mascot> {
    private final List<Mascot> mAllItems;
    private EmptyQueryBehaviour mBehaviour = EmptyQueryBehaviour.SHOW_ALL;

    public MascotSearcher(final List<Mascot> allItems) {
        mAllItems = Collections.unmodifiableList(allItems);
    }

    @Override
    public void filter(final String query, final FilterFinishedCallback<Mascot> callback) {
        final List<Mascot> result = new ArrayList<>();
        AppLog.d("Searcher is looking for " + query);
        // TODO: Do the search in a BG thread

        if (TextUtils.isEmpty(query)) {
            switch (mBehaviour) {
                case SHOW_ALL:
                    result.addAll(mAllItems);
                    break;
                case SHOW_NONE:
                    break;
                default:
                    throw new IllegalStateException("Unknown EmptyQueryBehaviour: " + mBehaviour);
            }
        } else {
            for (final Mascot mascot : mAllItems) {
                if (matches(query, mascot)) {
                    result.add(mascot);
                }
            }
        }

        Collections.sort(result, new MascotComparator());
        AppLog.d("Filtering data for '" + query + "', result count: " + result.size());
        callback.onSearchFinished(Collections.unmodifiableList(result));
    }

    @Override
    public void setEmptyQueryBehaviour(final EmptyQueryBehaviour behaviour) {
        mBehaviour = behaviour;
    }

    private static boolean matches(final String query, final Mascot mascot) {
        //noinspection SimplifiableIfStatement
        if (mascot.getName() == null) {
            return false;
        }

        return AppTextUtils.containsIgnoreCase(mascot.getName(), query) ||
                AppTextUtils.containsIgnoreCase(mascot.getRelease(), query);

    }

    private static class MascotComparator implements Comparator<Mascot> {
        @Override
        public int compare(final Mascot lhs, final Mascot rhs) {
            return (int) (lhs.getOrder() - rhs.getOrder());
        }
    }
}
