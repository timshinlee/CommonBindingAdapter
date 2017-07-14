package com.timshinlee.commonbindingadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonBindingAdapter<T extends ViewDataBinding, E>
        extends RecyclerView.Adapter<CommonBindingHolder<T>> {
    private int layoutId;
    private List<E> list;

    public CommonBindingAdapter(@LayoutRes int layoutId, List<E> list) {
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public CommonBindingHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        final T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        return new CommonBindingHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(CommonBindingHolder<T> holder, int position) {
        processView(holder, list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected abstract void processView(CommonBindingHolder<T> holder, E element);
}
