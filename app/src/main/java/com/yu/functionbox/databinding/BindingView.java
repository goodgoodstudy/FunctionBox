package com.yu.functionbox.databinding;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An Annotation for ViewModel indicate which layout or layouts this ViewModel related to.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface BindingView {
    @LayoutRes int[] value();
}
