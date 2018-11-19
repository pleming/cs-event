package com.event.cs.csevent.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.event.cs.csevent.R;
import com.event.cs.csevent.adapter.ProductAdapter;
import com.event.cs.csevent.model.ProductItem;

import java.util.ArrayList;

public class OneToOneFragment extends Fragment {
    private Context context;
    private ArrayList<ProductItem> productInfo = new ArrayList<ProductItem>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_one_to_one, container, false);

        GridView gridProduct = (GridView) linearLayout.findViewById(R.id.gridProduct);

        ProductAdapter productAdapter = new ProductAdapter(context);

        for (int i = 0; i < productInfo.size(); i++)
            productAdapter.addItem(productInfo.get(i));

        gridProduct.setAdapter(productAdapter);

        return linearLayout;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<ProductItem> getProductInfo() {
        return this.productInfo;
    }
}
