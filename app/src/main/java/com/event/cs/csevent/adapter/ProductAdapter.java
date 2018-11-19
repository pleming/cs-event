package com.event.cs.csevent.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.event.cs.csevent.model.ProductItem;
import com.event.cs.csevent.model.ProductItemView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ProductItem> items;

    public ProductAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<ProductItem>();
    }

    public void addItem(ProductItem productItem) {
        this.items.add(productItem);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductItemView view = new ProductItemView(context);
        ProductItem item = items.get(position);

        view.setCsName(item.getCsName());
        view.setProductImage(new byte[16]);
        view.setProductName(item.getProductName());
        view.setEventType(item.getEventType());
        view.setPrice(item.getPrice());

        return view;
    }
}
