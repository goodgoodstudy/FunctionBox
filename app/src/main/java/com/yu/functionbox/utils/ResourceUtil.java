package com.yu.functionbox.utils;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

import com.yu.functionbox.FunctionBoxApplication;

/**
 * Created by yanzhang on 2017/2/14.
 */

public class ResourceUtil {

    public static Drawable getDrawable(@DrawableRes int ResourceID) {
        return ContextCompat.getDrawable(FunctionBoxApplication.Companion.getApplication(), ResourceID);
    }

    public static String getString(@StringRes int ResourceID) {
        return FunctionBoxApplication.Companion.getApplication().getResources().getString(ResourceID);
    }

    public static String getString(@StringRes int id, Object... formatArgs) throws Resources.NotFoundException {
        return FunctionBoxApplication.Companion.getApplication().getResources().getString(id, formatArgs);
    }

    public static int getDimensionPixelSize(@DimenRes int ResourceID) {
        return FunctionBoxApplication.Companion.getApplication().getResources().getDimensionPixelSize(ResourceID);
    }

    public static int getColor(@ColorRes int ResourceID) {
        return ContextCompat.getColor(FunctionBoxApplication.Companion.getApplication(), ResourceID);
    }

    public static ColorStateList getColorStateList(@ColorRes int ResourceID) {
        return ContextCompat.getColorStateList(FunctionBoxApplication.Companion.getApplication(), ResourceID);
    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
