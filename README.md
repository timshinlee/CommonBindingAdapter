# CommonBindingAdapter
a common RecyclerView adapter with databinding

### how to import:

in the build.gradle of project:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

in the build.gradle of module: (Remember to enable databinding)
```
dataBinding {
    enabled = true;
}
dependencies {
    compile 'com.github.timshinlee:CommonBindingAdapter:1.0.1'
}
```


### 1. normal usage:

```
List<String> list = new ArrayList<>();

recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(new CommonBindingAdapter<ItemBinding, String>(R.layout.item, list) {
    @Override
    protected void processView(CommonBindingHolder<ItemBinding> holder, String element) {
        holder.binding.text.setText(element);
    }
});
```
The CommonBindingAdapter takes two generic types. The first is the binding class of item layout generated by DataBinding. The second is the type of data.

R.layout.item: (Remember to add `<layout></layout>` as the root label of item layout)
```
<?xml version="1.0" encoding="utf-8"?>
<layout>

    <LinearLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:id="@+id/title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:padding="10dp"/>
    </LinearLayout>
</layout>

```

### 2. multiple view types usage:
```
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
```
