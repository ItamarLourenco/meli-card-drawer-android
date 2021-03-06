package com.meli.android.carddrawer;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public final class ViewHelper {

    private ViewHelper() {
    }

    public static GradientDrawable getGradientDrawable(@NonNull final Resources resources,
        @Nullable final List<String> gradientColors) {
        final GradientDrawable gradientDrawable = (GradientDrawable) resources.getDrawable(R.drawable.card_drawer_gradient);
        if (gradientColors == null || gradientColors.isEmpty()) {
            return gradientDrawable;
        } else {
            final GradientDrawable gradientCopy = (GradientDrawable) gradientDrawable.mutate().getConstantState().newDrawable();
            final int colorsAmount = Math.min(gradientColors.size(), 3);
            if (colorsAmount == 1) {
                gradientCopy.setColor(Color.parseColor(gradientColors.get(0)));
            } else {
                final int[] parsedColors = new int[colorsAmount];
                for (int i = 0; i<colorsAmount; i++) {
                    parsedColors[i] = Color.parseColor(gradientColors.get(i));
                }
                gradientCopy.setColors(parsedColors);
            }
            return gradientCopy;
        }
    }

    public static void runWhenViewIsAttachedToWindow(final View view, final Runnable runnable) {
        if (view.isAttachedToWindow()) {
            runnable.run();
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(final View v) {
                    runnable.run();
                    v.removeOnAttachStateChangeListener(this);
                }

                @Override
                public void onViewDetachedFromWindow(final View v) {

                }
            });
        }
    }
}