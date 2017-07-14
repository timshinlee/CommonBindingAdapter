package com.timshinlee.commonbindingadapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class CommonBindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T binding;

    public CommonBindingHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
