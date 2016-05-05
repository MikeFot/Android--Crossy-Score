package com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement;

import android.view.View;
import android.widget.ViewFlipper;

import com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage.DefaultQuotePageController;
import com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage.QuoteOnClickListenerWrapper;
import com.michaelfotiadis.crossyscore.ui.core.common.error.errorpage.QuotePageController;

/**
 *
 */
public class SimpleUiStateKeeper implements UiStateKeeper {

    public static final int EMPTY_VIEW_ID = R.id.cm_std_empty;
    public static final int ERROR_VIEW_ID = R.id.cm_std_error;
    public static final int FLIPPER_ID = R.id.cm_std_view_changer;
    public static final int PROGRESS_VIEW_ID = R.id.cm_std_progress;
    private static final int INVALID_INDEX = -1;
    private final QuotePageController mEmptyPageController;
    private final QuotePageController mErrorPageController;
    private final ViewFlipper mFlipper;
    private int mContentViewIndex = INVALID_INDEX;
    private int mEmptyViewIndex = INVALID_INDEX;
    private int mErrorViewIndex = INVALID_INDEX;
    private int mProgressViewIndex = INVALID_INDEX;


    public SimpleUiStateKeeper(final View view, final int contentViewId) {
        mFlipper = (ViewFlipper) view.findViewById(FLIPPER_ID);

        if (mFlipper == null) {
            throw new IllegalStateException("Could not find main ViewFlipper: No direct child with id='R.id.cm_std_view_changer'");
        }

        mFlipper.setInAnimation(view.getContext(), R.anim.quick_fade_in);
        mFlipper.setOutAnimation(view.getContext(), R.anim.quick_fade_out);

        for (int i = 0; i < mFlipper.getChildCount(); i++) {
            final int id = mFlipper.getChildAt(i).getId();

            if (id == contentViewId) {
                mContentViewIndex = i;
            } else if (id == ERROR_VIEW_ID) {
                mErrorViewIndex = i;
            } else if (id == PROGRESS_VIEW_ID) {
                mProgressViewIndex = i;
            } else if (id == EMPTY_VIEW_ID) {
                mEmptyViewIndex = i;
            }
        }

        if (mContentViewIndex == INVALID_INDEX) {
            throw new IllegalStateException("Could not find content view: No direct child with id=" + contentViewId);
        }

        if (mErrorViewIndex != INVALID_INDEX) {
            mErrorPageController = new DefaultQuotePageController(getErrorView(),
                    new ErrorQuotePicker(view.getContext()));
        } else {
            mErrorPageController = null;
        }

        if (mEmptyViewIndex != INVALID_INDEX) {
            mEmptyPageController = new DefaultQuotePageController(getEmptyView(),
                    new EmptyQuotePicker(view.getContext()));
        } else {
            mEmptyPageController = null;
        }
    }

    public SimpleUiStateKeeper(final View view, final View contentView) {
        this(view, contentView.getId());
    }


    @Override
    public View getContentView() {
        return getViewInternal(mContentViewIndex);
    }

    @Override
    public View getEmptyView() {
        return getViewInternal(mEmptyViewIndex);
    }

    @Override
    public View getErrorView() {
        return getViewInternal(mErrorViewIndex);
    }

    @Override
    public View getProgressView() {
        return getViewInternal(mProgressViewIndex);
    }

    private View getViewInternal(final int index) {
        if (index == INVALID_INDEX) {
            return null;
        } else {
            return mFlipper.getChildAt(index);
        }
    }

    @Override
    public void showContent() {
        mFlipper.setDisplayedChild(mContentViewIndex);
    }

    @Override
    public void showEmpty() {
        showEmpty(null);
    }

    @Override
    public void showEmpty(final CharSequence message) {
        validateEmptyView();
        mEmptyPageController.display(message);
        mFlipper.setDisplayedChild(mEmptyViewIndex);
    }

    @Override
    public void showError(final CharSequence message) {
        showError(message, null);
    }

    @Override
    public void showError(final CharSequence message, final QuoteOnClickListenerWrapper listenerWrapper) {
        validateErrorView();
        mErrorPageController.display(message, listenerWrapper);
        mFlipper.setDisplayedChild(mErrorViewIndex);
    }

    @Override
    public void showProgress() {
        validateProgressView();
        mFlipper.setDisplayedChild(mProgressViewIndex);
    }

    private void validateEmptyView() {
        if (mEmptyViewIndex == INVALID_INDEX) {
            throw new IllegalStateException("Could not find empty view: No direct child with id='R.id.cm_std_empty'");
        }
    }

    private void validateErrorView() {
        if (mErrorViewIndex == INVALID_INDEX) {
            throw new IllegalStateException("Could not find error view: No direct child with id='R.id.cm_std_error'");
        }
    }

    private void validateProgressView() {
        if (mProgressViewIndex == INVALID_INDEX) {
            throw new IllegalStateException("Could not find progress view: No direct child with id='R.id.cm_std_progress'");
        }
    }
}
