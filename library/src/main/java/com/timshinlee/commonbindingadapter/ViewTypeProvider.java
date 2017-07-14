package com.timshinlee.commonbindingadapter;

import android.support.annotation.LayoutRes;

/**
 * Created by Timshin Lee on 2017/7/14.
 */
public interface ViewTypeProvider {
    int getItemViewType(int position);

    @LayoutRes
    int getItemLayoutId(int itemViewType);
}
