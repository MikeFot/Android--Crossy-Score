package com.michaelfotiadis.crossyscore.ui.components.score;

import android.text.TextUtils;

import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
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
public class ScoreWrapperSearcher implements DataFilter<ScoreUiWrapper> {
    private final List<ScoreUiWrapper> mAllItems;
    private EmptyQueryBehaviour mBehaviour = EmptyQueryBehaviour.SHOW_ALL;

    public ScoreWrapperSearcher(final List<ScoreUiWrapper> allItems) {
        mAllItems = Collections.unmodifiableList(allItems);
    }

    @Override
    public void filter(final String query, final FilterFinishedCallback<ScoreUiWrapper> callback) {
        final List<ScoreUiWrapper> result = new ArrayList<>();
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
            for (final ScoreUiWrapper item : mAllItems) {
                if (matches(query, item)) {
                    result.add(item);
                }
            }
        }

        Collections.sort(result, new ScoreUiComparator());
        AppLog.d("Filtering data for '" + query + "', result count: " + result.size());
        callback.onSearchFinished(Collections.unmodifiableList(result));
    }

    @Override
    public void setEmptyQueryBehaviour(final EmptyQueryBehaviour behaviour) {
        mBehaviour = behaviour;
    }

    private static boolean matches(final String query, final ScoreUiWrapper item) {
        //noinspection SimplifiableIfStatement
        if (item.getValue() == null) {
            return false;
        }

        if (AppTextUtils.containsIgnoreCase(String.valueOf(item.getValue()), query)) {
            return true;
        } else if (AppTextUtils.containsIgnoreCase(String.valueOf(item.getPlayerName()), query)) {
            return true;
        } else //noinspection RedundantIfStatement
            if (AppTextUtils.containsIgnoreCase(String.valueOf(item.getMascotName()), query)) {
                return true;
            } else {
                return false;
            }

    }

    private static class ScoreUiComparator implements Comparator<ScoreUiWrapper> {
        @Override
        public int compare(final ScoreUiWrapper lhs, final ScoreUiWrapper rhs) {
            return (int) (rhs.getTimeStamp() - lhs.getTimeStamp());
        }
    }
}
