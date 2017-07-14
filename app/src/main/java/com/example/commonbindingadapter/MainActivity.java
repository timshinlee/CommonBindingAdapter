package com.example.commonbindingadapter;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.commonbindingadapter.databinding.ActivityMainBinding;
import com.example.commonbindingadapter.databinding.ItemBinding;
import com.example.commonbindingadapter.databinding.ItemRightBinding;
import com.timshinlee.commonbindingadapter.CommonBindingAdapter;
import com.timshinlee.commonbindingadapter.CommonBindingHolder;
import com.timshinlee.commonbindingadapter.MultiBindingAdapter;
import com.timshinlee.commonbindingadapter.ViewTypeProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        for (int i = 0; i < 30; i++) {
            list.add("item " + i);
        }

        binding.recyclerLeft.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerLeft.setAdapter(new CommonBindingAdapter<ItemBinding, String>(R.layout.item, list) {
            @Override
            protected void processView(CommonBindingHolder<ItemBinding> holder, String element) {
                holder.binding.text.setText(element);
            }
        });


        final ViewTypeProvider viewTypeProvider = new ViewTypeProvider() {
            @Override
            public int getItemViewType(int position) {
                return position % 2;
            }

            @Override
            public int getItemLayoutId(int itemViewType) {
                return itemViewType == 0 ? R.layout.item : R.layout.item_right;
            }
        };
        binding.recyclerRight.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerRight.setAdapter(new MultiBindingAdapter<String>(viewTypeProvider, list) {
            @Override
            protected void processView(CommonBindingHolder holder, String element) {
                final int itemViewType = holder.getItemViewType();
                if (itemViewType == 0) {
                    final ItemBinding itemBinding = (ItemBinding) holder.binding;
                    itemBinding.text.setText(element);
                } else {
                    final ItemRightBinding itemRightBinding = (ItemRightBinding) holder.binding;
                    itemRightBinding.right.setText(element);
                }
            }
        });
    }
}
