package com.michaelfotiadis.crossyscore.utils.view;

import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 *
 */
public final class ViewUtils {

    private ViewUtils() {
        // INSTANTIATION BAD
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(final float dp, final Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(final float px, final Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    public static boolean isEllipsized(final TextView tv) {
        final Layout layout = tv.getLayout();
        final int lines = layout.getLineCount();
        if (lines > 0) {
            final int ellipsisCount = layout.getEllipsisCount(lines - 1);
            return ellipsisCount > 0;
        } else {
            return false;
        }
    }

    /**
     * Will try to set the text in a {@link TextView} if the passed text is not empty.
     * If the {@param text} is empty or null (as asserted by {@link TextUtils#isEmpty(CharSequence)}),
     * it will set the visibility of the TextView to {@link View#GONE}.
     * If {@param text} is not empty it will set the visibility of the TextView to {@link View#VISIBLE}.
     *
     * @param view the {@link TextView}
     * @param text the {@link CharSequence} we want to display
     * @return true if the TextView is visible after the operation, false otherwise.
     */
    public static boolean setTextOrHide(final TextView view, final CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            view.setVisibility(View.GONE);
            view.setText(null);
            return false;
        } else {
            view.setVisibility(View.VISIBLE);
            view.setText(text);
            return true;
        }
    }

    public static void setViewHeight(final View view, final int heightInPixels) {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = heightInPixels;
        view.setLayoutParams(params);
    }

    public static void setViewWidth(final View view, final int widthInPixels) {
        final ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = widthInPixels;
        view.setLayoutParams(params);
    }

    public static void showView(final View view, final boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    public static void showViewAnimated(final View view, final boolean show) {
        if (show) {
            ViewAnimator.expand(view);
        } else {
            ViewAnimator.collapse(view);
        }
    }
}
