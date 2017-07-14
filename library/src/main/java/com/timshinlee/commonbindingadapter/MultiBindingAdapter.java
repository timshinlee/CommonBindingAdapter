package com.timshinlee.commonbindingadapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public abstract class MultiBindingAdapter<E>
        extends RecyclerView.Adapter<CommonBindingHolder> {
    private ViewTypeProvider typeProvider;
    private List<E> list;

    public MultiBindingAdapter(@NonNull ViewTypeProvider typeProvider, @NonNull List<E> list) {
        this.typeProvider = typeProvider;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return typeProvider.getItemViewType(position);
    }

    @Override
    public CommonBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                typeProvider.getItemLayoutId(viewType),
                parent,
                false);
        return new CommonBindingHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(CommonBindingHolder holder, int position) {
        processView(holder, list.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected abstract void processView(CommonBindingHolder holder, E element);
}
